package it.unibas.icar.freesbee.modello;

import it.unibas.icar.freesbee.xml.ValidatoreXSDBustaEGov;
import it.unibas.icar.freesbee.xml.XmlException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

public class ConfigurazioneStatico {

    private static Log logger = LogFactory.getLog(ConfigurazioneStatico.class);
    private static ConfigurazioneStatico singleton = new ConfigurazioneStatico();
    private Properties properties = new Properties();
    private String webservicesPort;
    private String webservicesInteroperabilitaPort;
    private String webservicesIndirizzo;
    private String interoperabilitaRegistroFreesbee;
    private String interoperabilitaRegistroNica;
    private String percorsoFile;
    private String freesbeeVersion;
    private int camelThreadPool;
    private int camelThreadPoolMax;
    private int threadJettyPoolMin;
    private int threadJettyPoolMax;
    private long intervalloSincronizzazione;
    private int intervalloSincronizzazioneTotale;
    private boolean attivo = true;
    private boolean tracciaFile = false;
    private int concurrentConsumer;
    private boolean cacheDB = true;
    private boolean cacheWS = true;
    private Schema xmlSchema;

    public static ConfigurazioneStatico getInstance() {
        return singleton;
    }

    private ConfigurazioneStatico() {
        try {
            if (logger.isInfoEnabled())
                logger.info("Avvio la configurazione statica");
            InputStream inStream = ConfigurazioneStatico.class.getResourceAsStream("/freesbee.properties");
            properties.load(inStream);
            this.webservicesPort = properties.getProperty("webservices.port");
            this.webservicesInteroperabilitaPort = properties.getProperty("webservices.interoperabilita.port");
            this.webservicesIndirizzo = properties.getProperty("webservices.indirizzo");
            this.interoperabilitaRegistroFreesbee = properties.getProperty("interoperabilita.registro.freesbee");
            this.interoperabilitaRegistroNica = properties.getProperty("interoperabilita.registro.nica");
            this.camelThreadPool = Integer.parseInt(properties.getProperty("camel.thread.pool"));
            this.camelThreadPoolMax = Integer.parseInt(properties.getProperty("camel.thread.pool.max"));
            this.threadJettyPoolMin = Integer.parseInt(properties.getProperty("thread.jetty.pool.min"));
            this.threadJettyPoolMax = Integer.parseInt(properties.getProperty("thread.jetty.pool.max"));
            this.intervalloSincronizzazione = Long.parseLong(properties.getProperty("sincronizzazione.intervallo"));
            this.intervalloSincronizzazioneTotale = Integer.parseInt(properties.getProperty("sincronizzazioneTotale.intervallo"));
            this.percorsoFile = properties.getProperty("file.percorso");
            String tracciaAttiva = properties.getProperty("file.traccia");
            if (tracciaAttiva.equalsIgnoreCase("true")) {
                this.tracciaFile = true;
            } else {
                this.tracciaFile = false;
            }
            String cacheDBString = properties.getProperty("cache.dbms");
            if (cacheDBString != null) {
                this.cacheDB = Boolean.parseBoolean(cacheDBString);
            }
            String cacheWSString = properties.getProperty("cache.ws");
            if (cacheWSString != null) {
                this.cacheWS = Boolean.parseBoolean(cacheWSString);
            }
            this.concurrentConsumer = Integer.parseInt(properties.getProperty("concurrentConsumer"));
            inStream = ConfigurazioneStatico.class.getResourceAsStream("/build.properties");
            properties.load(inStream);
            String majorVersion = properties.getProperty("major.version");
            String minorVersion = properties.getProperty("minor.version");
            String buildNumber = properties.getProperty("build.number");
            this.freesbeeVersion = majorVersion + "." + minorVersion + "." + buildNumber;
            caricaValidatore();
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

    public int getCamelThreadPool() {
        return camelThreadPool;
    }

    public void setCamelThreadPool(int camelThreadPool) {
        this.camelThreadPool = camelThreadPool;
    }

    public int getCamelThreadPoolMax() {
        return camelThreadPoolMax;
    }

    public void setCamelThreadPoolMax(int camelThreadPoolMax) {
        this.camelThreadPoolMax = camelThreadPoolMax;
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

    public String getPercorsoFile() {
        return percorsoFile;
    }

    public void setPercorsoFile(String percorsoFile) {
        this.percorsoFile = percorsoFile;
    }

    public boolean isTracciaFile() {
        return tracciaFile;
    }

    public void setTracciaFile(boolean tracciaFile) {
        this.tracciaFile = tracciaFile;
    }

    public String getFreesbeeVersion() {
        return freesbeeVersion;
    }

    public void setFreesbeeVersion(String freesbeeVersion) {
        this.freesbeeVersion = freesbeeVersion;
    }

    public int getConcurrentConsumer() {
        return concurrentConsumer;
    }

    public void setConcurrentConsumer(int concurrentConsumer) {
        this.concurrentConsumer = concurrentConsumer;
    }

    public boolean isCacheDB() {
        return cacheDB;
    }

    public void setCacheDB(boolean cacheDB) {
        this.cacheDB = cacheDB;
    }

    public boolean isCacheWS() {
        return cacheWS;
    }

    public void setCacheWS(boolean cacheWS) {
        this.cacheWS = cacheWS;
    }

    private void caricaValidatore() throws XmlException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        InputStream streamXSD = ValidatoreXSDBustaEGov.class.getResourceAsStream("/IntestazioniEGov.xsd");
        if (streamXSD == null) {
            logger.error("Impossibile caricare il file IntestazioniEGov.xsd");
            throw new XmlException("Impossibile caricare il file IntestazioniEGov.xsd");
        }
        StreamSource ss = new StreamSource(streamXSD);
        Schema xmlSchema;
        try {
            xmlSchema = schemaFactory.newSchema(ss);
        } catch (SAXException ex) {
            if (logger.isDebugEnabled())
                ex.printStackTrace();
            logger.error("Il file IntestazioniEGov.xsd non è valido. " + ex.getLocalizedMessage());
            throw new XmlException("Il file IntestazioniEGov.xsd non è valido.");
        }
        this.xmlSchema = xmlSchema;
    }

    public Validator getValidatore() {
        return this.xmlSchema.newValidator();
    }
}
