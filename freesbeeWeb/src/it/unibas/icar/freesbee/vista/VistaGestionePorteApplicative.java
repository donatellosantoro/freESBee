package it.unibas.icar.freesbee.vista;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaGestionePorteApplicative implements Serializable{
    
    private Log logger = LogFactory.getLog(this.getClass());
    private PortaApplicativa nuovoPortaApplicativa = new PortaApplicativa();
    private transient UIData tabellaPorteApplicative;
    private UITabPanel tabPanel;
    private List<PortaApplicativa> listaPorteApplicative;
    private List<SelectItem> listaServizi = new ArrayList<SelectItem>();
    private List<SelectItem> listaServiziApplicativi = new ArrayList<SelectItem>();
    private List<SelectItem> listaAzioni = new ArrayList<SelectItem>();
    private boolean elimina;
    private boolean inserisci;
    private boolean modifica;
    private boolean soloGestione;
    private boolean pannelloVisibile = true;
    private boolean nicaPresente;
    
    public VistaGestionePorteApplicative() {
        this.inserisci = true;
        this.modifica = false;
        this.elimina = false;
    }

    public PortaApplicativa getNuovoPortaApplicativa() {
        return nuovoPortaApplicativa;
    }

    public void setNuovoPortaApplicativa(PortaApplicativa nuovoPortaApplicativa) {
        this.nuovoPortaApplicativa = nuovoPortaApplicativa;
    }

    public UIData getTabellaPorteApplicative() {
        return tabellaPorteApplicative;
    }

    public void setTabellaPorteApplicative(UIData tabellaPorteApplicative) {
        this.tabellaPorteApplicative = tabellaPorteApplicative;
    }

    public UITabPanel getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(UITabPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    public List<PortaApplicativa> getListaPorteApplicative() {
        return listaPorteApplicative;
    }

    public void setListaPorteApplicative(List<PortaApplicativa> listaPorteApplicative) {
        this.listaPorteApplicative = listaPorteApplicative;
    }

    public List<SelectItem> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<Servizio> lista) {
        this.listaServizi = new ArrayList<SelectItem>();
        this.listaServizi.add(new SelectItem(""));
        if(lista==null){
            return;
        }
        Collections.sort(lista);
        for (Servizio servizio : lista) {
            listaServizi.add(new SelectItem(servizio.getId(),servizio.getNomeBreve()));
        }
    }

    public List<SelectItem> getListaServiziApplicativi() {
        return listaServiziApplicativi;
    }

    public void setListaServiziApplicativi(List<ServizioApplicativo> lista) {
        this.listaServiziApplicativi = new ArrayList<SelectItem>();
        this.listaServiziApplicativi.add(new SelectItem(""));
        if(lista==null){
            return;
        }
        Collections.sort(lista);
        for (ServizioApplicativo servizioApplicativo : lista) {
            listaServiziApplicativi.add(new SelectItem(servizioApplicativo.getId(),servizioApplicativo.getNome()));
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

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
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

    public boolean isNicaPresente() {
        return nicaPresente;
    }

    public void setNicaPresente(boolean nicaPresente) {
        this.nicaPresente = nicaPresente;
    }
    

}
