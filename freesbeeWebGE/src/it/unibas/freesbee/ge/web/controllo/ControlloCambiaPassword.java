package it.unibas.freesbee.ge.web.controllo;

import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOException;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOAutenticazioneSOAP;
import it.unibas.freesbee.ge.web.vista.VistaCambiaPassword;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloCambiaPassword {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaCambiaPassword vista;
    private String messaggio;
    private String errore;
    private String conferma;
    private DAOAutenticazioneSOAP daoAutenticazione;
    private Utente utente;


    public String cambiaPassword(){
        String vecchiaPasswordMD5 = utente.md5hash(this.vista.getVecchiaPassword());
        if(!vecchiaPasswordMD5.equals(utente.getPassword())){
            this.setErrore("ERRORE! La vecchia password non è valida!");
            return this.forwardCambioPassword();
        }
        try{
            String nuovaPasswordMD5 = utente.md5hash(this.vista.getNuovaPassword());
            boolean cambiaPassword = this.daoAutenticazione.cambiaPassword(nuovaPasswordMD5, this.utente);
            if(cambiaPassword){
                this.utente.setPassword(nuovaPasswordMD5);
                if (logger.isInfoEnabled()) logger.info("Password cambiata con successo");
                this.setMessaggio("Password cambiata con successo");
                return this.forwardCambioPassword();
            }
        }catch(DAOException ex){
             if (logger.isInfoEnabled()) logger.info("Impossibile cambiare la password per l'utente "+this.utente.getNomeUtente() + " - " + ex);   
            }
        return "passwordCambiata";
    }

    public String forwardCambioPassword() {
        return "forwardCambioPassword";
    }

    public String backwardHome() {
        return "loginEffettuato";
    }

    public VistaCambiaPassword getVista() {
        return vista;
    }

    public void setVista(VistaCambiaPassword vista) {
        this.vista = vista;
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

    public String getConferma() {
        return conferma;
    }

    public void setConferma(String conferma) {
        this.conferma = conferma;
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

    public DAOAutenticazioneSOAP getDaoAutenticazione() {
        return daoAutenticazione;
    }

    public void setDaoAutenticazione(DAOAutenticazioneSOAP daoAutenticazione) {
        this.daoAutenticazione = daoAutenticazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
