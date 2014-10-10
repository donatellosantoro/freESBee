package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class PreSbustamentoRichiesta extends RouteBuilder {

    private static Log logger = LogFactory.getLog(PreSbustamentoRichiesta.class);

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_PRESBUSTAMENTO_RICHIESTA)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	  		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //.process(new ProcessorLog(this.getClass()))
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .setHeader(CostantiBusta.SBUSTA_RICHIESTA_RISPOSTA, constant(CostantiBusta.VALORE_SBUSTA_RICHIESTA))
                .to(FreesbeeCamel.SEDA_ENVELOPE_WRAPPER_EGOV_SBUSTAMENTO);
    }
}
