package it.unibas.icar.freesbeesp.controllo.filtri;

import it.unibas.icar.freesbeesp.strategy.ILoginStrategy;
import it.unibas.icar.freesbeesp.strategy.LoginFactory;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FiltroSSO implements Filter {

    private Log logger = LogFactory.getLog(this.getClass());

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("[Filtro] - " + this.getClass());
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ILoginStrategy loginStrategy = null;
        String tipologiaAutenticazione = httpRequest.getParameter("autenticazione");
        // se autenticazione null in automatico autenticazione utente
        if ((tipologiaAutenticazione != null) && (tipologiaAutenticazione.equals(LoginFactory.AUTENTICAZIONE_AGENTE))) {
            if (logger.isInfoEnabled()) {
                logger.info("Sto eseguendo l'autenticazione di un agente");
            }
            loginStrategy = LoginFactory.getInstance(LoginFactory.AUTENTICAZIONE_AGENTE);
            loginStrategy.login(request, response, chain);
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("Sto eseguendo l'autenticazione di un utente");
            }
            loginStrategy = LoginFactory.getInstance(LoginFactory.AUTENTICAZIONE_UTENTE);
            loginStrategy.login(request, response, chain);
        }
    }

}
