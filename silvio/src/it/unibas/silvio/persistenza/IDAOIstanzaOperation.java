package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.IstanzaOperation;
import java.util.List;

public interface IDAOIstanzaOperation extends IDAOGenerico<IstanzaOperation>{
    
    public IstanzaOperation findByTipo(String tipo) throws DAOException;
    
    public List<IstanzaOperation> findAll() throws DAOException;

}
