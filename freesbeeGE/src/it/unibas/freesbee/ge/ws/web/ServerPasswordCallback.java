/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.freesbee.ge.ws.web;


import it.unibas.freesbee.ge.modello.Utente;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOUtente;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSPasswordCallback;
import org.hibernate.SessionFactory;


public class ServerPasswordCallback implements CallbackHandler {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOUtente daoUtente;

    public ServerPasswordCallback(IDAOUtente daoUtente) {
        this.daoUtente = daoUtente;
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = null;
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            for (int i = 0; i < callbacks.length; i++) {
                pc = (WSPasswordCallback) callbacks[i];
                String nomeUtente = pc.getIdentifer();

                sessionFactory.getCurrentSession().beginTransaction();
                if (logger.isDebugEnabled()) {
                    logger.debug("Richiedo al dao " + daoUtente + " l'utente " + nomeUtente);
                }
                Utente utente = daoUtente.findByNomeUtente(nomeUtente);
                sessionFactory.getCurrentSession().getTransaction().commit();
                if (utente != null) {
                    if (logger.isInfoEnabled()) {
                        logger.info("E' stato trovato l'utente " + utente.getNomeUtente() + " nel DB");
                    }
                    pc.setPassword(utente.getPassword());
                } else {
                    logger.error("Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente: " + nomeUtente);
                    throw new UnsupportedCallbackException(pc, "Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente: " + nomeUtente);
                }
            }
        } catch (DAOException ex) {
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    logger.debug("C'e' una transazione di hibernate attiva e faccio il rollback");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            logger.error("Si e' verificato un errore durante l'autenticazione dell'utente " + ex);
            throw new UnsupportedCallbackException(pc, "Si e' verificato un errore durante l'autenticazione dell'utente " + ex);
        }
    }
}
