package it.unibas.icar.freesbeesp.sso.driver;

import it.unibas.icar.freesbeesp.modello.ConfigurazioneStatico;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HtmlLoginFactory {
    
    private static Log logger = LogFactory.getLog(HtmlLoginFactory.class);
    
    private IHtmlLogin singleton;

    public HtmlLoginFactory() {
        String stringaDriver = ConfigurazioneStatico.getInstance().getSsoLogin();
        try {
            singleton = (IHtmlLogin) Class.forName(stringaDriver).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Impossibile caricare il driver " + stringaDriver);
        }
    }
    
    public IHtmlLogin getInstance() {
        return this.singleton;
    }
    
    
    
}
