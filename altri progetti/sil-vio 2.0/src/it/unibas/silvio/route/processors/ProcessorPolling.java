package it.unibas.silvio.route.processors;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.util.CostantiSilvio;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.component.seda.SedaComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorPolling implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private CamelContext context;

    public ProcessorPolling(CamelContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO);
        String canalePolling = messaggio.getCanalePolling();

        logger.debug("Mi metto in attesa sul canale " + canalePolling);

        SedaComponent sedaComponent = (SedaComponent) context.getComponent("seda");
        sedaComponent.createEndpoint(canalePolling);
        Endpoint endpoint = context.getEndpoint(canalePolling);

        PollingConsumer consumer = endpoint.createPollingConsumer();
        consumer.start();
        Exchange newExchange = consumer.receive();
        consumer.stop();

        logger.debug("Canale polling " + canalePolling + " sbloccato");
        
        String messaggioRisposta = newExchange.getIn().getBody(String.class);
        exchange.getOut().setBody(messaggioRisposta,String.class);
    }
}