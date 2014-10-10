package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.Utente;
import java.util.List;

public interface IDAOAzione extends IDAOGenerico<Azione> {

    public List<Azione> findByAccordo(Utente utente, AccordoServizio accordo) throws DAOException;
            
}



