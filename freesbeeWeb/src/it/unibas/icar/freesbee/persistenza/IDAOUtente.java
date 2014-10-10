package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.modello.Utente;

public interface IDAOUtente  extends IDAOGenerico<Soggetto> {
    
    public Soggetto findByNome(Utente utente, String nome, String tipo) throws DAOException; 
            
}



