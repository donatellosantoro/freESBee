package it.unibas.freesbeesla;

import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ConfigurazioneErrori {
    
    private static Properties properties = new Properties();
    private Log logger = LogFactory.getLog(ConfigurazioneErrori.class);
    private static ConfigurazioneErrori singleton = new ConfigurazioneErrori();
    
    
    private ConfigurazioneErrori() {
        try {
            InputStream inStream = ConfigurazioneErrori.class.getResourceAsStream("/errori.properties");
            properties.load(inStream);
        }catch(Exception ex) {
            logger.error("Impossibile caricare la configurazione degli errori : " + ex);
        }
    }

    public String trovaDescrizioneErrore(String valore) {
        return properties.getProperty(valore);
    }
    
    public static ConfigurazioneErrori getInstance() {
        return singleton;
    }

}
