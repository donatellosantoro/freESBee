package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.persistenza.soap.DAOSoggettoSOAP;
import it.unibas.freesbee.websla.vista.VistaGestioneSoggetti;
import it.unibas.icar.freesbee.modello.Soggetto;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloGestioneSoggetti {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaGestioneSoggetti vista;
    private String messaggio;
    private String errore;
    private DAOSoggettoSOAP daoSoggetto;
    private Utente utente;
    
    public void caricaTuttiSoggetti() {
        try {
            List<Soggetto> listaSoggettiNICA = daoSoggetto.getSoggettiNICA(utente);
            Collections.sort(listaSoggettiNICA);
            this.vista.setListaSoggettiNICA(listaSoggettiNICA);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei soggetti presenti sul nica dal modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei soggetti presenti sul nica dal modulo  freESBeeSLA. Controllare che sia avviato");
        }
        try {
            List<Soggetto> listaSoggettiINF2 = daoSoggetto.getSoggettiInf2(utente);

            Collections.sort(listaSoggettiINF2);
            this.vista.setListaSoggettiINF2(listaSoggettiINF2);
        } catch (DAOException ex) {
            logger.error("Impossibile leggere l'elenco dei soggetti presenti sul modulo  freESBeeSLA. " + ex);
            this.setErrore("Impossibile leggere l'elenco dei soggetti presenti sul modulo  freESBeeSLA. Controllare che sia avviato");
        }
    }

    public String caricaGestioneSoggetti() {
        logger.debug("Caricamento di tutti i soggetti in corso.");
        caricaTuttiSoggetti();
        return "schermoGestioneSoggetti";
    }

    public String inserisci() {
        try {
            Soggetto soggettoSelezionato = (Soggetto) this.vista.getTabellaSoggettiNICA().getRowData();
            daoSoggetto.aggiungiSoggetto(utente, soggettoSelezionato);
            this.setMessaggio("Soggetto aggiunto correttamente.");
            caricaTuttiSoggetti();
        } catch (DAOException ex) {
            logger.error("Impossibile aggiungere il soggetto. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    public String elimina() {
        try {
            Soggetto soggettoSelezionato = (Soggetto) this.vista.getTabellaSoggettiINF2().getRowData();
            daoSoggetto.eliminaSoggetto(utente, soggettoSelezionato);
            this.setMessaggio("Soggetto eliminato correttamente dal modulo  freESBeeSLA.");
            caricaTuttiSoggetti();
        } catch (DAOException ex) {
            logger.error("Impossibile eliminare il soggetto. " + ex);
            String erroreFormattato = ex.getMessage().substring(ex.getMessage().indexOf(":") + 1);
            this.setErrore(erroreFormattato);
        }
        return null;
    }

    VistaGestioneSoggetti getVista() {
        return vista;
    }

    public void setVista(VistaGestioneSoggetti vista) {
        this.vista = vista;
    }

    public DAOSoggettoSOAP getDaoSoggetto() {
        return daoSoggetto;
    }

    public void setDaoSoggetto(DAOSoggettoSOAP daoSoggetto) {
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

    public boolean isVisualizzaMessaggio() {
        return (this.messaggio != null && !this.messaggio.equalsIgnoreCase(""));
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }


}
