package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.Message;
import java.util.List;

public interface IDAOMessage extends IDAOGenerico<Message>{
    
    public Message findByNameType(String name, String type) throws DAOException;
    
    public List<Message> findAll() throws DAOException;

}
