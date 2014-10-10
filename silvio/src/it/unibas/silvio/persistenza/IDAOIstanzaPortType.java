package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.IstanzaPortType;

public interface IDAOIstanzaPortType extends IDAOGenerico<IstanzaPortType>{
    
    public IstanzaPortType findById(long id) throws DAOException;

}
