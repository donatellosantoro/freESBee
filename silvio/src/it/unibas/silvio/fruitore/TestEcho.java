package it.unibas.silvio.fruitore;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.util.CostantiSOAP;

public class TestEcho extends RouteBuilder {

    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void configure() throws Exception {
        
        String indirizzo = "jetty:http://localhost:9999/echo/";
        logger.info(indirizzo);
        this.from(indirizzo)
            .process(new ProcessorLog(this.getClass()))
            .process(new ProcessorCreaMessaggio());
    }
    
    private class ProcessorCreaMessaggio implements Processor{

        public void process(Exchange exchange) throws Exception {
            Message messageIn = exchange.getIn();
            Message messageOut = exchange.getOut();
              logger.info(messageIn);
            messageOut.copyFrom(messageIn);
            messageOut.setHeader(CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO, "idspcoop");
        }
        
        
    }
    
}
