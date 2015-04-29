package it.unibas.icar.freesbee.modulocontrollo.imbustamento;

import com.google.inject.Singleton;
import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class PreImbustamentoRichiesta extends RouteBuilder {
//    private static Log logger = LogFactory.getLog(PreImbustamentoRichiesta.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PreImbustamentoRichiesta.class.getName());

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_PREIMBUSTAMENTO_RICHIESTA)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	 		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //.process(new ProcessorLog(this.getClass()))
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .process(new ProcessorFiltro())
                .to(FreesbeeCamel.SEDA_DETOUR_COLLABORAZIONE_IMBUSTAMENTO);
    }

    private class ProcessorFiltro implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            //PROPRIETA' IMBUSTA RICHIESTA/RISPOSTA
            exchange.getIn().setHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA, CostantiBusta.VALORE_IMBUSTA_RICHIESTA);
        }
    }
}
