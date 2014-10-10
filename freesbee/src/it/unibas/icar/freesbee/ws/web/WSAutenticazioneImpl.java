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
        if (logger.isInfoEnabled()) logger.info("Richiesta l'autenticazione di un utente");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (logger.isDebugEnabled()) {
                if (logger.isDebugEnabled()) logger.debug("Richiedo al dao " + daoUtente + " l'utente " + nomeUtente);
            }
            Utente utente = daoUtente.findByNomeUtente(nomeUtente);
            sessionFactory.getCurrentSession().getTransaction().commit();
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente. Utente inesistente: " + nomeUtente);
            }
        } catch (DAOException ex) {
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    if (logger.isDebugEnabled()) logger.debug("C'è una transazione di hibernate attiva e faccio il rollback");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            logger.error("Si e' verificato un errore durante l'autenticazione dell'utente" + ex);
            throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente" + ex);
        }
    }

    public void cambiaPassword(String nuovaPassword, String nomeUtente) throws SOAPFault {
        if (logger.isInfoEnabled())logger.info("Richiesta la modifica della password di un utente");
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
                    if (logger.isDebugEnabled()) logger.debug("C'è una transazione di hibernate attiva e faccio il rollback");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            logger.error("Si e' verificato un errore durante la modifica della password dell'utente" + ex);
            throw new SOAPFault("Si e' verificato un errore durante la modifica della password dell'utente" + ex);
        }
    }

    public String getRuolo(String nomeUtente) throws SOAPFault {
        if (logger.isInfoEnabled()) {
            logger.info("Richiesto il ruolo di un utente");
        }
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (logger.isDebugEnabled()) if (logger.isDebugEnabled()) logger.debug("Richiedo al dao " + daoUtente + " l'utente " + nomeUtente);
            Utente utente = daoUtente.findByNomeUtente(nomeUtente);
            sessionFactory.getCurrentSession().getTransaction().commit();
            if (utente == null) {
                throw new SOAPFault("Si e' verificato un errore durante l'individuazione del ruolo dell'utente. Utente inesistente: " + nomeUtente);
            } else {
                return utente.getRuolo();
            }
        } catch (DAOException ex) {
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    if (logger.isDebugEnabled()) logger.debug("C'è una transazione di hibernate attiva e faccio il rollback");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            logger.error("Si e' verificato un errore durante l'autenticazione dell'utente" + ex);
            throw new SOAPFault("Si e' verificato un errore durante l'autenticazione dell'utente" + ex);
        }
    }
}
