package it.unibas.guicefreesbee;

import com.google.inject.Guice;
import com.google.inject.Stage;
import it.unibas.icar.freesbee.consegnabustesoap.HttpConsegnaBusteSOAP;
import it.unibas.icar.freesbee.inoltrobustaegov.ContentBasedRouterRispostaEGov;
import it.unibas.icar.freesbee.inoltrobustaegov.HttpInoltroBustaEGov;
import it.unibas.icar.freesbee.modulocontrollo.imbustamento.*;
import it.unibas.icar.freesbee.modulocontrollo.sbustamento.*;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import it.unibas.icar.freesbee.portaapplicativa.EnricherIdentificatoreErogatore;
import it.unibas.icar.freesbee.portaapplicativa.EnricherPreImbustamentoRisposta;
import it.unibas.icar.freesbee.portaapplicativa.HttpRicezionePortaApplicativa;
import it.unibas.icar.freesbee.portaapplicativa.RispostaAck;
import it.unibas.icar.freesbee.portadelegata.EnricherAccordoServizio;
import it.unibas.icar.freesbee.portadelegata.FiltroAutenticazione;
import it.unibas.icar.freesbee.portadelegata.RichiestaAck;
import it.unibas.icar.freesbee.processors.ProcessorControllaEccezioniEGov;
import it.unibas.icar.freesbee.processors.ProcessorStampaEccezioniEGov;
import it.unibas.icar.freesbee.processors.ProcessorEccezione;
import it.unibas.icar.freesbee.processors.ProcessorEnricher;
import it.unibas.icar.freesbee.processors.ProcessorEnricherRisposta;
import it.unibas.icar.freesbee.processors.ProcessorEnricherTestInteroperabilita;
import it.unibas.icar.freesbee.processors.ProcessorErroreImbustamento;
import it.unibas.icar.freesbee.processors.ProcessorErroreSbustamento;
import it.unibas.icar.freesbee.processors.ProcessorIdentificaNica;
import it.unibas.icar.freesbee.processors.ProcessorIdentificazioneErogatore;
//import it.unibas.icar.freesbee.processors.ProcessorLogFactory;
import it.unibas.icar.freesbee.processors.ProcessorMessaggioDiagnostico;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaApplicativa;
import it.unibas.icar.freesbee.processors.ProcessorSbloccaPollingConsumerPortaDelegata;
//import it.unibas.icar.freesbee.processors.ProcessorTrace;
import it.unibas.icar.freesbee.processors.ProcessorUnWrapper;
import it.unibas.icar.freesbee.processors.ProcessorValidaBustaEGov;
import it.unibas.icar.freesbee.processors.ProcessorValidaXSD;
import it.unibas.icar.freesbee.processors.ProcessorWrapper;
//import it.unibas.icar.freesbee.processors.SOAPProcessorReader;
import it.unibas.icar.freesbee.processors.SOAPProcessorValidaXSD;
//import it.unibas.icar.freesbee.processors.SOAPProcessorWriterFactory;
import it.unibas.icar.freesbee.processors.strategy.EnricherPortaDelegataSemplice;
import it.unibas.icar.freesbee.ws.echoservice.WSEchoService;
import it.unibas.icar.freesbee.ws.logger.WSLogger;
import it.unibas.icar.freesbee.ws.registroservizi.WSRegistroServizi;
import it.unibas.icar.freesbee.ws.registroservizi.WSRegistroServiziProxy;
import it.unibas.icar.freesbee.ws.tracciamento.WSMessaggio;
import it.unibas.icar.freesbee.ws.tracciamentoTest.WSMessaggioTest;
import it.unibas.icar.freesbee.ws.verificainstallazione.WSVerificaInstallazione;
import it.unibas.icar.freesbee.ws.web.*;
import java.util.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.camel.CamelContext;
import org.apache.camel.guice.CamelModuleWithMatchingRoutes;
import org.apache.camel.guice.GuiceCamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.ServerImpl;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.http_jetty.JettyHTTPDestination;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine;
import org.eclipse.jetty.server.Connector;

public class ContextStartup implements ServletContextListener {

    private static Log logger = LogFactory.getLog(ContextStartup.class);
    private CamelContext camelContext;
    private static List<EndpointImpl> endpointAvviati = new ArrayList<EndpointImpl>();
//    private static Map<String, Integer> threadPerClasse = new HashMap<String, Integer>();

    public static void main(String[] args) {
        new ContextStartup().contextInitialized(null);
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            if (logger.isInfoEnabled()) logger.info("Avvio di freESBee in corso...");
            camelContext = new GuiceCamelContext(Guice.createInjector(Stage.PRODUCTION, new FreesbeeModule()));
        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante l'avvio del sistema.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
        }
        if (logger.isInfoEnabled()) logger.info("Avvio completato con successo.");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            arrestaServerJettyWS();
            camelContext.stop();
            DAOUtilHibernate.closeSessionFactory();
        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante l'arresto del sistema.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
        }
        if (logger.isInfoEnabled()) logger.info("freESBee e' stato arrestato correttamente");
    }

    public static synchronized List<EndpointImpl> getEndpointAvviati() {
        return endpointAvviati;
    }

