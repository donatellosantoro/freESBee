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
        if(logger.isDebugEnabled()) logger.debug("Creazione dell'HibernateInterceptor.");
    }

    public void handleMessage(Message message) throws Fault {
        if(logger.isDebugEnabled()) logger.debug("L'HibernateInterceptor sta processando il messaggio.");
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
            if(logger.isDebugEnabled()) logger.debug("L'HibernateInterceptor sta processando il messaggio.");
            try {
                sessionFactory.getCurrentSession().beginTransaction();
            } catch (Exception ex) {
                logger.error("Si e' verificato un errore, impossibile avviare la sessione di hibernate.");
                if (logger.isDebugEnabled()) ex.printStackTrace();
                try {
                    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                    }
                } catch (Throwable rbEx) {
                    logger.error("Si e' verificato un errore durante il rollback della transazione sul DB.");
                    if (logger.isDebugEnabled()) ex.printStackTrace();
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
            if(logger.isDebugEnabled()) logger.debug("L'HibernateInterceptorOut sta processando il messaggio.");
            try {
                sessionFactory.getCurrentSession().getTransaction().commit();
            } catch (Exception ex) {
                logger.error("Si e' verificato un errore, impossibile chiudere la sessione di hibernate.");
                if (logger.isDebugEnabled()) ex.printStackTrace();
                try {
                    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
                        sessionFactory.getCurrentSession().getTransaction().rollback();
                    }
                } catch (Throwable rbEx) {
                    logger.error("Si e' verificato un errore durante il rollback della transazione sul DB.");
                    if (logger.isDebugEnabled()) ex.printStackTrace();
                }
                throw new Fault(ex);
            }
        }
    }
}
