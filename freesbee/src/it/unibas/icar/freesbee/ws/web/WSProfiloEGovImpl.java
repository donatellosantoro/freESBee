package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

public class WSProfiloEGovImpl implements IWSProfiloEGov {

    private static Log logger = LogFactory.getLog(WSProfiloEGovImpl.class);
    private IDAOProfiloEGov daoProfiloEgov;

    public WSProfiloEGovImpl(IDAOProfiloEGov daoProfiloEgov) {
        this.daoProfiloEgov = daoProfiloEgov;
    }

    public long addProfiloEGov(ProfiloEGov profiloEGov) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();

            if (profiloEGov.getId() == 0) {
                //E' UN PROFILO EGOV DA AGGIUNGERE
                if (logger.isDebugEnabled()) logger.debug("Richiesta l'aggiunta di un profilo EGov");
                daoProfiloEgov.makePersistent(profiloEGov);
            } else {
                //E' UN PROFILO EGOV DA MODIFICARTE
                if (logger.isDebugEnabled()) logger.debug("Richiesta la modifica di un profilo EGov");
                ProfiloEGov profiloEGovModificare = daoProfiloEgov.findById(profiloEGov.getId(), true);
                if (profiloEGovModificare == null) {
                    throw new SOAPFault("Impossibile modificare il profilo EGov. Non esiste alcun profilo con l'id specificato");
                }
                copiaProprieta(profiloEGov, profiloEGovModificare);
                daoProfiloEgov.makePersistent(profiloEGovModificare);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return profiloEGov.getId();
        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante l'aggiunta del profilo EGov.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante l'aggiunta del profilo EGov.");
        }
    }

    public void removeProfiloEGov(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la cancellazione di un profilo EGov");
            sessionFactory.getCurrentSession().beginTransaction();
            ProfiloEGov profiloEGovEliminare = daoProfiloEgov.findById(id, false);
            daoProfiloEgov.makeTransient(profiloEGovEliminare);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante l'eliminazione del profilo EGov.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante l'eliminazione del profilo EGov.");
        }
    }

    public List<ProfiloEGov> getListaProfiliEGov() throws SOAPFault {
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la lista dei profili EGov");
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            List<ProfiloEGov> listaProfiliEGov = daoProfiloEgov.findAll();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return listaProfiliEGov;
        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante la lettura della lista dei profili EGov.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura della lista dei profili EGov.");
        }
    }

    public ProfiloEGov getProfiloEGov(long id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            ProfiloEGov profiloEgov = daoProfiloEgov.findById(id, false);
            Hibernate.initialize(profiloEgov);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return profiloEgov;
        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante la lettura del profilo EGov.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura del profilo EGov.");
        }
    }

    private void copiaProprieta(ProfiloEGov profiloEGov, ProfiloEGov profiloEGovModificare) {
        profiloEGovModificare.setId(profiloEGov.getId());
        profiloEGovModificare.setConfermaRicezione(profiloEGov.isConfermaRicezione());
        profiloEGovModificare.setConsegnaInOrdine(profiloEGov.isConsegnaInOrdine());
        profiloEGovModificare.setGestioneDuplicati(profiloEGov.isGestioneDuplicati());
        profiloEGovModificare.setIdCollaborazione(profiloEGov.isIdCollaborazione());
        profiloEGovModificare.setScadenza(profiloEGov.getScadenza());
    }
}
