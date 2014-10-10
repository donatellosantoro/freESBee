package it.unibas.freesbee.ge.web.vista;

import it.unibas.freesbee.ge.web.utilita.Utilita;
import it.unibas.freesbee.ge.web.ws.stub.DatiConfigurazione;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VistaConfigurazione {

    private Log logger = LogFactory.getLog(this.getClass());
    private DatiConfigurazione datiConfigurazione = new DatiConfigurazione();
    private boolean attivaModificaGE = false;

    public String getScadenza() {
        int ss = datiConfigurazione.getConfigurazione().getScadenzaMessaggi();


        return Utilita.convertiSeconditoGiorni(ss) + " gg.";
    }

    public DatiConfigurazione getDatiConfigurazione() {
        return datiConfigurazione;
    }

    public void setDatiConfigurazione(DatiConfigurazione datiConfigurazione) {
        this.datiConfigurazione = datiConfigurazione;
    }

    public boolean isAttivaModificaGE() {
        return attivaModificaGE;
    }

    public void setAttivaModificaGE(boolean attivaModificaGE) {
        this.attivaModificaGE = attivaModificaGE;
    }
   
}
