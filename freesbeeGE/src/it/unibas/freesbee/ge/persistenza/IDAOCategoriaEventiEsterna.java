package it.unibas.freesbee.ge.persistenza;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;

public interface IDAOCategoriaEventiEsterna extends IDAOGenerico<CategoriaEventiEsterna> {

    CategoriaEventiEsterna findByNome(String nome) throws DAOException;

    boolean existsPubblicatoreEsterno(CategoriaEventiEsterna categoriaEventiEsterna, PubblicatoreEsterno pubblicatoreEsterno);

    boolean existsSottoscrittore(CategoriaEventiEsterna categoriaEventiEsterna, Sottoscrittore sottoscrittore) throws DAOException;

    SottoscrizioneEsterna findByCategoriaEventiSottoscrittore(CategoriaEventiEsterna categoriaEventiEsterna, Sottoscrittore sottoscrittore) throws DAOException;

    void removePubblicatoreEsternoPerCategoriaEventi(CategoriaEventiEsterna categoriaEventiEsterna, PubblicatoreEsterno pubblicatoreEsterno) throws DAOException;
}



