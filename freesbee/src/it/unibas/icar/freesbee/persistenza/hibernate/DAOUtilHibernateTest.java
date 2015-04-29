package it.unibas.icar.freesbee.persistenza.hibernate;

import it.unibas.icar.freesbee.persistenza.DAOException;
import java.util.Properties;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.LoggerFactory;

public class DAOUtilHibernateTest {

//    private static Log logger = LogFactory.getLog(DAOUtilHibernateTest.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOUtilHibernateTest.class.getName());

    private static SessionFactory sessionFactory;

    static {
        try {
            Properties prop = new Properties();
            prop.load(DAOUtilHibernateTest.class.getResourceAsStream("/hibernate.properties"));
            String testDb =  prop.getProperty("hibernate.connection.test.url");
            AnnotationConfiguration configuration = new AnnotationConfiguration();
            configuration.setProperty("hibernate.connection.url", testDb);
            sessionFactory = configuration.configure("hibernateTest.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new ExceptionInInitializerError("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected static Session getCurrentSession() throws DAOException {
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

    public static void rollback() throws DAOException{
        try {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }
}
