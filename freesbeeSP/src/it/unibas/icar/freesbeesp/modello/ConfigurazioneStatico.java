package it.unibas.icar.freesbeesp.modello;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurazioneStatico {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(ConfigurazioneStatico.class);
    private static ConfigurazioneStatico singleton = new ConfigurazioneStatico();
    
    private Properties properties = new Properties();
    private String configDir;

    private String ssoDriver;
    private String ssoLogin;
    private String indirizzoEndpointErogatore;
    
    private String percorsoFileCertificatoSP;
    private String passwordSP;
    private String nomeCertificatoSP;
    
    private String percorsoFileCertificatoIdP;
    private String passwordIdP;
    private String nomeCertificatoIdP;

    private ConfigurazioneStatico() {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Avvio la configurazione statica");
            }
            InputStream inStream = ConfigurazioneStatico.class.getResourceAsStream("/freesbeesp.properties");
            properties.load(inStream);
            
            this.ssoDriver = properties.getProperty("sso.driver");
            this.configDir = properties.getProperty("dir.config");
            this.indirizzoEndpointErogatore = properties.getProperty("indirizzo.endpoint.erogatore");
            this.percorsoFileCertificatoSP = properties.getProperty("certificato.sp.file");
            this.passwordSP = properties.getProperty("certificato.sp.password");
            this.nomeCertificatoSP = properties.getProperty("certificato.sp.nome");
            this.percorsoFileCertificatoIdP = properties.getProperty("certificato.idp.file");
            this.passwordIdP = properties.getProperty("certificato.idp.password");
            this.nomeCertificatoIdP = properties.getProperty("certificato.idp.nome");
            this.ssoLogin = properties.getProperty("sso.login");
        } catch (Exception ex) {
            logger.error("Impossibile caricare il file freesbeesp.properties " + ex);
        }
    }

    public String getIndirizzoErogatore() {
        return indirizzoEndpointErogatore;
    }

    public void setIndirizzoErogatore(String indirizzoErogatore) {
        this.indirizzoEndpointErogatore = indirizzoErogatore;
    }

    public String getNomeCertificatoSP() {
        return nomeCertificatoSP;
    }

    public void setNomeCertificatoSP(String nomeCertificatoSP) {
        this.nomeCertificatoSP = nomeCertificatoSP;
    }

    public String getPasswordSP() {
        return passwordSP;
    }

    public void setPasswordSP(String passwordSP) {
        this.passwordSP = passwordSP;
    }

    public String getPercorsoFileCertificatoSP() {
        return percorsoFileCertificatoSP;
    }

    public void setPercorsoFileCertificatoSP(String percorsoFileCertificatoSP) {
        this.percorsoFileCertificatoSP = percorsoFileCertificatoSP;
    }

    public String getNomeCertificatoIdP() {
        return nomeCertificatoIdP;
    }

    public void setNomeCertificatoIdP(String nomeCertificatoIdP) {
        this.nomeCertificatoIdP = nomeCertificatoIdP;
    }

    public String getPasswordIdP() {
        return passwordIdP;
    }

    public void setPasswordIdP(String passwordIdP) {
        this.passwordIdP = passwordIdP;
    }

    public String getPercorsoFileCertificatoIdP() {
        return percorsoFileCertificatoIdP;
    }

    public void setPercorsoFileCertificatoIdP(String percorsoFileCertificatoIdP) {
        this.percorsoFileCertificatoIdP = percorsoFileCertificatoIdP;
    }

    public String getConfigDir() {
        return configDir;
    }

    public void setConfigDir(String configDir) {
        this.configDir = configDir;
    }

    public String getSsoDriver() {
        return ssoDriver;
    }

    public void setSsoDriver(String ssoDriver) {
        this.ssoDriver = ssoDriver;
    }

    public String getSsoLogin() {
        return ssoLogin;
    }

    public void setSsoLogin(String ssoLogin) {
        this.ssoLogin = ssoLogin;
    }

    public static ConfigurazioneStatico getInstance() {
        return singleton;
    }
}
