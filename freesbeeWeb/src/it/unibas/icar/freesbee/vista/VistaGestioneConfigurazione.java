package it.unibas.icar.freesbee.vista;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VistaGestioneConfigurazione implements Serializable{

    private Log logger = LogFactory.getLog(this.getClass());
    private Configurazione configurazione = new Configurazione();
    private IDAOSoggetto daoSoggetto;
    private List<SelectItem> listaSoggettiErogatoreRegistroServizi = new ArrayList<SelectItem>();
    private List<SelectItem> listaSoggettiErogatoreNICA = new ArrayList<SelectItem>();
    private boolean inserisci;
    private boolean modifica;
    private boolean pannelloVisibile = true;
    private boolean inviaANICAChecked;
    private boolean NICAChecked;
    private boolean freesbeeChecked;

    public boolean isInserisci() {
        return inserisci;
    }

    public void setInserisci(boolean inserisci) {
        this.inserisci = inserisci;
    }

    public boolean isModifica() {
        return modifica;
    }

    public void setModifica(boolean modifica) {
        this.modifica = modifica;
    }

    public boolean isPannelloVisibile() {
        return pannelloVisibile;
    }

    public void setPannelloVisibile(boolean pannelloVisibile) {
        this.pannelloVisibile = pannelloVisibile;
    }

    public Configurazione getConfigurazione() {
        return configurazione;
    }

    public void setConfigurazione(Configurazione configurazione) {
        this.configurazione = configurazione;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public boolean isinviaANICAChecked() {
        return inviaANICAChecked;
    }

    public void setinviaANICAChecked(boolean inviaANICAChecked) {
        this.inviaANICAChecked = inviaANICAChecked;
    }

    public boolean isNICAChecked() {
        return NICAChecked;
    }

    public void setNICAChecked(boolean NICAChecked) {
        this.NICAChecked = NICAChecked;
    }

    public List<SelectItem> getListaSoggettiErogatoreRegistroServizi() {
        return this.listaSoggettiErogatoreRegistroServizi;
    }

    public void setListaSoggettiErogatoreRegistroServizi(List<SelectItem> listaSoggettiErogatoreRegistroServizi) {
        this.listaSoggettiErogatoreRegistroServizi = listaSoggettiErogatoreRegistroServizi;
    }

    public List<SelectItem> getListaSoggettiErogatoreNICA() {
        return listaSoggettiErogatoreNICA;
    }

    public void setListaSoggettiErogatoreNICA(List<SelectItem> listaSoggettiErogatoreNICA) {
        this.listaSoggettiErogatoreNICA = listaSoggettiErogatoreNICA;
    }

    public boolean isFreesbeeChecked() {
        return freesbeeChecked;
    }

    public void setFreesbeeChecked(boolean freesbeeChecked) {
        this.freesbeeChecked = freesbeeChecked;
    }
}
