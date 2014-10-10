package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.PortType;
import java.util.List;

public interface IDAOPortType extends IDAOGenerico<PortType>{
    
    public PortType findByName(String name) throws DAOException;
    
    public PortType findByNameTipo(String name, String tipo) throws DAOException;
    
    public List<PortType> findAll() throws DAOException;

}
