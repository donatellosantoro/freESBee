package it.unibas.icar.freesbee.portadelegatahttp;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.portadelegata.RichiestaAck;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorTrace;
import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.processors.SOAPProcessorWriterFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import it.unibas.icar.freesbee.utilita.MessageUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.direct.DirectEndpoint;
import org.apache.camel.component.jetty.JettyHttpComponent;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;
import org.slf4j.LoggerFactory;

public class HttpPortaDelegata extends RouteBuilder {

//    private static Log logger = LogFactory.getLog(HttpPortaDelegata.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpPortaDelegata.class.getName());
    private DBManager dbManager;
    private String portaDelegataContattata;
    private String tipo;

    public HttpPortaDelegata(String portaDelegataContattata, String tipo, DBManager dbManager) {
        this.portaDelegataContattata = portaDelegataContattata;
        this.tipo = tipo;
        this.dbManager = dbManager;
    }

    @Override
    public void configure() throws Exception {
        Configurazione configurazione = dbManager.getConfigurazione();
        
        List<String> listaIndirizziPortaDelegata = configurazione.getListaIndirizziPortaDelegata();
        for (String indirizzo : listaIndirizziPortaDelegata) {
            String indirizzoPortaDelegata = indirizzo + portaDelegataContattata + "/";
            // this.avviaPortaDelegata("jetty:" + indirizzoPortaDelegata + "?continuationTimeout=0");
            
            int porta = FreesbeeUtil.impostaNumeroPortaDaIndirizzo(indirizzo);
            
            if (indirizzoPortaDelegata.contains("https")) {
                if(logger.isInfoEnabled()) {logger.info("Si sta predisponendo la PD per aprire un canale criptato HTTPS sull'indirizzo " + indirizzoPortaDelegata + " con porta " + porta);}
                JettyHttpComponent jettyComponent = getContext().getComponent("jetty", JettyHttpComponent.class);
                SslSelectChannelConnector sslConnector = new SslSelectChannelConnector();
                sslConnector.setPort(porta);
                sslConnector.setKeystore(ConfigurazioneStatico.getInstance().getFileKeyStore());
                sslConnector.setKeyPassword(ConfigurazioneStatico.getInstance().getPasswordKeyStore());
                sslConnector.setTruststore(ConfigurazioneStatico.getInstance().getFileTrustStore());
                sslConnector.setTrustPassword(ConfigurazioneStatico.getInstance().getPasswordTrustStore());
                
                if (configurazione.isMutuaAutenticazionePortaDelegata()) {
                    if(logger.isInfoEnabled()) {logger.info("Si sta configurando la PD per richiedere l'autenticazione client\n");}
                    sslConnector.setNeedClientAuth(true);
                } else {
                    sslConnector.setNeedClientAuth(false);
                }
                
                Map<Integer, SslSelectChannelConnector> connectors = new HashMap<Integer, SslSelectChannelConnector>();
                connectors.put(porta, sslConnector);
                jettyComponent.setSslSocketConnectors(connectors);
            } else {
                if(logger.isInfoEnabled()) {logger.info("Si sta predisponendo la PD per aprire un canale HTTP sull'indirizzo " + indirizzoPortaDelegata + " con porta " + porta);}
            }
            
            this.avviaPortaDelegata("jetty:" + indirizzoPortaDelegata); //FIXME
        }
    }

    private void avviaPortaDelegata(String indirizzo) {
        if (logger.isInfoEnabled()) logger.info("Si sta avviando la porta delegata all'indirizzo " + indirizzo);
        this.from(indirizzo)
//                .process(ProcessorTrace.getInstance(ProcessorTrace.IN, "PD_REQ"))
                .process(ProcessorTrace.getInstance(ProcessorTrace.IN, "E' stata contattata la PD all'indirizzo " + indirizzo))
//                .choice().when(header("CamelHttpMethod").isEqualTo("POST"))
                .choice()
                    .when(body().isNull())
                    .otherwise().when(header("CamelHttpMethod").isEqualTo("POST"))
                        .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                        .doTry()
                            .process(SOAPProcessorReader.getInstance())
                            .process(new ProcessInitialize())
                            .to(FreesbeeCamel.SEDA_FILTRO_AUTENTICAZIONE)
                            .process(new ProcessorPolling())
                        .doCatch(Exception.class)
                            .process(new FaultProcessor())
                .end()
//                .process(ProcessorTrace.getInstance(ProcessorTrace.IN, "PD_RESP"));
                .process(ProcessorTrace.getInstance(ProcessorTrace.IN, "E' stata prodotta la seguente risposta SOAP"));

    }

