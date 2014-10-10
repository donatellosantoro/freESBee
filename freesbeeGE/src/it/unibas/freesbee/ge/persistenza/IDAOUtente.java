package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.Utente;

public interface IDAOUtente  extends IDAOGenerico<Utente> {
    
    public Utente findByNomeUtente(String nomeUtente) throws DAOException; 
            
}



