package it.unibas.freesbee.ge.web.vista;

import it.unibas.freesbee.ge.web.ws.stub.DatiConfigurazione;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VistaConfigurazioneSP {

    private Log logger = LogFactory.getLog(this.getClass());
    private DatiConfigurazione datiConfigurazione = new DatiConfigurazione();
    private boolean attivaModificaSP = false;

    public DatiConfigurazione getDatiConfigurazione() {
        return datiConfigurazione;
    }

    public void setDatiConfigurazione(DatiConfigurazione datiConfigurazione) {
        this.datiConfigurazione = datiConfigurazione;
    }

    public boolean isAttivaModificaSP() {
        return attivaModificaSP;
    }

    public void setAttivaModificaSP(boolean attivaModificaSP) {
        this.attivaModificaSP = attivaModificaSP;
    }

   

}
