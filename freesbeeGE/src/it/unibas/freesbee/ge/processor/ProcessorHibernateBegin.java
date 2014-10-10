package it.unibas.freesbee.ge.processor;


import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class ProcessorHibernateBegin implements Processor {

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        logger.debug("ProcessorHibernateBegin processa il messaggio");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            DAOUtilHibernate.beginTransaction();
//            sessionFactory.getCurrentSession().beginTransaction();
        } catch (Exception ex) {
            logger.error("Impossibile avviare la sessione di hibernate " + ex);
            try {
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
//                    sessionFactory.getCurrentSession().getTransaction().rollback();
                    DAOUtilHibernate.rollback();
                }
            } catch (Throwable rbEx) {
            }
            throw new DAOException(ex);
        }
    }
}
