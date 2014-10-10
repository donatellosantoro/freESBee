package it.unibas.icar.freesbee.controllo;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAutenticazione;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import it.unibas.icar.freesbee.vista.VistaLogin;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloLogin {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaLogin vista;
    private Utente utente;
    private String messaggio;
    private String errore;
    private IDAOAutenticazione daoAutenticazione;
    private IDAOConfigurazione daoConfigurazione;

    public String login() {
        String server = this.vista.getUtente().getServer();
        if (!server.endsWith("/")) {
            this.vista.getUtente().setServer(server + "/");
        }
        this.utente.setNomeUtente(this.vista.getUtente().getNomeUtente());
        String passwordInChiaro = this.vista.getUtente().getPassword();
        String passwordMD5 = this.vista.getUtente().md5hash(passwordInChiaro);
        this.utente.setPassword(passwordMD5);
        this.utente.setServer(this.vista.getUtente().getServer());
        try {
            boolean autenticazione = this.daoAutenticazione.autentica(utente);
            if (autenticazione) {
                this.utente.setAutenticato(true);
                this.utente.setRuolo(this.daoAutenticazione.getRuolo(utente));
                controllaNica(utente);
                controllaVersioneFreesbee(utente);
                return "loginEffettuato";
            }
        } catch (DAOException ex) {
            this.errore = "Impossibile effettuare l'autenticazione. Controllare nome utente e password.";
            logger.info("Impossibile effettuare l'autenticazione " + ex);
        }
        return null;
    }

    public String logout() {
        this.utente.setNomeUtente("");
        this.utente.setPassword("");
        this.utente.setRuolo("");
        this.utente.setAutenticato(false);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            logger.info("INVALIDO LA SESSIONE");
            session.invalidate();
        }
        return "logoutEffettuato";
    }

    public String cambiaPassword() {
        return null;
    }

    public String forwardCambioPassword() {
        return "forwardCambioPassword";
    }

    public String backwardHome() {
        return "loginEffettuato";
    }

    public VistaLogin getVista() {
        return vista;
    }

    public void setVista(VistaLogin vista) {
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

    public IDAOAutenticazione getDaoAutenticazione() {
        return daoAutenticazione;
    }

    public void setDaoAutenticazione(IDAOAutenticazione daoAutenticazione) {
        this.daoAutenticazione = daoAutenticazione;
    }

    public IDAOConfigurazione getDaoConfigurazione() {
        return daoConfigurazione;
    }

    public void setDaoConfigurazione(IDAOConfigurazione daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

    private void controllaNica(Utente utente) {
        try {
            Configurazione configurazione = daoConfigurazione.find(utente);
            utente.setNica(configurazione.isNICA());
        } catch (DAOException daoe) {
            logger.warn("Impossibile leggere la configurazione dalla base di dati. " + daoe);
        }
    }
    
        private void controllaVersioneFreesbee(Utente utente) {
        try {
            Configurazione configurazione = daoConfigurazione.find(utente);
            utente.setFreesbeeVersion(configurazione.getFreesbeeVersion());
        } catch (DAOException daoe) {
            logger.warn("Impossibile leggere la versione di freesbee " + daoe);
        }
    }
}
