package it.unibas.icar.freesbee.vista;

import it.unibas.icar.freesbee.modello.Soggetto;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaGestioneSoggetti implements Serializable{

    private Log logger = LogFactory.getLog(this.getClass());
    private Soggetto nuovoSoggetto = new Soggetto();
    private transient UIData tabellaSoggetti;
    private List<Soggetto> listaSoggetti;
    private boolean inserisci;
    private boolean modifica;
    private boolean elimina;
    private boolean soloGestione;
    private boolean pannelloVisibile = true;
    private UITabPanel tabPanel;
    
    public VistaGestioneSoggetti() {
        this.inserisci = false;
        this.modifica = true;
        this.elimina = true;
    }
    
    public Soggetto getNuovoSoggetto() {
        return nuovoSoggetto;
    }

    public void setNuovoSoggetto(Soggetto nuovoSoggetto) {
        this.nuovoSoggetto = nuovoSoggetto;
    }

    public UIData getTabellaSoggetti() {
        return tabellaSoggetti;
    }

    public void setTabellaSoggetti(UIData tabellaSoggetti) {
        this.tabellaSoggetti = tabellaSoggetti;
    }

    public List<Soggetto> getListaSoggetti() {
        return listaSoggetti;
    }

    public void setListaSoggetti(List<Soggetto> listaSoggetti) {
        this.listaSoggetti = listaSoggetti;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        if(elimina){
            soloGestione = false;
            setPannelloVisibile(true);
        }
        this.elimina = elimina;
    }

    public boolean isInserisci() {
        return inserisci;
    }

    public void setInserisci(boolean inserisci) {
        if(inserisci){
            soloGestione = false;
            setPannelloVisibile(true);
        }
        this.inserisci = inserisci;
    }

    public boolean isModifica() {
        return modifica;
    }

    public void setModifica(boolean modifica) {
        if(modifica){
            soloGestione = false;
        }
        this.modifica = modifica;
    }

    public boolean isSoloGestione() {
        return soloGestione;
    }

    public void setSoloGestione(boolean soloGestione) {
        if (soloGestione == true) {
            this.elimina = false;
            this.inserisci = false;
            this.modifica = false;
        }
        this.soloGestione = soloGestione;
    }

    public boolean isPannelloVisibile() {
        return pannelloVisibile;
    }

    public void setPannelloVisibile(boolean pannelloVisibile) {
        this.pannelloVisibile = pannelloVisibile;
    }

    public UITabPanel getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(UITabPanel tabPanel) {
        this.tabPanel = tabPanel;
    }
}
