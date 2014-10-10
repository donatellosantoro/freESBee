package it.unibas.freesbee.ge.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorLog implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());
    private Class classe;

      public ProcessorLog() {
        this.classe = this.classe;
    }
    public ProcessorLog(Class classe) {
        this.classe = classe;
    }

    public void process(Exchange exchange) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info(classe.getSimpleName() + ": processo la richiesta");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("La mappa delle proprieta' vale: " + exchange.getProperties());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("La mappa delle instestazioni del messaggio vale: " + exchange.getIn().getHeaders());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("@@ Messaggio Exchange " + exchange.getIn().getBody(String.class));
        }
    }
}
