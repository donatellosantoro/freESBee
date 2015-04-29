package it.unibas.icar.freesbee.inoltrobustaegov;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.exception.FreesbeeErrorListener;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaApplicativa;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.MessageUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Singleton
public class ContentBasedRouterRispostaEGov extends RouteBuilder {
    
//    private static Log logger = LogFactory.getLog(ContentBasedRouterRispostaEGov.class);
    private static final Logger logger = LoggerFactory.getLogger(ContentBasedRouterRispostaEGov.class.getName());
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;

    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(false);
        this.from(FreesbeeCamel.SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE_NICA)
//            .threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//            		 ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                .process(new ProcessorVerificaAck())
                .choice().when(header("ACK").isEqualTo(false))
                    .process(processorSbloccaPollingConsumerPortaApplicativa)
                .otherwise()
                    .to(FreesbeeCamel.SEDA_RISPOSTA_ACK);

        this.from(FreesbeeCamel.SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE_PDD)
//        	.threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
//       		         ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                .process(new ProcessorVerificaAck())
                .choice().when(header("ACK").isEqualTo(false))
                    .to(FreesbeeCamel.SEDA_PRESBUSTAMENTO_RISPOSTA)
                .otherwise()
                    .to(FreesbeeCamel.SEDA_RICHIESTA_ACK);
    }

    private class ProcessorVerificaAck implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
//            Message messaggioIn = exchange.getIn();
            String profiloCollaborazione = (String) exchange.getProperty(CostantiBusta.VALOREPROFILOCOLLABORAZIONE);
            if (MessageUtil.isEmpty(exchange.getIn()) && !profiloCollaborazione.equals(AccordoServizio.PROFILO_SINCRONO)) {
                exchange.setProperty("ACK", true);
                if (logger.isDebugEnabled()) logger.debug("Il messaggio e' vuoto e il profilo di collaborazione non e' sincrono (ma " + profiloCollaborazione + ")");
            }else {
                if (!profiloCollaborazione.equals(AccordoServizio.PROFILO_SINCRONO)) {
                    messaggio.setTipo(Messaggio.TIPO_ACK_RICHIESTA);
                }
                exchange.setProperty("ACK", false);
            }
        }
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }
}
