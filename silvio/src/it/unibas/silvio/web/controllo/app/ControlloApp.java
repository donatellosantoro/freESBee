package it.unibas.silvio.web.controllo.app;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOConfigurazione;
import it.unibas.silvio.persistenza.IDAOIstanzaAccordoDiCollaborazione;
import it.unibas.silvio.persistenza.IDAOIstanzaOperation;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import it.unibas.silvio.web.vista.app.VistaApp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;

public class ControlloApp {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());
    private VistaApp vista;
    private String messaggio;
    private String errore;
    private String erroreIndirizzo;
    private IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordoDiCollaborazione;
    private IDAOIstanzaOperation daoIstanzaOperation;
    private IDAOConfigurazione daoConfigurazione;
    private IDAOMessaggio daoMessaggio;
    private Utente utente;

    public String avviaApp() {
        try {
            IstanzaOperation istanza = daoIstanzaOperation.findById(new Long(4), false);
            this.vista.setIstanzaOperationSelezionata(istanza);
        } catch (DAOException ex) {
            logger.error("Impossibile avviare il template per l'istanza. " + ex);
            messaggio = "Impossibile avviare il template per l'istanza. " + ex;
        }
        return "app";
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    public IDAOIstanzaAccordoDiCollaborazione getDaoIstanzaAccordoDiCollaborazione() {
        return daoIstanzaAccordoDiCollaborazione;
    }

    public void setDaoIstanzaAccordoDiCollaborazione(IDAOIstanzaAccordoDiCollaborazione daoIstanzaAccordoDiCollaborazione) {
        this.daoIstanzaAccordoDiCollaborazione = daoIstanzaAccordoDiCollaborazione;
    }

    public IDAOIstanzaOperation getDaoIstanzaOperation() {
        return daoIstanzaOperation;
    }

    public void setDaoIstanzaOperation(IDAOIstanzaOperation daoIstanzaOperation) {
        this.daoIstanzaOperation = daoIstanzaOperation;
    }

    public IDAOMessaggio getDaoMessaggio() {
        return daoMessaggio;
    }

    public void setDaoMessaggio(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public String getErroreIndirizzo() {
        return erroreIndirizzo;
    }

    public void setErroreIndirizzo(String erroreIndirizzo) {
        this.erroreIndirizzo = erroreIndirizzo;
    }

    public Log getLogger() {
        return logger;
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public VistaApp getVista() {
        return vista;
    }

    public void setVista(VistaApp vista) {
        this.vista = vista;
    }
}
