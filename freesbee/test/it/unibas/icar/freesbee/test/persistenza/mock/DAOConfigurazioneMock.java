package it.unibas.icar.freesbee.test.persistenza.mock;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOConfigurazione;
import java.util.List;

public class DAOConfigurazioneMock implements IDAOConfigurazione{
    
    private Configurazione configurazione;

    public DAOConfigurazioneMock(Configurazione configurazione) {
        this.configurazione = configurazione;
    }

    public Configurazione getConfigurazione() throws DAOException {
        return configurazione;
    }

    public Configurazione findById(Long id, boolean lock) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Configurazione> findAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Configurazione> findAll(int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Configurazione makePersistent(Configurazione entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(Configurazione entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void lock(Configurazione entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

}
