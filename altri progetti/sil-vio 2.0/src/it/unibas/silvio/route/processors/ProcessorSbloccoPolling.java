package it.unibas.silvio.route.processors;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.seda.SedaComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorSbloccoPolling implements Processor {

    private boolean eccezione;
    private Log logger = LogFactory.getLog(this.getClass());

    public ProcessorSbloccoPolling() {
        this.eccezione = false;
    }

    public ProcessorSbloccoPolling(boolean eccezione) {
        this.eccezione = eccezione;
    }

    public void process(Exchange exchange) throws Exception {
        logger.debug("Sblocco il polling. Errore? : " + eccezione);
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO);
        String canalePolling = messaggio.getCanalePolling();
        
//        if (!eccezione) {
//            String risposta = "";
//            if (messaggioRisposta!= null && messaggioRisposta.getMessaggio()!=null) {
//                risposta = messaggioRisposta.getMessaggio();
//            }
//            exchange.getIn().setBody(risposta, String.class);
//        }
//        if (messaggioRisposta != null && messaggioRisposta.getTipo().equals(Messaggio.ACK)) {
//            exchange.getIn().setBody("", String.class);
//        } else {
//            new ProcessorSOAPWriter(eccezione).process(exchange);
//        }

        CamelContext context = exchange.getContext();
        logger.debug("Sblocco il canale " + canalePolling);
        SedaComponent sedaComponent = (SedaComponent) context.getComponent("seda");
        sedaComponent.createEndpoint(canalePolling);
        Endpoint endpoint = context.getEndpoint(canalePolling);
        Producer producer = endpoint.createProducer();
        producer.start();
        producer.process(exchange);
        producer.stop();

        logger.info("Canale sbloccato");
    }
}