//    public static synchronized void aggiungiThread(String classe) {
//        Integer valore = threadPerClasse.get(classe);
//        if (valore == null) {
//            valore = new Integer(1);
//            threadPerClasse.put(classe, valore);
//        } else {
//            valore = new Integer(valore.intValue() + 1);
//            threadPerClasse.put(classe, valore);
//        }
//    }
    private void arrestaServerJettyWS() throws Exception {
        //applicationContext.stop();
        Map<Connector, JettyHTTPServerEngine> jettyServer = new HashMap<Connector, JettyHTTPServerEngine>();

        for (EndpointImpl endpoint : endpointAvviati) {
            JettyHTTPDestination jDestination = (JettyHTTPDestination) endpoint.getServer().getDestination();
            ServerImpl serverImpl = (ServerImpl) endpoint.getServer();
            JettyHTTPServerEngine httpEngine = (JettyHTTPServerEngine) jDestination.getEngine();
            httpEngine.getConnector().stop();
            jDestination.shutdown();
            serverImpl.stop();
            endpoint.stop();
            jettyServer.put(httpEngine.getConnector(), httpEngine);
        }
        Collection<JettyHTTPServerEngine> jettyServers = jettyServer.values();
        for (JettyHTTPServerEngine httpEngine : jettyServers) {
            if (logger.isInfoEnabled()) logger.info("Si sta arrestando il server jetty sulla porta " + httpEngine.getPort());
            httpEngine.shutdown();
        }

//        System.out.println("##### THREAD AVVIATI ####");
//        for (String classe : threadPerClasse.keySet()) {
//            System.out.println(classe + ": " + threadPerClasse.get(classe));
//        }
    }

    private class FreesbeeModule extends CamelModuleWithMatchingRoutes {

        @Override
        protected void configure() {
            super.configure();
            // PORTA APPLICATIVA
            bind(EnricherIdentificatoreErogatore.class);
            bind(EnricherPreImbustamentoRisposta.class);
            bind(HttpRicezionePortaApplicativa.class);
            bind(RispostaAck.class);
            // PORTA DELEGATA
            bind(EnricherPortaDelegataSemplice.class);
            bind(EnricherAccordoServizio.class);
            bind(FiltroAutenticazione.class);
            bind(RichiestaAck.class);
            // MODULO CONTROLLO INBUSTAMENTO
            bind(ContentBasedRouterImbustamento.class);
            bind(DetourCollaborazioneImbustamento.class);
            bind(EnricherTestInteroperabilita.class);
            bind(EnvelopeWrapperEgovImbustamento.class);
            bind(PreImbustamentoRichiesta.class);
            bind(PreImbustamentoRisposta.class);
            bind(WireTapCollaborazioneImbustamento.class);
            bind(WireTapTracciamentoImbustamento.class);
            // MODULO CONTROLLO SBUSTAMENTO
            bind(ContentBasedRouterSbustamento.class);
            bind(ContentBasedRouterSbustamentoNica.class);
            bind(DetourCollaborazioneSbustamento.class);
            bind(EnvelopeWrapperEgovSbustamento.class);
            bind(EnvelopeWrapperTest.class);
            bind(PreSbustamentoRichiesta.class);
            bind(PreSbustamentoRisposta.class);
            bind(WireTapCollaborazioneSbustamento.class);
            bind(WireTapTracciamentoSbustamento.class);
            // INOLTRO BUSTA E-GOV
            bind(ContentBasedRouterRispostaEGov.class);
            bind(HttpInoltroBustaEGov.class);
            // CONSEGNA BUSTE SOAP
            bind(HttpConsegnaBusteSOAP.class);
            // WS ECHO SERVICE
            bind(WSEchoService.class);
            // WS REGISTRO SERVIZI
            bind(WSRegistroServizi.class);
            bind(WSRegistroServiziProxy.class);
            // WS TRACCIAMENTO
            bind(WSMessaggio.class);
            // WS TRACCIAMENTO TEST
            bind(WSMessaggioTest.class);
            // WS VERIFICA INSTALLAZIONE
            bind(WSVerificaInstallazione.class);
            // WS LOGGER
            bind(WSLogger.class);
            // WS WEB
            bind(WSAccordoServizio.class);
            bind(WSAutenticazione.class);
            bind(WSAzione.class);
            bind(WSConfigurazione.class);
            bind(WSPortaApplicativa.class);
            bind(WSPortaDelegata.class);
            bind(WSProfiloEGov.class);
            bind(WSSecurityInterceptor.class);
            bind(WSServizio.class);
            bind(WSServizioApplicativo.class);
            bind(WSSoggetto.class);

            // PROCESSORS
            bind(ProcessorControllaEccezioniEGov.class);
            bind(ProcessorStampaEccezioniEGov.class);
            bind(ProcessorEccezione.class);
            bind(ProcessorEnricher.class);
            bind(ProcessorEnricherRisposta.class);
            bind(ProcessorEnricherTestInteroperabilita.class);
            bind(ProcessorErroreImbustamento.class);
            bind(ProcessorErroreSbustamento.class);
            bind(ProcessorIdentificaNica.class);
            bind(ProcessorIdentificazioneErogatore.class);
//            bind(ProcessorLogFactory.class);
            bind(ProcessorMessaggioDiagnostico.class);
            bind(ProcessorSbloccaPollingConsumerPortaApplicativa.class);
            bind(ProcessorSbloccaPollingConsumerPortaDelegata.class);
//            bind(ProcessorTrace.class);
            bind(ProcessorUnWrapper.class);
            bind(ProcessorValidaBustaEGov.class);
            bind(ProcessorValidaXSD.class);
            bind(ProcessorWrapper.class);
//            bind(SOAPProcessorReader.class);
            bind(SOAPProcessorValidaXSD.class);
//            bind(SOAPProcessorWriterFactory.class);

            // DB Manager
            bind(DBManager.class);

            //INTERFREESBEE
            bind(it.unibas.icar.interfreesbee.freesbee.registroservizi.WSRegistroServizi.class);

            // SINCRONIZZATORE
            //bind(DemoneSincronizzazione.class);
        }
    }
}