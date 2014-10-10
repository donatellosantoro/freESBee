package it.unibas.icar.freesbeesp.sso.driver;

import it.unibas.icar.freesbeesp.modello.ConfigurazioneStatico;

public class SSODriverFactory {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(SSODriverFactory.class);
    private ISSODriver singleton;

    public SSODriverFactory() {
        String stringaDriver = ConfigurazioneStatico.getInstance().getSsoDriver();
        try {
            singleton = (ISSODriver) Class.forName(stringaDriver).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile caricare il driver " + stringaDriver);
        }
    }

    public ISSODriver getIstance() {
        return singleton;
    }
}
