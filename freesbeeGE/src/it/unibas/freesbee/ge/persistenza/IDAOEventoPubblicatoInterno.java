package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.EventoPubblicatoInterno;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;

public interface IDAOEventoPubblicatoInterno extends IDAOGenerico<EventoPubblicatoInterno> {

     void removeByPubblicatoreInterno(PubblicatoreInterno pubblicatoreInterno) throws DAOException;

}



