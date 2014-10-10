package it.unibas.icar.freesbee.vista;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.ProfiloEGov;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIInput;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UIDataTable;
import org.richfaces.component.UITabPanel;

public class VistaGestioneAccordiServizio implements Serializable{

    private Log logger = LogFactory.getLog(this.getClass());
    private AccordoServizio nuovoAccordoServizio = new AccordoServizio();
    private AccordoServizio accordoServizioEliminare = new AccordoServizio();
    private AccordoServizio policyAccordoServizio = new AccordoServizio();
    private Azione azioneAggiungere = new Azione();
    private ProfiloEGov profiloAggiungere = new ProfiloEGov();
    private List<SelectItem> listaProfiliCollaborazione = new ArrayList<SelectItem>();
    private boolean inserisci;
    private boolean modifica;
    private boolean elimina;
    private boolean inserisciAzione;
    private boolean soloGestione;
    private boolean pannelloVisibile = false;
    private boolean profiloEgovSpecifico;
    private UIInput accordoSelezionato;
    private List<AccordoServizio> listaAccordi;
    private UITabPanel tabPanel;
    private UIDataTable tabellaAccordi;
    private UIDataTable tabellaAzioni;
    private boolean nicaPresente;
    private boolean visualizzaPolicy;
    
    public VistaGestioneAccordiServizio() {
        this.inserisci = false;
        this.modifica = true;
        this.elimina = true;
        this.listaProfiliCollaborazione.add(new SelectItem(AccordoServizio.PROFILO_ONEWAY, AccordoServizio.convertiNomeProfilo(AccordoServizio.PROFILO_ONEWAY)));
        this.listaProfiliCollaborazione.add(new SelectItem(AccordoServizio.PROFILO_SINCRONO, AccordoServizio.convertiNomeProfilo(AccordoServizio.PROFILO_SINCRONO)));
        this.listaProfiliCollaborazione.add(new SelectItem(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO, AccordoServizio.convertiNomeProfilo(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO)));
        this.listaProfiliCollaborazione.add(new SelectItem(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO, AccordoServizio.convertiNomeProfilo(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO)));
    }

    public void mostraPannello() {
        pannelloVisibile = false;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        if (elimina) {
            soloGestione = false;
        }
        this.elimina = elimina;
    }

    public boolean isInserisci() {
        return inserisci;
    }

    public void setInserisci(boolean inserisci) {
        if (inserisci) {
            soloGestione = false;
        }
        this.inserisci = inserisci;
    }

    public boolean isModifica() {
        return modifica;
    }

    public void setModifica(boolean modifica) {
        if (modifica) {
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

    public AccordoServizio getNuovoAccordoServizio() {
        return nuovoAccordoServizio;
    }

    public void setNuovoAccordoServizio(AccordoServizio nuovoAccordoServizio) {
        this.nuovoAccordoServizio = nuovoAccordoServizio;
    }

    public List<SelectItem> getListaProfiliCollaborazione() {
        return listaProfiliCollaborazione;
    }

    public void setListaProfiliCollaborazione(List<SelectItem> listaProfiliCollaborazione) {
        this.listaProfiliCollaborazione = listaProfiliCollaborazione;
    }

    public boolean isPannelloVisibile() {
        return pannelloVisibile;
    }

    public void setPannelloVisibile(boolean pannelloVisibile) {
        this.pannelloVisibile = pannelloVisibile;
    }

    public UIInput getAccordoSelezionato() {
        return accordoSelezionato;
    }

    public void setAccordoSelezionato(UIInput accordoSelezionato) {
        this.accordoSelezionato = accordoSelezionato;
    }

    public List<AccordoServizio> getListaAccordi() {
        return listaAccordi;
    }

    public void setListaAccordi(List<AccordoServizio> listaAccordi) {
        this.listaAccordi = listaAccordi;
    }

    public AccordoServizio getAccordoServizioEliminare() {
        return accordoServizioEliminare;
    }

    public void setAccordoServizioEliminare(AccordoServizio accordoServizioEliminare) {
        this.accordoServizioEliminare = accordoServizioEliminare;
    }

    public UITabPanel getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(UITabPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    public UIDataTable getTabellaAccordi() {
        return tabellaAccordi;
    }

    public void setTabellaAccordi(UIDataTable tabellaAccordi) {
        this.tabellaAccordi = tabellaAccordi;
    }

    public boolean isInserisciAzione() {
        return inserisciAzione;
    }

    public void setInserisciAzione(boolean inserisciAzione) {
        this.inserisciAzione = inserisciAzione;
    }

    public Azione getAzioneAggiungere() {
        return azioneAggiungere;
    }

    public void setAzioneAggiungere(Azione azioneAggiungere) {
        this.azioneAggiungere = azioneAggiungere;
    }

    public boolean isProfiloEgovSpecifico() {
        return profiloEgovSpecifico;
    }

    public void setProfiloEgovSpecifico(boolean profiloEgovSpecifico) {
        this.profiloEgovSpecifico = profiloEgovSpecifico;
    }

    public ProfiloEGov getProfiloAggiungere() {
        return profiloAggiungere;
    }

    public void setProfiloAggiungere(ProfiloEGov profiloAggiungere) {
        this.profiloAggiungere = profiloAggiungere;
    }

    public UIDataTable getTabellaAzioni() {
        return tabellaAzioni;
    }

    public void setTabellaAzioni(UIDataTable tabellaAzioni) {
        this.tabellaAzioni = tabellaAzioni;
    }

    public boolean isNicaPresente() {
        return nicaPresente;
    }

    public void setNicaPresente(boolean nicaPresente) {
        this.nicaPresente = nicaPresente;
    }

    public boolean isVisualizzaPolicy() {
        return visualizzaPolicy;
    }

    public void setVisualizzaPolicy(boolean visualizzaPolicy) {
        this.visualizzaPolicy = visualizzaPolicy;
    }

    public AccordoServizio getPolicyAccordoServizio() {
        return policyAccordoServizio;
    }

    public void setPolicyAccordoServizio(AccordoServizio policyAccordoServizio) {
        this.policyAccordoServizio = policyAccordoServizio;
    }
    
}
