package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.*;
import org.apache.camel.component.direct.DirectEndpoint;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class ProcessorSbloccaPollingConsumerPortaApplicativa implements Processor {

    private boolean eccezione;
//    private static Log logger = LogFactory.getLog(ProcessorSbloccaPollingConsumerPortaApplicativa.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProcessorSbloccaPollingConsumerPortaApplicativa.class.getName());
    @Inject
    private ProcessorEccezione processorEccezione;

    public ProcessorSbloccaPollingConsumerPortaApplicativa() {
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa(boolean eccezione) {
        this.eccezione = eccezione;
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        //new ProcessorLog(this.getClass()).process(exchange);

        ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()).process(exchange);
        if (isEccezione() == true) {
            processorEccezione.setGeneraIntestazioniEGov(true);
            processorEccezione.process(exchange);
        }

        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        CamelContext context = exchange.getContext();
        String idPollingPortaApplicativa = messaggio.getPortaApplicativaChannel();
//        String canale = FreesbeeCamel.SEDA_POLLING_CONSUMER_PORTA_APPLICATIVA_CHANNEL + idPollingPortaApplicativa + FreesbeeCamel.SEDA_ARGS;
        String canale = FreesbeeCamel.DIRECT_POLLING_CONSUMER_PORTA_APPLICATIVA_CHANNEL + idPollingPortaApplicativa;
        if (logger.isDebugEnabled()) logger.debug("Sblocco il canale " + canale);
        
//        SedaComponent sedaComponent = (SedaComponent) context.getComponent("seda");
//        sedaComponent.createEndpoint(canale);
//        Endpoint endpoint = context.getEndpoint(canale);

        DirectEndpoint endpoint = (DirectEndpoint) context.getEndpoint(canale);
        endpoint.setExchangePattern(ExchangePattern.InOnly);
        Producer producer = endpoint.createProducer();
        producer.start();
        producer.process(exchange);
        producer.stop();
        
        if (logger.isDebugEnabled()) logger.debug("Canale sbloccato");
    }

    public ProcessorEccezione getProcessorEccezione() {
        return processorEccezione;
    }

    public void setProcessorEccezione(ProcessorEccezione processorEccezione) {
        this.processorEccezione = processorEccezione;
    }

    public boolean isEccezione() {
        return eccezione;
    }

    public void setEccezione(boolean eccezione) {
        this.eccezione = eccezione;
    }
}
