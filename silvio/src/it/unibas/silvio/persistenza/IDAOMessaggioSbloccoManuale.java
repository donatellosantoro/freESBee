package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.MessaggioSbloccoManuale;

public interface IDAOMessaggioSbloccoManuale extends IDAOGenerico<MessaggioSbloccoManuale>{
    
    public MessaggioSbloccoManuale findById(long id) throws DAOException;

}
