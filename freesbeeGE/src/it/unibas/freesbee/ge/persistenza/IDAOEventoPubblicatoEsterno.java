package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.EventoPubblicatoEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;

public interface IDAOEventoPubblicatoEsterno extends IDAOGenerico<EventoPubblicatoEsterno> {

     void removeByPubblicatoreEsterno(PubblicatoreEsterno pubblicatoreEsterno) throws DAOException;

}



