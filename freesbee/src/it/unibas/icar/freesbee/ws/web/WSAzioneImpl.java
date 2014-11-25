package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOProfiloEGov;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class WSAzioneImpl implements IWSAzione {

    private static Log logger = LogFactory.getLog(WSAzioneImpl.class);
    private IDAOAzione daoAzione;
    private IDAOAccordoServizio daoAccordoServizio;
    private IDAOProfiloEGov daoProfiloEGov;

    public WSAzioneImpl(IDAOAzione daoAzione, IDAOAccordoServizio daoAccordoServizio, IDAOProfiloEGov daoProfiloEGov) {
        this.daoAzione = daoAzione;
        this.daoAccordoServizio = daoAccordoServizio;
        this.daoProfiloEGov = daoProfiloEGov;
    }

    public void addAzione(Azione azione) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (azione.getId() == 0) {
                //E' UN'AZIONE DA AGGIUNGERE
                if (logger.isInfoEnabled()) logger.info("Richiesta l'aggiunta dell'azione " + azione);
                Azione nuovaAzione = daoAzione.findByNome(azione.getNome(),azione.getIdAccordoServizio());
                if (nuovaAzione != null) {
                    throw new SOAPFault("Impossibile aggiungere l'azione. Esiste gi� un'azione con il nome specificato");
                }
                riempiRiferimenti(azione);
                daoAzione.makePersistent(azione);
            } else {
                //E' UN'AZIONE DA MODIFICARE
                if (logger.isInfoEnabled())logger.info("Richiesta la modifica dell'azione");
                Azione azioneModificare = daoAzione.findById(azione.getId(), true);
                if (azioneModificare == null) {
                    throw new SOAPFault("Impossibile modificare l'azione. Non esiste alcuna azione con l'id specificato");
                }
                riempiRiferimenti(azione);
                copiaProprieta(azione, azioneModificare);
                daoAzione.makePersistent(azioneModificare);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile aggiungere l'azione " + ex);
            throw new SOAPFault("Impossibile aggiungere l'azione " + ex.getMessage());
        }
    }

    public void removeAzione(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            Azione azione = daoAzione.findById(id, false);
            daoAzione.makeTransient(azione);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile eliminare l'azione " + ex);
            throw new SOAPFault("Impossibile eliminare l'azione " + ex.getMessage());
        }
    }

    public List<Azione> getListaAzioni() throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Richiesta la lista delle azioni");
            }
            sessionFactory.getCurrentSession().beginTransaction();
            List<Azione> listaAzioni = daoAzione.findAll();
            for (Azione azione : listaAzioni) {
                settaRiferimenti(azione);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return listaAzioni;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile leggere la lista delle azioni. " + ex);
            throw new SOAPFault("Impossibile leggere la lista delle azioni. " + ex.getMessage());
        }
    }

    public Azione getAzione(long id) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Richiesta l'azione " + id);
            }
            sessionFactory.getCurrentSession().beginTransaction();
            Azione azione = daoAzione.findById(id, false);
            settaRiferimenti(azione);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return azione;
        } catch (Exception ex) {
            if(logger.isDebugEnabled()) ex.printStackTrace();
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Impossibile leggere l'azione. " + ex);
            throw new SOAPFault("Impossibile leggere l'azione. " + ex.getMessage());
        }
    }

    private void copiaProprieta(Azione azione, Azione azioneModificare) {
        azioneModificare.setNome(azione.getNome());
        azioneModificare.setDescrizione(azione.getDescrizione());
        azioneModificare.setAccordoServizio(azione.getAccordoServizio());
        azioneModificare.setProfiloEGov(azione.getProfiloEGov());
        azioneModificare.setProfiloCollaborazione(azione.getProfiloCollaborazione());
    }

    private void riempiRiferimenti(Azione azione) throws DAOException {
        azione.setAccordoServizio(daoAccordoServizio.findById(azione.getIdAccordoServizio(), false));
        if (azione.getIdProfiloEGov() > 0) {
            azione.setProfiloEGov(daoProfiloEGov.findById(azione.getIdProfiloEGov(), false));
        }
    }

    private void settaRiferimenti(Azione azione) {
        azione.setIdAccordoServizio(azione.getAccordoServizio().getId());
        if (azione.getProfiloEGov() != null) {
            azione.setIdProfiloEGov(azione.getProfiloEGov().getId());
        }
    }
}