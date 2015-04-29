package it.unibas.icar.freesbee.persistenza.hibernate;

import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOGenerico;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.slf4j.LoggerFactory;

public class DAOGenericoHibernate<T> implements IDAOGenerico<T> {

//    private static Log logger = LogFactory.getLog(DAOGenericoHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOGenericoHibernate.class.getName());
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public DAOGenericoHibernate(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected static Session getSession() throws DAOException {
        try {
            return DAOUtilHibernate.getCurrentSession();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    @SuppressWarnings("unchecked")
    public T makePersistent(T entity) throws DAOException {
        try {
            getSession().saveOrUpdate(entity);
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
        return entity;
    }

    public void makeTransient(T entity) throws DAOException {
        try {
            getSession().delete(entity);
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    @SuppressWarnings("unchecked")
    public void lock(T entity) throws DAOException {
        try {
            getSession().buildLockRequest(LockOptions.UPGRADE).lock(entity);
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    @SuppressWarnings("unchecked")
    public T findById(Long id, boolean lock) throws DAOException {
        T entity;
        try {
            if (lock) {
                entity = (T) getSession().get(getPersistentClass(), id, LockOptions.UPGRADE);
            } else {
                entity = (T) getSession().get(getPersistentClass(), id);
            }
//            if (lock) {
//                entity = (T) getSession().load(getPersistentClass(), id, LockOptions.UPGRADE);
//            } else {
//                entity = (T) getSession().load(getPersistentClass(), id);
//            }
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() throws DAOException {
        return findByCriteria();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(int offset, int limite) throws DAOException {
        return findByCriteria(offset, limite);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion... criterion) throws DAOException {
        try {
            Criteria crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                crit.add(c);
            }
            return crit.list();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(int offset, int limite, Criterion... criterion) throws DAOException {
        try {
            Criteria crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                crit.add(c);
            }
            crit.setFirstResult(offset);
            crit.setMaxResults(limite);
            return crit.list();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    @SuppressWarnings("unchecked")
    public T reattach(T obj, Long id) throws DAOException {
        try {
            T persistentObject = (T) getSession().get(persistentClass, id);
            return persistentObject;
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) ex.printStackTrace();
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }
}