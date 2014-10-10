package it.unibas.freesbee.ge.persistenza.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.hibernate.SessionFactory;

public class HibernateInterceptorFault extends AbstractPhaseInterceptor {

    private Log logger = LogFactory.getLog(this.getClass());

    public HibernateInterceptorFault() {
        super(Phase.SETUP);
    }

    public void handleMessage(Message message) throws Fault {
        logger.info("HibernateInterceptorFault processa il messaggio");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                logger.debug("C'e' una transazione di hibernate attiva e faccio il rollback");
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        } catch (Throwable rbEx) {
            logger.error("Errore nell'intercettore");
            rbEx.printStackTrace();
        }
    }
}
