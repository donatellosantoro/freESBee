package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;
import java.util.List;

public interface IDAOServizio extends IDAOGenerico<Servizio> {

    public Servizio findByNome(Utente utente, String nome, String tipo, Soggetto erogatore) throws DAOException;

    public List<Servizio> findAllNoLazy(Utente utente) throws DAOException;

    public List<Servizio> findByAccordo(Utente utente, AccordoServizio accordo) throws DAOException;

    
}



