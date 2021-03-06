package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
import org.apache.camel.CamelContext;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

public class WSServizioApplicativoImpl implements IWSServizioApplicativo {

//    private static Log logger = LogFactory.getLog(WSServizioApplicativoImpl.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSServizioApplicativoImpl.class.getName());
    private CamelContext context;
    private IDAOServizioApplicativo daoServizioApplicativo;
    private IDAOPortaApplicativa daoPortaApplicativa;

    public WSServizioApplicativoImpl(CamelContext context, IDAOServizioApplicativo daoServizioApplicativo, IDAOPortaApplicativa daoPortaApplicativa) {
        this.context = context;
        this.daoServizioApplicativo = daoServizioApplicativo;
        this.daoPortaApplicativa = daoPortaApplicativa;
    }

    public CamelContext getContext() {
        return context;
    }

    public void setContext(CamelContext context) {
        this.context = context;
    }

    public void addServizioApplicativo(ServizioApplicativo servizioApplicativo) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (servizioApplicativo.getId() == 0) {
                //E' UN SERVIZIO APPLICATIVO DA AGGIUNGERE
                if (logger.isDebugEnabled()) logger.debug("Richiesta l'aggiunta del servizio applicativo " + servizioApplicativo);
                ServizioApplicativo nuovoServizioApplicativo = daoServizioApplicativo.findByNome(servizioApplicativo.getNome());
                if (nuovoServizioApplicativo != null) {
                    throw new SOAPFault("Impossibile aggiungere il servizio applicativo. Esiste gi� un servizio applicativo con il nome specificato");
                }
                daoServizioApplicativo.makePersistent(servizioApplicativo);
            } else {
                //E' UN SERVIZIO APPLICATIVO DA MODIFICARE
                if (logger.isDebugEnabled()) logger.debug("Richiesta la modifica del servizio");
                ServizioApplicativo servizioApplicativoModificare = daoServizioApplicativo.findById(servizioApplicativo.getId(), true);
                if (servizioApplicativoModificare == null) {
                    throw new SOAPFault("Impossibile modificare il servizio applicativo. Non esiste alcuna servizio applicativo con l'id specificato");
                }
                copiaProprieta(servizioApplicativo, servizioApplicativoModificare);
                daoServizioApplicativo.makePersistent(servizioApplicativoModificare);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante l'aggiunta del servizio applicativo.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante l'aggiunta del servizio applicativo.");
        }
    }

    public void removeServizioApplicativo(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la rimozione del servizio applicativo " + id);
            sessionFactory.getCurrentSession().beginTransaction();
            ServizioApplicativo servizioApplicativo = daoServizioApplicativo.findById(id, false);
            daoServizioApplicativo.makeTransient(servizioApplicativo);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante la rimozione del servizio applicativo.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la rimozione del servizio applicativo.");
        }
    }

    public List<ServizioApplicativo> getListaServiziApplicativi() throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la lista dei servizi applicativi");
            sessionFactory.getCurrentSession().beginTransaction();
            List<ServizioApplicativo> listaServiziApplicativi = daoServizioApplicativo.findAll();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return listaServiziApplicativi;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante la lettura della lista dei servizi applicativi.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura della lista dei servizi applicativi.");
        }
    }

    public ServizioApplicativo getServizioApplicativo(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesto il servizio applicativo " + id);
            sessionFactory.getCurrentSession().beginTransaction();
            ServizioApplicativo servizioApplicativo = daoServizioApplicativo.findById(id, false);
            Hibernate.initialize(servizioApplicativo);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return servizioApplicativo;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante la lettura del servizio applicativo.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura del servizio applicativo.");
        }
    }

    private void copiaProprieta(ServizioApplicativo servizioApplicativo, ServizioApplicativo servizioApplicativoModificare) {
        servizioApplicativoModificare.setConnettore(servizioApplicativo.getConnettore());
        servizioApplicativoModificare.setDescrizione(servizioApplicativo.getDescrizione());
        servizioApplicativoModificare.setNome(servizioApplicativo.getNome());
        servizioApplicativoModificare.setMutuaAutenticazione(servizioApplicativo.isMutuaAutenticazione());
    }
}
