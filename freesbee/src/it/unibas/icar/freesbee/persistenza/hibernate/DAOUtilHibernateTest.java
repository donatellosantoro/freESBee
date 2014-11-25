package it.unibas.icar.freesbee.persistenza.hibernate;

import it.unibas.icar.freesbee.persistenza.DAOException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DAOUtilHibernateTest {

    private static Log logger = LogFactory.getLog(DAOUtilHibernateTest.class);

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
            logger.error("Building SessionFactory failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected static Session getCurrentSession() throws DAOException {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    public static void beginTransaction() throws DAOException {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
        } catch (HibernateException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    public static void commit() throws DAOException {
        try {
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (HibernateException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    public static void rollback(){
        try {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        } catch (HibernateException ex) {
            logger.error(ex);
        }
    }
}