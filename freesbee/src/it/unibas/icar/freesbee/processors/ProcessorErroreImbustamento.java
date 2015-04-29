package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class ProcessorErroreImbustamento implements Processor {

//    private static Log logger = LogFactory.getLog(ProcessorErroreImbustamento.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProcessorErroreImbustamento.class.getName());
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;
    @Inject
    private ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata;
    @Inject
    private ProcessorStampaEccezioniEGov processStampaEccezioniEGov;

    public ProcessorErroreImbustamento() {
    }

    public void process(Exchange exchange) throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(true);
        processorSbloccaPollingConsumerPortaDelegata.setEccezione(true);
        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        if (messaggio != null && messaggio.isNica()) {
            logger.error("Si e' verificato un errore sul NICA durante l'imbustamento del messaggio.");
            processorSbloccaPollingConsumerPortaApplicativa.process(exchange);
            return;
        }
        logger.error("Si e' verificato un errore sulla PDD durante l'imbustamento del messaggio.");
        processStampaEccezioniEGov.process(exchange);
        String sbusta = (String) exchange.getIn().getHeader(CostantiBusta.IMBUSTA_RICHIESTA_RISPOSTA);
        if (sbusta.equals(CostantiBusta.VALORE_IMBUSTA_RISPOSTA)) {
            if (logger.isDebugEnabled()) logger.debug("Si stava imbustando la risposta. Si procede con lo sblocco la porta applicativa.");
            processorSbloccaPollingConsumerPortaApplicativa.process(exchange);
        } else if (sbusta.equals(CostantiBusta.VALORE_IMBUSTA_RICHIESTA)) {
            if (logger.isDebugEnabled()) logger.debug("Si stava imbustando la richiesta. Si procede con lo sblocco la porta delegata.");
            processorSbloccaPollingConsumerPortaDelegata.process(exchange);
        } else {
            logger.error("Si e' verificato un errore nel sistema. Non e' possibile stabilire se l'errore e' stato generato da una richiesta o da una risposta.");
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
