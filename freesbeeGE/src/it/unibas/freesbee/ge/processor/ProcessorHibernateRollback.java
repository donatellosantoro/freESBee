package it.unibas.freesbee.ge.processor;


import it.unibas.freesbee.ge.persistenza.hibernate.DAOUtilHibernate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class ProcessorHibernateRollback implements Processor{

    private Log logger = LogFactory.getLog(this.getClass());

    public void process(Exchange exchange) throws Exception {
        logger.info("ProcessorHibernateRollback processa il messaggio");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                logger.debug("C'e' una transazione di hibernate attiva e faccio il rollback");
//                sessionFactory.getCurrentSession().getTransaction().rollback();
                DAOUtilHibernate.rollback();
            }
        } catch (Throwable rbEx) {
        }
    }

}
