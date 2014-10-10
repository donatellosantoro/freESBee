package it.unibas.freesbeesla;

import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigurazioneStatico {

    public static final String FREESBEE = "FREESBEE";
    public static final String NICA = "NICA";
    public static final String NON_VERIFICATO = "NON_VERIFICATO";
    private static Log logger = LogFactory.getLog(ConfigurazioneStatico.class);
    private static ConfigurazioneStatico singleton = new ConfigurazioneStatico();
    private Properties properties = new Properties();
    //Properties
    private String webservicesIndirizzo;
    private String namespaceFreesbe;
    //
    private boolean protezioneSP = false;
    private int threadPool;
    private int threadJettyPoolMin;
    private int threadJettyPoolMax;

    private String tipoNica = FREESBEE;
    private boolean attivo = true;

    public static ConfigurazioneStatico getInstance() {
        return singleton;
    }

    private ConfigurazioneStatico() {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Avvio la configurazione statica");
            }
            InputStream inStream = ConfigurazioneStatico.class.getResourceAsStream("/freesbeesla.properties");
            properties.load(inStream);
            this.webservicesIndirizzo = properties.getProperty("freesbeesla.webservices.indirizzo");
            this.threadPool = Integer.parseInt(properties.getProperty("thread.pool"));
            this.threadJettyPoolMin = Integer.parseInt(properties.getProperty("thread.jetty.pool.min"));
            this.threadJettyPoolMax = Integer.parseInt(properties.getProperty("thread.jetty.pool.max"));
            this.namespaceFreesbe = "targetNamespace=\"" + properties.getProperty("namespace.freesbee") + "\"";
            this.protezioneSP = Boolean.valueOf(properties.getProperty("protezioneSP"));
        } catch (Exception ex) {
            logger.error("Impossibile caricare la configurazione statica " + ex);
            ex.printStackTrace();
        }
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


    public String getNamespaceFreesbe() {
        return namespaceFreesbe;
    }

    public String getTipoNica() {
        return tipoNica;
    }

    public void setTipoNica(String tipoNica) {
        this.tipoNica = tipoNica;
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



}
