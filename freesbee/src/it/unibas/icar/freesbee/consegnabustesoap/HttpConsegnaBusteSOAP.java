package it.unibas.icar.freesbee.consegnabustesoap;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.guicefreesbee.ContextStartup;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.exception.SOAPFaultException;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaApplicativa;
import it.unibas.icar.freesbee.processors.ProcessorTrace;
import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.processors.SOAPProcessorWriterFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import it.unibas.icar.freesbee.utilita.MessageUtil;
import java.util.Map;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class HttpConsegnaBusteSOAP extends RouteBuilder {

    private static Log logger = LogFactory.getLog(HttpConsegnaBusteSOAP.class);
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;

    @Override
    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(true);
        this.from(FreesbeeCamel.SEDA_HTTP_CONSEGNA_BUSTE_SOAP)
                //                .threads(ConfigurazioneStatico.getInstance().getCamelThreadPool(),
                //                ConfigurazioneStatico.getInstance().getCamelThreadPoolMax())
                //  .process(new ProcessorLog(this.getClass()))
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .doTry()
                .process(SOAPProcessorWriterFactory.getInstance().getProcessorWriter())
                .process(new ProcessorSend())
                .to(FreesbeeCamel.SEDA_PROCESSA_RISPOSTA_SERVIZIO_APPLICATIVO)
                .doCatch(Exception.class)
                .process(processorSbloccaPollingConsumerPortaApplicativa);

        this.from(FreesbeeCamel.SEDA_PROCESSA_RISPOSTA_SERVIZIO_APPLICATIVO)
                .process(new ProcessorVerificaAck())
                .choice().when(header("ACK").isEqualTo(false))
                .to(FreesbeeCamel.SEDA_ENRICHER_PREIMBUSTAMENTO_RISPOSTA)
                .otherwise()
                .to(FreesbeeCamel.SEDA_RISPOSTA_ACK);
    }

    private class ProcessorVerificaAck implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());

            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            String profiloCollaborazione = (String) exchange.getProperty(CostantiBusta.VALOREPROFILOCOLLABORAZIONE);

            if (MessageUtil.isEmpty(exchange.getIn()) && !profiloCollaborazione.equals(AccordoServizio.PROFILO_SINCRONO)) {
                exchange.setProperty("ACK", true);
                if (logger.isInfoEnabled()) logger.info("Il messaggio e' vuoto e il profilo di collaborazione non e' sincrono (ma " + profiloCollaborazione + ")");
            } else {
                exchange.setProperty("ACK", false);
                if (!profiloCollaborazione.equals(AccordoServizio.PROFILO_SINCRONO)) {
                    messaggio.setTipo(Messaggio.TIPO_ACK_RISPOSTA);
                }
            }
        }
    }

    private class ProcessorSend implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            if (logger.isDebugEnabled()) logger.debug("Intestazioni del messaggio: " + FreesbeeUtil.stampaIntestazioni(exchange));
            
            String connettoreServizioApplicativo = messaggio.getConnettoreServizioApplicativo();
            if (logger.isDebugEnabled()) logger.debug("Connettore servizio applicativo: " + connettoreServizioApplicativo);
            if (connettoreServizioApplicativo == null) {
                throw new FreesbeeException("Connettore del ServizioApplicativo non specificato");
            }
//            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SOAPAction", "portaApplicativa");

            FreesbeeUtil.aggiungiIntestazioniInteroperabilita(exchange.getIn(), messaggio);
            aggiungiIntestazioniAS(messaggio, exchange); //QUESTE INTESTAZIONI INVIANO ALL'EROGATORE LE INFORMAZIONI SUL FRUITORE, EROGATORE, SERVIZIO
            if (logger.isInfoEnabled()) logger.info(generaMessaggioLog(messaggio));
            HttpComponent httpComponent = (HttpComponent) getContext().getComponent("http");
            httpComponent.createEndpoint(connettoreServizioApplicativo);
            Endpoint endpoint = getContext().getEndpoint(connettoreServizioApplicativo);

            try {
                ProcessorTrace.getInstance(ProcessorTrace.IN, "SEND_TO_SA_REQ").process(exchange);
                Producer producer = endpoint.createProducer();
                producer.start();
                producer.process(exchange);
                producer.stop();
                ProcessorTrace.getInstance(ProcessorTrace.OUT, "SEND_TO_SA_RESP").process(exchange);

                //Il producer se riceve un errore lo mette nell'header come String, e non come Exception. Quindi convertiamolo
                //TODO: Controllare che con Camel 2.9 il producer mette l'errore ancora come stringa e non direttamente come exception
                Object objectEx = exchange.getIn().getHeader("caught.exception");
                if (objectEx instanceof String) {
                    exchange.getIn().setHeader("caught.exception", new SOAPFaultException((String) objectEx));
                    exchange.setProperty(Exchange.EXCEPTION_CAUGHT, new SOAPFaultException((String) objectEx));
                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Errore nell'inoltro della busta soap. " + e);
                logger.error("Body: "+ MessageUtil.getString(exchange.getIn()));
                String errore = "Impossibile inoltrare la busta soap al servizio applicativo " + connettoreServizioApplicativo;
                throw new FreesbeeException(errore + ". " + e.getMessage());
            }
            try {
                elaboraRisposta(exchange);
            } catch (SOAPFaultException e) {
                if (logger.isInfoEnabled()) logger.info("Il servizio applicativo mi ha risposto con un fault " + e);
            }

            messaggio.setTipo(Messaggio.TIPO_INVIATO);
            messaggio.setId(0);
        }

        private void aggiungiIntestazioniAS(Messaggio messaggioRichiesta, Exchange exchange) {
            if (messaggioRichiesta == null) {
                return;
            }
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO, messaggioRichiesta.getIdMessaggio());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO, messaggioRichiesta.getRiferimentoMessaggio());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SPCoopTipoMittente", messaggioRichiesta.getTipoFruitore());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SPCoopMittente", messaggioRichiesta.getFruitore());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SPCoopTipoDestinatario", messaggioRichiesta.getTipoErogatore());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SPCoopDestinatario", messaggioRichiesta.getErogatore());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SPCoopTipoServizio", messaggioRichiesta.getTipoServizio());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SPCoopServizio", messaggioRichiesta.getServizio());
        }

        private void elaboraRisposta(Exchange exchange) throws FreesbeeException, Exception {
            MessageUtil.copyMessage(exchange.getOut(), exchange.getIn());
            FreesbeeUtil.copiaIntestazioniMime(exchange.getOut(), exchange.getIn());
            SOAPProcessorReader soapReader = SOAPProcessorReader.getInstance();
            soapReader.process(exchange);
            MessageUtil.copyMessage(exchange.getIn(), exchange.getOut());
            FreesbeeUtil.copiaIntestazioniMime(exchange.getIn(), exchange.getOut());
            if(logger.isInfoEnabled()) logger.info("Ricevuta risposta dal servizio applicativo");
        }
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }

    private String generaMessaggioLog(Messaggio messaggio) {
        StringBuilder sb = new StringBuilder();
        sb.append("Consegno il messaggio al servizio applicativo: ").append(messaggio.getConnettoreServizioApplicativo()).append(" Id messaggio: ").append(messaggio.getIdMessaggio())
                .append("\nFrom: ").append(messaggio.getTipoFruitore()).append("/").append(messaggio.getFruitore())
                .append(" -> ").append(messaggio.getTipoErogatore()).append("/").append(messaggio.getErogatore()).append("/").append(messaggio.getServizio()).append("/").append(messaggio.getAzione());
        return sb.toString();
    }
}
