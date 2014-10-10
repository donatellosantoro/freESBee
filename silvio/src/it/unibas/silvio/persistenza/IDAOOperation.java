package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.Operation;
import java.util.List;

public interface IDAOOperation extends IDAOGenerico<Operation>{
    
    public Operation findByNameProfiloTipo(String name, String profilo, String tipo) throws DAOException;
    
    public List<Operation> findAll() throws DAOException;

}
