package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;

public interface IDAOPubblicatoreEsterno extends IDAOGenerico<PubblicatoreEsterno> {

    PubblicatoreEsterno findByTipoNome(String tipo, String nome) throws DAOException;

    void deletePubblicatoreEsterno(PubblicatoreEsterno pubblicatoreEsterno) throws DAOException;
}



