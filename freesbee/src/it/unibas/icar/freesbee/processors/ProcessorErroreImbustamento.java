package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorErroreImbustamento implements Processor {

    private static Log logger = LogFactory.getLog(ProcessorErroreImbustamento.class);
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;
    @Inject
    private ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata;
    @Inject
    private ProcessStampaEccezioniEGov processStampaEccezioniEGov;

    public ProcessorErroreImbustamento() {
    }

    public void process(Exchange exchange) throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(true);
        processorSbloccaPollingConsumerPortaDelegata.setEccezione(true);
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        if (messaggio != null && messaggio.isNica()) {
            logger.error("E' stato generato un errore mentre imbustavo il messaggio da NICA");
            processorSbloccaPollingConsumerPortaApplicativa.process(exchange);
            return;
        }
        logger.error("E' stato generato un errore mentre si imbustava un messaggio");
        processStampaEccezioniEGov.process(exchange);
        String sbusta = (String) exchange.getIn().getHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA);
        if (sbusta.equals(CostantiBusta.VALORE_IMBUSTA_RISPOSTA)) {
            logger.error("Si stava imbustando una risposta. Sblocco la porta applicativa");
            processorSbloccaPollingConsumerPortaApplicativa.process(exchange);
        } else if (sbusta.equals(CostantiBusta.VALORE_IMBUSTA_RICHIESTA)) {
            logger.error("Si stava imbustando una richiesta. Sblocco la porta delegata");
            processorSbloccaPollingConsumerPortaDelegata.process(exchange);
        } else {
            logger.error("Errore GRAVE: Non e' possibile stabilire se l'errore e' stato generato da una richiesta o da una risposta");
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
