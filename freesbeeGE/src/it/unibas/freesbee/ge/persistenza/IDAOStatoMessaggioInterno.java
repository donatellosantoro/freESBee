package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.EventoPubblicatoInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.StatoMessaggioInterno;

public interface IDAOStatoMessaggioInterno extends IDAOGenerico<StatoMessaggioInterno> {

//    StatoMessaggioInterno findById(long id) throws DAOException;

    void removeByEentoPubblicatoInterno(EventoPubblicatoInterno eventoPubblicatoInterno) throws DAOException;

    void removeBySottoscrittore(Sottoscrittore sottoscrittore) throws DAOException;
}



