package it.unibas.icar.freesbee.modulocontrollo.sbustamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaDelegata;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class ContentBasedRouterSbustamento extends RouteBuilder {
    
    private static Log logger = LogFactory.getLog(ContentBasedRouterSbustamento.class);
    @Inject
    private ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata;
    
    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaDelegata.setEccezione(false);
        this.from(FreesbeeCamel.SEDA_CONTENT_BASED_ROUTER_SBUSTAMENTO)
         .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
         .choice()
            .when(header(CostantiBusta.SBUSTA_RICHIESTA_RISPOSTA)
                .isEqualTo(CostantiBusta.VALORE_SBUSTA_RICHIESTA))
                .to(FreesbeeCamel.SEDA_CONTENT_BASED_ROUTER_NICA)                
            .when(header(CostantiBusta.SBUSTA_RICHIESTA_RISPOSTA)
                .isEqualTo(CostantiBusta.VALORE_SBUSTA_RISPOSTA))
                .process(processorSbloccaPollingConsumerPortaDelegata)
            .otherwise()
                .process(new ProcessorLogErrore());
    }

    private class ProcessorLogErrore implements Processor{

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            logger.error("L'intestazione " + CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA + " contiene un valore sconosciuto o nullo: " +
                         exchange.getIn().getHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA));
            logger.error("Il messaggio verra' scartato.");
        }

    }

    public ProcessorSbloccaPollingConsumerPortaDelegata getProcessorSbloccaPollingConsumerPortaDelegata() {
        return processorSbloccaPollingConsumerPortaDelegata;
    }

    public void setProcessorSbloccaPollingConsumerPortaDelegata(ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata) {
        this.processorSbloccaPollingConsumerPortaDelegata = processorSbloccaPollingConsumerPortaDelegata;
    }
}
