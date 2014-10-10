package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.Utente;

public interface IDAOUtente  extends IDAOGenerico<Utente> {
    
    public Utente findByNomeUtente(String nomeUtente) throws DAOException; 
            
}



