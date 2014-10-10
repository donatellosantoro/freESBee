package it.unibas.icar.freesbee.controllo;

import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import it.unibas.icar.freesbee.vista.VistaGestioneServiziApplicativi;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestioneServiziApplicativi {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestioneServiziApplicativi vista;
    private IDAOServizioApplicativo daoServizioApplicativo;
    private String messaggioTest;
    private String errore;
    private String conferma;
    private Utente utente;

    public void caricaTuttiServiziApplicativi() {
        try {
            List<ServizioApplicativo> lista = daoServizioApplicativo.findAll(this.utente);
            Collections.sort(lista);
            if (lista.size() == 0) {
                this.setMessaggio("Non ho trovato alcun servizio applicativo.");
            }
            this.getVista().setListaServiziApplicativi(lista);
            this.getVista().setTarget(new String[0]);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei servizi applicativi dalla base di dati. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei servizi applicativi dalla base di dati. Controllare che freESBee sia avviato");
        }
    }

    public String caricaGestioneServiziApplicativi() {
        this.vista.setElimina(false);
        ServizioApplicativo nuovoServizioApplicativo = new ServizioApplicativo();
        this.vista.setNuovoServizioApplicativo(nuovoServizioApplicativo);
        caricaTuttiServiziApplicativi();
        return "gestioneServiziApplicativi";
    }

    public String gestioneInserisciServiziApplicativi(ServizioApplicativo nuovoServizioApplicativo) {
        this.vista.setInserisci(true);
        this.vista.setModifica(false);
        this.vista.setElimina(false);
        this.vista.setNuovoServizioApplicativo(nuovoServizioApplicativo);
        return null;
    }
    
    public String gestioneModificaServizioApplicativo(ServizioApplicativo servizioApplicativo) {
        this.vista.setInserisci(false);
        this.vista.setModifica(true);
        this.vista.setElimina(false);
        this.vista.setNuovoServizioApplicativo(servizioApplicativo);
        return null;
    }
    
    public String gestioneEliminaServizioApplicativo(ServizioApplicativo servizioApplicativoDaEliminare) {
        this.vista.setInserisci(false);
        this.vista.setModifica(false);
        this.vista.setElimina(true);
        this.vista.setNuovoServizioApplicativo(servizioApplicativoDaEliminare);
        return null;
    }
    
    public String inserisci() {
        try {
            ServizioApplicativo servizioApplicativoAggiungere = this.getVista().getNuovoServizioApplicativo();
            daoServizioApplicativo.makePersistent(this.utente, servizioApplicativoAggiungere);
            this.setMessaggio("Servizio applicativo inserito correttamente.");
            gestioneInserisciServiziApplicativi(new ServizioApplicativo());
            caricaTuttiServiziApplicativi();
        } catch (DAOException ex) {
            logger.error("Impossibile inserire il nuovo servizio applicativo. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }

        return "gestioneServiziApplicativi";
    }

    public String modifica() {
        try {
            ServizioApplicativo servizioApplicativoDaModificare = this.vista.getNuovoServizioApplicativo();
            daoServizioApplicativo.makePersistent(this.utente, servizioApplicativoDaModificare);
            this.vista.setNuovoServizioApplicativo(new ServizioApplicativo());
            this.setMessaggio("Servizio applicativo modificato correttamente.");
            gestioneInserisciServiziApplicativi(new ServizioApplicativo());
            caricaTuttiServiziApplicativi();
        } catch (DAOException ex) {
            logger.error("Impossibile modificare il nuovo servizio applicativo. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String elimina() {
        try {
            ServizioApplicativo servizioApplicativoDaEliminare = this.vista.getNuovoServizioApplicativo();
            daoServizioApplicativo.makeTransient(this.utente, servizioApplicativoDaEliminare);
            this.setMessaggio("Servizio applicativo eliminato correttamente.");
            caricaTuttiServiziApplicativi();
            gestioneInserisciServiziApplicativi(new ServizioApplicativo());
            this.vista.setNuovoServizioApplicativo(new ServizioApplicativo());
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare il nuovo servizio applicativo. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }
    
    public String schermoModificaDaTabella() {
        ServizioApplicativo servizioApplicativoDaTabella = (ServizioApplicativo)this.vista.getTabellaServiziApplicativi().getRowData();
//        this.vista.setNuovoServizioApplicativo(servizioApplicativoDaTabella);
        gestioneModificaServizioApplicativo(servizioApplicativoDaTabella);
        return "gestioneServiziApplicativi";
    }
    
    public String schermoEliminaDaTabella() {
        ServizioApplicativo servizioApplicativo = (ServizioApplicativo)this.vista.getTabellaServiziApplicativi().getRowData();
//        this.vista.setNuovoServizioApplicativo(servizioApplicativo);
        this.conferma = "Sei sicuro di voler eliminare il servizio applicativo selezionato? Verranno eliminate anche tutte le porte applicative collegate!";
        gestioneEliminaServizioApplicativo(servizioApplicativo);
        return null;
    }
    
    public String getMessaggio() {
        return messaggioTest;
    }

    public void setMessaggio(String messaggio) {
        this.messaggioTest = messaggio;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public String getConferma() {
        return conferma;
    }

    public void setConferma(String conferma) {
        this.conferma = conferma;
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggioTest != null && !this.messaggioTest.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaConferma() {
        return (this.getConferma() != null && !this.conferma.equalsIgnoreCase(""));
    }

    public IDAOServizioApplicativo getDaoServizioApplicativo() {
        return daoServizioApplicativo;
    }

    public void setDaoServizioApplicativo(IDAOServizioApplicativo daoServizioApplicativo) {
        this.daoServizioApplicativo = daoServizioApplicativo;
    }

    public VistaGestioneServiziApplicativi getVista() {
        return vista;
    }

    public void setVista(VistaGestioneServiziApplicativi vista) {
        this.vista = vista;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
