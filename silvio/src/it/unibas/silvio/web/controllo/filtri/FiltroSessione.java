package it.unibas.silvio.web.controllo.filtri;

import it.unibas.silvio.modello.ConfigurazioneStatico;
import it.unibas.silvio.web.vista.VistaEseguiIstanza;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FiltroSessione implements Filter {

    private static Log logger = LogFactory.getLog(FiltroSessione.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("[Filtro] - " + this.getClass());
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        VistaEseguiIstanza vistaEseguiIstanza = (VistaEseguiIstanza) session.getAttribute("vistaEseguiIstanza");

        String freesbeeSPSessionId = httpRequest.getParameter("freesbeeSPSessionId");
        if (freesbeeSPSessionId != null) {
            String urlInvio = vistaEseguiIstanza.getUrlInvio();
            urlInvio += ";jsessionid=" + freesbeeSPSessionId;
            vistaEseguiIstanza.setUrlInvio(urlInvio);
        }

        if ((httpRequest.getServletPath().equals("/acquisisciPortafoglio.faces")) ||
                (httpRequest.getServletPath().equals("/acquisisciPortafoglioInvalida.faces")) ||
                (httpRequest.getServletPath().equals("/app/acquisisciPortafoglioApp.faces")) ||
                (httpRequest.getServletPath().equals("/app/acquisisciPortafoglioInvalidaApp.faces"))) {
            String risorsa = vistaEseguiIstanza.getIndirizzoInvio();
            if (risorsa == null || risorsa.trim().isEmpty()) {
                throw new ServletException("Impossibile inoltrare la richiesta. La risorsa da contattare e' nulla");
            }
            String paginaRitorno = "eseguiIstanzaOperation";
            if (httpRequest.getServletPath().equals("/app/acquisisciPortafoglioApp.faces") ||
                httpRequest.getServletPath().equals("/app/acquisisciPortafoglioInvalidaApp.faces")) {
                paginaRitorno = "app/index";
            }
            String indirizzoRedirect = "";
            String indirizzoSP = ConfigurazioneStatico.getInstance().getFreesbeeSPURL();

            String tipoProtocollo;
            if (request.isSecure()) {
                tipoProtocollo = "https://";
            } else {
                tipoProtocollo = "http://";
            }

            if (freesbeeSPSessionId != null) {
                indirizzoRedirect = indirizzoSP + "/schermoLogin.faces;jsessionid=" + freesbeeSPSessionId + "?autenticazione=UTENTE&uriDiDestinazione="+ tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa;
            } else {
                indirizzoRedirect = indirizzoSP + "/schermoLogin.faces?autenticazione=UTENTE&uriDiDestinazione=" + tipoProtocollo + request.getServerName() + ":" + request.getServerPort() + "/" + httpRequest.getContextPath() + "/" + paginaRitorno + ".faces&silSessionId=" + session.getId() + "&risorsa=" + risorsa;
            }
            
            if (httpRequest.getServletPath().equals("/acquisisciPortafoglioInvalida.faces") ||
                httpRequest.getServletPath().equals("/app/acquisisciPortafoglioInvalidaApp.faces") ) {
                indirizzoRedirect += "&invalidate=true";
            }

            logger.info("Redirect a " + indirizzoRedirect);
            httpResponse.sendRedirect(indirizzoRedirect);
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
