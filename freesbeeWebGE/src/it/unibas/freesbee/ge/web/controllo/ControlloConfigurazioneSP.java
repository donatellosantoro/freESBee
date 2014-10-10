package it.unibas.freesbee.ge.web.controllo;


import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOConfigurazioneSOAP;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOException;
import it.unibas.freesbee.ge.web.vista.VistaConfigurazioneSP;
import it.unibas.freesbee.ge.web.ws.stub.DatiConfigurazione;
import it.unibas.freesbee.ge.web.ws.stub.SOAPFaultException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloConfigurazioneSP {

      private Log logger = LogFactory.getLog(this.getClass());
    private String messaggio;
    private String errore;
    private VistaConfigurazioneSP vista;
    private Utente utente;
    private DAOConfigurazioneSOAP daoConfigurazione;

    public String caricaConfigurazioneSP()  {
        this.ripulisci();
        try {
           DatiConfigurazione datiConfigurazione = daoConfigurazione.caricaDatiConfigurazione(utente);
            vista.setDatiConfigurazione(datiConfigurazione);

        } catch (DAOException ex) {
            logger.error("Problemi nel caricamento della configurazione: " + ex.getMessage());
            this.errore = "Problemi nel caricamento della configurazione.";
            return null;
        }
        return "schermoGestioneConfigurazioneSP";
    }

    public void attivaModificaSP() {
        vista.setAttivaModificaSP(true);
    }

    public void annullaModificaSP() throws SOAPFaultException {
       caricaConfigurazioneSP();
    }

    public void salvaModificaSP()  {

        try {

            daoConfigurazione.modificaDatiConfigurazioneSP(utente, vista.getDatiConfigurazione());
            caricaConfigurazioneSP();
            vista.setAttivaModificaSP(false);
            this.messaggio = "Configurazione cambiata";
        } catch (DAOException ex) {
            logger.error("Impossibile modificare la configurazione del modulo GE. " + ex);
            this.setErrore("Impossibile modificare la configurazione del modulo GE. ");

        }
    }

    private void ripulisci() {
        this.messaggio = "";
        this.errore = "";
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

    public VistaConfigurazioneSP getVista() {
        return vista;
    }

    public void setVista(VistaConfigurazioneSP vista) {
        this.vista = vista;
    }


    public DAOConfigurazioneSOAP getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(DAOConfigurazioneSOAP daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }
}
