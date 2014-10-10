package it.unibas.freesbee.ge.web.controllo;

import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOCategoriaEventiInternaSOAP;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOCostanti;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOException;
import it.unibas.freesbee.ge.web.vista.VistaCategoriaEventiInterna;
import it.unibas.freesbee.ge.web.ws.stub.CategoriaEventiInterna;
import it.unibas.freesbee.ge.web.ws.stub.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.web.ws.stub.PubblicatoreInterno;
import it.unibas.freesbee.ge.web.ws.stub.SottoscrizioneInterna;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloCategoriaEventiInterna {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaCategoriaEventiInterna vista;
    private String messaggio;
    private String errore;
    private String conferma;
    private DAOCategoriaEventiInternaSOAP daoCategoriaEventi;
    private Utente utente;

    public String getListaCategoriaEventi() {
        try {
            vista.ripulisci();
            List<CategoriaEventiInterna> lista = this.daoCategoriaEventi.getListaCategoriaEventi(utente);
            this.vista.setListaCategoriaEventi(lista);
            if (lista.size() == 0) {
                this.messaggio = "Non ci sono categorie di eventi da visualizzare.";
            }
            return "schermoGestioneCategoriaEventiInterna";
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la lista delle categorie di eventi " + ex.getMessage());
            this.errore = ex.getMessage();
        }
        return null;
    }

    public String addCategoriaEventi() {
        CategoriaEventiInterna categoriaEventi = new CategoriaEventiInterna();
        try {
            categoriaEventi.setNome(this.vista.getNomeNuovaCategoria());
            categoriaEventi.setAttiva(false);
            this.daoCategoriaEventi.addCategoriaEventi(categoriaEventi, utente);
            this.messaggio = "La categoria di eventi è stata inserita correttamente.";
            getListaCategoriaEventi();
            return "schermoGestioneCategoriaEventiInterna";
        } catch (DAOException ex) {
            logger.error("Impossibile aggiungere la nuova categoria di eventi " + ex.getMessage());
            this.errore = ex.getMessage();
        }
        return null;
    }

    public String updateCategoriaEventi(boolean stato) {
        CategoriaEventiInterna categoriaEventi = null;
        try {
            categoriaEventi = this.vista.getCategoriaEventiSelezionata();
            this.vista.setElimina(false);
            if (categoriaEventi.getNome().equals(DAOCostanti.GE_CONTROL_PROTOCOL)) {
                this.errore = "La categoria di evneti " + DAOCostanti.GE_CONTROL_PROTOCOL + " non è modificabile";
                categoriaEventi.setAttiva(!stato);
                vista.ripulisci();
                return null;
            }
            categoriaEventi.setAttiva(stato);
            this.daoCategoriaEventi.updateCategoriaEventi(categoriaEventi, utente);
            this.messaggio = "La categoria di evneti è stata aggiornata correttamente.";
            getListaCategoriaEventi();
            return "schermoGestioneCategoriaEventiInterna";
        } catch (DAOException ex) {
            categoriaEventi.setAttiva(!stato);
            vista.ripulisci();
            logger.error("Impossibile aggiornare la categoria di eventi " + ex.getMessage());
            this.errore =ex.getMessage();
        }

        return null;
    }

    public String visuliazzaPubblicatori(){
        String nomeCategoriaEventi = ((CategoriaEventiInterna) this.vista.getTabellaCategoriaEventi().getRowData()).getNome();
        try{
        CategoriaEventiInterna categoriaEventi = daoCategoriaEventi.getCategoriaEventi(utente, nomeCategoriaEventi);
        getListaCategoriaEventi();
        this.vista.setCategoriaEventiSelezionata(categoriaEventi);
        this.vista.setListaPubblicatore(categoriaEventi.getListaPubblicatori());
        this.vista.setPubblicatori(true);
        this.vista.setSottoscrizioni(false);
        this.vista.setDettagli(false);
        } catch (DAOException ex) {
            vista.ripulisci();
            logger.error("Impossibile visualizzare i pubblicatori della categoria di eventi " + ex.getMessage());
            this.errore = ex.getMessage();
        }

        return null;
    }

    public String visuliazzaSottoscrizioni() {
       String nomeCategoriaEventi = ((CategoriaEventiInterna) this.vista.getTabellaCategoriaEventi().getRowData()).getNome();
        try{
        CategoriaEventiInterna categoriaEventi = daoCategoriaEventi.getCategoriaEventi(utente, nomeCategoriaEventi);
        getListaCategoriaEventi();
        this.vista.setCategoriaEventiSelezionata(categoriaEventi);
        this.vista.setListaSottoscrizione(categoriaEventi.getListaSottoscrizioni());
        this.vista.setSottoscrizioni(true);
        this.vista.setDettagli(false);
        this.vista.setPubblicatori(false);
       } catch (DAOException ex) {
            vista.ripulisci();
            logger.error("Impossibile visualizzare le sottoscrizioni della categoria di eventi " + ex.getMessage());
            this.errore = ex.getMessage();
        }
        return null;
    }

    public String visuliazzaDettagliSottoscrizione() {
        SottoscrizioneInterna sottoscrizione = (SottoscrizioneInterna) this.vista.getTabellaSottoscrizione().getRowData();
        this.vista.setSottoscrizioneSelezionata(sottoscrizione);
        List<PubblicatoreInterno> listaP = new ArrayList<PubblicatoreInterno>();
        for (FiltroPubblicatoreInterno fp : sottoscrizione.getListaFiltroPublicatore()) {
            listaP.add(fp.getPubblicatore());
        }
        this.vista.setListaPubblicatoreFiltro(listaP);
        this.vista.setDettagli(true);
        this.vista.setSottoscrizioni(true);
        this.vista.setPubblicatori(false);
        return null;
    }

    public void attiva() {
        this.updateCategoriaEventi(true);
    }

    public void disattiva() {
        this.updateCategoriaEventi(false);
    }

    public String schermoDisattivaDaTabella() {
        CategoriaEventiInterna categoriaEventi = (CategoriaEventiInterna) this.vista.getTabellaCategoriaEventi().getRowData();
        this.vista.setCategoriaEventiSelezionata(categoriaEventi);
        this.vista.setElimina(true);
        this.conferma = "Sei sicuro di voler disattivare la categoria di eventi selezionata? Verranno disattivate anche tutte le sottoscrizioni relative!";
        return null;
    }

    public String schermoAttivaDaTabella() {
        CategoriaEventiInterna categoriaEventi = (CategoriaEventiInterna) this.vista.getTabellaCategoriaEventi().getRowData();
        this.vista.setCategoriaEventiSelezionata(categoriaEventi);
        this.vista.setElimina(true);
        this.conferma = "Sei sicuro di voler attivare la categoria di eventi selezionata? Verranno attivate anche tutte le sottoscrizioni relative!";
        return null;
    }

    public VistaCategoriaEventiInterna getVista() {
        return vista;
    }

    public void setVista(VistaCategoriaEventiInterna vista) {
        this.vista = vista;
    }

    public DAOCategoriaEventiInternaSOAP getDaoCategoriaEventi() {
        return daoCategoriaEventi;
    }

    public void setDaoCategoriaEventi(DAOCategoriaEventiInternaSOAP daoCategoriaEventi) {
        this.daoCategoriaEventi = daoCategoriaEventi;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public String getConferma() {
        return conferma;
    }

    public void setConferma(String conferma) {
        this.conferma = conferma;
    }
}