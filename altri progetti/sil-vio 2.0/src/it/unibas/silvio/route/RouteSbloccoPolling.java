package it.unibas.silvio.route;

import it.unibas.silvio.route.processors.ProcessorLog;
import it.unibas.silvio.route.processors.ProcessorSbloccoPolling;

public class RouteSbloccoPolling extends AbstractRoute {
    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass().getName());

    @Override
    public void configure() throws Exception {

        this.from(SEDA_SBLOCCA_POLLING)
                .process(new ProcessorLog(this))
                .process(new ProcessorSbloccoPolling());
    }

}
