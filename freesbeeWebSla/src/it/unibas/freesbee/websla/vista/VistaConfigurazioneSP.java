package it.unibas.freesbee.websla.vista;

import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VistaConfigurazioneSP {

    private Log logger = LogFactory.getLog(this.getClass());
    private DatiConfigurazione datiConfigurazione = new DatiConfigurazione();


    public DatiConfigurazione getDatiConfigurazione() {
        return datiConfigurazione;
    }

    public void setDatiConfigurazione(DatiConfigurazione datiConfigurazione) {
        this.datiConfigurazione = datiConfigurazione;
    }
}
