package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.Configurazione;


public interface IDAOConfigurazione extends IDAOGenerico<Configurazione>{
    

    public Configurazione caricaConfigurazione() throws DAOException;

    public void salvaConfigurazione(Configurazione configurazione) throws DAOException;

}
