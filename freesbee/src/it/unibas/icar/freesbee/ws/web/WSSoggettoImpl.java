package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.Date;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

public class WSSoggettoImpl implements IWSSoggetto {

    private static Log logger = LogFactory.getLog(WSSoggettoImpl.class);
    private CamelContext context;
    private IDAOSoggetto daoSoggetto;

    public WSSoggettoImpl(CamelContext context, IDAOSoggetto daoSoggetto) {
        this.context = context;
        this.daoSoggetto = daoSoggetto;
    }

    public CamelContext getContext() {
        return context;
    }

    public void setContext(CamelContext context) {
        this.context = context;
    }

    public void addSoggetto(Soggetto soggetto) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();

            if (soggetto.getId() == 0) {
                //E' UN SOGGETTO DA AGGIUNGERE
                if (logger.isInfoEnabled()) logger.info("Richiesta l'aggiunta di un soggetto");
                Soggetto nuovoSoggetto = daoSoggetto.findByNome(soggetto.getNome(), soggetto.getTipo());
                if (nuovoSoggetto != null) {
                    throw new SOAPFault("Impossibile aggiungere il soggetto. Esiste già un soggetto con nome e tipo specificati");
                }
                soggetto.setOraRegistrazione(new Date());
                daoSoggetto.makePersistent(soggetto);
            } else {
                //E' UN SOGGETTO DA MODIFICARE
                if (logger.isInfoEnabled()) logger.info("Richiesta la modifica di un soggetto");
                Soggetto soggettoModificare = daoSoggetto.findById(soggetto.getId(), true);
                if (soggettoModificare == null) {
                    throw new SOAPFault("Impossibile modificare il soggetto. Non esiste alcun soggetto con l'id specificato");
                }
                copiaProprieta(soggetto, soggettoModificare);
                soggettoModificare.setOraRegistrazione(new Date());
                daoSoggetto.makePersistent(soggettoModificare);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile aggiungere il soggetto " + ex);
            throw new SOAPFault("Impossibile aggiungere il soggetto " + ex.getMessage());
        }
    }

    public void removeSoggetto(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isInfoEnabled()) logger.info("Richiesta la cancellazione di un soggetto");
            sessionFactory.getCurrentSession().beginTransaction();
            Soggetto soggettoEliminare = daoSoggetto.findById(id, false);
            for (Servizio serv : soggettoEliminare.getListaServiziFruitore()) {
                serv.getFruitori().remove(soggettoEliminare);
            }
            daoSoggetto.makeTransient(soggettoEliminare);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {            
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                logger.error("Could not rollback transaction after exception!", rbEx);
            }
            logger.error("Impossibile eliminare il soggetto " + ex);
            throw new SOAPFault("Impossibile eliminare il soggetto " + ex.getMessage());
        }
    }

    public List<Soggetto> getListaSoggetti() throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isInfoEnabled()) logger.info("Richiesta la lista dei soggetti");
            sessionFactory.getCurrentSession().beginTransaction();
            List<Soggetto> listaSoggetti = daoSoggetto.findAll();
            sessionFactory.getCurrentSession().getTransaction().commit();
            return listaSoggetti;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile leggere la lista dei soggetti." + ex);
            throw new SOAPFault("Impossibile leggere la lista dei soggetti. " + ex.getMessage());
        }
    }

    public Soggetto getSoggetto(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isInfoEnabled()) logger.info("Richiesto il soggetto " + id);
            sessionFactory.getCurrentSession().beginTransaction();
            Soggetto soggetto = daoSoggetto.findById(id, false);
            Hibernate.initialize(soggetto);
            Hibernate.initialize(soggetto.getListaServiziErogatore());
            sessionFactory.getCurrentSession().getTransaction().commit();
            return soggetto;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile leggere il soggetto." + ex);
            throw new SOAPFault("Impossibile leggere il soggetto. " + ex.getMessage());
        }
    }

    private void copiaProprieta(Soggetto soggetto, Soggetto soggettoModificare) {
        soggettoModificare.setDescrizione(soggetto.getDescrizione());
        soggettoModificare.setNome(soggetto.getNome());
        soggettoModificare.setPortaDominio(soggetto.getPortaDominio());
        soggettoModificare.setTipo(soggetto.getTipo());
        soggettoModificare.setMutuaAutenticazione(soggetto.isMutuaAutenticazione());
    }
}
