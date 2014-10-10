package it.unibas.silvio.web.controllo.filtri;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.hibernate.DAOIstanzaOperationHibernate;
import it.unibas.silvio.web.controllo.ControlloEseguiIstanza;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FiltroApp implements Filter {

    private Log logger = LogFactory.getLog(this.getClass());

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String servletPath = httpRequest.getServletPath();
        String queryString = httpRequest.getQueryString();
        if (queryString == null || !queryString.contains("id=")) {
            logger.error("Per contattare la pagina app.jsp bisogna specificare anche l'id dell'istanza da avviare");
            return;
        }

        String stringId = "";
        String[] coppia = queryString.split("&");
        for (String s : coppia) {
            String[] ss = s.split("=");
            if (ss.length == 2 && ss[0].equals("id")) {
                stringId = ss[1];
            }
        }
        if (stringId == null || stringId.isEmpty()) {
            logger.error("Per contattare la pagina app.jsp bisogna specificare anche l'id dell'istanza da avviare");
            return;
        }

        long id;
        try {
            id = Long.parseLong(stringId);
        } catch (NumberFormatException e) {
            logger.error("Per contattare la pagina app.jsp bisogna specificare anche l'id numerico dell'istanza da avviare");
            return;
        }

        logger.info("********* Istanza id: " + id);

        try {
            IstanzaOperation istanzaOperation = new DAOIstanzaOperationHibernate().findById(id, true);
            if (istanzaOperation.getIstanzaPortType().isFruizione() && istanzaOperation.isAvvioRapido() && istanzaOperation.isAutenticazioneFederata()) {
                caricaIstanza(id,httpRequest);
            } else {
                logger.error("Per avviare automaticamente un'istanza questa dev'essere di fruizione, protetta da autenticazione federata e abilitata all'avvio rapido");
                return;
            }
        } catch (DAOException ex) {
            logger.warn("Impossibile leggere l'elenco delle istanze.");
            return;
        }

        chain.doFilter(request, response);
    }

    private void caricaIstanza(long id, HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(true);
        Utente utente = new Utente();

        utente.setNome("avvioRapido");
        utente.setRuolo("avvioRapido");
        utente.setAutenticato(true);

        session.setAttribute("utente",utente);

        //TODO: chiamare in qualche modo controlloEseguiIstanza.avviaApp(id);
        //Probabilmente il filtroapp non va bene ma ci vuole un PhaseListener
    }
}
