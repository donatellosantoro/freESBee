package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOUtente;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class WSAutenticazioneImpl implements IWSAutenticazione {

    private static Log logger = LogFactory.getLog(WSAutenticazioneImpl.class);
    private IDAOUtente daoUtente;

    public WSAutenticazioneImpl(IDAOUtente daoUtente) {
        this.daoUtente = daoUtente;
    }

    public void autentica(String nomeUtente) throws SOAPFault {
        if (logger.isDebugEnabled()) logger.debug("Richiesta l'autenticazione di un utente");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (logger.isDebugEnabled()) logger.debug("Richiesto l'utente " + nomeUtente + " al DB.");
            Utente utente = daoUtente.findByNomeUtente(nomeUtente);
            sessionFactory.getCurrentSession().getTransaction().commit();
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente. L'utente " + nomeUtente + " e' inesistente.");
            }
        } catch (DAOException ex) {
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    if (logger.isDebugEnabled()) logger.debug("Si sta eseguendo il rollback della transazione sul DB.");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            logger.error("Si e' verificato un errore durante l'autenticazione dell'utente.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente.");
        }
    }

    public void cambiaPassword(String nuovaPassword, String nomeUtente) throws SOAPFault {
        if (logger.isDebugEnabled())logger.debug("Richiesta la modifica della password di un utente");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Utente utente = daoUtente.findByNomeUtente(nomeUtente);
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente");
            }
            utente.setPassword(nuovaPassword);
            daoUtente.makePersistent(utente);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    if (logger.isDebugEnabled()) logger.debug("Si sta eseguendo il rollback della transazione sul DB.");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            logger.error("Si e' verificato un errore durante la modifica della password dell'utente.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la modifica della password dell'utente.");
        }
    }

    public String getRuolo(String nomeUtente) throws SOAPFault {
        if (logger.isDebugEnabled()) logger.debug("Richiesto il ruolo di un utente");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (logger.isDebugEnabled()) logger.debug("Richiedo al dao " + daoUtente + " l'utente " + nomeUtente);
            Utente utente = daoUtente.findByNomeUtente(nomeUtente);
            sessionFactory.getCurrentSession().getTransaction().commit();
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'individuazione del ruolo dell'utente. L'utente " + nomeUtente + " e' inesistente.");
            } else {
                return utente.getRuolo();
            }
        } catch (DAOException ex) {
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    if (logger.isDebugEnabled()) logger.debug("Si sta eseguendo il rollback della transazione sul DB.");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            logger.error("Si e' verificato un errore durante l'autenticazione dell'utente.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente.");
        }
    }
}
