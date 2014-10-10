package it.unibas.silvio.web.controllo;

import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOUtente;
import it.unibas.silvio.web.vista.VistaLogin;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloLogin {

    private Log logger = LogFactory.getLog(this.getClass());
    private VistaLogin vista;
    private String messaggio;
    private String errore;
    private IDAOUtente daoUtente;
    private Utente utente;
    private ControlloEseguiIstanza controlloEseguiIstanza;

    public String login() {
        Utente u = null;
        try {
            u = this.daoUtente.findByNomeUtente(this.vista.getUtente().getNome());
            if (u != null) {
                logger.info("Trovato utente: \"" + u.getNome() + "\"");
                String md5 = u.md5hash(this.vista.getUtente().getPassword());
                logger.info("MD5: " + md5);
                if (u.getPassword().equals(md5)) {
                    utente.setNome(u.getNome());
                    utente.setRuolo(u.getRuolo());
                    utente.setAutenticato(true);

                    String avvioRapido = this.vista.getAvvioRapidoSelezionato();
                    int idAvvioRapido = Integer.parseInt(avvioRapido);
                    if (avvioRapido == null || idAvvioRapido == 0) {
                        return "home";
                    }else{
                        return controlloEseguiIstanza.avviaApp(idAvvioRapido);
                    }
                }
                utente.setNome(null);
                utente.setRuolo(null);
                utente.setAutenticato(false);
            }
            this.errore = "Errore! Password Errata";
        } catch (DAOException daoe) {
            logger.debug("Errore " + daoe);
            this.errore = "Errore! Utente non trovato";
        }
        return null;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            logger.info("INVALIDO LA SESSIONE");
            session.invalidate();
        }
        return "logout";
    }

    public String forwardCambiaPassword() {
        return "forwardCambiaPassword";
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public VistaLogin getVista() {
        return vista;
    }

    public void setVista(VistaLogin vista) {
        this.vista = vista;
    }

    public IDAOUtente getDaoUtente() {
        return daoUtente;
    }

    public void setDaoUtente(IDAOUtente daoUtente) {
        this.daoUtente = daoUtente;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ControlloEseguiIstanza getControlloEseguiIstanza() {
        return controlloEseguiIstanza;
    }

    public void setControlloEseguiIstanza(ControlloEseguiIstanza controlloEseguiIstanza) {
        this.controlloEseguiIstanza = controlloEseguiIstanza;
    }

    
}
