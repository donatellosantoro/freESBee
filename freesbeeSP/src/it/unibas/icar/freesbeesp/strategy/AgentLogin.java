package it.unibas.icar.freesbeesp.strategy;

import it.unibas.icar.freesbeesp.modello.Utente;
import it.unibas.icar.freesbeesp.sso.SSOClient;
import it.unibas.icar.freesbeesp.sso.SSOSessionResponse;
import it.unibas.icar.freesbeesp.sso.driver.ISSODriver;
import it.unibas.icar.freesbeesp.sso.driver.SSODriverFactory;
import it.unibas.icar.freesbeesp.util.FileUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AgentLogin implements ILoginStrategy {

    private Log logger = LogFactory.getLog(this.getClass());
    private Utente utente = new Utente();

    public void login(ServletRequest request, ServletResponse response, FilterChain chain) {
        effettuaLogin(request, response, chain);
    }

    private void effettuaLogin(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);
        try {

            Map<String, String> mappaParametriPDDinamica = new HashMap<String, String>();
            Enumeration enumeration = httpRequest.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String chiaveHeaderHttpRequest = (String) enumeration.nextElement();
                mappaParametriPDDinamica.put(chiaveHeaderHttpRequest.toUpperCase(), httpRequest.getHeader(chiaveHeaderHttpRequest));
            }

            String risorsa = httpRequest.getParameter("risorsa");
            if (risorsa == null) {
                return;
            }

            String username = httpRequest.getParameter("username");
            if (username == null) {
                return;
            }

            String password = httpRequest.getParameter("password");
            if (password == null) {
                return;
            }

            String idp = httpRequest.getParameter("idp");

            ISSODriver ssoDriver = new SSODriverFactory().getIstance();
            SSOSessionResponse shibSession = ssoDriver.createSession(username, password, risorsa, idp);
            if (shibSession != null) {
                utente.setNomeUtente(username);
                utente.setPortafoglioAsserzioni(shibSession.getElementAssertion());
                utente.setSsoSession(shibSession.getSession());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                FileUtil.copyStream(request.getInputStream(), out);
                String stringaRichiesta = out.toString();
                if (logger.isDebugEnabled()) logger.debug("Ho ricevuto una richiesta di inoltro alla PD all'indirizzo " + risorsa);
                if (logger.isDebugEnabled()) logger.debug("Messaggio da inviare : " + stringaRichiesta);

                String risposta = new SSOClient().sendSoapSamlRequest(mappaParametriPDDinamica, risorsa, utente.getPortafoglioAsserzioni(), utente.getSsoSession(), stringaRichiesta);

                FileUtil.copyStream(new ByteArrayInputStream(risposta.getBytes()), httpResponse.getOutputStream());
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Si e' verificato un errore : " + ex);
        }
        return;
    }
}
