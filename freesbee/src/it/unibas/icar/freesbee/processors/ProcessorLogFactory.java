package it.unibas.icar.freesbee.processors;

import it.unibas.icar.freesbee.utilita.MessageUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessorLogFactory {

    private static Log logger = LogFactory.getLog(ProcessorLogFactory.class);
    private static ProcessorLogFactory singleton = new ProcessorLogFactory();
    private Map<Class, ProcessorLog> cache = new HashMap<Class, ProcessorLog>();

    private ProcessorLogFactory() {
    }

    public static ProcessorLogFactory getInstance() {
        return singleton;
    }

    public Processor getProcessorLog(Class classe) {
        ProcessorLog processor = this.cache.get(classe);
        if (processor == null) {
            processor = new ProcessorLog(classe);
            this.cache.put(classe, processor);
        }
        return processor;
    }

    private class ProcessorLog implements Processor {

        private Class classe;

        public ProcessorLog(Class classe) {
            this.classe = classe;
        }

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            if (logger.isDebugEnabled()) logger.debug(classe.getSimpleName() + ": processo la richiesta");
            if (logger.isDebugEnabled()) logger.debug("\tBody Class: " + exchange.getIn().getBody().getClass());
            if (logger.isDebugEnabled()) logger.debug("\tBody: \n" + MessageUtil.getString(exchange.getIn()) + "\n");
            if (logger.isDebugEnabled()) logger.debug("La mappa delle proprietà vale: " + exchange.getProperties());
            if (logger.isDebugEnabled()) logger.debug("La mappa delle instestazioni del messaggio vale: " + exchange.getIn().getHeaders());
//        String body = exchange.getIn().getBody(String.class);
            if (logger.isDebugEnabled()) logger.debug("@@ Messaggio Exchange " + MessageUtil.getString(exchange.getIn()));
        }
    }
}
