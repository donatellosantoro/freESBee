package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.EventoPubblicatoEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.StatoMessaggioEsterno;

public interface IDAOStatoMessaggioEsterno extends IDAOGenerico<StatoMessaggioEsterno> {

    void removeByEentoPubblicatoEsterno(EventoPubblicatoEsterno eventoPubblicatoEsterno) throws DAOException;

    void removeBySottoscrittore(Sottoscrittore sottoscrittore) throws DAOException;
}



