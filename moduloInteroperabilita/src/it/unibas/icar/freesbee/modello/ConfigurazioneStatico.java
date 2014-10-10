package it.unibas.icar.freesbee.modello;

import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigurazioneStatico {

    private static Log logger = LogFactory.getLog(ConfigurazioneStatico.class);
    private static ConfigurazioneStatico singleton = new ConfigurazioneStatico();
    private Properties properties = new Properties();
    private String webservicesPort;
    private String webservicesInteroperabilitaPort;
    private String webservicesIndirizzo;
    private String interoperabilitaRegistroFreesbee;
    private String interoperabilitaRegistroNica;
    private int threadPool;
    private int threadJettyPoolMin;
    private int threadJettyPoolMax;
    private long intervalloSincronizzazione;
    private int intervalloSincronizzazioneTotale;
    private boolean attivo = true;

    public static ConfigurazioneStatico getInstance() {
        return singleton;
    }

    private ConfigurazioneStatico() {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Avvio la configurazione statica");
            }
            InputStream inStream = ConfigurazioneStatico.class.getResourceAsStream("/freesbee.properties");
            properties.load(inStream);
            this.webservicesPort = properties.getProperty("webservices.port");
            this.webservicesInteroperabilitaPort = properties.getProperty("webservices.interoperabilita.port");
            this.webservicesIndirizzo = properties.getProperty("webservices.indirizzo");
            this.interoperabilitaRegistroFreesbee = properties.getProperty("interoperabilita.registro.freesbee");
            this.interoperabilitaRegistroNica = properties.getProperty("interoperabilita.registro.nica");
            this.threadPool = Integer.parseInt(properties.getProperty("thread.pool"));
            this.threadJettyPoolMin = Integer.parseInt(properties.getProperty("thread.jetty.pool.min"));
            this.threadJettyPoolMax = Integer.parseInt(properties.getProperty("thread.jetty.pool.max"));
            this.intervalloSincronizzazione = Long.parseLong(properties.getProperty("sincronizzazione.intervallo"));
            this.intervalloSincronizzazioneTotale = Integer.parseInt(properties.getProperty("sincronizzazioneTotale.intervallo"));
        } catch (Exception ex) {
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

    public String getInteroperabilitaRegistroFreesbee() {
        return interoperabilitaRegistroFreesbee;
    }

    public void setInteroperabilitaRegistroFreesbee(String interoperabilitaRegistroFreesbee) {
        this.interoperabilitaRegistroFreesbee = interoperabilitaRegistroFreesbee;
    }

    public String getInteroperabilitaRegistroNica() {
        return interoperabilitaRegistroNica;
    }

    public void setInteroperabilitaRegistroNica(String interoperabilitaRegistroNica) {
        this.interoperabilitaRegistroNica = interoperabilitaRegistroNica;
    }

    public long getIntervalloSincronizzazione() {
        return intervalloSincronizzazione;
    }

    public void setIntervalloSincronizzazione(long intervalloSincronizzazione) {
        this.intervalloSincronizzazione = intervalloSincronizzazione;
    }

    public int getIntervalloSincronizzazioneTotale() {
        return intervalloSincronizzazioneTotale;
    }

    public void setIntervalloSincronizzazioneTotale(int intervalloSincronizzazioneTotale) {
        this.intervalloSincronizzazioneTotale = intervalloSincronizzazioneTotale;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public String getWebservicesInteroperabilitaPort() {
        return webservicesInteroperabilitaPort;
    }

    public void setWebservicesInteroperabilitaPort(String webservicesInteroperabilitaPort) {
        this.webservicesInteroperabilitaPort = webservicesInteroperabilitaPort;
    }
    
    
    
}
