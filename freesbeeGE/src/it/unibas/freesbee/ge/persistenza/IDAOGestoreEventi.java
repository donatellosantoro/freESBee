package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.GestoreEventi;

public interface IDAOGestoreEventi extends IDAOGenerico<GestoreEventi> {

    GestoreEventi findByTipoNome(String tipo, String nome) throws DAOException;
}



