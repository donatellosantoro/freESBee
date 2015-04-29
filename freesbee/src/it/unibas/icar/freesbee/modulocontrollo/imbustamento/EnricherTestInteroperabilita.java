package it.unibas.icar.freesbee.modulocontrollo.imbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.processors.ProcessorEnricherTestInteroperabilita;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class EnricherTestInteroperabilita extends RouteBuilder {
//    private static Log logger = LogFactory.getLog(EnricherTestInteroperabilita.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EnricherTestInteroperabilita.class.getName());
    @Inject
    private ProcessorEnricherTestInteroperabilita processorEnricherTestInteroperabilita;

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_ENRICHER_TEST)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	 		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
            .process(processorEnricherTestInteroperabilita)
            .to(FreesbeeCamel.SEDA_ENVELOPE_WRAPPER_EGOV_IMBUSTAMENTO);
    }

    public ProcessorEnricherTestInteroperabilita getProcessorEnricherTestInteroperabilita() {
        return processorEnricherTestInteroperabilita;
    }

    public void setProcessorEnricherTestInteroperabilita(ProcessorEnricherTestInteroperabilita processorEnricherTestInteroperabilita) {
        this.processorEnricherTestInteroperabilita = processorEnricherTestInteroperabilita;
    }
}
