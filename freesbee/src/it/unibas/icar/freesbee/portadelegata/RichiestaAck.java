package it.unibas.icar.freesbee.portadelegata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaDelegata;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class RichiestaAck extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(RichiestaAck.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RichiestaAck.class.getName());
    @Inject
    private ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata;

    @Override
    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaDelegata.setEccezione(false);
        this.from(FreesbeeCamel.SEDA_RICHIESTA_ACK)
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
            .process(new ProcessorAck())
            .process(processorSbloccaPollingConsumerPortaDelegata);
    }

    public ProcessorSbloccaPollingConsumerPortaDelegata getProcessorSbloccaPollingConsumerPortaDelegata() {
        return processorSbloccaPollingConsumerPortaDelegata;
    }

    public void setProcessorSbloccaPollingConsumerPortaDelegata(ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata) {
        this.processorSbloccaPollingConsumerPortaDelegata = processorSbloccaPollingConsumerPortaDelegata;
    }
    
    private class ProcessorAck implements Processor{

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            exchange.setProperty("FREESBEE_ACK", true);
        }
        
    }
}
