package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriEseguiIstanza;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SOAPUtil;
import it.unibas.silvio.util.SilvioUtil;
import java.net.ConnectException;
import java.util.Map;
import java.util.Set;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.builder.Builder;
import org.apache.camel.component.http.HttpProducer;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.component.jetty.JettyHttpEndpoint;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorSendFruitore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private CamelContext context;

    public ProcessorSendFruitore(CamelContext context) {
        this.context = context;
    }

    public void process(Exchange exchange) throws Exception {
        Message messageIn = exchange.getIn();
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        ParametriEseguiIstanza parametri = messaggioRichiesta.getParametriEseguiIstanza();
        IstanzaOperation istanzaOperation = messaggioRichiesta.getIstanzaOperation();
        Dati datiIstanza = istanzaOperation.getDatiRichiesta();
        String indirizzoErogatore = parametri.getIndirizzo();
        String risorsa = parametri.getRisorsa();
        if (indirizzoErogatore == null || indirizzoErogatore.trim().isEmpty()) {
            throw new SilvioException("Impossibile inviare la richiesta. Nessun indirizzo specificato");
        }
        indirizzoErogatore = indirizzoErogatore.trim();
        messaggioRichiesta.setIndirizzo(indirizzoErogatore);
        try {
            logger.info("Invio il messaggio all'erogatore all'indirizzo " + indirizzoErogatore);
            JettyHttpComponent jettyHttpComponent = (JettyHttpComponent) context.getComponent("jetty");
            jettyHttpComponent.createEndpoint("jetty:" + SilvioUtil.controllaConnettore(indirizzoErogatore));
            JettyHttpEndpoint endpoint = context.getEndpoint("jetty:" + indirizzoErogatore, JettyHttpEndpoint.class);

//            Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 8192);
            Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
            Protocol.registerProtocol("https", easyhttps);

            if (!parametri.isTest() || datiIstanza.isPayloadCostante()) {
                logger.info("Non e' un istanza di test, riempio il body");
                messageIn.setBody(messaggioRichiesta.getMessaggio(), String.class);
            }

            Exchange exchangeInviare = exchange.copy();
            new ProcessorSOAPWriter().process(exchangeInviare);
//            if (parametri.getCorrelazione().equals(CostantiSilvio.CORRELAZIONE_SPCOOP)) {
                exchangeInviare.getIn().setHeader("SOAPAction", "\"urn:" + parametri.getNomeOperazione() + "\"");
//            }
            if (risorsa != null) {
//                exchangeInviare.getIn().setHeader(HttpProducer.QUERY, Builder.constant("risorsa=" + risorsa));
                exchangeInviare.getIn().setHeader("risorsa", risorsa);
            }
            SilvioUtil.copiaQueryStringToHttp(indirizzoErogatore,exchangeInviare.getIn());
            Producer producer = endpoint.createProducer();
            producer.start();
            producer.process(exchangeInviare);
            producer.stop();

//            messaggioRichiesta.setMessaggio(exchangeInviare.getIn().getBody(String.class));

            Message messageRispostaOut = exchangeInviare.getOut();
            String stringaMessaggioRisposta = messageRispostaOut.getBody(String.class);
            Messaggio messaggioRisposta = creaMessaggioRisposta(messaggioRichiesta, stringaMessaggioRisposta);
            String stringaFault = SOAPUtil.controllaFault(stringaMessaggioRisposta);
            if (stringaFault != null) {
                logger.info("L'erogatore mi ha risposto con un SOAP FAULT");
                messaggioRisposta.setFault(true);
                throw new SilvioException("L'erogatore mi ha risposto con un SOAP FAULT: " + stringaFault);
            }
            if (stringaMessaggioRisposta.contains("<title>Authorization Failed</title>")) {
                throw new SilvioException("Authorization Failed: impossibile contattare l'erogatore. " +
                        "Il portafoglio delle asserzioni non contiene degli attributi validi per accedere alla risorsa.");
            }
            logger.info("L'erogatore mi ha risposto con " + stringaMessaggioRisposta);
            exchange.setProperty(CostantiSilvio.MESSAGGIO_RISPOSTA, messaggioRisposta);
            copiaProprietaCorrelazione(messageIn, messageRispostaOut);
            SilvioUtil.copiaIntestazioniHTTP(messageRispostaOut, messageIn);
        } catch (ConnectException e) {
            logger.error("Errore nell'inoltro del messaggio SOAP. " + e);
            String errore = "Impossibile contattare l'erogatore all'indirizzo " + indirizzoErogatore;
            throw new SilvioException(errore + ". " + e.getMessage());
        }
    }

    private void copiaProprietaCorrelazione(Message messageIn, Message messageRispostaOut) {
        Map<String, Object> mappaIntestazioni = messageRispostaOut.getHeaders();
        Set<String> chiavi = mappaIntestazioni.keySet();
        for (String string : chiavi) {
            if (string.equalsIgnoreCase(CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO)) {
                messageIn.setHeader(string, mappaIntestazioni.get(string));
            }
            if (string.equalsIgnoreCase(CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO)) {
                messageIn.setHeader(string, mappaIntestazioni.get(string));
            }
        }
    }

    private Messaggio creaMessaggioRisposta(Messaggio messaggioRichiesta, String stringaMessaggioRisposta) {
        Messaggio messaggioRisposta = new Messaggio();
        messaggioRisposta.setChannelFruitore(messaggioRichiesta.getChannelFruitore());
        messaggioRisposta.setTipo(Messaggio.RICEVUTO);
        messaggioRisposta.setMessaggio(stringaMessaggioRisposta);
        messaggioRisposta.setIstanzaOperation(messaggioRichiesta.getIstanzaOperation());
        messaggioRisposta.setIstanzaPortType(messaggioRichiesta.getIstanzaPortType());
        messaggioRisposta.setIdRelatesTo(messaggioRichiesta.getIdMessaggio());
        messaggioRisposta.setParametriEseguiIstanza(messaggioRichiesta.getParametriEseguiIstanza());
        return messaggioRisposta;
    }
}
