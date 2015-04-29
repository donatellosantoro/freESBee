package it.unibas.icar.freesbee.portaapplicativa;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorEnricherRisposta;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class EnricherPreImbustamentoRisposta extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(EnricherPreImbustamentoRisposta.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EnricherPreImbustamentoRisposta.class.getName());
    @Inject
    private ProcessorEnricherRisposta processorEnricherRisposta;

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_ENRICHER_PREIMBUSTAMENTO_RISPOSTA)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	  		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //.process(new ProcessorLog(this.getClass()))
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .process(processorEnricherRisposta)
                .to(FreesbeeCamel.SEDA_PREIMBUSTAMENTO_RISPOSTA);
    }

    public ProcessorEnricherRisposta getProcessorEnricherRisposta() {
        return processorEnricherRisposta;
    }

    public void setProcessorEnricherRisposta(ProcessorEnricherRisposta processorEnricherRisposta) {
        this.processorEnricherRisposta = processorEnricherRisposta;
    }
    
}
