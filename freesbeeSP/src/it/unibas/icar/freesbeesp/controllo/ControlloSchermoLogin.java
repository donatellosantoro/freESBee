package it.unibas.icar.freesbeesp.controllo;

import it.unibas.icar.freesbeesp.modello.Utente;
import it.unibas.icar.freesbeesp.sso.SSOSessionResponse;
import it.unibas.icar.freesbeesp.sso.driver.ISSODriver;
import it.unibas.icar.freesbeesp.sso.driver.SSODriverFactory;
import it.unibas.icar.freesbeesp.vista.VistaSchermoLogin;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloSchermoLogin {

    private Log logger = LogFactory.getLog(this.getClass());
    private Utente utente;
    private String samlResponse;
    private boolean visualizzaSamlResponse;
    private Map<String, String> mappaIdP;
    private VistaSchermoLogin vistaSchermo;
    private String errore;
    private String uriDiProvenienza;
    private String idSessione;
    private String risorsa;

    public ControlloSchermoLogin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.uriDiProvenienza = (String) session.getAttribute("uriDiDestinazione");
        this.idSessione = (String) session.getAttribute("silSessionId");
        this.risorsa = (String) session.getAttribute("risorsa");
        mappaIdP = (Map<String, String>) session.getAttribute("mappaIdP");
    }

    public String login() {
        try {
            ISSODriver ssoDriver = new SSODriverFactory().getIstance();

            String username = this.vistaSchermo.getNomeUtente();
            String password = this.vistaSchermo.getPassword();
            String idp = this.vistaSchermo.getIdpSelezionato();
            SSOSessionResponse shibSession = ssoDriver.createSession(username, password, risorsa, idp);
            if (shibSession != null) {
                utente.setNomeUtente(this.vistaSchermo.getNomeUtente());
                utente.setPortafoglioAsserzioni(shibSession.getElementAssertion());
                utente.setSilSessionId(idSessione);
                utente.setSsoSession(shibSession.getSession());
                return "redirectASchermoApplicazione";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Si e' verificato un errore : " + ex);
            this.errore = ex.getMessage();
        }
        return null;
    }

    public String annullaLogin() {
        return "vaiASchermoLogin";
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public VistaSchermoLogin getVistaSchermoLogin() {
        return vistaSchermo;
    }

    public void setVistaSchermoLogin(VistaSchermoLogin vistaSchermo) {
        vistaSchermo.riempiSelectItem(mappaIdP);
        this.vistaSchermo = vistaSchermo;
    }

    public String getSamlResponse() {
        return samlResponse;
    }

    public void setSamlResponse(String samlResponse) {
        this.samlResponse = samlResponse;
    }

    public boolean isVisualizzaSamlResponse() {
        return visualizzaSamlResponse;
    }

    public void setVisualizzaSamlResponse(boolean visualizzaSamlResponse) {
        this.visualizzaSamlResponse = visualizzaSamlResponse;
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

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }
}
