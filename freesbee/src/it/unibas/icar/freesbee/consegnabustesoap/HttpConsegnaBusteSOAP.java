package it.unibas.icar.freesbee.consegnabustesoap;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.contrib.AuthSSLProtocolSocketFactoryCustomized;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.exception.SOAPFaultException;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DBManager;
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
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpComponent;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class HttpConsegnaBusteSOAP extends RouteBuilder {

    private static Log logger = LogFactory.getLog(HttpConsegnaBusteSOAP.class);
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;
    @Inject
    private DBManager dbManager;

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
                if (logger.isDebugEnabled()) logger.debug("Il messaggio e' vuoto e il profilo di collaborazione non e' sincrono (ma " + profiloCollaborazione + ")");
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
                logger.error("Connettore del servizio applicativo non specificato !");
                throw new FreesbeeException("Connettore del servizio applicativo non specificato !");
            }
//            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SOAPAction", "portaApplicativa");
            int porta = FreesbeeUtil.impostaNumeroPortaDaIndirizzo(connettoreServizioApplicativo);

            FreesbeeUtil.aggiungiIntestazioniInteroperabilita(exchange.getIn(), messaggio);
            aggiungiIntestazioniAS(messaggio, exchange); //QUESTE INTESTAZIONI INVIANO ALL'EROGATORE LE INFORMAZIONI SUL FRUITORE, EROGATORE, SERVIZIO
            
//            if (logger.isInfoEnabled()) logger.info("Si sta consegnando il messaggio al servizio applicativo: " + messaggio.getConnettoreServizioApplicativo());
            
            if (logger.isDebugEnabled()) logger.debug(generaMessaggioLog(messaggio));
            
            HttpComponent httpComponent = (HttpComponent) getContext().getComponent("http");

            if ((messaggio.isMutuaAutenticazione()) && (connettoreServizioApplicativo.contains("https"))) {
                if(logger.isInfoEnabled()) {logger.info("Si sta effettuando una connessione HTTPS con autenticazione lato client all' URL " + connettoreServizioApplicativo + " sulla porta " + porta);}
                impostaParametriMutuaAutenticazione(porta);
            }

            if ((!messaggio.isMutuaAutenticazione()) && (connettoreServizioApplicativo.contains("https"))) {
                if(logger.isInfoEnabled()) {logger.info("Si sta effettuando una connessione HTTPS all' URL " + connettoreServizioApplicativo + " sulla porta " + porta);}
                impostaParametriSicurezza(porta);
            }
            
            httpComponent.createEndpoint(connettoreServizioApplicativo);
            Endpoint endpoint = getContext().getEndpoint(connettoreServizioApplicativo);

            try {
                ProcessorTrace.getInstance(ProcessorTrace.IN, "Si sta inoltrando il messaggio SOAP al servizio applicativo all'indirizzo " + connettoreServizioApplicativo).process(exchange);
//                ProcessorTrace.getInstance(ProcessorTrace.IN, "SEND_TO_SA_REQ").process(exchange);
                Producer producer = endpoint.createProducer();
                producer.start();
                producer.process(exchange);
                producer.stop();
                ProcessorTrace.getInstance(ProcessorTrace.OUT, "E' stato ricevuto un messaggio SOAP dall'indirizzo " + connettoreServizioApplicativo).process(exchange);
//                ProcessorTrace.getInstance(ProcessorTrace.OUT, "SEND_TO_SA_RESP").process(exchange);

                //Il producer se riceve un errore lo mette nell'header come String, e non come Exception. Quindi convertiamolo
                //TODO: Controllare che con Camel 2.9 il producer mette l'errore ancora come stringa e non direttamente come exception
                Object objectEx = exchange.getIn().getHeader("caught.exception");
                if (objectEx instanceof String) {
                    exchange.getIn().setHeader("caught.exception", new SOAPFaultException((String) objectEx));
                    exchange.setProperty(Exchange.EXCEPTION_CAUGHT, new SOAPFaultException((String) objectEx));
                }
            } catch (Exception e) {
                logger.error("Si e' verificato un errore durante l'inoltro del messaggio SOAP al servizio applicativo.");
                if (logger.isDebugEnabled()) e.printStackTrace();
                throw new FreesbeeException("Impossibile inoltrare la busta soap al servizio applicativo " + connettoreServizioApplicativo);
            }
            
            try {
                elaboraRisposta(exchange);
            } catch (SOAPFaultException e) {
                logger.error("Si e' verificato un errore mentre si cercava di elaborare la risposta generata dal servizio applicativo.");
                if (logger.isDebugEnabled()) e.printStackTrace();
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
            
//            String messaggioRicevuto = MessageUtil.getString(exchange.getIn()); //TODO [Michele]: Verificare che prendendo il contenuto dell'exchange.getIn il messaggio non venga perso
////            if(logger.isInfoEnabled()) logger.info("Ricevuta risposta dal servizio applicativo");
        }
    }

    private String generaMessaggioLog(Messaggio messaggio) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Id messaggio: ")
          .append(messaggio.getIdMessaggio())
          .append("\nFrom: ")
          .append(messaggio.getTipoFruitore())
          .append("/")
          .append(messaggio.getFruitore())
          .append(" -> ")
          .append(messaggio.getTipoErogatore())
          .append("/")
          .append(messaggio.getErogatore())
          .append("/")
          .append(messaggio.getServizio())
          .append("/")
          .append(messaggio.getAzione());
        return sb.toString();
    }

    private void impostaParametriMutuaAutenticazione(int porta) throws MalformedURLException {            
        URL keystoreUrl = new URL("file:" + ConfigurazioneStatico.getInstance().getFileKeyStore());
        String keyStorePassword = ConfigurazioneStatico.getInstance().getPasswordKeyStore();

        URL truststoreUrl = new URL("file:" + ConfigurazioneStatico.getInstance().getFileTrustStore());
        String trustStorePassword = ConfigurazioneStatico.getInstance().getPasswordTrustStore();

        ProtocolSocketFactory factory = new AuthSSLProtocolSocketFactoryCustomized(keystoreUrl, keyStorePassword, truststoreUrl, trustStorePassword);
        Protocol.registerProtocol("https",new Protocol("https",factory,porta));
    }

    private void impostaParametriSicurezza(int porta) {
        ProtocolSocketFactory factory = new EasySSLProtocolSocketFactory();
        Protocol.registerProtocol("https",new Protocol("https",factory,porta));
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }
    
}
