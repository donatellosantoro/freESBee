package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorErroreSbustamento;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorUnWrapper;
import it.unibas.icar.freesbee.processors.ProcessorValidaBustaEGov;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class EnvelopeWrapperEgovSbustamento extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(EnvelopeWrapperEgovSbustamento.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EnvelopeWrapperEgovSbustamento.class.getName());
    @Inject
    private ProcessorErroreSbustamento processorErroreSbustamento;
    @Inject
    private ProcessorUnWrapper processorUnWrapper;
    @Inject
    private ProcessorValidaBustaEGov processorValidaBustaEGov;

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_ENVELOPE_WRAPPER_EGOV_SBUSTAMENTO)
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))    
            .doTry()
                .process(processorUnWrapper)
                .process(processorValidaBustaEGov)
                .to(FreesbeeCamel.SEDA_ENVELOPE_WRAPPER_TEST)
            .doCatch(Exception.class)
                .process(processorErroreSbustamento);
    }

    public ProcessorErroreSbustamento getProcessorErroreSbustamento() {
        return processorErroreSbustamento;
    }

    public void setProcessorErroreSbustamento(ProcessorErroreSbustamento processorErroreSbustamento) {
        this.processorErroreSbustamento = processorErroreSbustamento;
    }

    public ProcessorUnWrapper getProcessorUnWrapper() {
        return processorUnWrapper;
    }

    public void setProcessorUnWrapper(ProcessorUnWrapper processorUnWrapper) {
        this.processorUnWrapper = processorUnWrapper;
    }

    public ProcessorValidaBustaEGov getProcessorValidaBustaEGov() {
        return processorValidaBustaEGov;
    }

    public void setProcessorValidaBustaEGov(ProcessorValidaBustaEGov processorValidaBustaEGov) {
        this.processorValidaBustaEGov = processorValidaBustaEGov;
    }
    
}
