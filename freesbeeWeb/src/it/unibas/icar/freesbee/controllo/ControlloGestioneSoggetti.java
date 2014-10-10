package it.unibas.icar.freesbee.controllo;

import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.vista.VistaGestioneSoggetti;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestioneSoggetti {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestioneSoggetti vista;
    private IDAOSoggetto daoSoggetto;
    private IDAOPortaDelegata daoPortaDelegata;
    private String messaggio;
    private String errore;
    private String conferma;
    private String messaggioPorteDelegateInteressate;
    private List<PortaDelegata> porteDelegateInteressate;
    private Utente utente;

    public void caricaTuttiSoggetti() {
        try {
            List<Soggetto> lista = daoSoggetto.findAll(this.utente);
            Collections.sort(lista);
            if (lista.size() == 0) {
                this.setMessaggio("Non ho trovato alcun soggetto.");
            }
            this.vista.setListaSoggetti(lista);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei soggetti dalla base di dati. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei soggetti dalla base di dati. Controllare che freESBee sia avviato");
        }
    }

    public String caricaGestioneSoggetti() {
//        this.getVista().setSoloGestione(false);
        gestioneInserisciSoggetti(new Soggetto());
        caricaTuttiSoggetti();
        return "gestioneSoggetti";
    }

    public String gestioneInserisciSoggetti(Soggetto soggetto) {
        this.vista.setInserisci(true);
        this.vista.setModifica(false);
        this.vista.setElimina(false);
        this.vista.setNuovoSoggetto(soggetto);
        return null;
    }

    public String gestioneModificaSoggetti(Soggetto soggetto) {
        this.vista.setInserisci(false);
        this.vista.setModifica(true);
        this.vista.setElimina(false);
        this.vista.setNuovoSoggetto(soggetto);
        return null;
    }

    public String gestioneEliminaSoggetti(Soggetto soggetto) {
        this.vista.setInserisci(false);
        this.vista.setModifica(false);
        this.vista.setElimina(true);
        this.vista.setNuovoSoggetto(soggetto);
        return null;
    }

    public String inserisci() {
        try {
            daoSoggetto.makePersistent(this.utente, this.vista.getNuovoSoggetto());
            this.setMessaggio("Soggetto inserito correttamente.");
            gestioneInserisciSoggetti(new Soggetto());
            caricaTuttiSoggetti();
        } catch (DAOException ex) {
            logger.error("Impossibile inserire il nuovo soggetto. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }

        return null;
    }

    public String modifica() {
        try {
            Soggetto soggettoModificare = this.vista.getNuovoSoggetto();
            daoSoggetto.makePersistent(this.utente, soggettoModificare);
            this.setMessaggio("Soggetto modificato correttamente.");
            gestioneInserisciSoggetti(new Soggetto());
            caricaTuttiSoggetti();
        } catch (DAOException ex) {
            logger.error("Impossibile modificare il soggetto. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String elimina() {
        try {
            Soggetto soggettoEliminare = this.vista.getNuovoSoggetto();
            daoSoggetto.makeTransient(this.utente, soggettoEliminare);
            this.setMessaggio("Soggetto eliminato correttamente.");
            gestioneInserisciSoggetti(new Soggetto());
            caricaTuttiSoggetti();
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare il soggetto. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String schermoModificaDaTabella() {
        Soggetto soggettoSelezionato = (Soggetto) this.vista.getTabellaSoggetti().getRowData();
        this.vista.setNuovoSoggetto(soggettoSelezionato);
        gestioneModificaSoggetti(soggettoSelezionato);
        return null;
    }

    public String schermoEliminaDaTabella() {
//        try {
            Soggetto soggettoSelezionato = (Soggetto) this.vista.getTabellaSoggetti().getRowData();
            this.vista.setNuovoSoggetto(soggettoSelezionato);
            gestioneEliminaSoggetti(soggettoSelezionato);
//            setPorteDelegateInteressate(daoPortaDelegata.findBySoggetto(soggettoSelezionato));
//            if (this.porteDelegateInteressate.size() > 0) {
//                setMessaggioPorteDelegateInteressate("Il soggetto e' presente nelle seguenti porte delegate, che verranno anch'esse eliminate.");
//            }
            this.setConferma("Sei sicuro di voler eliminare il soggetto? Verranno eliminate tutte le sue porte delegate e applicative, e tutti i servizi che eroga!");
//        } catch (DAOException ex) {
//            logger.error("Impossibile leggere le porte delegate in cui compare il soggetto da eliminare. " + ex);
//            this.setErrore("Impossibile leggere le porte delegate in cui compare il soggetto da eliminare.");
//        }
        return null;
    }

    VistaGestioneSoggetti getVista() {
        return vista;
    }

    public void setVista(VistaGestioneSoggetti vista) {
        this.vista = vista;
    }

    public IDAOSoggetto getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(IDAOSoggetto daoSoggetto) {
        this.daoSoggetto = daoSoggetto;
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

    public IDAOPortaDelegata getDaoPortaDelegata() {
        return daoPortaDelegata;
    }

    public void setDaoPortaDelegata(IDAOPortaDelegata daoPortaDelegata) {
        this.daoPortaDelegata = daoPortaDelegata;
    }

    public List<PortaDelegata> getPorteDelegateInteressate() {
        return porteDelegateInteressate;
    }

    public void setPorteDelegateInteressate(List<PortaDelegata> porteDelegateInteressate) {
        this.porteDelegateInteressate = porteDelegateInteressate;
    }

    public String getMessaggioPorteDelegateInteressate() {
        return messaggioPorteDelegateInteressate;
    }

    public void setMessaggioPorteDelegateInteressate(String messaggioPorteDelegateInteressate) {
        this.messaggioPorteDelegateInteressate = messaggioPorteDelegateInteressate;
    }

    public boolean isVisualizzaTabellaPorteDelegate() {
        return (this.porteDelegateInteressate != null && this.porteDelegateInteressate.size() > 0);
    }

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaConferma() {
        return (this.getConferma() != null && !this.conferma.equalsIgnoreCase(""));
    }

    public String getConferma() {
        return conferma;
    }

    public void setConferma(String conferma) {
        this.conferma = conferma;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
