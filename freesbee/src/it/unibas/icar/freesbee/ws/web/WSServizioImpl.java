package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

public class WSServizioImpl implements IWSServizio {

    private static Log logger = LogFactory.getLog(WSServizioImpl.class);
    private IDAOServizio daoServizio;
    private IDAOPortaApplicativa daoPortaApplicativa;
    private IDAOPortaDelegata daoPortaDelegata;
    private IDAOAccordoServizio daoAccordoServizio;
    private IDAOSoggetto daoSoggetto;

    public WSServizioImpl(IDAOServizio daoServizio, IDAOPortaApplicativa daoPortaApplicativa, IDAOPortaDelegata daoPortaDelegata, IDAOAccordoServizio daoAccordoServizio, IDAOSoggetto daoSoggetto) {
        this.daoServizio = daoServizio;
        this.daoPortaApplicativa = daoPortaApplicativa;
        this.daoPortaDelegata = daoPortaDelegata;
        this.daoAccordoServizio = daoAccordoServizio;
        this.daoSoggetto = daoSoggetto;
    }

    public void addServizio(Servizio servizio) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (servizio.getId() == 0) {
                //E' UN SERVIZIO DA AGGIUNGERE
                if (logger.isDebugEnabled()) logger.debug("Richiesta l'aggiunta del servizio " + servizio);
                riempiRiferimenti(servizio);
                Servizio nuovoServizio = daoServizio.findByNome(servizio.getNome(), servizio.getTipo(), servizio.getErogatore());
                if (nuovoServizio != null) {
                    throw new SOAPFault("Impossibile aggiungere il servizio. Esiste già un servizio con nome, tipo ed erogatore specificati");
                }
//                nuovoServizio = daoServizio.findByAccordoErogatore(servizio.getAccordoServizio(), servizio.getErogatore());
//                if (nuovoServizio != null) {
//                    throw new SOAPFault("Impossibile aggiungere il servizio. L'accordo di servizio " + servizio.getAccordoServizio().getNome() + " eroga già un servizio del soggetto " + servizio.getErogatore().getNomeBreve());
//                }
                servizio.setOraRegistrazione(new Date());
                daoServizio.makePersistent(servizio);
            } else {
                //E' UN SERVIZIO DA MODIFICARE
                if (logger.isInfoEnabled()) logger.debug("Richiesta la modifica del servizio");
                Servizio servizioModificare = daoServizio.findById(servizio.getId(), true);
                if (servizioModificare == null) {
                    throw new SOAPFault("Impossibile modificare il servizio. Non esiste alcuna servizio con l'id specificato");
                }
                riempiRiferimenti(servizio);
                copiaProprieta(servizio, servizioModificare);
                servizioModificare.setOraRegistrazione(new Date());
                daoServizio.makePersistent(servizioModificare);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante l'aggiunta del servizio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante l'aggiunta del servizio.");
        }
    }

    public void removeServizio(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la cancellazione del servizio " + id);
            sessionFactory.getCurrentSession().beginTransaction();
            Servizio servizio = daoServizio.findById(id, false);
            riempiRiferimenti(servizio);
            daoServizio.makeTransient(servizio);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (DAOException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante l'eliminazione del servizio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante l'eliminazione del servizio.");
        }
    }

    public List<Servizio> getListaServizi() throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la lista dei servizi");
            sessionFactory.getCurrentSession().beginTransaction();
            List<Servizio> listaServizi = daoServizio.findAll();
            for (Servizio servizio : listaServizi) {
                settaRiferimenti(servizio);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return listaServizi;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante la lettura della lista dei servizi.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura della lista dei servizi.");
        }
    }

    public Servizio getServizio(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesto il servizio " + id);
            sessionFactory.getCurrentSession().beginTransaction();
            Servizio servizio = daoServizio.findById(id, false);
            Hibernate.initialize(servizio);
            settaRiferimenti(servizio);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return servizio;
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante la lettura del servizio.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura del servizio.");
        }
    }

    private void copiaProprieta(Servizio servizio, Servizio servizioModificare) {
        servizioModificare.setAccordoServizio(servizio.getAccordoServizio());
        servizioModificare.setCorrelato(servizio.isCorrelato());
        servizioModificare.setErogatore(servizio.getErogatore());
        servizioModificare.setFruitori(servizio.getFruitori());
        servizioModificare.setNome(servizio.getNome());
//        servizioModificare.setPortaApplicativa(servizio.getPortaApplicativa());
        servizioModificare.setPrivato(servizio.isPrivato());
        servizioModificare.setTipo(servizio.getTipo());
        servizioModificare.setUrlServizio(servizio.getUrlServizio());
        servizioModificare.setAzioni(servizio.getAzioni());
        servizioModificare.setMutuaAutenticazione(servizio.isMutuaAutenticazione());
    }

    private void riempiRiferimenti(Servizio servizio) throws DAOException {
        servizio.setAccordoServizio(daoAccordoServizio.findById(servizio.getIdAccordoServizio(), false));
        servizio.setErogatore(daoSoggetto.findById(servizio.getIdErogatore(), false));
        servizio.setFruitori(riempiListaSoggettiFruitori(servizio.getIdFruitori()));
    }

    private List<Soggetto> riempiListaSoggettiFruitori(List<Long> idFruitori) throws DAOException {
        List<Soggetto> listaFruitori = new ArrayList<Soggetto>();

        for (Iterator<Long> iterator = idFruitori.iterator(); iterator.hasNext();) {
            long idFruitore = (long) iterator.next();
            Soggetto soggettoFruitore = daoSoggetto.findById(idFruitore, false);
            listaFruitori.add(soggettoFruitore);
        }
        return listaFruitori;
    }

    private void settaRiferimenti(Servizio servizio) {
        servizio.setIdAccordoServizio(servizio.getAccordoServizio().getId());
        servizio.setIdErogatore(servizio.getErogatore().getId());
        List<Long> listaFruitori = new ArrayList<Long>();
        List<Soggetto> fruitori = servizio.getFruitori();
        for (Soggetto fruitore : fruitori) {
            listaFruitori.add(new Long(fruitore.getId()));
        }
        servizio.setIdFruitori(listaFruitori);
    }
}
