package it.unibas.icar.freesbee.test.persistenza.mock;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import java.util.Date;
import java.util.List;

public class DAOAccordoServizioMock implements IDAOAccordoServizio {

    public AccordoServizio findByNome(String nome) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<AccordoServizio> findAllFilter(Date maxDate, Date minDate, String nome) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AccordoServizio findById(Long id, boolean lock) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<AccordoServizio> findAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<AccordoServizio> findAll(int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AccordoServizio makePersistent(AccordoServizio entity) throws DAOException {
        return entity;
    }

    public void makeTransient(AccordoServizio entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void lock(AccordoServizio entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
