package it.unibas.icar.freesbee.modulocontrollo.imbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaApplicativa;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class ContentBasedRouterImbustamento extends RouteBuilder {
    
    private static Log logger = LogFactory.getLog(ContentBasedRouterImbustamento.class);
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;
    
    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(false);
        this.from(FreesbeeCamel.SEDA_CONTENT_BASED_ROUTER_IMBUSTAMENTO)
            .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
            .choice()
            .when(header(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA)
                .isEqualTo(CostantiBusta.VALORE_IMBUSTA_RICHIESTA))
                .to(FreesbeeCamel.SEDA_HTTP_INOLTRO_BUSTA_EGOV)
            .when(header(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA)
                .isEqualTo(CostantiBusta.VALORE_IMBUSTA_RISPOSTA))
                .process(processorSbloccaPollingConsumerPortaApplicativa)
            .otherwise()
                .process(new ProcessorLogErrore());
                
    }

    private class ProcessorLogErrore implements Processor{

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            logger.error("L'intestazione " + CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA + " contiene un valore sconosciuto o nullo: " +
                         exchange.getIn().getHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA));
            logger.error("Il messaggio verr� scartato.");
        }

    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }
}
