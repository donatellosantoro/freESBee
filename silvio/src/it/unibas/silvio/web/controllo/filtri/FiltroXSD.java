package it.unibas.silvio.web.controllo.filtri;

import it.unibas.silvio.modello.Utente;
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

public class FiltroXSD implements Filter {

    private static Log logger = LogFactory.getLog(FiltroXSD.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("Richiesto un file xsd...");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null || utente.getNome() == null || utente.getNome().equals("")) {
            if (logger.isInfoEnabled()) {
                logger.info("Non c'e' nessun utente nella sessione");
            }
            return;
        }
        httpResponse.setContentType("text/xsd");
        httpResponse.getWriter().print(utente.getFileXSD());
        httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + utente.getNomeFileXSD() + "\"");
    }

    public void destroy() {
    }
}
