package it.unibas.silvio.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.ServerImpl;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.http_jetty.JettyHTTPDestination;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine;
import org.mortbay.jetty.Connector;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextStartup implements javax.servlet.ServletContextListener {

    private Log logger = LogFactory.getLog(this.getClass());
    private String applicationContextUri = "camel-context.xml";
    private AbstractApplicationContext applicationContext;
    private CamelContext camelContext;
    private static List<EndpointImpl> endpointAvviati = new ArrayList<EndpointImpl>();

    public void contextInitialized(ServletContextEvent arg0) {
        try {
            applicationContext = new ClassPathXmlApplicationContext(applicationContextUri);
            //applicationContext.start();
            camelContext = SpringCamelContext.springCamelContext(applicationContext);
            camelContext.start();
            JettyHttpComponent component = (JettyHttpComponent) camelContext.getComponent("jetty");
            //TODO: Versione 2.2.0 non ha questo metodo
//            component.getServer().setStopAtShutdown(true);

            logger.info("SIL-VIO 2.0 avviato");
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            arrestaServerJettyWS();
            camelContext.stop();

            logger.info("SIL-VIO 2.0 fermato");
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public static synchronized List<EndpointImpl> getEndpointAvviati() {
        return endpointAvviati;
    }

    private void arrestaServerJettyWS() throws IOException {
        //applicationContext.stop();
        Map<Connector, JettyHTTPServerEngine> jettyServer = new HashMap<Connector, JettyHTTPServerEngine>();

        for (EndpointImpl endpoint : endpointAvviati) {
            JettyHTTPDestination jDestination = (JettyHTTPDestination) endpoint.getServer().getDestination();
            ServerImpl serverImpl = (ServerImpl) endpoint.getServer();
            JettyHTTPServerEngine httpEngine = (JettyHTTPServerEngine) jDestination.getEngine();
            httpEngine.getConnector().close();
            jDestination.shutdown();
            serverImpl.stop();
            endpoint.stop();
            jettyServer.put(httpEngine.getConnector(), httpEngine);
        }
        Collection<JettyHTTPServerEngine> jettyServers = jettyServer.values();
        for (JettyHTTPServerEngine httpEngine : jettyServers) {
            if (logger.isInfoEnabled()) {
                logger.info("Arresto il jettyServer sulla porta " + httpEngine.getPort());
            }
            httpEngine.shutdown();
        }
    }
}
