package it.unibas.icar.freesbee.portaapplicativa;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorTrace;
import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.processors.ProcessorValidaXSD;
import it.unibas.icar.freesbee.processors.SOAPProcessorWriterFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import it.unibas.icar.freesbee.utilita.MessageUtil;
import java.util.List;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.direct.DirectEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class HttpRicezionePortaApplicativa extends RouteBuilder {

    private static Log logger = LogFactory.getLog(HttpRicezionePortaApplicativa.class);
    @Inject
    private ProcessorValidaXSD processorValidaXSD;
    @Inject
    private DBManager dbManager;

    @Override
    public void configure() throws Exception {
        Configurazione configurazione = dbManager.getConfigurazione();
        List<String> indirizziPortaApplicativa = configurazione.getListaIndirizziPortaApplicativa();
        for (String indirizzo : indirizziPortaApplicativa) {
            this.avviaPortaApplicativa("jetty:" + indirizzo);
        }
    }

    private void avviaPortaApplicativa(String indirizzo) {
        if (logger.isInfoEnabled()) logger.info("Avvio la porta applicativa all'indirizzo " + indirizzo);
        this.from(indirizzo)
                .process(ProcessorTrace.getInstance(ProcessorTrace.IN, "PA_REQ"))
                //                .choice().when(header("CamelHttpMethod").isEqualTo("POST"))
                .choice()
                .when(body().isNull())
                .otherwise().when(header("CamelHttpMethod").isEqualTo("POST"))
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .doTry()
                //.process(new SOAPProcessorReader())
                .process(SOAPProcessorReader.getInstance())
                .process(processorValidaXSD)
                .process(new ProcessInitialize())
                .to(FreesbeeCamel.SEDA_PRESBUSTAMENTO_RICHIESTA)
                .process(new ProcessorPolling())
                .doCatch(Exception.class)
                .process(new FaultProcessor())
                .end()
                .process(ProcessorTrace.getInstance(ProcessorTrace.IN, "PA_RESP"));
    }

    private class ProcessInitialize implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = new Messaggio();
            messaggio.setPortaApplicativaChannel(exchange.getExchangeId());
            messaggio.setTipo(Messaggio.TIPO_RICEVUTO);
            exchange.setProperty(CostantiBusta.MESSAGGIO, messaggio);
        }
    }

    private class ProcessorPolling implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            // new ProcessorLog(this.getClass()).process(exchange);
            ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()).process(exchange);
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);

            String idPortaApplicativaChannel = messaggio.getPortaApplicativaChannel();
//            String canale = FreesbeeCamel.SEDA_POLLING_CONSUMER_PORTA_APPLICATIVA_CHANNEL + idPortaApplicativaChannel+FreesbeeCamel.SEDA_ARGS;
            String canale = FreesbeeCamel.DIRECT_POLLING_CONSUMER_PORTA_APPLICATIVA_CHANNEL + idPortaApplicativaChannel;
            if (logger.isDebugEnabled()) logger.debug("Mi metto in attesa sul canale " + canale);

//            SedaComponent sedaComponent = (SedaComponent) getContext().getComponent("seda");
//            sedaComponent.createEndpoint(canale)
            DirectEndpoint endpoint = (DirectEndpoint) getContext().getEndpoint(canale);
            endpoint.setExchangePattern(ExchangePattern.InOnly);
//            Endpoint endpoint = getContext().getEndpoint(canale);

            if (logger.isDebugEnabled()) logger.debug("Endpoint: " + endpoint);
            PollingConsumer consumer = endpoint.createPollingConsumer();
            consumer.start();
            Exchange newExchange = consumer.receive();
            consumer.stop();

            if (newExchange.getProperty("FREESBEE_ACK") != null && !newExchange.isFailed()) {
                if (logger.isInfoEnabled()) logger.info("Ho ricevuto una risposta di ack. Probabilmente il profilo di collaborazione non e' sincrono");
                MessageUtil.setString(exchange.getOut(), "");
                return;
            }
            if (logger.isInfoEnabled()) logger.info("Ricevuta risposta sincrona");

            Processor processorWriter = SOAPProcessorWriterFactory.getInstance().getProcessorWriter();
            processorWriter.process(newExchange);
            MessageUtil.copyMessage(newExchange.getIn(), exchange.getOut());
            FreesbeeUtil.copiaIntestazioniMime(newExchange.getIn(), exchange.getOut());
            if (logger.isInfoEnabled()) logger.info(generaMessaggioLog(messaggio));
            if (logger.isDebugEnabled()) logger.debug("Intestazioni del messaggio: " + FreesbeeUtil.stampaIntestazioni(exchange));
            if (logger.isDebugEnabled()) logger.debug("@ Messaggio Ricevuto!");
        }

        private String generaMessaggioLog(Messaggio messaggio) {
            StringBuilder sb = new StringBuilder();
            sb.append("Porta applicativa contattata. Ricevuto messaggio: ").append(messaggio.getIdMessaggio())
                    .append("\nFrom: ").append(messaggio.getTipoFruitore()).append("/").append(messaggio.getFruitore())
                    .append(" -> ").append(messaggio.getTipoErogatore()).append("/").append(messaggio.getErogatore()).append("/").append(messaggio.getServizio()).append("/").append(messaggio.getAzione())
                    .append("\nConnettore servizio applicativo: ").append(messaggio.getConnettoreServizioApplicativo());
            sb.append("\nInoltro il messaggio di risposta");
            return sb.toString();
        }
    }

    private class FaultProcessor implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
//            Exception e = (Exception) exchange.getIn().getHeader("caught.exception");
            Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
            if (logger.isDebugEnabled()) e.printStackTrace();
            logger.error("Ricevuto messaggio SOAP non valido. " + e.getMessage());
            Processor soapWriter = SOAPProcessorWriterFactory.getInstance().getProcessorWriter("001", "SOAP_ENV:Client");
            soapWriter.process(exchange);
            MessageUtil.copyMessage(exchange.getIn(), exchange.getOut());
        }
    }

    public ProcessorValidaXSD getProcessorValidaXSD() {
        return processorValidaXSD;
    }

    public void setProcessorValidaXSD(ProcessorValidaXSD processorValidaXSD) {
        this.processorValidaXSD = processorValidaXSD;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
