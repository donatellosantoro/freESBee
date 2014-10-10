package it.unibas.silvio.modello;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Configurazione implements Serializable{

    private static Log logger = LogFactory.getLog(Configurazione.class);
    private static Configurazione singleton = new Configurazione();
    private Properties properties = new Properties();
    private String webservicesPort;
    private String webservicesIndirizzo;
    private String asincronoPort;
    private int threadPool;
    private int threadJettyPoolMin;
    private int threadJettyPoolMax;

    public static Configurazione getInstance() {
        return singleton;
    }

    private Configurazione() {
        try {
            InputStream inStream = Configurazione.class.getResourceAsStream("/silvio.properties");
            properties.load(inStream);
            this.webservicesPort = properties.getProperty("webservices.port");
            this.webservicesIndirizzo = properties.getProperty("webservices.indirizzo");
            this.threadPool = Integer.parseInt(properties.getProperty("thread.pool"));
            this.threadJettyPoolMin = Integer.parseInt(properties.getProperty("thread.jetty.pool.min"));
            this.threadJettyPoolMax = Integer.parseInt(properties.getProperty("thread.jetty.pool.max"));
            this.asincronoPort = properties.getProperty("asincrono.port");
            if (logger.isInfoEnabled()) {
                logger.info("Configurazione statica creata");
            }
        } catch (Exception ex) {
            logger.error("Impossibile caricare la configurazione statica " + ex);
        }
    }

    public String getAsincronoPort() {
        return asincronoPort;
    }

    public void setAsincronoPort(String asincronoPort) {
        this.asincronoPort = asincronoPort;
    }        

    public String getWebservicesPort() {
        return webservicesPort;
    }

    public void setWebservicesPort(String webservicesPort) {
        this.webservicesPort = webservicesPort;
    }

    public int getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(int threadPool) {
        this.threadPool = threadPool;
    }

    public int getThreadJettyPoolMin() {
        return threadJettyPoolMin;
    }

    public void setThreadJettyPoolMin(int threadJettyPoolMin) {
        this.threadJettyPoolMin = threadJettyPoolMin;
    }

    public int getThreadJettyPoolMax() {
        return threadJettyPoolMax;
    }

    public void setThreadJettyPoolMax(int threadJettyPoolMax) {
        this.threadJettyPoolMax = threadJettyPoolMax;
    }

    public String getWebservicesIndirizzo() {
        return webservicesIndirizzo;
    }

    public void setWebservicesIndirizzo(String webservicesIndirizzo) {
        this.webservicesIndirizzo = webservicesIndirizzo;
    }

}
