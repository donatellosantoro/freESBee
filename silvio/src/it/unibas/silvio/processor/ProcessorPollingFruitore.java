package it.unibas.silvio.processor;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.util.CostantiCamel;
import it.unibas.silvio.util.CostantiSilvio;
import it.unibas.silvio.util.SilvioUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.component.seda.SedaComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorPollingFruitore implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private CamelContext context;

    public ProcessorPollingFruitore(CamelContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        Messaggio messaggioRichiesta = (Messaggio) exchange.getProperty(CostantiSilvio.MESSAGGIO_RICHIESTA);
        String idPollingFruitore = messaggioRichiesta.getChannelFruitore();
        String canale = CostantiCamel.POLLING_CHANNEL_FRUITORE + idPollingFruitore;
        if (logger.isInfoEnabled()) {
            logger.info("Mi metto in attesa sul canale " + canale);
        }
        SedaComponent sedaComponent = (SedaComponent) context.getComponent("seda");
        sedaComponent.createEndpoint(canale);
        Endpoint endpoint = context.getEndpoint(canale);

        PollingConsumer consumer = endpoint.createPollingConsumer();
        consumer.start();
        Exchange newExchange = consumer.receive();
        consumer.stop();
        
        String messaggioRisposta = newExchange.getIn().getBody(String.class);
        logger.info("Ricevuto messaggio di risposta " + messaggioRisposta);
        exchange.getOut().setBody(messaggioRisposta,String.class);

        if (logger.isDebugEnabled()) {
            logger.debug("Intestazioni del messaggio: " + SilvioUtil.stampaIntestazioni(exchange.getIn()));
        }
        if (logger.isInfoEnabled()) {
            logger.info("@ Messaggio Ricevuto!");
        }
    }
}