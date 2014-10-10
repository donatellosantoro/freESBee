package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOUtente;
import it.unibas.silvio.web.vista.VistaCambiaPassword;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloCambiaPassword {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaCambiaPassword vista;
    private String messaggio;
    private String errore;
    private String conferma;
    private Utente utente;
    private IDAOUtente daoUtente;

    public String cambiaPassword() {
        Utente utenteDB = null;
        try {
            utenteDB = this.daoUtente.findByNomeUtente(this.utente.getNome());
            if (utenteDB != null) {
                if (!this.vista.getVecchiaPassword().equals(utente.getPassword())) {
                    this.setErrore("ERRORE! La vecchia password non è valida!");
                    return this.forwardCambiaPassword();
                }
                String nuovaMD5 = utenteDB.md5hash(this.vista.getNuovaPassword());
                utenteDB.setPassword(nuovaMD5);
                this.daoUtente.makePersistent(utenteDB);
                return "passwordCambiata";
            }
        } catch (DAOException daoe) {
            logger.error(daoe);
        }

        return null;
    }

    public String forwardCambiaPassword() {
        return "forwardCambiaPassword";
    }

    public String backwardHome() {
        return "annullaCambiaPassword";
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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public IDAOUtente getDaoUtente() {
        return daoUtente;
    }

    public void setDaoUtente(IDAOUtente daoUtente) {
        this.daoUtente = daoUtente;
    }
}
