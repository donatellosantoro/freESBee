package it.unibas.icar.freesbee.portadelegata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.portaapplicativa.RispostaAck;
import it.unibas.icar.freesbee.processors.ProcessorEnricher;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaDelegata;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class EnricherAccordoServizio extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(EnricherAccordoServizio.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EnricherAccordoServizio.class.getName());
    @Inject
    private ProcessorEnricher processorEnricher;
    @Inject
    private ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata;

    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaDelegata.setEccezione(true);
        this.from(FreesbeeCamel.SEDA_ENRICHER_ACCORDO_SERVIZIO)
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
            .doTry()
                .process(processorEnricher)
                .to(FreesbeeCamel.SEDA_PREIMBUSTAMENTO_RICHIESTA)
            .doCatch(Exception.class)
                .process(processorSbloccaPollingConsumerPortaDelegata);
    }

    public ProcessorEnricher getProcessorEnricher() {
        return processorEnricher;
    }

    public void setProcessorEnricher(ProcessorEnricher processorEnricher) {
        this.processorEnricher = processorEnricher;
    }

    public ProcessorSbloccaPollingConsumerPortaDelegata getProcessorSbloccaPollingConsumerPortaDelegata() {
        return processorSbloccaPollingConsumerPortaDelegata;
    }

    public void setProcessorSbloccaPollingConsumerPortaDelegata(ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata) {
        this.processorSbloccaPollingConsumerPortaDelegata = processorSbloccaPollingConsumerPortaDelegata;
    }
}
