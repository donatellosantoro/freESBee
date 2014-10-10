package it.unibas.silvio.web.controllo;

import it.unibas.silvio.web.vista.VistaEseguiIstanza;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;

public class AcquisisciPortafoglio {

    private Log logger = LogFactory.getLog(this.getClass());
    
    public String annulla() {
        return "home";
    }
    
    public String acquisisciPortafoglio() {
        return "eseguiIstanzaOperation";
    }
}
