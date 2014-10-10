package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Dati;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.ParametriMessaggioRicevuto;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.util.CostantiSOAP;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import java.net.ConnectException;
import java.util.Map;
import java.util.Set;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.component.jetty.JettyHttpEndpoint;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorSendErogatore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private CamelContext context;

    public ProcessorSendErogatore(CamelContext context) {
        this.context = context;
    }

    public void process(Exchange exchange) throws Exception {
        Message messageIn = exchange.getIn();
        Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        ParametriMessaggioRicevuto parametri = messaggioRisposta.getParametriMessaggioRicevuto();
        String indirizzoRisposta = parametri.getIndirizzoRisposta();
        IstanzaOperation istanza = messaggioRisposta.getIstanzaOperation();
        Dati datiIstanza = istanza.getDatiRisposta();
        if (indirizzoRisposta == null) {
            throw new SilvioException("Impossibile inviare la risposta. Nessun indirizzo specificato");
        }
        try {
            logger.info("Invio il messaggio di risposta al fruitore all'indirizzo " + indirizzoRisposta);
            JettyHttpComponent jettyHttpComponent = (JettyHttpComponent) context.getComponent("jetty");
            jettyHttpComponent.createEndpoint("jetty:" + SilvioUtil.controllaConnettore(indirizzoRisposta));
            JettyHttpEndpoint endpoint = context.getEndpoint("jetty:" + indirizzoRisposta, JettyHttpEndpoint.class);

            Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 8192);
            Protocol.registerProtocol("https", easyhttps);

            if (!parametri.isTest() || datiIstanza.isPayloadCostante()) {
                logger.info("Non e' un istanza di test, riempio il body");
                messageIn.setBody(messaggioRisposta.getMessaggio(), String.class);
            }

            Exchange exchangeInviare = exchange.copy();
            new ProcessorSOAPWriter().process(exchangeInviare);
            Producer producer = endpoint.createProducer();
            producer.start();
            producer.process(exchangeInviare);
            producer.stop();

            messaggioRisposta.setMessaggio(exchangeInviare.getIn().getBody(String.class));

            Message messageRispostaOut = exchangeInviare.getOut();
            String stringaMessaggioRisposta = messageRispostaOut.getBody(String.class);
            logger.info("Il fruitore mi ha risposto con " + stringaMessaggioRisposta);
            copiaProprietaCorrelazione(messageIn, messageRispostaOut);
        } catch (ConnectException e) {
            logger.error("Errore nell'inoltro del messaggio SOAP. " + e);
            String errore = "Impossibile contattare il fruitore all'indirizzo " + indirizzoRisposta;
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
}
