package it.unibas.icar.freesbee.test.persistenza.mock;

import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import java.util.Date;
import java.util.List;

public class DAOSoggettoMock implements IDAOSoggetto {

    public Soggetto findByNome(String nome, String tipo) throws DAOException {
        Soggetto soggetto = new Soggetto();
        soggetto.setNome("SoggettoDiTest");
        soggetto.setTipo("TEST");

        return soggetto;
    }

    public Soggetto findById(Long id, boolean lock) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Soggetto> findAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Soggetto> findAll(int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Soggetto makePersistent(Soggetto entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(Soggetto entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void lock(Soggetto entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Soggetto> findAllFilter(Date maxDate, Date minDate, String nome, String tipo) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
