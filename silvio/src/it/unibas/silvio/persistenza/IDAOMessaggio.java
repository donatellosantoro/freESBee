package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.Messaggio;
import java.util.List;

public interface IDAOMessaggio extends IDAOGenerico<Messaggio>{
    
    public Messaggio findResponseAsyncByIdMessageRequest(String idMessaggio) throws DAOException;

    public List<Messaggio> findByData(String data)throws DAOException;
    public List<Messaggio> findByIDMessaggio(String idMessaggio)throws DAOException;
    public List<Messaggio> findByIDRelatesTo(String idRelatesTo)throws DAOException;
    public Messaggio findByIDRelatesTo(String idRelatesTo, String tipo)throws DAOException;
    public List<Messaggio> findByIstanzaPortType(String istanzaPortType)throws DAOException;
    public Messaggio findByIDMessaggio(String idMessaggio, String tipo) throws DAOException;
    public List<Messaggio> findByTipo(String tipo) throws DAOException;
    public List<Messaggio> findByTipoIstanza(String tipo, String istanzaPortType) throws DAOException;

}