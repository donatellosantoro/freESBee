package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAzione;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

public class WSPortaApplicativaImpl implements IWSPortaApplicativa {

//    private static Log logger = LogFactory.getLog(WSPortaApplicativaImpl.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSPortaApplicativaImpl.class.getName());
    private IDAOPortaApplicativa daoPortaApplicativa;
    private IDAOAzione daoAzione;
    private IDAOServizio daoServizio;
    private IDAOServizioApplicativo daoServizioApplicativo;

    public WSPortaApplicativaImpl(IDAOPortaApplicativa daoPortaApplicativa, IDAOAzione daoAzione, IDAOServizio daoServizio, IDAOServizioApplicativo daoServizioApplicativo) {
        this.daoPortaApplicativa = daoPortaApplicativa;
        this.daoAzione = daoAzione;
        this.daoServizio = daoServizio;
        this.daoServizioApplicativo = daoServizioApplicativo;
    }

    public void addPortaApplicativa(PortaApplicativa pa) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            if (pa.getId() == null) {
                //E' UNA PORTA APPLICATIVA DA AGGIUNGERE
                if (logger.isDebugEnabled()) logger.debug("Richiesta l'aggiunta della porta applicativa " + pa);
                String nome = pa.getNome();
                PortaApplicativa nuovaPortaApplicativa = daoPortaApplicativa.findByNome(nome);
                if (nuovaPortaApplicativa != null) {
                    throw new SOAPFault("Impossibile aggiungere la porta applicativa. Esiste gia' una porta applicativa con il nome specificato");
                }
                riempiRiferimenti(pa);
                daoPortaApplicativa.makePersistent(pa);
            } else {
                //E' UNA PORTA APPLICATIVA DA MODIFICARE
                if (logger.isDebugEnabled()) logger.debug("Richiesta la modifica della porta applicativa");
                PortaApplicativa paModificare = daoPortaApplicativa.findById(pa.getId(), true);
                if (paModificare == null) {
                    throw new SOAPFault("Impossibile modificare la porta applicativa. Non esiste alcuna porta applicativa con l'id specificato");
                }
                riempiRiferimenti(pa);
                copiaProprieta(pa, paModificare);
                daoPortaApplicativa.makePersistent(paModificare);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante l'aggiunta di una nuova porta applicativa.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante l'aggiunta di una nuova porta applicativa.");
        }
    }

    public void removePortaApplicativa(PortaApplicativa pa) throws SOAPFault {
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la rimozione della porta applicativa " + pa.getId());
            sessionFactory.getCurrentSession().beginTransaction();
            PortaApplicativa portaApplicativaRimuovere = daoPortaApplicativa.findById(pa.getId(), true);
            Hibernate.initialize(portaApplicativaRimuovere);
            Hibernate.initialize(portaApplicativaRimuovere.getServizioApplicativo());
            daoPortaApplicativa.makeTransient(portaApplicativaRimuovere);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("Si e' verificato un errore durante la rimozione della porta applicativa.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la rimozione della porta applicativa.");
            
        }
    }

    public List<PortaApplicativa> getListaPorteApplicative() throws SOAPFault {
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la lista delle porte applicative");
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            List<PortaApplicativa> listaPorteApplicative = daoPortaApplicativa.findAll();
            for (PortaApplicativa portaApplicativa : listaPorteApplicative) {
                settaRiferimenti(portaApplicativa);
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return listaPorteApplicative;
        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante la lettura delle porte applicative.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura delle porte applicative.");
        }
    }

    public PortaApplicativa getPortaApplicativa(long id) throws SOAPFault {
        try {
            if (logger.isDebugEnabled()) logger.debug("Richiesta la porta applicativa " + id);
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            PortaApplicativa portaApplicativa = daoPortaApplicativa.findById(id, false);
            settaRiferimenti(portaApplicativa);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return portaApplicativa;
        } catch (Exception ex) {
            logger.error("Si e' verificato un errore durante la lettura della porta applicativa.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Si e' verificato un errore durante la lettura della porta applicativa.");
        }
    }

    private void copiaProprieta(PortaApplicativa pa, PortaApplicativa paModificare) {
        paModificare.setAzione(pa.getAzione());
        paModificare.setDescrizione(pa.getDescrizione());
        paModificare.setNome(pa.getNome());
        paModificare.setServizio(pa.getServizio());
        paModificare.setServizioApplicativo(pa.getServizioApplicativo());
        paModificare.setStringaAzione(pa.getStringaAzione());
        paModificare.setStringaTipoErogatore(pa.getStringaTipoErogatore());
        paModificare.setStringaErogatore(pa.getStringaErogatore());
        paModificare.setStringaTipoServizio(pa.getStringaTipoServizio());
        paModificare.setStringaServizio(pa.getStringaServizio());
    }

    private void riempiRiferimenti(PortaApplicativa pa) throws DAOException {
        if (pa.getIdAzione() != null && pa.getIdAzione() > 0) {
            long idAzione = pa.getIdAzione();
            Azione azione = daoAzione.findById(idAzione, false);
            pa.setAzione(azione);
        }
        if (pa.getIdServizio() != null && pa.getIdServizio() > 0) {
            long idServizio = pa.getIdServizio();
            Servizio servizio = daoServizio.findById(idServizio, false);
            pa.setServizio(servizio);
        }
        if (pa.getIdServizioApplicativo() != null && pa.getIdServizioApplicativo() > 0) {
            long idServizioApplicativo = pa.getIdServizioApplicativo();
            ServizioApplicativo servizioApplicativo = daoServizioApplicativo.findById(idServizioApplicativo, false);
            pa.setServizioApplicativo(servizioApplicativo);
        }
    }

    private void settaRiferimenti(PortaApplicativa pa) {
        if (pa.getAzione() != null) {
            pa.setIdAzione(pa.getAzione().getId());
        }
        if (pa.getServizio() != null) {
            pa.setIdServizio(pa.getServizio().getId());
        }
        if (pa.getServizioApplicativo() != null) {
            pa.setIdServizioApplicativo(pa.getServizioApplicativo().getId());
        }
    }
}
