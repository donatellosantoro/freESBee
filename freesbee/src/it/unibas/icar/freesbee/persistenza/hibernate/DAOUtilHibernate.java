package it.unibas.icar.freesbee.persistenza.hibernate;

import it.unibas.icar.freesbee.persistenza.DAOException;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.slf4j.LoggerFactory;

public class DAOUtilHibernate {

//    private static Log logger = LogFactory.getLog(DAOUtilHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOUtilHibernate.class.getName());
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static int counter = 0;

    static {
        buildSessionFactory();
    }

    private static void buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new ExceptionInInitializerError("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
////        if (++counter % 1000 == 0) {
////            System.out.println("---------------------");
////            Statistics statistic = sessionFactory.getStatistics();
////            statistic.logSummary();
////            System.out.println("---------------------\n\n");
////        }
//        if (logger.isDebugEnabled()) {
//            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//            logger.debug("Richiesta session factory da " + getUltimaChiamataSignificativa(stackTrace));
////            for (StackTraceElement stackTraceElement : stackTrace) {
////                if (logger.isDebugEnabled()) logger.debug("\t" + stackTraceElement);
////            }
//        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory == null) {
            return;
        }
        if (sessionFactory instanceof SessionFactoryImpl) {
            SessionFactoryImpl sf = (SessionFactoryImpl) sessionFactory;
            ConnectionProvider conn = sf.getConnectionProvider();
            if (conn instanceof C3P0ConnectionProvider) {
                ((C3P0ConnectionProvider) conn).close();
            }
        }
        sessionFactory.close();
        sessionFactory = null;
    }

//    private static String getUltimaChiamataSignificativa(StackTraceElement[] stackTrace) {
//        for (StackTraceElement stackTraceElement : stackTrace) {
//            String className = stackTraceElement.getClassName();
//            if (className.equals("it.unibas.icar.freesbee.persistenza.hibernate.DAOUtilHibernate")) {
//                continue;
//            }
//            if (className.startsWith("it.unibas.icar.freesbee")) {
//                return className + "." + stackTraceElement.getMethodName() + "()";
//            }
//        }
//        return stackTrace[0].getClassName();
//    }

    public static Session getCurrentSession() throws DAOException {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    public static void beginTransaction() throws DAOException {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    public static void commit() throws DAOException {
        try {
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    public static void rollback() throws DAOException {
        try {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }
}