package it.unibas.silvio.route.processors;

import it.unibas.silvio.modello.Step;
import it.unibas.silvio.route.IRoute;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessorLog implements Processor {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass().getName());
    private IRoute route;

    public ProcessorLog(IRoute route) {
        this.route = route;
    }

    public void process(Exchange exchange) throws Exception {
        String logString = "Eseguo " + route.getClass().getSimpleName();
        Step step = route.getStepCorrente(exchange);
        if(step!=null) logString += " [" + step.getNome() + "]";
        logger.info(logString + "...");
    }
}
