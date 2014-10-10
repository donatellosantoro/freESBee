package it.unibas.icar.freesbee.ws.registroservizi;

import it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.hibernate.SessionFactory;

public class HibernateInterceptor extends AbstractPhaseInterceptor {

    private static Log logger = LogFactory.getLog(HibernateInterceptor.class);

    public HibernateInterceptor() {
        super(Phase.RECEIVE);
        if(logger.isDebugEnabled()) logger.debug("Creo HibernateInterceptor");
    }

    public void handleMessage(Message message) throws Fault {
        if(logger.isDebugEnabled()) logger.debug("HibernateInterceptor processa il messaggio");
        SessionFactory sessionFactory = DAOUtilHibernate.getSessionFactory();
        message.getInterceptorChain().add(new HibernateInterceptorIn(sessionFactory));
        message.getInterceptorChain().add(new HibernateInterceptorOut(sessionFactory));

    }

    private class HibernateInterceptorIn extends AbstractPhaseInterceptor {

        private SessionFactory sessionFactory;

        public HibernateInterceptorIn(SessionFactory sessionFactory) {
            super(Phase.PRE_INVOKE);
            this.sessionFactory = sessionFactory;
        }

        public void handleMessage(Message message) throws Fault {
            if(logger.isDebugEnabled()) logger.debug("HibernateInterceptorIn processa il messaggio");
            try {
                sessionFactory.getCurrentSession().beginTransaction();
            } catch (Exception ex) {
                logger.error("Impossibile avviare la sessione di hibernate " + ex);
                try {
                    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                    }
                } catch (Throwable rbEx) {
                }
                throw new Fault(ex);
            }
        }
    }

    private class HibernateInterceptorOut extends AbstractPhaseInterceptor {

        private SessionFactory sessionFactory;

        public HibernateInterceptorOut(SessionFactory sessionFactory) {
            super(Phase.POST_INVOKE);
            this.sessionFactory = sessionFactory;
        }

        public void handleMessage(Message message) throws Fault {
            if(logger.isDebugEnabled()) logger.debug("HibernateInterceptorOut processa il messaggio");
            try {
                sessionFactory.getCurrentSession().getTransaction().commit();
            } catch (Exception ex) {
                logger.error("Impossibile chiudere la sessione di hibernate " + ex);
                try {
                    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                    }
                } catch (Throwable rbEx) {
                }
                throw new Fault(ex);
            }
        }
    }
}
