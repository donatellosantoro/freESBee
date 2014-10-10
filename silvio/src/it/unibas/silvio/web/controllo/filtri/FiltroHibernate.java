package it.unibas.silvio.web.controllo.filtri;

import it.unibas.silvio.persistenza.hibernate.DAOUtilHibernate;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
    
public class FiltroHibernate implements Filter {

    private static Log log = LogFactory.getLog(FiltroHibernate.class);

    private SessionFactory sessionFactory;
    
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Initializing filter...");
        log.debug("Obtaining SessionFactory from static HibernateUtil singleton");
        sessionFactory = DAOUtilHibernate.getSessionFactory();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            log.debug("Starting a database transaction");
            sessionFactory.getCurrentSession().beginTransaction();

            chain.doFilter(request, response);

            log.debug("Committing the database transaction");
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (StaleObjectStateException staleEx) {
            log.error("This interceptor does not implement optimistic concurrency control!");
            log.error("Your application will not work until you add compensation actions!");
            throw staleEx;
        } catch (Throwable ex) {
            ex.printStackTrace();
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    log.debug("Trying to rollback database transaction after exception");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                log.error("Could not rollback transaction after exception!", rbEx);
            }
            throw new ServletException(ex);
        }
    }
    
    public void destroy() {}

}    

