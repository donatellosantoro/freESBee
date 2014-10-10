package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import java.util.List;

public interface IDAOSottoscrizioneEsterna extends IDAOGenerico<SottoscrizioneEsterna> {

    List<SottoscrizioneEsterna> removeFiltroPubblicatore(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatoreEsterno) throws DAOException;

 
}



