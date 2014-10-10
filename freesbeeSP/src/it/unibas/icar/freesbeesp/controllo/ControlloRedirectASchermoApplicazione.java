package it.unibas.icar.freesbeesp.controllo;

import it.unibas.icar.freesbeesp.modello.Utente;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class ControlloRedirectASchermoApplicazione {
    
    private Utente utente;
    private String sessionId;
    private String uriDiProvenienza;
    private String idSessione;
    
    public ControlloRedirectASchermoApplicazione () {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
  
        this.uriDiProvenienza = (String) session.getAttribute("uriDiProvenienza");
        this.idSessione = (String) session.getAttribute("idSessione");
    }
    
    public String redirectASchermoApplicazione() {
        return "redirectASchermoApplicazione";
        //return "redirectASchermoApplicazione?faces-redirect=true";
    }
    
    public String sessione() {        
        return "visualizzaUtenteInSessione";
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIdSessione() {
        return idSessione;
    }

    public void setIdSessione(String idSessione) {
        this.idSessione = idSessione;
    }

    public String getUriDiProvenienza() {
        return uriDiProvenienza;
    }

    public void setUriDiProvenienza(String uriDiProvenienza) {
        this.uriDiProvenienza = uriDiProvenienza;
    }
}
