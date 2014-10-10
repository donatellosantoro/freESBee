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
        if (logger.isInfoEnabled()) logger.info("HibernateInterceptorFault processa il messaggio");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        try {
            if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                if(logger.isDebugEnabled()) logger.debug("C'è una transazione di hibernate attiva e faccio il rollback");
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        } catch (Throwable rbEx) {
        }
    }
}
