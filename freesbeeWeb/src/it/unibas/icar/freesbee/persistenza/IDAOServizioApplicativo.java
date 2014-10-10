package it.unibas.icar.freesbee.persistenza;

import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.modello.Utente;

public interface IDAOServizioApplicativo extends IDAOGenerico<ServizioApplicativo> {

    public ServizioApplicativo findByNome(Utente utente, String nome) throws DAOException;

    }



