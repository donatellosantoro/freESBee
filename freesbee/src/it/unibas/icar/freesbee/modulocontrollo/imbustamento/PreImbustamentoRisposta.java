package it.unibas.icar.freesbee.modulocontrollo.imbustamento;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class PreImbustamentoRisposta extends RouteBuilder {
//    private static Log logger = LogFactory.getLog(PreImbustamentoRisposta.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PreImbustamentoRisposta.class.getName());

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_PREIMBUSTAMENTO_RISPOSTA)
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
            if (logger.isDebugEnabled()) logger.debug("Le intestazioni del messaggio sono:\n\n" + FreesbeeUtil.stampaIntestazioni(exchange));
            exchange.getIn().setHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA, CostantiBusta.VALORE_IMBUSTA_RISPOSTA);
        }
    }
}
