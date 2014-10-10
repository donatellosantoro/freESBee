package it.unibas.icar.freesbee.portadelegata;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class FiltroAutenticazione extends RouteBuilder {
    private static Log logger = LogFactory.getLog(FiltroAutenticazione.class);
    
    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_FILTRO_AUTENTICAZIONE)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	  		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
        //.process(new ProcessorLog(this.getClass()))
        .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
        .to(FreesbeeCamel.SEDA_ENRICHER_ACCORDO_SERVIZIO);
//        .to("seda:service:http://icar.unibas.it/FreESBee/EnvelopeWrapperEgovImbustamento");
    }
}
