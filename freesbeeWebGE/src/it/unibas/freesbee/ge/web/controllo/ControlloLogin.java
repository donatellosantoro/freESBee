package it.unibas.freesbee.ge.web.controllo;

import it.unibas.freesbee.ge.web.modello.ConfigurazioneStatico;
import it.unibas.freesbee.ge.web.modello.Utente;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOException;
import it.unibas.freesbee.ge.web.persistenza.soap.DAOAutenticazioneSOAP;
import it.unibas.freesbee.ge.web.vista.VistaLogin;
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
    private DAOAutenticazioneSOAP daoAutenticazione;
    private static ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public String login() {
        String server = conf.getIndirizzoGE();
        if (!server.endsWith("/")) {
            conf.setIndirizzoGE(server + "/");
        }
        this.utente.setNomeUtente(this.vista.getUtente().getNomeUtente());
        String passwordInChiaro = this.vista.getUtente().getPassword();
        String passwordMD5 = this.vista.getUtente().md5hash(passwordInChiaro);
        this.utente.setPassword(passwordMD5);

        try {
            boolean autenticazione = this.daoAutenticazione.autentica(this.utente);
            if (autenticazione) {
                this.utente.setAutenticato(true);
                return "loginEffettuato";
            }
        } catch (DAOException ex) {
            this.errore = ex.getMessage();
            logger.error("Impossibile effettuare l'autenticazione. " + ex);
        }
        return null;
    }

    public String logout() {
        this.utente.setNomeUtente("");
        this.utente.setPassword("");
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

    public DAOAutenticazioneSOAP getDaoAutenticazione() {
        return daoAutenticazione;
    }

    public void setDaoAutenticazione(DAOAutenticazioneSOAP daoAutenticazione) {
        this.daoAutenticazione = daoAutenticazione;
    }
}
