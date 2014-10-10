package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class DetourCollaborazioneSbustamento extends RouteBuilder {
    private static Log logger = LogFactory.getLog(DetourCollaborazioneSbustamento.class);
    
    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_DETOUR_COLLABORAZIONE_SBUSTAMENTO)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	 		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
            //.process(new ProcessorLog(this.getClass()))
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))    
            .choice()
            .when(header(CostantiBusta.VALOREPROFILOCOLLABORAZIONE).isEqualTo(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO))
                .to(FreesbeeCamel.SEDA_WIRETAP_COLLABORAZIONE_SBUSTAMENTO)
            .otherwise()
                .to(FreesbeeCamel.SEDA_WIRETAP_TRACCIAMENTO_SBUSTAMENTO);
    }
}
