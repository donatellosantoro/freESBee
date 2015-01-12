package it.unibas.icar.freesbee.inoltrobustaegov;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.contrib.AuthSSLProtocolSocketFactoryCustomized;
import it.unibas.icar.freesbee.exception.FreesbeeException;
import it.unibas.icar.freesbee.exception.SOAPFaultException;
import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
//import it.unibas.icar.freesbee.modello.ConfigurazioneStatico;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaApplicativa;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaDelegata;
import it.unibas.icar.freesbee.processors.ProcessorTrace;
import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.processors.SOAPProcessorWriterFactory;
import it.unibas.icar.freesbee.utilita.CostantiBusta;
import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import it.unibas.icar.freesbee.utilita.FreesbeeCamel;
import it.unibas.icar.freesbee.utilita.FreesbeeUtil;
import it.unibas.icar.freesbee.utilita.MessageUtil;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.GetSoggettoSPCoop;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.IWSRegistroServizi;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.WSRegistroServiziImplService;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.component.http.HttpOperationFailedException;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class HttpInoltroBustaEGov extends RouteBuilder {

    private static Log logger = LogFactory.getLog(HttpInoltroBustaEGov.class);
    @Inject
    private DBManager dbManager;
    @Inject
    private ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa;
    @Inject
    private ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata;
    private static Map<String, String> cacheConnettoreNica = new HashMap<String, String>();

    @Override
    public void configure() throws Exception {
        processorSbloccaPollingConsumerPortaApplicativa.setEccezione(true);
        processorSbloccaPollingConsumerPortaDelegata.setEccezione(true);
        this.from(FreesbeeCamel.SEDA_HTTP_INOLTRO_BUSTA_EGOV)
                .process(ProcessorLogFactory.getInstance().getProcessorLog(this.getClass()))
                .doTry()
                .process(SOAPProcessorWriterFactory.getInstance().getProcessorWriter())
                .process(new ProcessorSend())
                .to(FreesbeeCamel.SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE)
                .doCatch(Exception.class)
                .choice()
                .when(header(CostantiBusta.RUOLO_NICA).isEqualTo(true))
                .process(processorSbloccaPollingConsumerPortaApplicativa)
                .otherwise()
                .process(processorSbloccaPollingConsumerPortaDelegata);

        this.from(FreesbeeCamel.SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE)
                .choice()
                .when(header(CostantiBusta.RUOLO_NICA).isEqualTo(true))
                .to(FreesbeeCamel.SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE_NICA)
                .otherwise()
                .to(FreesbeeCamel.SEDA_PROCESSA_RISPOSTA_PORTA_DOMINIO_EROGATORE_PDD);
    }

    private class ProcessorSend implements Processor {

        public void process(Exchange exchange) throws Exception {
            //ContextStartup.aggiungiThread(this.getClass().getName());
            Messaggio messaggio = (Messaggio) exchange.getProperty(CostantiBusta.MESSAGGIO);
            Configurazione configurazione = dbManager.getConfigurazione();
            String connettoreDestinatario = getConnettoreDestinatario(messaggio, configurazione);
//            connettoreDestinatario += "?httpClientMinThreads=100&httpClientMaxThreads=100";
            try {
                if (exchange.getIn().getHeader("SOAPAction") == null || ((String) exchange.getIn().getHeader("SOAPAction")).isEmpty()) {
                    FreesbeeUtil.aggiungiInstestazioniHttp(exchange.getIn(), "SOAPAction", " ");
                }
                FreesbeeUtil.aggiungiIntestazioniInteroperabilita(exchange.getIn(), messaggio);
                HttpComponent httpComponent = (HttpComponent) getContext().getComponent("http");

                if (messaggio.isMutuaAutenticazione()) {
                    if (logger.isInfoEnabled()) {
                        logger.info("Si sta effettuando una connessione HTTPS con autenticazione lato client all' URL " + connettoreDestinatario);
                    }

                    URL keystoreUrl = new URL("file:" + ConfigurazioneStatico.getInstance().getFileKeyStore());
                    String keyStorePassword = ConfigurazioneStatico.getInstance().getPasswordKeyStore();

                    URL truststoreUrl = new URL("file:" + ConfigurazioneStatico.getInstance().getFileTrustStore());
                    String trustStorePassword = ConfigurazioneStatico.getInstance().getPasswordTrustStore();

                    ProtocolSocketFactory factory = new AuthSSLProtocolSocketFactoryCustomized(keystoreUrl, keyStorePassword, truststoreUrl, trustStorePassword);

                    Protocol.registerProtocol("https", new Protocol("https", factory, 443));
                }

                httpComponent.createEndpoint(connettoreDestinatario);
                Endpoint endpoint = getContext().getEndpoint(connettoreDestinatario);
                ProcessorTrace.getInstance(ProcessorTrace.IN, "SEND_TO_PA_REQ").process(exchange);
                Producer producer = endpoint.createProducer();
                producer.start();
                producer.process(exchange);
                producer.stop();
                ProcessorTrace.getInstance(ProcessorTrace.OUT, "SEND_TO_PA_RESP").process(exchange);
                //     producer.stop();
                if (logger.isDebugEnabled()) {
                    StringBuilder log = new StringBuilder();
                    log.append("\n\n\n");
                    log.append("Ho inviato il messaggio all'indirizzo: ").append(connettoreDestinatario).append("\n\n");
                    log.append("Richiesta\n");
                    log.append(MessageUtil.getString(exchange.getIn())).append("\n\n");
                    log.append("Risposta\n");
                    log.append(MessageUtil.getString(exchange.getOut())).append("\n\n");
                    log.append("Eccezione\n");
                    log.append(exchange.getException());
                    log.append("\n\n");
                    logger.debug(log);
                } else {
                    if (logger.isInfoEnabled()) logger.info("Ho inviato il messaggio all'indirizzo: " + connettoreDestinatario);
                }
            } catch (ConnectException e) {
                logger.error("Errore nell'inoltro della busta EGov. " + e);
                String errore = "Impossibile contattare la porta di dominio all'indirizzo " + connettoreDestinatario;
                throw new FreesbeeException(errore + ". - " + e.getMessage());
            } catch (ProtocolException ssle) {
                logger.error("Errore nell'inoltro della busta EGov. Il messaggio non viaggia in una connessione SSL. " + ssle);
                String errore = "Impossibile contattare la porta di dominio all'indirizzo " + connettoreDestinatario + ". Il messaggio non viaggia in una connessione SSL.";
                throw new FreesbeeException(errore + ". - " + ssle.getMessage());
            } catch (HttpOperationFailedException hofe) {
                StringBuilder sb = new StringBuilder();
                sb.append("Riscontrato errore durante l'inoltro del Messaggio SPCoop con identificativo :").append(messaggio.getIdMessaggio())
                        .append("\nFrom: ").append(messaggio.getTipoFruitore()).append("/").append(messaggio.getFruitore())
                        .append(" -> ").append(messaggio.getTipoErogatore()).append("/").append(messaggio.getErogatore()).append("/").append(messaggio.getServizio()).append("/").append(messaggio.getAzione())
                        .append("\nDescrizione errore: ").append(hofe.getMessage());
                //logger.error(sb.toString());
                exchange.setProperty(CostantiSOAP.SOAP_HEADER_MESSAGE_EXCEPTION, sb.toString());
                throw new FreesbeeException(sb.toString());
            } catch (UnknownHostException uhe) {
                StringBuilder sb = new StringBuilder();
                sb.append("Riscontrato errore durante l'inoltro del Messaggio SPCoop con identificativo :").append(messaggio.getIdMessaggio())
                        .append("\nFrom: ").append(messaggio.getTipoFruitore()).append("/").append(messaggio.getFruitore())
                        .append(" -> ").append(messaggio.getTipoErogatore()).append("/").append(messaggio.getErogatore()).append("/").append(messaggio.getServizio()).append("/").append(messaggio.getAzione())
                        .append("\nDescrizione errore: ").append(uhe.getMessage());
                //logger.error(sb.toString());
                exchange.setProperty(CostantiSOAP.SOAP_HEADER_MESSAGE_EXCEPTION, sb.toString());
                throw new FreesbeeException(sb.toString());
            } catch (Exception e) {
                logger.error("Errore nell'inoltro della busta EGov. " + e);
                String errore = "Si e' verificato un errore mentre si cercava di contattare la porta di dominio all'indirizzo " + connettoreDestinatario + ".";
                e.printStackTrace();
                throw new FreesbeeException(errore + ". -  " + e.getMessage());
            }
            try {
                elaboraRisposta(exchange, messaggio);
            } catch (SOAPFaultException e) {
                if (logger.isInfoEnabled()) logger.info("La porta di dominio mi ha risposto con un fault " + e);
            }
        }

        private void elaboraRisposta(Exchange exchange, Messaggio messaggio) throws SOAPFaultException, Exception {
            Messaggio newMessaggio = new Messaggio();
            newMessaggio.setPortaDelegataChannel(messaggio.getPortaDelegataChannel());
            newMessaggio.setPortaApplicativaChannel(messaggio.getPortaApplicativaChannel());
            newMessaggio.setTipo(Messaggio.TIPO_RICEVUTO);
            exchange.setProperty(CostantiBusta.MESSAGGIO, newMessaggio);
            exchange.setProperty(CostantiBusta.MESSAGGIO_RICHIESTA, messaggio);
            MessageUtil.copyMessage(exchange.getOut(), exchange.getIn());
            FreesbeeUtil.copiaIntestazioniMime(exchange.getOut(), exchange.getIn());
            SOAPProcessorReader soapReader = SOAPProcessorReader.getInstance();
            soapReader.process(exchange);
            MessageUtil.copyMessage(exchange.getIn(), exchange.getOut());
            FreesbeeUtil.copiaIntestazioniMime(exchange.getIn(), exchange.getOut());
            if (logger.isInfoEnabled()) logger.info("Risposta ricevuta dall'erogatore");
        }

        private String getConnettoreDestinatario(Messaggio messaggio, Configurazione configurazione) throws FreesbeeException {
            String connettoreDestinatario;
            if (configurazione.isInviaANICA()) {
                connettoreDestinatario = getConnettoreNica(configurazione, messaggio);
            } else {
                connettoreDestinatario = messaggio.getConnettoreErogatore();
                if (connettoreDestinatario == null || connettoreDestinatario.equals("")) {
                    throw new FreesbeeException("Impossibile inoltrare la busta EGov. Nessun connettore specificato per l'erogatore " + messaggio.getErogatore());
                }
                if (logger.isInfoEnabled()) logger.info("Invio la busta EGov direttamente all'erogatore all'indirizzo " + connettoreDestinatario);
            }
            return connettoreDestinatario;
        }

        private String getConnettoreNica(Configurazione configurazione ,Messaggio messaggio) throws FreesbeeException {
            String connettoreDestinatario = null;
            Soggetto soggettoNICA = configurazione.getSoggettoErogatoreRegistroServizi();  
            //                connettoreDestinatario = soggettoNICA.getPortaDominio();
            String chiave = soggettoNICA.getNomeBreve();
            if (ConfigurazioneStatico.getInstance().isCacheWS() && cacheConnettoreNica.containsKey(chiave)) {
                return cacheConnettoreNica.get(chiave);
            }
            String connettoreRegistroServizi = configurazione.getConnettoreRegistroServizi();
            String indirizzoWSDL = connettoreRegistroServizi;
            String indirizzoRichiesta = null;
            if (!configurazione.isRegistroFreesbee()) {
                ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();
                indirizzoWSDL = conf.getInteroperabilitaRegistroFreesbee(); //DEVO RICHIEDERE AL MODULO DI INTEROPERABILITA'
                indirizzoRichiesta = connettoreRegistroServizi; //E GLI DEVO INVIARE L'INDIRIZZO DEL REGISTRO
            } else {
                if (!(indirizzoWSDL.toLowerCase()).endsWith("?wsdl")) {
                    indirizzoWSDL = indirizzoWSDL + "?wsdl";
                }
            }
            if (logger.isInfoEnabled()) logger.info("Richiedo le informazioni al registro dei servizi " + indirizzoWSDL);
            try {
                WSRegistroServiziImplService service = new WSRegistroServiziImplService(new URL(indirizzoWSDL));
                IWSRegistroServizi port = service.getWSRegistroServiziImplPort();
                GetSoggettoSPCoop richiesta = new GetSoggettoSPCoop();
                richiesta.setSoggetto(new SoggettoRS(soggettoNICA));
                SoggettoRSRisposta risposta = port.getSoggettoSPCoop(richiesta, indirizzoRichiesta).getReturn();
                connettoreDestinatario = risposta.getPortaDominio();
            } catch (Exception ex) {
                logger.error("Impossibile richiedere l'indirizzo del nica " + soggettoNICA.getTipo() + " - " + soggettoNICA.getNome() + " al registro dei servizi " + connettoreRegistroServizi + ". " + ex);
                throw new FreesbeeException("Impossibile richiedere l'indirizzo del nica " + soggettoNICA.getTipo() + " - " + soggettoNICA.getNome() + " al registro dei servizi " + connettoreRegistroServizi + ". " + ex);
            }
            if (connettoreDestinatario == null || connettoreDestinatario.equals("")) {
                throw new FreesbeeException("Impossibile inoltrare la busta EGov. Nessun connettore specificato per il NICA");
            }
            if (ConfigurazioneStatico.getInstance().isCacheWS()) {
                cacheConnettoreNica.put(chiave, connettoreDestinatario);
            }
            if (logger.isInfoEnabled()) logger.info("E' presente un nica. Inoltro tutto all'indirizzo " + connettoreDestinatario);
            boolean mutuaAutenticazione = soggettoNICA.isMutuaAutenticazione();
            if (logger.isDebugEnabled()) logger.info("Mutua Autenticazione " + mutuaAutenticazione);
            messaggio.setMutuaAutenticazione(mutuaAutenticazione);
            return connettoreDestinatario;
        }
    }

    public ProcessorSbloccaPollingConsumerPortaApplicativa getProcessorSbloccaPollingConsumerPortaApplicativa() {
        return processorSbloccaPollingConsumerPortaApplicativa;
    }

    public void setProcessorSbloccaPollingConsumerPortaApplicativa(ProcessorSbloccaPollingConsumerPortaApplicativa processorSbloccaPollingConsumerPortaApplicativa) {
        this.processorSbloccaPollingConsumerPortaApplicativa = processorSbloccaPollingConsumerPortaApplicativa;
    }

    public ProcessorSbloccaPollingConsumerPortaDelegata getProcessorSbloccaPollingConsumerPortaDelegata() {
        return processorSbloccaPollingConsumerPortaDelegata;
    }

    public void setProcessorSbloccaPollingConsumerPortaDelegata(ProcessorSbloccaPollingConsumerPortaDelegata processorSbloccaPollingConsumerPortaDelegata) {
        this.processorSbloccaPollingConsumerPortaDelegata = processorSbloccaPollingConsumerPortaDelegata;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
