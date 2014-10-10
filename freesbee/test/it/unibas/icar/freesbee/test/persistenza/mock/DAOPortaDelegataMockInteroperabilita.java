package it.unibas.icar.freesbee.test.persistenza.mock;

import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import java.util.List;

public class DAOPortaDelegataMockInteroperabilita implements IDAOPortaDelegata {

    public PortaDelegata findByNome(String nome) throws DAOException {
        PortaDelegata portaDelegata = new PortaDelegata();
        portaDelegata.setNome(nome);
        portaDelegata.setAzione(null);
        portaDelegata.setDescrizione("");        
        portaDelegata.setStringaFruitore("FruitoreUNICAR");
        portaDelegata.setStringaTipoFruitore("SPC");
        portaDelegata.setStringaErogatore("ErogatoreUNICAR");
        portaDelegata.setStringaTipoErogatore("SPC");
        portaDelegata.setStringaServizio("IcarOneWayLoopback");
        portaDelegata.setStringaTipoServizio("SPC");

        return portaDelegata;
    }

    public List<PortaDelegata> findBySoggetto(Soggetto soggetto) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PortaDelegata findById(Long id, boolean lock) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PortaDelegata> findAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PortaDelegata> findAll(int offset, int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PortaDelegata makePersistent(PortaDelegata entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void makeTransient(PortaDelegata entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void lock(PortaDelegata entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeByServizio(Servizio servizio) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
