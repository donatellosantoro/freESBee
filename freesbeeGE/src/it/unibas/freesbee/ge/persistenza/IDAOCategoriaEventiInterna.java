package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;

public interface IDAOCategoriaEventiInterna extends IDAOGenerico<CategoriaEventiInterna> {

    CategoriaEventiInterna findByNome(String nome) throws DAOException;

    boolean existsPubblicatoreInterno(CategoriaEventiInterna categoriaEventiInterna, PubblicatoreInterno pubblicatoreInterno);

    boolean existsSottoscrittore(CategoriaEventiInterna categoriaEventiInterna, Sottoscrittore sottoscrittore);

    SottoscrizioneInterna findByCategoriaEventiSottoscrittore(CategoriaEventiInterna categoriaEventiInterna, Sottoscrittore sottoscrittore) throws DAOException;
}



