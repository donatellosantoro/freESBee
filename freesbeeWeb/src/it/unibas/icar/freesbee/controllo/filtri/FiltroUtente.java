package it.unibas.icar.freesbee.controllo.filtri;

import it.unibas.icar.freesbee.modello.Utente;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FiltroUtente implements Filter {

    private Log logger = LogFactory.getLog(this.getClass());

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String servletPath = httpRequest.getServletPath();
        if (!servletPath.startsWith("/a4j")
         && !servletPath.equals("/index.faces")
         && !servletPath.equals("/schermoLogin.faces")
         && !servletPath.equals("/schermoErrore.faces")) {
            
            if (!httpRequest.isRequestedSessionIdValid()) {
                schermoErrore(request, response);
                return;
            }
            
            HttpSession session = httpRequest.getSession(false);

            Utente utente = (Utente) session.getAttribute("utente");
            if (utente == null) {
                if (logger.isInfoEnabled())
                    logger.info("utente null");
                schermoErrore(request, response);
                return;
            }
            if (!utente.isAutenticato()) {
                if (logger.isInfoEnabled())
                    logger.info("utente non autenticato");
                schermoErrore(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void schermoErrore(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String contextPath = httpRequest.getContextPath();
        String servletPath = httpRequest.getServletPath();
        HttpSession session = httpRequest.getSession();
        ServletContext application = session.getServletContext();
        application.log("FiltroSessione: Sto filtrando la richiesta a " + contextPath + " - " + servletPath);
        RequestDispatcher dispatcher = application.getRequestDispatcher("/schermoErrore.faces");
        dispatcher.forward(request, response);
    }
}
