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

public class ProcessorSbloccaPollingConsumerErogatore implements Processor {

    private boolean eccezione;
    private Log logger = LogFactory.getLog(this.getClass());

    public ProcessorSbloccaPollingConsumerErogatore() {
        this.eccezione = false;
    }

    public ProcessorSbloccaPollingConsumerErogatore(boolean eccezione) {
        this.eccezione = eccezione;
    }

    public void process(Exchange exchange) throws Exception {
        logger.info("Sblocco il polling consumer erogatore. Errore? : " + eccezione);
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RISPOSTA);
        if (!eccezione) {
            String risposta = "";
            if (messaggioRisposta!= null && messaggioRisposta.getMessaggio()!=null) {
                risposta = messaggioRisposta.getMessaggio();
            }
            exchange.getIn().setBody(risposta, String.class);
        }
        if (messaggioRisposta != null && messaggioRisposta.getTipo().equals(Messaggio.ACK)) {
            exchange.getIn().setBody("", String.class);
        } else {
            new ProcessorSOAPWriter(eccezione).process(exchange);
        }

        CamelContext context = exchange.getContext();
        String idPollingErogatore = messaggioRichiesta.getChannelErogatore();
        String canale = CostantiCamel.POLLING_CHANNEL_EROGATORE + idPollingErogatore;
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
}
