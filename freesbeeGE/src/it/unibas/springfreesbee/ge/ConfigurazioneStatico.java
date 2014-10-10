package it.unibas.springfreesbee.ge;

import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigurazioneStatico {

    private static Log logger = LogFactory.getLog(ConfigurazioneStatico.class);
    private static ConfigurazioneStatico singleton = new ConfigurazioneStatico();
    private Properties properties = new Properties();
    private String webservicesPort;
    private String webservicesPortGE;
    private String webservicesPortGestori;
    private String webservicesIndirizzo;
    private int threadPool;
    private int threadJettyPoolMin;
    private int threadJettyPoolMax;
    private boolean attivo = true;
    private boolean protezioneSP = false;
    private String protocollo = "http";

    public static ConfigurazioneStatico getInstance() {
        return singleton;
    }

    private ConfigurazioneStatico() {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Avvio la configurazione statica");
            }
            InputStream inStream = ConfigurazioneStatico.class.getResourceAsStream("/freesbeege.properties");
            properties.load(inStream);
            this.webservicesPort = properties.getProperty("webservices.port");
//            this.webservicesPortGE = properties.getProperty("webservices.port.ge");
            this.webservicesPortGestori = properties.getProperty("webservices.port.gestori");
            this.webservicesIndirizzo = properties.getProperty("webservices.indirizzo");
            this.threadPool = Integer.parseInt(properties.getProperty("thread.pool"));
            this.threadJettyPoolMin = Integer.parseInt(properties.getProperty("thread.jetty.pool.min"));
            this.threadJettyPoolMax = Integer.parseInt(properties.getProperty("thread.jetty.pool.max"));
            this.protezioneSP = Boolean.valueOf(properties.getProperty("protezioneSP"));
            this.protocollo = properties.getProperty("protocollo").trim();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile caricare la configurazione statica " + ex);
        }
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

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public boolean isProtezioneSP() {
        return protezioneSP;
    }

    public void setProtezioneSP(boolean protezioneSP) {
        this.protezioneSP = protezioneSP;
    }


    public String getWebservicesPortGE() {
        return webservicesPortGE;
    }


    public void setWebservicesPortGE(String webservicesPortGE) {
        this.webservicesPortGE = webservicesPortGE;
    }


    public String getWebservicesPortGestori() {
        return webservicesPortGestori;
    }


    public void setWebservicesPortGestori(String webservicesPortGestori) {
        this.webservicesPortGestori = webservicesPortGestori;
    }

    public String getProtocollo() {
        return protocollo;
    }

    public void setProtocollo(String protocollo) {
        this.protocollo = protocollo;
    }
}

