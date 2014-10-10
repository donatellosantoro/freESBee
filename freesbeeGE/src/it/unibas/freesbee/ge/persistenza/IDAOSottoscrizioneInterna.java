package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import java.util.List;

public interface IDAOSottoscrizioneInterna extends IDAOGenerico<SottoscrizioneInterna> {

    List<SottoscrizioneInterna> removeFiltroPubblicatore(CategoriaEventiInterna categoriaEventi, PubblicatoreInterno pubblicatoreInterno) throws DAOException;
}



