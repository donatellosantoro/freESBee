package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.ConfigurazioneSP;


public interface IDAOConfigurazioneSP extends IDAOGenerico<ConfigurazioneSP>{
    

    public ConfigurazioneSP caricaConfigurazioneSP() throws DAOException;

    public void salvaConfigurazioneSP(ConfigurazioneSP configurazioneSP) throws DAOException;

}
