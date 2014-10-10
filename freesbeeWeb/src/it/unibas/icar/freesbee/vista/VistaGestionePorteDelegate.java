package it.unibas.icar.freesbee.vista;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaGestionePorteDelegate implements Serializable{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private PortaDelegata nuovoPortaDelegata = new PortaDelegata();
    private transient UIData tabellaPorteDelegate;
    private UITabPanel tabPanel;
    private List<PortaDelegata> listaPorteDelegate;
    private List<SelectItem> listaSoggetti = new ArrayList<SelectItem>();
    private List<SelectItem> listaServizi = new ArrayList<SelectItem>();
    private List<SelectItem> listaAzioni = new ArrayList<SelectItem>();
    private IDAOSoggetto daoSoggetto;
    private boolean elimina;
    private boolean inserisci;
    private boolean modifica;
    private boolean soloGestione;
    private boolean pannelloVisibile = true;
    private boolean nicaPresente;
    
    public VistaGestionePorteDelegate() {
        this.inserisci = false;
        this.modifica = true;
        this.elimina = true;
    }
    
    public PortaDelegata getNuovoPortaDelegata() {
        return nuovoPortaDelegata;
    }

    public void setNuovoPortaDelegata(PortaDelegata nuovoPortaDelegata) {
        this.nuovoPortaDelegata = nuovoPortaDelegata;
    }

    public UIData getTabellaPorteDelegate() {
        return tabellaPorteDelegate;
    }

    public void setTabellaPorteDelegate(UIData tabellaPorteDelegate) {
        this.tabellaPorteDelegate = tabellaPorteDelegate;
    }

    public List<PortaDelegata> getListaPorteDelegate() {
        return listaPorteDelegate;
    }

    public void setListaPorteDelegate(List<PortaDelegata> listaPorteDelegate) {
        this.listaPorteDelegate = listaPorteDelegate;
    }

    public List<SelectItem> getListaSoggetti() {
        return listaSoggetti;
    }

    public boolean isNicaPresente() {
        return nicaPresente;
    }

    public void setNicaPresente(boolean nicaPresente) {
        this.nicaPresente = nicaPresente;
    }

    public void setListaSoggetti(List<Soggetto> lista) {
        this.listaSoggetti = new ArrayList<SelectItem>();
        listaSoggetti.add(new SelectItem(""));
        if(lista==null){
            return;
        }
        Collections.sort(lista);
        for (Soggetto soggetto : lista) {
            listaSoggetti.add(new SelectItem(soggetto.getId(),soggetto.getNomeBreve()));
        }
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        if(elimina){
            this.pannelloVisibile = true;
        }
        this.elimina = elimina;
    }

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
        if(modifica){
            this.pannelloVisibile = true;
        }
        this.modifica = modifica;
    }

    public boolean isSoloGestione() {
        return soloGestione;
    }

    public void setSoloGestione(boolean soloGestione) {
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

    public List<SelectItem> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<Servizio> lista) {        
        this.listaServizi = new ArrayList<SelectItem>();
        listaServizi.add(new SelectItem(""));
        if(lista==null){
            return;
        }
        Collections.sort(lista);
        for (Servizio servizio : lista) {
            listaServizi.add(new SelectItem(servizio.getId(),servizio.getNomeBreve()));
        }
    }

    public List<SelectItem> getListaAzioni() {
        return listaAzioni;
    }

    public void setListaAzioni(List<Azione> lista) {
        this.listaAzioni = new ArrayList<SelectItem>();
        listaAzioni.add(new SelectItem(-1,""));
        if(lista==null){
            return;
        }
        Collections.sort(lista);
        for (Azione azione : lista) {
            listaAzioni.add(new SelectItem(azione.getId(),azione.getNome()));
        }
    }


}
