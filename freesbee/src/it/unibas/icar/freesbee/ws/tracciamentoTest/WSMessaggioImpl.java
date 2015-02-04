package it.unibas.icar.freesbee.ws.tracciamentoTest;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggio;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernateTest;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

@Singleton
public class WSMessaggioImpl implements IWSMessaggio {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOMessaggio daoMessaggio;

    @Inject()
    public WSMessaggioImpl(IDAOMessaggio daoMessaggio) {
        this.daoMessaggio = daoMessaggio;
    }

    public Messaggio getMessaggioInviatoByIDSil(String id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernateTest.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggio = daoMessaggio.findByIDSil(id, Messaggio.TIPO_INVIATO);
            if (messaggio == null) {
                throw new SOAPFault("Non esiste nessun messaggio con l'id specificato!");
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return messaggio;
        } catch (Exception ex) {
            logger.error("Impossibile restituire il messaggio con l'id specificato");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile restituire il messaggio con l'id specificato");
        }
    }

    public Messaggio getMessaggioInviatoByIDsilRelatesTo(String id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernateTest.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggio = daoMessaggio.findByIDSilRelatesTo(id, Messaggio.TIPO_INVIATO);
            if (messaggio == null) {
                throw new SOAPFault("Non esiste nessun messaggio con l'id specificato!");
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return messaggio;
        } catch (Exception ex) {
            logger.error("Impossibile restituire il messaggio con l'id specificato");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile restituire il messaggio con l'id specificato");
        }
    }
    public Messaggio getMessaggioRicevutoByIDSil(String id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernateTest.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggio = daoMessaggio.findByIDSil(id, Messaggio.TIPO_RICEVUTO);
            if (messaggio == null) {
                throw new SOAPFault("Non esiste nessun messaggio con l'id specificato!");
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return messaggio;
        } catch (Exception ex) {
            logger.error("Impossibile restituire il messaggio con l'id specificato");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile restituire il messaggio con l'id specificato");
        }
    }

    public Messaggio getMessaggioRicevutoByIDsilRelatesTo(String id) throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernateTest.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();
            Messaggio messaggio = daoMessaggio.findByIDSilRelatesTo(id, Messaggio.TIPO_RICEVUTO);
            if (messaggio == null) {
                throw new SOAPFault("Non esiste nessun messaggio con l'id specificato!");
            }
            sessionFactory.getCurrentSession().getTransaction().commit();
            return messaggio;
        } catch (Exception ex) {
            logger.error("Impossibile restituire il messaggio con l'id specificato");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile restituire il messaggio con l'id specificato");
        }
    }

    public void svuotaDB() throws SOAPFault {
        try {
            SessionFactory sessionFactory = DAOUtilHibernateTest.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();            
            List<Messaggio> listaMessaggi = daoMessaggio.findAll();            
            for(Messaggio mes : listaMessaggi){
                daoMessaggio.makeTransient(mes);
            }            
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception ex) {
            logger.error("Impossibile svuotare la tabella.");
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new SOAPFault("Impossibile svuotare la tabella.");
        }
    }
}
