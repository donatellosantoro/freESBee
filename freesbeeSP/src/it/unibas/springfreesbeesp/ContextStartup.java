package it.unibas.springfreesbeesp;

import it.unibas.icar.freesbeesp.modello.XMLConfigurazione;
import javax.servlet.ServletContextEvent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextStartup implements javax.servlet.ServletContextListener {

    private Log logger = LogFactory.getLog(this.getClass());
    private String applicationContextUri = "camel-context.xml";
    private AbstractApplicationContext applicationContext;
    private CamelContext camelContext;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            applicationContext = new ClassPathXmlApplicationContext(applicationContextUri);
//            applicationContext.start();
            camelContext = SpringCamelContext.springCamelContext(applicationContext);
            camelContext.start();

            XMLConfigurazione xmlConfigurazione = XMLConfigurazione.getInstance();
            xmlConfigurazione.setCamelContext(camelContext);

            JettyHttpComponent component = (JettyHttpComponent) camelContext.getComponent("jetty");
            component.getServer().setStopAtShutdown(true);
            if (logger.isInfoEnabled()) {
                logger.info("freESBeeSP avviato");
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        try {
//            ConfigurazioneStatico config = ConfigurazioneStatico.getInstance();
//            config.setAttivo(false);
//            arrestaServerJettyWS();
            camelContext.stop();
            if (logger.isInfoEnabled()) {
                logger.info("freESBeeSP arrestato");
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

//    private void arrestaServerJettyWS() throws IOException {
//        //applicationContext.stop();
//        Map<Connector, JettyHTTPServerEngine> jettyServer = new HashMap<Connector, JettyHTTPServerEngine>();
//
//        for (SoapHttpsEndpoint endpoint : endpointAvviati) {
//            JettyHTTPDestination jDestination = (JettyHTTPDestination) endpoint.getServer().getDestination();
//            ServerImpl serverImpl = (ServerImpl) endpoint.getServer();
//            JettyHTTPServerEngine httpEngine = (JettyHTTPServerEngine) jDestination.getEngine();
//            httpEngine.getConnector().close();
//            jDestination.shutdown();
//            serverImpl.stop();
//            endpoint.stop();
//            jettyServer.put(httpEngine.getConnector(), httpEngine);
//        }
//        Collection<JettyHTTPServerEngine> jettyServers = jettyServer.values();
//        for (JettyHTTPServerEngine httpEngine : jettyServers) {
//            if (logger.isInfoEnabled()) {
//                logger.info("Arresto il jettyServer sulla porta " + httpEngine.getPort());
//            }
//            httpEngine.shutdown();
//        }
//    }
}
