package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorErroreSbustamento implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorErroreSbustamento.class);
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;
    @Inject
    private ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata;
    @Inject
    private ProcessStampaEccezioniEGov processStampaEccezioniEGov;

    public ProcessorErroreSbustamento() {
    }

    public void process(Exchange exchange) throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(true);
        processorSbloccaPollingConsumerPortaDelegata.setEccezione(true);
        logger.error("E' stato generato un errore mentre si sbustava un messaggio");
        processStampaEccezioniEGov.process(exchange);
        String sbusta = (String) exchange.getIn().getHeader(CostantiBusta.SBUSTA_RICHIESTA_RISPOSTA);
        if (sbusta.equals(CostantiBusta.VALORE_SBUSTA_RISPOSTA)) {
            logger.error("Si stava sbustando una risposta. Sblocco la porta delegata");
            processorSbloccaPollingConsumerPortaDelegata.process(exchange);
        } else if (sbusta.equals(CostantiBusta.VALORE_SBUSTA_RICHIESTA)) {
            logger.error("Si stava sbustando una richiesta. Sblocco la porta applicativa");
            processorSbloccaPollingConsumerPortaApplicativa.process(exchange);
        } else {
            logger.error("Errore GRAVE: Non è possibile stabilire se l'errore è stato generato da una richiesta o da una risposta");
        }
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }

    public ProcessorSbloccaPollingConsumerPortaDelegata getProcessorSbloccaPollingConsumerPortaDelegata() {
        return processorSbloccaPollingConsumerPortaDelegata;
    }

    public void setProcessorSbloccaPollingConsumerPortaDelegata(ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata) {
        this.processorSbloccaPollingConsumerPortaDelegata = processorSbloccaPollingConsumerPortaDelegata;
    }
}
