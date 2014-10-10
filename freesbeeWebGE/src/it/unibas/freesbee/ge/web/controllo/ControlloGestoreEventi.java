package it.unibas.freesbee.ge.web.controllo;

import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOConfigurazioneSOAP;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOException;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOGestoreEventiSOAP;
import it.unibas.freesbee.ge.web.vista.VistaGestoreEventi;
import it.unibas.freesbee.ge.web.ws.stub.GestoreEventi;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestoreEventi {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestoreEventi vista;
    private String messaggio;
    private String errore;
    private String conferma;
    private DAOGestoreEventiSOAP daoGestoreEventi;
    private DAOConfigurazioneSOAP daoConfigurazione;
    private Utente utente;

    public String getListaGestoreEventi() {
        try {
            vista.ripulisci();
            List<GestoreEventi> lista = this.daoGestoreEventi.getListaGestoreEventi(utente);
            this.vista.setListaGestoreEventi(lista);
            if (lista.size() == 0) {
                this.messaggio = "Non ci sono gestori eventi da visualizzare";
            }
            return "schermoGestioneGestoreEventi";
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la lista dei gestori eventi " + ex.getMessage());
            this.errore = "Impossibile caricare la lista dei gestori eventi. ";
        }
        return null;
    }

    public String addGestoreEventi() {
        GestoreEventi gestoreEventi = null;
        try {
            gestoreEventi = this.vista.getGestoreEventiSelezionato();
            if (gestoreEventi == null) {
                this.setErrore("Impossibile leggere il gestore eventi. Aggiornare la pagina e riprovare");
                return null;
            }
            this.daoGestoreEventi.addGestoreEventi(gestoreEventi, utente);
            this.messaggio = "Il gestore eventi è stato inserito correttamente.";
            getListaGestoreEventi();
            return "schermoGestioneGestoreEventi";
        } catch (DAOException ex) {
            logger.error("Impossibile aggiungere il nuovo gestore eventi " + ex.getMessage());
            this.errore = "Impossibile aggiungere il nuovo gestore eventi.";
        }
        return null;
    }

//    public String updateGestoreEventi() {
//        try {
//            GestoreEventi gestoreEventi = this.vista.getGestoreEventiSelezionato();
//            DatiConfigurazione datiConfigurazione = daoConfigurazione.caricaDatiConfigurazione(utente);
//            this.vista.setElimina(false);
//
//            if (gestoreEventi.getTipo().equals(datiConfigurazione.getConfigurazione().getTipoGestoreEventi())&&  gestoreEventi.getNome().equals(datiConfigurazione.getConfigurazione().getNomeGestoreEventi())) {
//                this.errore = "I dati del gestore eventi devono essere modificati dalla sezione configurazione.";
//                vista.ripulisci();
//                return null;
//            }
//
//
//            this.daoCategoriaEventi.upda(gestoreEventi, utente);
//            this.messaggio = "La categoria di evneti è stata aggiornata correttamente.";
//            getListaCategoriaEventi();
//            return "schermoGestioneCategoriaEventi";
//        } catch (DAOException ex) {
//            gestoreEventi.setAttiva(!stato);
//            vista.ripulisci();
//            logger.error("Impossibile aggiornare la categoria di eventi " + ex.getMessage());
//            this.errore = "Impossibile aggiornare la categoria di eventi. ";
//        }
//
//        return null;
//    }

//    public void attiva() {
//        this.updateCategoriaEventi(true);
//    }
//
//    public void disattiva() {
//        this.updateCategoriaEventi(false);
//    }

//    public String schermoDisattivaDaTabella() {
//        GestoreEventi gestoreEventi = (GestoreEventi) this.vista.getTabellaGestoriEventi().getRowData();
//        this.vista.setGestoreEventiSelezionato(gestoreEventi);
//        this.vista.setElimina(true);
//        this.conferma = "Sei sicuro di voler disattivare la categoria di eventi selezionata? Verranno disattivate anche tutte le sottoscrizioni relative!";
//        return null;
//    }
//
//    public String schermoAttivaDaTabella() {
//        CategoriaEventiEsterna categoriaEventi = (CategoriaEventiEsterna) this.vista.getTabellaCategoriaEventi().getRowData();
//        this.vista.setCategoriaEventiSelezionata(categoriaEventi);
//        this.vista.setElimina(true);
//        this.conferma = "Sei sicuro di voler attivare la categoria di eventi selezionata? Verranno attivate anche tutte le sottoscrizioni relative!";
//        return null;
//    }

    public VistaGestoreEventi getVista() {
        return vista;
    }

    public void setVista(VistaGestoreEventi vista) {
        this.vista = vista;
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

    public DAOConfigurazioneSOAP getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(DAOConfigurazioneSOAP daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public DAOGestoreEventiSOAP getDaoGestoreEventi() {
        return daoGestoreEventi;
    }

    public void setDaoGestoreEventi(DAOGestoreEventiSOAP daoGestoreEventi) {
        this.daoGestoreEventi = daoGestoreEventi;
    }
}