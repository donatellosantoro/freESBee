package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

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
public class PreSbustamentoRisposta extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(PreSbustamentoRisposta.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PreSbustamentoRisposta.class.getName());

    public void configure() throws Exception {
        this.from(FreesbeeCamel.SEDA_PRESBUSTAMENTO_RISPOSTA)
//	    	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//	  		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //.process(new ProcessorLog(this.getClass()))
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .process(new ProcessorPreSbusta())
                .to(FreesbeeCamel.SEDA_ENVELOPE_WRAPPER_EGOV_SBUSTAMENTO);
    }

    private class ProcessorPreSbusta implements Processor {
        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            exchange.getIn().setHeader(CostantiBusta.SBUSTA_RICHIESTA_RISPOSTA, CostantiBusta.VALORE_SBUSTA_RISPOSTA);
//            exchange.getOut().setHeader(CostantiBusta.SBUSTA_RICHIESTA_RISPOSTA, CostantiBusta.VALORE_SBUSTA_RISPOSTA);
        }
    }
}
