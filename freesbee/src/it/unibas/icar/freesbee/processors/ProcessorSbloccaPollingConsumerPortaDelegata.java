package it.unibas.icar.freesbee.processors;

import com.google.inject.Inject;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import org.apache.camel.*;
import org.apache.camel.component.direct.DirectEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorSbloccaPollingConsumerPortaDelegata implements Processor {

    private boolean eccezione;
    private static Log logger = LogFactory.getLog(ProcessorSbloccaPollingConsumerPortaDelegata.class);
    @Inject
    private ProcessorEccezione processorEccezione;

    public ProcessorSbloccaPollingConsumerPortaDelegata() {
    }

    public ProcessorSbloccaPollingConsumerPortaDelegata(boolean eccezione) {
        this.eccezione = eccezione;
    }

    public void process(Exchange exchange) throws Exception {
        //ContextStartup.aggiungiThread(this.getClass().getName());
        if (isEccezione() == true) {
            processorEccezione.setGeneraIntestazioniEGov(false);
            processorEccezione.process(exchange);
        }

        Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
        CamelContext context = exchange.getContext();
        String idPollingPortaDelegata = messaggio.getPortaDelegataChannel();
//        String canale = FreesbeeCamel.SEDA_POLLING_CONSUMER_PORTA_DELEGATA_CHANNEL + idPollingPortaDelegata+ FreesbeeCamel.SEDA_ARGS;
        String canale = FreesbeeCamel.DIRECT_POLLING_CONSUMER_PORTA_DELEGATA_CHANNEL + idPollingPortaDelegata;
        if (logger.isDebugEnabled()) logger.debug("Sblocco il canale " + canale);

//        SedaComponent sedaComponent = (SedaComponent) context.getComponent("seda");
//        sedaComponent.createEndpoint(canale);
        DirectEndpoint endpoint = (DirectEndpoint) context.getEndpoint(canale);
        endpoint.setExchangePattern(ExchangePattern.InOnly);
//        Endpoint endpoint = context.getEndpoint(canale);

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
