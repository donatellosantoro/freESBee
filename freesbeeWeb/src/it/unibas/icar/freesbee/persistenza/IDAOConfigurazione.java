package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.modello.Utente;

public interface IDAOConfigurazione extends IDAOGenerico<Configurazione> {
    
    public Configurazione find(Utente utente) throws DAOException;
    
}



