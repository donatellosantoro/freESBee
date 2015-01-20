package it.unibas.icar.freesbee.modulocontrollo.imbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorErroreImbustamento;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorWrapper;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class EnvelopeWrapperEgovImbustamento extends RouteBuilder {

    private static Log logger = LogFactory.getLog(EnvelopeWrapperEgovImbustamento.class);
    @Inject
    private ProcessorWrapper processorWrapper;
    @Inject
    private ProcessorErroreImbustamento processorErroreImbustamento;

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_ENVELOPE_WRAPPER_EGOV_IMBUSTAMENTO)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	 		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
            //.process(new ProcessorLog(this.getClass()))
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
            .doTry()
                .process(processorWrapper)
                .to(FreesbeeCamel.SEDA_CONTENT_BASED_ROUTER_IMBUSTAMENTO)
            .doCatch(Exception.class)
                .process(processorErroreImbustamento);
    }

    public ProcessorWrapper getProcessorWrapper() {
        return processorWrapper;
    }

    public void setProcessorWrapper(ProcessorWrapper processorWrapper) {
        this.processorWrapper = processorWrapper;
    }

    public ProcessorErroreImbustamento getProcessorErroreImbustamento() {
        return processorErroreImbustamento;
    }

    public void setProcessorErroreImbustamento(ProcessorErroreImbustamento processorErroreImbustamento) {
        this.processorErroreImbustamento = processorErroreImbustamento;
    }


}
