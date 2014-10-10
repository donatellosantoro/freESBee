package it.unibas.silvio.erogatore;

import it.unibas.silvio.processor.ProcessorLog;
import it.unibas.silvio.util.CostantiCamel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EventDrivenConsumerSbloccoRisposta extends RouteBuilder{
    
    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void configure() throws Exception {
        this.from(CostantiCamel.EVENT_DRIVER_CONSUMER_SBLOCCO_RISPOSTA)
                .process(new ProcessorLog(this.getClass()))
                .to(CostantiCamel.ENRICHER_DATI_PARZIALI_COMPLETI_EROGATORE);
    }

}
