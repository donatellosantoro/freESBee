package it.unibas.freesbee.websla.controllo;

import it.unibas.freesbee.websla.modello.ConfigurazioneStatico;
import it.unibas.freesbee.websla.modello.Utente;
import it.unibas.freesbee.websla.persistenza.soap.DAOAutenticazioneSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOConfigurazioneSOAP;
import it.unibas.freesbee.websla.persistenza.soap.DAOCostanti;
import it.unibas.freesbee.websla.persistenza.soap.DAOException;
import it.unibas.freesbee.websla.utilita.UtilitaMessaggi;
import it.unibas.freesbee.websla.vista.VistaLogin;
import it.unibas.freesbee.websla.ws.web.stub.DatiConfigurazione;
import java.net.URL;
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
    private DAOConfigurazioneSOAP daoConfigurazione;
    private ConfigurazioneStatico conf = ConfigurazioneStatico.getInstance();

    public String login() {
        String server = conf.getIndirizzoModuloSLA();
      
        if (!server.endsWith("/")) {
            conf.setIndirizzoModuloSLA(server + "/");
        }
        this.utente.setNomeUtente(this.vista.getUtente().getNomeUtente());
        String passwordInChiaro = this.vista.getUtente().getPassword();
        String passwordMD5 = this.vista.getUtente().md5hash(passwordInChiaro);
        this.utente.setPassword(passwordMD5);

        try {
            URL url = new URL(conf.getIndirizzoModuloSLA() + DAOCostanti.URL_WSDL_AUTENTICAZIONE);
            String wsdl = UtilitaMessaggi.invocaWSDL(url);
            if (wsdl.equals("")) {
                this.errore = "Verificare l'indirizzo del modulo di freESBeeSLA inserito.";
            }
        } catch (Exception ex) {
            this.errore = "Verificare l'indirizzo del modulo di freESBeeSLA inserito.";
            logger.error("Impossibile effettuare l'autenticazione. " + ex);
            return null;
        }



        try {
            boolean autenticazione = this.daoAutenticazione.autentica(this.utente);
            if (autenticazione) {
                this.utente.setAutenticato(true);
            }
        } catch (DAOException ex) {
            this.errore = ex.getMessage();
            logger.error("Impossibile effettuare l'autenticazione. " + ex);
            return null;
        }

        try {
            if (!verificaTipoNICA()) {
                logger.error("Impossibile verificare la comunicazione con il NICA ");
                this.errore = "Impossibile verificare la comunicazione con il NICA. ";
                return null;
            }
        } catch (DAOException ex) {
            logger.error("Impossibile verificare la comunicazione con il NICA " + ex);
            this.errore = "Impossibile verificare la comunicazione con il NICA. ";
            return null;
        }

        try {
            DatiConfigurazione dati = acquisizioneDatiConfigurazione(utente);
            if (dati == null) {
                return null;
            } else {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                session.setAttribute("datiConfigurazione", dati);
            }
            return "loginEffettuato";
        } catch (DAOException ex) {
            logger.error("Impossibile caricare la configurazione del modulo degli SLA " + ex);
            this.errore = "Impossibile caricare la configurazione del modulo degli SLA.";
        }

          return null;
    }

    private boolean verificaTipoNICA() throws DAOException {
        //Verifica il tipo di NICA
        String tipoNICA = getDaoConfigurazione().verificaTipoNICA(utente);
        logger.debug("TIPO NICA:" + tipoNICA);
        conf.setTipoNica(tipoNICA);

        if (tipoNICA.equalsIgnoreCase(ConfigurazioneStatico.FREESBEE)) {
            logger.info("Il NICA e' " + tipoNICA);
            return true;
        }

        if (tipoNICA.equalsIgnoreCase(ConfigurazioneStatico.NICA)) {
            this.errore = "Il modulo di monitoraggio si appoggia ad un nica che non è di tipo freesbe.\n Verificare la configurazione del modulo di monitoraggio.";
            return false;
        }

        if (tipoNICA.equalsIgnoreCase(ConfigurazioneStatico.NON_VERIFICATO)) {
            this.errore = "Non è stato possibile verificare il tipo di NICA configurato.";
            return false;
        }

        return false;
    }

    private DatiConfigurazione acquisizioneDatiConfigurazione(Utente utente) throws DAOException {
        //Verifica il tipo di NICA
        DatiConfigurazione datiConfigurazione = getDaoConfigurazione().caricaDatiConfigurazione(utente);
        logger.info("Carico configurazione:");

        return datiConfigurazione;
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


    public DAOConfigurazioneSOAP getDaoConfigurazione() {
        return daoConfigurazione;
    }


    public void setDaoConfigurazione(DAOConfigurazioneSOAP daoConfigurazione) {
        this.daoConfigurazione = daoConfigurazione;
    }

}
