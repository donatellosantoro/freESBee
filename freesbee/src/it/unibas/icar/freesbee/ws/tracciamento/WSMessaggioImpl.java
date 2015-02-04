package it.unibas.icar.freesbee.ws.tracciamento;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggio;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

@Singleton
public class WSMessaggioImpl implements IWSMessaggio {

    private static Log logger = LogFactory.getLog(WSMessaggioImpl.class);
    private IDAOMessaggio daoMessaggio;

    @Inject()
    public WSMessaggioImpl(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public Messaggio getMessaggioInviatoByIDSil(String id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggio = daoMessaggio.findByIDSil(id, Messaggio.TIPO_INVIATO);
            if (messaggio == null) {
                throw new SOAPFault("Non esiste nessun messaggio con l'id specificato");
            }
            return messaggio;
        } catch (Exception ex) {
            logger.error("Impossibile restituire il messaggio con l'id specificato");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile restituire il messaggio con l'id specificato");
        }
    }

    public Messaggio getMessaggioInviatoByIDsilRelatesTo(String id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggio = daoMessaggio.findByIDSilRelatesTo(id, Messaggio.TIPO_INVIATO);
            if (messaggio == null) {
                throw new SOAPFault("Non esiste nessun messaggio con l'id specificato");
            }
            return messaggio;
        } catch (Exception ex) {
            logger.error("Impossibile restituire il messaggio con l'id specificato");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile restituire il messaggio con l'id specificato");
        }
    }

    public Messaggio getMessaggioRicevutoByIDSil(String id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggio = daoMessaggio.findByIDSil(id, Messaggio.TIPO_RICEVUTO);
            if (messaggio == null) {
                throw new SOAPFault("Non esiste nessun messaggio con l'id specificato!");
            }
            return messaggio;
        } catch (Exception ex) {
            logger.error("Impossibile restituire il messaggio con l'id specificato");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile restituire il messaggio con l'id specificato");
        }
    }

    public Messaggio getMessaggioRicevutoByIDsilRelatesTo(String id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggio = daoMessaggio.findByIDSilRelatesTo(id, Messaggio.TIPO_RICEVUTO);
            if (messaggio == null) {
                throw new SOAPFault("Non esiste nessun messaggio con l'id specificato!");
            }
            return messaggio;
        } catch (Exception ex) {
            logger.error("Impossibile restituire il messaggio con l'id specificato");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile restituire il messaggio con l'id specificato");
        }
    }
}
