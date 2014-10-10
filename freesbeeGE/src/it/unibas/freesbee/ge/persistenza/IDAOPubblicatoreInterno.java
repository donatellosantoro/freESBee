package it.unibas.freesbee.ge.persistenza;


import it.unibas.freesbee.ge.modello.PubblicatoreInterno;

public interface IDAOPubblicatoreInterno extends IDAOGenerico<PubblicatoreInterno> {

    PubblicatoreInterno findByTipoNome(String tipo, String nome) throws DAOException;

 
}



