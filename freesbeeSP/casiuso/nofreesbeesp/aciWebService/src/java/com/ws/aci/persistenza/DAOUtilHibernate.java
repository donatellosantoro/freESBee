package com.ws.aci.persistenza;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DAOUtilHibernate {
    
    private static Log logger = LogFactory.getLog(DAOUtilHibernate.class);

    private static SessionFactory sessionFactory;
    
    static {
        try {
            AnnotationConfiguration configuration = new AnnotationConfiguration();
            sessionFactory = configuration.configure().buildSessionFactory();
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