    private class ProcessInitialize implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = new Messaggio();
            messaggio.setPortaDelegata(portaDelegataContattata);
            messaggio.setTipo(Messaggio.TIPO_INVIATO);
            messaggio.setPortaDelegataChannel(exchange.getExchangeId());
            exchange.setProperty(CostantiBusta.MESSAGGIO, messaggio);
        }
    }

    private class ProcessorPolling implements Processor {

        @SuppressWarnings("unchecked")
        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            String idPollingPortaDelegata = messaggio.getPortaDelegataChannel();
//            String canale = FreesbeeCamel.SEDA_POLLING_CONSUMER_PORTA_DELEGATA_CHANNEL + idPollingPortaDelegata+"?waitForTaskToComplete=Never";
            String canale = FreesbeeCamel.DIRECT_POLLING_CONSUMER_PORTA_DELEGATA_CHANNEL + idPollingPortaDelegata;
            if (logger.isDebugEnabled()) logger.debug("Si rimane in attesa sul canale " + canale);

//            SedaComponent sedaComponent = (SedaComponent) getContext().getComponent("seda");
//            sedaComponent.createEndpoint(canale);
            DirectEndpoint endpoint = (DirectEndpoint) getContext().getEndpoint(canale);
            endpoint.setExchangePattern(ExchangePattern.InOnly);
//            Endpoint endpoint = getContext().getEndpoint(canale);

            PollingConsumer consumer = endpoint.createPollingConsumer();
            consumer.start();
            Exchange newExchange = consumer.receive();
            consumer.stop();

            if (exchange.getProperty("FREESBEE_ACK") != null && !newExchange.isFailed()) {
                if (logger.isDebugEnabled()) logger.debug("E' stata ricevuta una risposta di ack. Probabilmente il profilo di collaborazione non e' sincrono");
                Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO_RICHIESTA);
                aggiungiIntestazioniAck(messaggioRisposta, exchange);
                return;
            }
            Processor processorWriter = SOAPProcessorWriterFactory.getInstance().getProcessorWriter();
            processorWriter.process(newExchange);
            MessageUtil.copyMessage(newExchange.getIn(), exchange.getOut());
            FreesbeeUtil.copiaIntestazioniMime(newExchange.getIn(), exchange.getOut());

            if (logger.isDebugEnabled()) logger.debug("Intestazioni del messaggio: " + FreesbeeUtil.stampaIntestazioni(exchange));
//            if (logger.isInfoEnabled()) logger.info("Messaggio Ricevuto!");
        }

        private void aggiungiIntestazioniAck(Messaggio messaggioRisposta, Exchange exchange) {
            if (messaggioRisposta == null) {
                logger.error("Impossibile aggiungere le intestazioni di ack, il messaggio di risposta è nullo.");
                return;
            }
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), CostantiSOAP.INTEGRAZIONE_ID_MESSAGGIO, messaggioRisposta.getIdMessaggio());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), CostantiSOAP.INTEGRAZIONE_RIFERIMENTO_MESSAGGIO, messaggioRisposta.getRiferimentoMessaggio());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), "SPCoopTipoMittente", messaggioRisposta.getTipoFruitore());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), "SPCoopMittente", messaggioRisposta.getFruitore());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), "SPCoopTipoDestinatario", messaggioRisposta.getTipoErogatore());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), "SPCoopDestinatario", messaggioRisposta.getErogatore());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), "SPCoopTipoServizio", messaggioRisposta.getTipoServizio());
            FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getOut(), "SPCoopServizio", messaggioRisposta.getServizio());
        }
    }

    private class FaultProcessor implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            logger.error("Ricevuto messaggio SOAP non valido.");
            Processor soapWriter = SOAPProcessorWriterFactory.getInstance().getProcessorWriter("300", "SOAP_ENV:Server");
            soapWriter.process(exchange);
            MessageUtil.copyMessage(exchange.getIn(), exchange.getOut());
        }
    }
}
