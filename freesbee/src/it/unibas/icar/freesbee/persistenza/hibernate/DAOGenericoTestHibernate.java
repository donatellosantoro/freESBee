package it.unibas.icar.freesbee.persistenza.hibernate;

import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOGenerico;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public class DAOGenericoTestHibernate<T> implements IDAOGenerico<T> {

    private static Log logger = LogFactory.getLog(DAOGenericoTestHibernate.class);

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public DAOGenericoTestHibernate(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    protected Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected static Session getSession() throws DAOException {
        try {
            return DAOUtilHibernateTest.getCurrentSession();
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) logger.error(ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    @SuppressWarnings("unchecked")
    public T makePersistent(T entity) throws DAOException {
        try {
            getSession().saveOrUpdate(entity);
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) logger.error(ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
        return entity;
    }

    public void makeTransient(T entity) throws DAOException {
        try {
            getSession().delete(entity);
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) logger.error(ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    @SuppressWarnings("unchecked")
    public void lock(T entity) throws DAOException {
        try {
            getSession().lock(entity, LockMode.UPGRADE);
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) logger.error(ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }

    @SuppressWarnings("unchecked")
    public T findById(Long id, boolean lock) throws DAOException {
        T entity;
        try {
            if (lock) {
                entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
            } else {
                entity = (T) getSession().load(getPersistentClass(), id);
            }
        } catch (HibernateException ex) {
            if (logger.isDebugEnabled()) logger.error(ex);
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
            if (logger.isDebugEnabled()) logger.error(ex);
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
            if (logger.isDebugEnabled()) logger.error(ex);
            throw new DAOException("Si e' verificato un errore durante l'accesso al DB.");
        }
    }
}