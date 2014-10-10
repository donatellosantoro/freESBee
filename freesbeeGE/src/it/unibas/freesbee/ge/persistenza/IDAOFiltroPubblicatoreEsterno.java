package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;

public interface IDAOFiltroPubblicatoreEsterno extends IDAOGenerico<FiltroPubblicatoreEsterno> {

    boolean existsFiltroPubblicatoreEsterno(FiltroPubblicatoreEsterno filtroPubblicatoreEsterno) throws DAOException;

    void removeFiltriPubblicatoreBySottoscrizioneEsterna(SottoscrizioneEsterna sottoscrizioneEsterna) throws DAOException;
}



