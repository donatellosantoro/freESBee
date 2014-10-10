package it.unibas.icar.freesbeesp.strategy;

import it.unibas.icar.freesbeesp.modello.Utente;
import it.unibas.icar.freesbeesp.sso.driver.ISSODriver;
import it.unibas.icar.freesbeesp.sso.driver.SSODriverFactory;
import java.io.IOException;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserLogin implements ILoginStrategy {

    private Log logger = LogFactory.getLog(this.getClass());

    public void login(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);

        String risorsa = httpRequest.getParameter("risorsa");
        if (risorsa != null) {
            session.setAttribute("risorsa", risorsa);
        } else {
            risorsa = (String) session.getAttribute("risorsa");
        }
        String silSessionId = httpRequest.getParameter("silSessionId");
        if (silSessionId != null) {
            session.setAttribute("silSessionId", silSessionId);
        } else {
            silSessionId = (String) session.getAttribute("silSessionId");
        }
        String uriDiDestinazione = httpRequest.getParameter("uriDiDestinazione");
        if (uriDiDestinazione != null) {
            session.setAttribute("uriDiDestinazione", uriDiDestinazione);
        } else {
            uriDiDestinazione = (String) session.getAttribute("uriDiDestinazione");
        }

        if (!httpRequest.isRequestedSessionIdValid()) {
            schermoLogin(request, response);
            return;
        }
        Utente utente = (Utente) session.getAttribute("utente");
        String invalidate = httpRequest.getParameter("invalidate");
        if (invalidate != null && !session.isNew() && utente != null) {
            utente.setPortafoglioAsserzioni(null);
        }

        if (utente != null && utente.getSilSessionId() != null && !silSessionId.equals(utente.getSilSessionId())) {
            //LA SESSIONE DEL SIL E' CAMBIATA, QUINDI DEVO INVALIDARE LA SESSIONE CORRENTE
            logger.info("La sessione del sil e' cambiata. Invalidamo la sessione corrente.");
            session.setAttribute("utente", new Utente());
            schermoLogin(request, response);
            return;
        }

        if ((utente != null) && (utente.getNomeUtente() != null) && (utente.getPortafoglioAsserzioni() != null)) {
            logger.info("Redirect a " + uriDiDestinazione + ";jsessionid=" + silSessionId + "?freesbeeSPSessionId=" + session.getId());

            httpResponse.sendRedirect(uriDiDestinazione
                    + ";jsessionid=" + silSessionId
                    + "?freesbeeSPSessionId=" + session.getId());
            return;
        }

        chain.doFilter(request, response);
    }

    private void schermoLogin(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);
        ServletContext application = session.getServletContext();
        String risorsa = (String) session.getAttribute("risorsa");

        ISSODriver ssoDriver = new SSODriverFactory().getIstance();
        Map<String, String> mappaIdP = ssoDriver.verificaDiscoveryService(risorsa);
        if (mappaIdP != null) {
            logger.info("Sono stati trovati " + mappaIdP.size() + " IdP");
            session.setAttribute("mappaIdP", mappaIdP);
        }

        RequestDispatcher dispatcher = application.getRequestDispatcher("/schermoLogin.faces");
        dispatcher.forward(request, response);
    }
}
