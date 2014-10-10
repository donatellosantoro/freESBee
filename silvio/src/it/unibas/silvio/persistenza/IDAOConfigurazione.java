package it.unibas.silvio.persistenza;

import it.unibas.silvio.modello.Configurazione;

public interface IDAOConfigurazione extends IDAOGenerico<Configurazione>{
    
    public Configurazione caricaConfigurazione() throws DAOException;
    
    public void salvaConfigurazione(Configurazione configurazione) throws DAOException;

}
