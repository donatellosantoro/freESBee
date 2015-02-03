package it.unibas.icar.freesbee.ws.registroservizi;

import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.hibernate.SessionFactory;

public class HibernateInterceptorFault extends AbstractPhaseInterceptor {

    private static Log logger = LogFactory.getLog(HibernateInterceptorFault.class);

    public HibernateInterceptorFault() {
        super(Phase.SETUP);
    }

    public void handleMessage(Message message) throws Fault {
        if (logger.isDebugEnabled()) logger.debug("L'HibernateInterceptorFault sta processando il messaggio.");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        } catch (Throwable rbEx) {
            logger.error("Si e' verificato un errore durante il rollback della transazione sul DB.");
            if (logger.isDebugEnabled()) logger.error(rbEx);
        }
    }
}
