package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Utente;
import java.util.List;

public interface IDAOAccordoServizio extends IDAOGenerico<AccordoServizio> {

    public AccordoServizio findByNome(Utente utente, String nome) throws DAOException;
    
    public List<AccordoServizio> findAllNoLazy(Utente utente) throws DAOException;
}



