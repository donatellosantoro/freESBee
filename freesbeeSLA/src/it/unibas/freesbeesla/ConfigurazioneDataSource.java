package it.unibas.freesbeesla;

import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigurazioneDataSource {

    private static Log logger = LogFactory.getLog(ConfigurazioneDataSource.class);
    private static ConfigurazioneDataSource singleton = new ConfigurazioneDataSource();
    private Properties properties = new Properties();
    private String username;
    private String password;
    private String driver;
    private String url;

    public static ConfigurazioneDataSource getInstance() {
        return singleton;
    }

    private ConfigurazioneDataSource() {
        try {
            properties = new Properties();
            InputStream inStream = ConfigurazioneStatico.class.getResourceAsStream("/torque-runtime.properties");
            properties.load(inStream);
            username = properties.getProperty("torque.dsfactory.freesbee_sla_gestionegare.connection.user");
            password = properties.getProperty("torque.dsfactory.freesbee_sla_gestionegare.connection.password");
            driver = properties.getProperty("torque.dsfactory.freesbee_sla_gestionegare.connection.driver");
            url = properties.getProperty("torque.dsfactory.freesbee_sla_gestionegare.connection.url");
        } catch (Exception ex) {
            logger.error("Impossibile caricare la configurazione per la DataSource " + ex);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
