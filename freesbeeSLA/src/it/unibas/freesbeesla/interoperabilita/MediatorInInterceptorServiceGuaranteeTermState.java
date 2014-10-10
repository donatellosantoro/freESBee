package it.unibas.freesbeesla.interoperabilita;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.Bus;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.endpoint.ServerRegistry;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.interceptor.StaxInInterceptor;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.MessageObserver;

public class MediatorInInterceptorServiceGuaranteeTermState extends AbstractPhaseInterceptor<SoapMessage> {

    private Log logger = LogFactory.getLog(this.getClass());

    public MediatorInInterceptorServiceGuaranteeTermState() {
        super(Phase.POST_STREAM);
        addBefore(StaxInInterceptor.class.getName());
    }

    public void handleMessage(SoapMessage message) {

//        String stringaMessaggio = "";
//
//        Bus bus = CXFBusFactory.getDefaultBus();
//        ServerRegistry serverRegistry = bus.getExtension(ServerRegistry.class);
//        List<Server> servers = serverRegistry.getServers();
//
//        try {
//            //trasformo il message in una stringa per poterlo elaborare
//            InputStream is = message.getContent(InputStream.class);
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//            String line;
//            stringaMessaggio = "";
//            while ((line = rd.readLine()) != null) {
//                stringaMessaggio += line;
//            }
//            rd.close();
//            logger.debug("\nMessaggio ricevuto Interceptor IN: " + stringaMessaggio);
//
//            //ricerco il namespace per verificare se è freesbee             
//            if (!stringaMessaggio.contains("http://sistemamonitoraggio.freesbee.unibas.it/")) {
//                logger.debug("Non è stao trovato il namespace: http://sistemamonitoraggio.freesbee.unibas.it/");
//
//                //creazione del messaggio soap
//                String bodyMessaggio = AdapterNicaToFreesbee.trasformaMessaggioServiceGuaranteeTermState(stringaMessaggio);
//                InputStream inNuovo = new ByteArrayInputStream(bodyMessaggio.getBytes());
//                message.setContent(InputStream.class, inNuovo);
//                logger.debug("\nMessaggio rielaborato Interceptor IN: " + bodyMessaggio);
//
//                //lo mando al ws per il nica openspcoop
//                Server targetServer = null;
//                for (Server server : servers) {
//                    String address = server.getEndpoint().getEndpointInfo().getAddress();
//                    String addressDesitnation = server.getDestination().getAddress().getAddress().getValue();
//                    if (addressDesitnation.equals("/ws/monitoraggio/guaranteetermstate/ServiceGuaranteeTermStateNica")) {
//                        logger.debug("************* TROVATO ******************");
//                        logger.debug("server: " + server.getEndpoint().getEndpointInfo().getName());
//                        logger.debug("address endpoint: " + address);
//                        logger.debug("address destination: " + addressDesitnation);
//                        logger.debug("****************************************");
//                        targetServer = server;
//
//                    }
//                }
//
//                //inoltro ilmessaggio
//                MessageObserver mo = targetServer.getDestination().getMessageObserver();
//                mo.onMessage(message);
//
//                InterceptorChain chain = message.getInterceptorChain();
//                chain.abort();
//
//            } else {
//                logger.debug("E' stato trovato il namespace: http://sistemamonitoraggio.freesbee.unibas.it/");
//
//                //ricopio il messaggio originale
//                InputStream inNuovo = new ByteArrayInputStream(stringaMessaggio.getBytes());
//                message.setContent(InputStream.class, inNuovo);
//
//                //lo mando al ws per il nica freesbee
//                Server targetServer = null;
//                for (Server server : servers) {
//                    String address = server.getEndpoint().getEndpointInfo().getAddress();
//                    String addressDesitnation = server.getDestination().getAddress().getAddress().getValue();
//                    if (addressDesitnation.equals("/ws/monitoraggio/guaranteetermstate/ServiceGuaranteeTermStateFreESBee")) {
//                        logger.debug("************* TROVATO ******************");
//                        logger.debug("server: " + server.getEndpoint().getEndpointInfo().getName());
//                        logger.debug("address endpoint: " + address);
//                        logger.debug("address destination: " + addressDesitnation);
//                        logger.debug("****************************************");
//                        targetServer = server;
//                    }
//                }
//
//                //inoltro ilmessaggio
//                MessageObserver mo = targetServer.getDestination().getMessageObserver();
//                mo.onMessage(message);
//
//                InterceptorChain chain = message.getInterceptorChain();
//                chain.abort();
//
//            }
//        } catch (Exception e) {
//            logger.error("Si è verificato un errore nel proxy dei messaggi in ingresso. ");
//             e.printStackTrace();
//        }

    }
}