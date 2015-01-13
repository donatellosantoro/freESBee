package it.unibas.icar.freesbee.portadelegatahttp;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DBManager;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;

public class HttpPortaDelegata extends RouteBuilder {

    private static Log logger = LogFactory.getLog(HttpPortaDelegata.class);
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
            
            if ((configurazione.isMutuaAutenticazionePortaDelegata()) && (indirizzo.contains("https"))) {
                if(logger.isInfoEnabled()) {logger.info("Sto configurando la PD per richiedere l'autenticazione client sull'indirizzo " + indirizzo);}
                JettyHttpComponent jettyComponent = getContext().getComponent("jetty", JettyHttpComponent.class);
                SslSelectChannelConnector sslConnector = new SslSelectChannelConnector();
                sslConnector.setPort(FreesbeeUtil.impostaNumeroPortaDaIndirizzo(indirizzo));
                sslConnector.setKeystore(ConfigurazioneStatico.getInstance().getFileKeyStore());
                sslConnector.setKeyPassword(ConfigurazioneStatico.getInstance().getPasswordKeyStore());
                sslConnector.setTruststore(ConfigurazioneStatico.getInstance().getFileTrustStore());
                sslConnector.setTrustPassword(ConfigurazioneStatico.getInstance().getPasswordTrustStore());
                sslConnector.setNeedClientAuth(true);
                Map<Integer, SslSelectChannelConnector> connectors = new HashMap<Integer, SslSelectChannelConnector>();
                connectors.put(FreesbeeUtil.impostaNumeroPortaDaIndirizzo(indirizzo), sslConnector);
                jettyComponent.setSslSocketConnectors(connectors);
            }
            
            this.avviaPortaDelegata("jetty:" + indirizzoPortaDelegata); //FIXME
        }
    }

    private void avviaPortaDelegata(String indirizzo) {
        if (logger.isInfoEnabled()) logger.info("Avvio la porta delegata all'indirizzo " + indirizzo);
        this.from(indirizzo)
                .process(ProcessorTrace.getInstance(ProcessorTrace.IN, "PD_REQ"))
//                .choice().when(header("CamelHttpMethod").isEqualTo("POST"))
                .choice()
                    .when(body().isNull())
                    .otherwise().when(header("CamelHttpMethod").isEqualTo("POST"))
                        .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                        .doTry()
                        //.process(new SOAPProcessorReader())
                        .process(SOAPProcessorReader.getInstance())
                        .process(new ProcessInitialize())
                        .to(FreesbeeCamel.SEDA_FILTRO_AUTENTICAZIONE)
                        .process(new ProcessorPolling())
                        .doCatch(Exception.class)
                        .process(new FaultProcessor())
                .end()
                .process(ProcessorTrace.getInstance(ProcessorTrace.IN, "PD_RESP"));

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
            if (logger.isInfoEnabled()) logger.info("Mi metto in attesa sul canale " + canale);

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
                if (logger.isInfoEnabled()) logger.info("Ho ricevuto una risposta di ack. Probabilmente il profilo di collaborazione non e' sincrono");
                Messaggio messaggioRisposta = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO_RICHIESTA);
                aggiungiIntestazioniAck(messaggioRisposta, exchange);
                return;
            }
            Processor processorWriter = SOAPProcessorWriterFactory.getInstance().getProcessorWriter();
            processorWriter.process(newExchange);
            MessageUtil.copyMessage(newExchange.getIn(), exchange.getOut());
            FreesbeeUtil.copiaIntestazioniMime(newExchange.getIn(), exchange.getOut());

            if (logger.isDebugEnabled()) logger.debug("Intestazioni del messaggio: " + FreesbeeUtil.stampaIntestazioni(exchange));
            if (logger.isInfoEnabled()) logger.info("@ Messaggio Ricevuto!");
        }

        private void aggiungiIntestazioniAck(Messaggio messaggioRisposta, Exchange exchange) {
            if (messaggioRisposta == null) {
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
            logger.error("Ricevuto messaggio SOAP non valido");
            Processor soapWriter = SOAPProcessorWriterFactory.getInstance().getProcessorWriter("300", "SOAP_ENV:Server");
            soapWriter.process(exchange);
            MessageUtil.copyMessage(exchange.getIn(), exchange.getOut());
        }
    }
}
