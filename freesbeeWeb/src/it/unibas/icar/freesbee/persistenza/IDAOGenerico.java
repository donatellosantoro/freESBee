package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.Utente;
import java.io.Serializable;
import java.util.List;

public interface IDAOGenerico<T> extends Serializable{

    public T findById(Utente utente, Long id, boolean lock) throws DAOException;

    public List<T> findAll(Utente utente) throws DAOException;

    public List<T> findAll(Utente utente, int offset, int limite) throws DAOException;

    public T makePersistent(Utente utente, T entity) throws DAOException;

    public void makeTransient(Utente utente, T entity) throws DAOException;

    public void lock(Utente utente, T entity) throws DAOException;
}
