package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.Date;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

public class WSAccordoServizioImpl implements IWSAccordoServizio {

//    private static Log logger = LogFactory.getLog(WSAccordoServizioImpl.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSAccordoServizioImpl.class.getName());
    private IDAOAccordoServizio daoAccordoServizio;
    private IDAOProfiloEGov daoProfiloEGov;

    public WSAccordoServizioImpl(IDAOAccordoServizio daoAccordoServizio, IDAOProfiloEGov daoProfiloEGov) {
        this.daoAccordoServizio = daoAccordoServizio;
        this.daoProfiloEGov = daoProfiloEGov;
    }

    public void addAccordoServizio(AccordoServizio accordoServizio) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();

            if (accordoServizio.getId() == 0) {
                //E' UN ACCORDO DI SERVIZIO DA AGGIUNGERE
                if (logger.isDebugEnabled()) logger.debug("Richiesta l'aggiunta di un accordo di servizio");
                AccordoServizio nuovoAccordoServizio = daoAccordoServizio.findByNome(accordoServizio.getNome());
                if (nuovoAccordoServizio != null) {
                    throw new SOAPFault("Impossibile aggiungere l'accordo di servizio. Esiste già un accordo di servizio con il nome specificato");
                }
                riempiRiferimenti(accordoServizio);
                accordoServizio.setOraRegistrazione(new Date());
                daoAccordoServizio.makePersistent(accordoServizio);

            } else {
                //E' UN ACCORDO DI SERVIZIO DA MODIFICARE
                if (logger.isDebugEnabled()) logger.debug("Richiesta la modifica di un accordo di servizio");
                AccordoServizio accordoServizioModificare = daoAccordoServizio.findById(accordoServizio.getId(), true);
                if (accordoServizioModificare == null) {
                    throw new SOAPFault("Impossibile modificare l'accordo di servizio. Non esiste alcun accordo di servizio con l'id specificato");
                }
                riempiRiferimenti(accordoServizio);
                copiaProprieta(accordoServizio, accordoServizioModificare);
                accordoServizioModificare.setOraRegistrazione(new Date());
                daoAccordoServizio.makePersistent(accordoServizioModificare);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();

        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile aggiungere l'Accordo di Servizio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile aggiungere l'Accordo di Servizio.");
        }
    }

    public void removeAccordoServizio(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            AccordoServizio accordoServizioEliminare = daoAccordoServizio.findById(id, false);
            //riempiRiferimenti(accordoServizioEliminare);
            daoAccordoServizio.makeTransient(accordoServizioEliminare);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile eliminare l'Accordo di Servizio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile eliminare l'Accordo di Servizio.");
        }
    }

    public List<AccordoServizio> getListaAccordiServizio() throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la lista degli Accordi di Servizio");
            sessionFactory.getCurrentSession().beginTransaction();
            List<AccordoServizio> listaAccordiServizio = daoAccordoServizio.findAll();
            for (AccordoServizio accordoServizio : listaAccordiServizio) {
                settaRiferimenti(accordoServizio);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return listaAccordiServizio;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile leggere la lista degli Accordi di Servizio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile leggere la lista degli Accordi di Servizio.");
        }
    }

    public AccordoServizio getAccordoServizio(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta l'accordo di servizio " + id);
            sessionFactory.getCurrentSession().beginTransaction();
            AccordoServizio accordoServizio = daoAccordoServizio.findById(id, false);
            settaRiferimenti(accordoServizio);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return accordoServizio;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile leggere l'Accordo di Servizio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile leggere l'Accordo di Servizio.");
        }
    }

    private void copiaProprieta(AccordoServizio accordoServizio, AccordoServizio accordoServizioModificare) {
        accordoServizioModificare.setDescrizione(accordoServizio.getDescrizione());
        accordoServizioModificare.setNome(accordoServizio.getNome());
        accordoServizioModificare.setProfiloCollaborazione(accordoServizio.getProfiloCollaborazione());
        accordoServizioModificare.setAzioni(accordoServizio.getAzioni());
        accordoServizioModificare.setPrivato(accordoServizio.isPrivato());
        accordoServizioModificare.setProfiloEGov(accordoServizio.getProfiloEGov());
        accordoServizioModificare.setServizi(accordoServizio.getServizi());
        accordoServizioModificare.setPolicyXACML(accordoServizio.getPolicyXACML());
    }

    private void riempiRiferimenti(AccordoServizio accordo) throws DAOException {
        accordo.setProfiloEGov(daoProfiloEGov.findById(accordo.getIdProfiloEGov(), false));
    }

    private void settaRiferimenti(AccordoServizio accordo) {
        accordo.setIdProfiloEGov(accordo.getProfiloEGov().getId());
    }
}
