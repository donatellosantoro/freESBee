package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.modello.RispostaEseguiIstanza;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.xml.DAOIstanzaXml;
import it.unibas.silvio.xml.XmlJDomUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.seda.SedaComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;

public class ProcessorSbloccaPollingConsumerFruitore implements Processor {

    private boolean eccezione;
    private Log logger = LogFactory.getLog(this.getClass());

    public ProcessorSbloccaPollingConsumerFruitore() {
        this.eccezione = false;
    }

    public ProcessorSbloccaPollingConsumerFruitore(boolean eccezione) {
        this.eccezione = eccezione;
    }

    public void process(Exchange exchange) throws Exception {
        RispostaEseguiIstanza risposta;
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        if (eccezione == true) {
            //SE CI SONO STATE ECCEZIONI, DOBBIAMO CONSEGNARE IL MESSAGGIO DI ERRORE
            Exception e = (Exception) exchange.getIn().getHeader("caught.exception");
            logger.info("E' stata lanciata un'eccezione dal fruitore: " + e);
            risposta = creaRispostaErrore(messaggioRichiesta, e);
        } else {
            //SE NON CI SONO STATE ECCEZIONI, ALLORA DOBBIAMO CONSEGNARE IL MESSAGGIO DI RISPOSTA
            Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
            risposta = creaRisposta(messaggioRichiesta, messaggioRisposta);
        }

        Document documentRisposta = DAOIstanzaXml.creaDocumentRisposta(risposta);
        String stringaRisposta = XmlJDomUtil.stampaXML(documentRisposta);
        exchange.getIn().setBody(stringaRisposta, String.class);

        CamelContext context = exchange.getContext();
        String idPollingFruitore = messaggioRichiesta.getChannelFruitore();
        String canale = CostantiCamel.POLLING_CHANNEL_FRUITORE + idPollingFruitore;
        if (logger.isInfoEnabled()) {
            logger.info("Sblocco il canale " + canale);
        }
        SedaComponent sedaComponent = (SedaComponent) context.getComponent("seda");
        sedaComponent.createEndpoint(canale);
        Endpoint endpoint = context.getEndpoint(canale);
        Producer producer = endpoint.createProducer();
        producer.start();
        producer.process(exchange);
        producer.stop();

        if (logger.isInfoEnabled()) {
            logger.info("Canale sbloccato");
        }
    }

    private RispostaEseguiIstanza creaRisposta(Messaggio messaggioRichiesta, Messaggio messaggioRisposta) {
        if (messaggioRichiesta == null || messaggioRisposta == null) {
            logger.error("Impossibile generare il messaggio di risposta. Uno dei messaggi è null");
            logger.error("messaggioRichiesta: " + messaggioRichiesta);
            logger.error("messaggioRisposta: " + messaggioRisposta);
            throw new IllegalArgumentException("Impossibile generare il messaggio di risposta. Uno dei messaggi è null");
        }
        RispostaEseguiIstanza risposta = new RispostaEseguiIstanza();
        risposta.setIdMessaggio(messaggioRichiesta.getIdMessaggio());
        risposta.setIdMessaggioRisposta(messaggioRisposta.getIdMessaggio());
        risposta.setMessaggio(messaggioRisposta.getMessaggio());
        risposta.setTipoMessaggio(messaggioRisposta.getTipo());
        return risposta;
    }

    private RispostaEseguiIstanza creaRispostaErrore(Messaggio messaggioRichiesta, Exception e) {
        RispostaEseguiIstanza risposta = new RispostaEseguiIstanza();
        risposta.setIdMessaggio(messaggioRichiesta.getIdMessaggio());
        risposta.setErrore(true);
        risposta.setMessaggioErrore(e.getMessage());
        return risposta;
    }
}
