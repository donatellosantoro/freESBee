package it.unibas.silvio.route;

import it.unibas.silvio.modello.StepInvioMessaggio;
import it.unibas.silvio.route.processors.ProcessorLog;
import java.net.ConnectException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.component.jetty.JettyHttpEndpoint;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;

public class RouteInvioMessaggio extends AbstractRoute {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass().getName());

    @Override
    public void configure() throws Exception {

        this.from(SEDA_INVIO_MESSAGGIO)
                .process(new ProcessorLog(this))
                .process(new ProcessorInvia())
                .process(new ProcessorProssimoStep())
            .recipientList(header(PROSSIMO_STEP));
    }

    private class ProcessorInvia implements Processor {

        public void process(Exchange exchange) throws Exception {
            StepInvioMessaggio step = (StepInvioMessaggio) getStepCorrente(exchange);
            String indirizzo = step.getIndirizzo();
            //TODO: aggiungere eccezione
//            if (indirizzo == null || indirizzo.trim().isEmpty()) {
//                throw new SilvioException("Impossibile inviare la richiesta. Nessun indirizzo specificato");
//            }
            indirizzo = indirizzo.trim();
            logger.debug("Devo inviare il messaggio all'indirizzo " + step.getIndirizzo());
            try {
                JettyHttpComponent jettyHttpComponent = (JettyHttpComponent) getContext().getComponent("jetty");
                //TODO: è necessario il controllo dello /?
                jettyHttpComponent.createEndpoint("jetty:" + this.controllaConnettore(indirizzo));
                JettyHttpEndpoint endpoint = getContext().getEndpoint("jetty:" + indirizzo, JettyHttpEndpoint.class);

                Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
                Protocol.registerProtocol("https", easyhttps);

                Exchange exchangeInviare = exchange.copy();
                //TODO: serve il soap writer?
//                new ProcessorSOAPWriter().process(exchangeInviare);
                Producer producer = endpoint.createProducer();
                producer.start();
                producer.process(exchangeInviare);
                producer.stop();

                Message messageRispostaOut = exchangeInviare.getOut();
                String stringaMessaggioRisposta = messageRispostaOut.getBody(String.class);
                
                //TODO: gestione fault
//                String stringaFault = SOAPUtil.controllaFault(stringaMessaggioRisposta);

                //TODO: gestione fault
//                if (stringaFault != null) {
//                    logger.info("L'erogatore mi ha risposto con un SOAP FAULT");
//                    messaggioRisposta.setFault(true);
//                    throw new SilvioException("L'erogatore mi ha risposto con un SOAP FAULT: " + stringaFault);
//                }
                
                logger.debug("Ho ricevuto la seguente risposta " + stringaMessaggioRisposta);
                exchange.getIn().setBody(stringaMessaggioRisposta, String.class);

            } catch (ConnectException e) {
                logger.error("Errore nell'inoltro del messaggio SOAP. " + e);
                String errore = "Impossibile inviare il messaggio all'indirizzo " + indirizzo;
//                throw new SilvioException(errore + ". " + e.getMessage());
            }
        }

        private String controllaConnettore(String connettore) {
            if (connettore.endsWith("/")) {
                return connettore;
            } else {
                return connettore + "/";
            }
        }
    }
}
