package it.unibas.freesbee.ge.persistenza;


import it.unibas.freesbee.ge.modello.Sottoscrittore;

public interface IDAOSottoscrittore extends IDAOGenerico<Sottoscrittore> {

     Sottoscrittore findByTipoNome(String tipo, String nome) throws DAOException;

 
}



