package it.unibas.icar.freesbee.vista;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UIListShuttle;
import org.richfaces.component.UITabPanel;

public class VistaGestioneServizi implements Serializable{

    private Log logger = LogFactory.getLog(this.getClass());
    private Servizio nuovoServizio = new Servizio();
    private ServizioApplicativo nuovoServizioApplicativo = new ServizioApplicativo();
    private transient UIData tabellaServizi;
    private List<Servizio> listaServizi;
    private boolean inserisci;
    private boolean modifica;
    private boolean elimina;
    private boolean soloGestione;
    private boolean pannelloVisibile = true;
    private IDAOSoggetto daoSoggetto;
    private IDAOAccordoServizio daoAccordoServizio;
    private List<Soggetto> tuttiSoggetti;
    private List<Soggetto> soggettiSelezionati;
    private UIInput servizioSelezionato;
    private UITabPanel tabPanel;
    private String[] source = new String[]{};
    private String[] target = new String[]{};
    private String[] azioniDisponibili = new String[]{};
    private String[] azioniSelezionate = new String[]{};
    private Utente utente;
    private boolean nicaPresente;


    public VistaGestioneServizi() {
        this.inserisci = false;
        this.modifica = true;
        this.elimina = true;
    }

    public UIData getTabellaServizi() {
        return tabellaServizi;
    }

    public void setTabellaServizi(UIData tabellaServizi) {
        this.tabellaServizi = tabellaServizi;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        if (elimina) {
            soloGestione = false;
            setPannelloVisibile(true);
        }
        this.elimina = elimina;
    }

    public List<SelectItem> getListaSoggetti() {
        List<SelectItem> listaSoggetti = new ArrayList<SelectItem>();
        try {
            List<Soggetto> lista = daoSoggetto.findAll(this.utente);
            listaSoggetti.add(new SelectItem(""));
            Collections.sort(lista);
            for (Soggetto soggetto : lista) {
                listaSoggetti.add(new SelectItem(soggetto.getId(), soggetto.getTipo() + "\\" + soggetto.getNome()));
            }
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei soggetti " + ex);
        }
        return listaSoggetti;
    }

    public List<SelectItem> getListaAccordiServizio() {
        List<SelectItem> listaAccordi = new ArrayList<SelectItem>();
        try {
            List<AccordoServizio> lista = daoAccordoServizio.findAll(this.utente);
            listaAccordi.add(new SelectItem(""));
            Collections.sort(lista);
            for (AccordoServizio accordo : lista) {
                listaAccordi.add(new SelectItem(accordo.getId(), accordo.getNome()));
            }
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco degli accordi di servizio " + ex);
        }
        return listaAccordi;
    }

    public boolean isInserisci() {
        return inserisci;
    }

    public void setInserisci(boolean inserisci) {
        if (inserisci) {
            soloGestione = false;
            setPannelloVisibile(true);
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

    public boolean isPannelloVisibile() {
        return pannelloVisibile;
    }

    public void setPannelloVisibile(boolean pannelloVisibile) {
        this.pannelloVisibile = pannelloVisibile;
    }

    public Servizio getNuovoServizio() {
        return nuovoServizio;
    }

    public void setNuovoServizio(Servizio nuovoServizio) {
        this.nuovoServizio = nuovoServizio;
    }

    public ServizioApplicativo getNuovoServizioApplicativo() {
        return nuovoServizioApplicativo;
    }

    public void setNuovoServizioApplicativo(ServizioApplicativo nuovoServizioApplicativo) {
        this.nuovoServizioApplicativo = nuovoServizioApplicativo;
    }

    public List<Servizio> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<Servizio> listaServizi) {
        this.listaServizi = listaServizi;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
    }

    public IDAOAccordoServizio getDaoAccordoServizio() {
        return daoAccordoServizio;
    }

    public void setDaoAccordoServizio(IDAOAccordoServizio daoAccordoServizio) {
        this.daoAccordoServizio = daoAccordoServizio;
    }

    public List<Soggetto> getTuttiSoggetti() {
        return tuttiSoggetti;
    }

    public void setTuttiSoggetti(List<Soggetto> tuttiSoggetti) {
        this.tuttiSoggetti = tuttiSoggetti;
    }

    public List<Soggetto> getSoggettiSelezionati() {
        return soggettiSelezionati;
    }

    public void setSoggettiSelezionati(List<Soggetto> soggettiSelezionati) {
        this.soggettiSelezionati = soggettiSelezionati;
    }

    public String[] getSource() {
        return source;
    }

    public void setSource(String[] source) {
        this.source = source;
    }

    public String[] getTarget() {
        return target;
    }

    public void setTarget(String[] target) {
        this.target = target;
    }

    public UITabPanel getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(UITabPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    public UIInput getServizioSelezionato() {
        return servizioSelezionato;
    }

    public void setServizioSelezionato(UIInput servizioSelezionato) {
        this.servizioSelezionato = servizioSelezionato;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public boolean isNicaPresente() {
        return nicaPresente;
    }

    public void setNicaPresente(boolean nicaPresente) {
        this.nicaPresente = nicaPresente;
    }
    
    public String[] getAzioniDisponibili() {
        return azioniDisponibili;
    }

    public void setAzioniDisponibili(String[] azioniDisponibili) {
        this.azioniDisponibili = azioniDisponibili;
    }

    public String[] getAzioniSelezionate() {
        return azioniSelezionate;
    }

    public void setAzioniSelezionate(String[] azioniSelezionate) {
        this.azioniSelezionate = azioniSelezionate;
    }
}
