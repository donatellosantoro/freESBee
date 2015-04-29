package it.unibas.icar.freesbee.portaapplicativa;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaApplicativa;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class RispostaAck extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(RispostaAck.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RispostaAck.class.getName());
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;

    @Override
    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(false);
        this.from(FreesbeeCamel.SEDA_RISPOSTA_ACK)
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .process(new ProcessorAck())
                .process(processorSbloccaPollingConsumerPortaApplicativa);
    }
    
    private class ProcessorAck implements Processor{

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            exchange.setProperty("FREESBEE_ACK", true);
        }
        
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }
}
