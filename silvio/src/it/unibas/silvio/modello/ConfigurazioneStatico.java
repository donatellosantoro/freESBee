package it.unibas.silvio.modello;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigurazioneStatico implements Serializable {

    private static Log logger = LogFactory.getLog(ConfigurazioneStatico.class);
    private static ConfigurazioneStatico singleton = new ConfigurazioneStatico();
    private Properties properties = new Properties();
    private String webservicesPort;
    private String webservicesIndirizzo;
    private String asincronoPort;
    private int threadPool;
    private int threadJettyPoolMin;
    private int threadJettyPoolMax;
    private boolean attivo = true;
//    private String freesbeeSPHost;
//    private int freesbeeSPPort;
//    private String freesbeeSPUri;
    private String freesbeeSPURL;
    private String indirizzoMailMittente;
    private String nomeServerMail;
    private String indirizzoServerMail;
    private String mailcontent;

    private ConfigurazioneStatico() {
        try {
            InputStream inStream = ConfigurazioneStatico.class.getResourceAsStream("/silvio.properties");
            properties.load(inStream);
            this.webservicesPort = properties.getProperty("webservices.port");
            this.webservicesIndirizzo = properties.getProperty("webservices.indirizzo");
            this.threadPool = Integer.parseInt(properties.getProperty("thread.pool"));
            this.threadJettyPoolMin = Integer.parseInt(properties.getProperty("thread.jetty.pool.min"));
            this.threadJettyPoolMax = Integer.parseInt(properties.getProperty("thread.jetty.pool.max"));
            this.asincronoPort = properties.getProperty("asincrono.port");
//            this.freesbeeSPHost = properties.getProperty("freesbeesp.host");
//            this.freesbeeSPPort = Integer.parseInt(properties.getProperty("freesbeesp.port"));
//            this.freesbeeSPUri = properties.getProperty("freesbeesp.uri");
            this.freesbeeSPURL = properties.getProperty("freesbeesp.url");
            this.indirizzoMailMittente = properties.getProperty("mail.mittente");
            this.nomeServerMail = properties.getProperty("mail.nomeServer");
            this.indirizzoServerMail = properties.getProperty("mail.indirizzoServer");
            this.mailcontent = properties.getProperty("mail.content");
            if (logger.isInfoEnabled()) {
                logger.info("Configurazione statica creata");
            }
        } catch (Exception ex) {
            logger.error("Impossibile caricare la configurazione statica " + ex);
        }
    }

    public String getMailcontent() {
        return mailcontent;
    }

    public void setMailcontent(String mailcontent) {
        this.mailcontent = mailcontent;
    }

    public String getIndirizzoMailMittente() {
        return indirizzoMailMittente;
    }

    public void setIndirizzoMailMittente(String indirizzoMailMittente) {
        this.indirizzoMailMittente = indirizzoMailMittente;
    }

    public String getIndirizzoServerMail() {
        return indirizzoServerMail;
    }

    public void setIndirizzoServerMail(String indirizzoServerMail) {
        this.indirizzoServerMail = indirizzoServerMail;
    }

    public String getNomeServerMail() {
        return nomeServerMail;
    }

    public void setNomeServerMail(String nomeServerMail) {
        this.nomeServerMail = nomeServerMail;
    }

    public static ConfigurazioneStatico getInstance() {
        return singleton;
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

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

//    public String getFreesbeeSPHost() {
//        return freesbeeSPHost;
//    }
//
//    public void setFreesbeeSPHost(String freesbeeSPHost) {
//        this.freesbeeSPHost = freesbeeSPHost;
//    }
//
//    public int getFreesbeeSPPort() {
//        return freesbeeSPPort;
//    }
//
//    public void setFreesbeeSPPort(int freesbeeSPPort) {
//        this.freesbeeSPPort = freesbeeSPPort;
//    }
//
//    public String getFreesbeeSPUri() {
//        return freesbeeSPUri;
//    }
//
//    public void setFreesbeeSPUri(String freesbeeSPUri) {
//        this.freesbeeSPUri = freesbeeSPUri;
//    }
    public String getFreesbeeSPURL() {
        return freesbeeSPURL;
    }

    public void setFreesbeeSPURL(String freesbeeSPURL) {
        this.freesbeeSPURL = freesbeeSPURL;
    }
}
