package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;

public interface IDAOFiltroPubblicatoreInterno extends IDAOGenerico<FiltroPubblicatoreInterno> {

    boolean existsFiltroPubblicatoreInterno(FiltroPubblicatoreInterno filtroPubblicatoreInterno) throws DAOException;

    void removeFiltriPubblicatoreBySottoscrizioneInterna(SottoscrizioneInterna sottoscrizioneInterna) throws DAOException;
}



