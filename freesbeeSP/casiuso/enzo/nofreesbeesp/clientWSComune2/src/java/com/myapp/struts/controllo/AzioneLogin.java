package com.myapp.struts.controllo;

import com.myapp.struts.form.FormLogin;
import it.unibas.icar.freesbeesp.modello.Utente;
import it.unibas.icar.freesbeesp.sso.SSOSessionResponse;
import it.unibas.icar.freesbeesp.sso.driver.ISSODriver;
import it.unibas.icar.freesbeesp.sso.driver.SSODriverFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;

public class AzioneLogin extends Action {

    private Log logger = LogFactory.getLog(this.getClass());
    private String risorsa = "https://sp.comune.pz/PD_CERCA_CITTADINO";

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionMessages errori = new ActionMessages();

        HttpSession session = request.getSession();
        FormLogin formLogin = (FormLogin) form;
        String nomeUtente = formLogin.getNomeUtente();
        String password = formLogin.getPassword();
        Utente utente = null;

        try {
            ISSODriver ssoDriver = new SSODriverFactory().getIstance();
            SSOSessionResponse shibSession = ssoDriver.createSession(nomeUtente, password, risorsa, null);
            if (shibSession != null) {
                utente = new Utente();
                utente.setNomeUtente(nomeUtente);
                utente.setPortafoglioAsserzioni(shibSession.getElementAssertion());
                utente.setSilSessionId(session.getId());
                utente.setSsoSession(shibSession.getSession());
                session.setAttribute("utente", utente);
                return mapping.findForward("successo");
            }
        } catch (Exception exception) {
            errori.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Errore nella richiesta all'SP"));
            saveErrors(request, errori);
        }
        return mapping.findForward("fallimento");

    }

}
