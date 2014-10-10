package it.unibas.silvio.route;

import it.unibas.silvio.modello.Step;
import it.unibas.silvio.util.CostantiSilvio;
import javax.naming.Context;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public abstract class AbstractRoute extends RouteBuilder implements IRoute {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass().getName());
    public static final String SEDA = "seda:";
    public static final String SEDA_INVIO_MESSAGGIO = SEDA + Step.INVIO_MESSAGGIO;
    public static final String SEDA_INTERROGAZIONE_BD = SEDA + Step.INTERROGAZIONE_BD;
    public static final String SEDA_TRANSAZIONE_BD = SEDA + Step.TRANSAZIONE_BD;
    public static final String SEDA_SBLOCCA_POLLING = SEDA + Step.SBLOCCA_POLLING;
    public static final String SEDA_CANALE_POLLING = SEDA + "CanalePolling";
    public static final String PROSSIMO_STEP = "PROSSIMO_STEP";

    public Step getStepCorrente(Exchange exchange) {
        return (Step) exchange.getProperty(CostantiSilvio.STEP);
    }

    public class ProcessorProssimoStep implements Processor {

        public void process(Exchange exchange) throws Exception {
            //Prendo lo step corrente
            Step step = getStepCorrente(exchange);
            if (step == null) {
                throw new IllegalArgumentException("Impossibile stabilire il prossimo step. Lo step corrente è NULL");
            }

            Step prossimoStep = step.getProssimoStep();                 //Leggo il prossimo step
            exchange.setProperty(CostantiSilvio.STEP, prossimoStep);    //E lo setto come corrente

            String stringProssimoStep;
            if (prossimoStep != null) {
                stringProssimoStep = SEDA + prossimoStep.getTipo();
            } else {
                stringProssimoStep = SEDA_SBLOCCA_POLLING;              //Se non esiste il prossimo step setto quello di default
            }

            exchange.getIn().setHeader(PROSSIMO_STEP, stringProssimoStep);
        }
    }
}
