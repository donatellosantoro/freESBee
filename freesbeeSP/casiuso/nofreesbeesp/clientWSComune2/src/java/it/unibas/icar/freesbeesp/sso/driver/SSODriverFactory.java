package it.unibas.icar.freesbeesp.sso.driver;

public class SSODriverFactory {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(SSODriverFactory.class);
    private ISSODriver singleton;

    public SSODriverFactory() {
        String stringaDriver = "it.unibas.icar.freesbeesp.sso.driver.shibboleth.ShibbolethDriver";
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
