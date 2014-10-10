package it.unibas.icar.freesbeesp.controllo.filtri;

import it.unibas.icar.freesbeesp.modello.Utente;
import it.unibas.icar.freesbeesp.sso.SSOClient;
import it.unibas.icar.freesbeesp.util.FileUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FiltroPost implements Filter {

    private org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(this.getClass());

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (logger.isInfoEnabled()) {
            logger.info("[Filtro] - " + this.getClass());
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);
        String servletPath = httpRequest.getServletPath();
        Map<String, String> mappaParametriPDDinamica = new HashMap<String, String>();
        Enumeration enumeration = httpRequest.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String chiaveHeaderHttpRequest = (String)enumeration.nextElement();
            mappaParametriPDDinamica.put(chiaveHeaderHttpRequest.toUpperCase(), httpRequest.getHeader(chiaveHeaderHttpRequest));
        }

        String risorsa = httpRequest.getHeader("risorsa");
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

        Utente utente = (Utente) session.getAttribute("utente");

        if ((servletPath.equals("/forwardToPD.post"))) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            FileUtil.copyStream(request.getInputStream(), out);
            String stringaRichiesta = out.toString();
            logger.info("Ho ricevuto una richiesta di inoltro alla PD all'indirizzo " + risorsa);
            logger.info("Messaggio da inviare : " + stringaRichiesta);

            String risposta = new SSOClient().sendSoapSamlRequest(mappaParametriPDDinamica, risorsa, utente.getPortafoglioAsserzioni(), utente.getSsoSession(), stringaRichiesta);

            FileUtil.copyStream(new ByteArrayInputStream(risposta.getBytes()), httpResponse.getOutputStream());
        } else {
            chain.doFilter(request, response);
        }
    }
}

