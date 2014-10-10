package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.Utente;

public interface IDAOAutenticazione extends IDAOGenerico<Utente> {
    
    public boolean autentica(Utente utente) throws DAOException;
    public boolean cambiaPassword(String nuovaPassword, Utente utente) throws DAOException;
    public String getRuolo(Utente utente) throws DAOException;
    
}
