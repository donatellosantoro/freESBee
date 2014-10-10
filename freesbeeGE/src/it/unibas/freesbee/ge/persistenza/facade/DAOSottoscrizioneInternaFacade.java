package it.unibas.freesbee.ge.persistenza.facade;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOFiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOFiltroPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOStatoMessaggioInternoHibernate;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOSottoscrizioneInternaFacade {

    private DAOSottoscrizioneInternaFacade() {
    }
    private static Log logger = LogFactory.getLog(DAOSottoscrizioneInternaFacade.class);
    private static IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
    private static IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
    private static IDAOSottoscrizioneInterna daoSottoscrizioneInterna = new DAOSottoscrizioneInternaHibernate();
    private static IDAOFiltroPubblicatoreInterno daoFiltroPubblicatoreInterno = new DAOFiltroPubblicatoreInternoHibernate();
    private static IDAOStatoMessaggioInterno daoStatoMessaggioInterno = new DAOStatoMessaggioInternoHibernate();

    public static void verificaNonEsistenzaSottoscrittoreSottoscrizioneInterna(CategoriaEventiInterna categoriaEventiInterna, Sottoscrittore sottoscrittore) throws DAOException {
        if (daoCategoriaEventiInterna.existsSottoscrittore(categoriaEventiInterna, sottoscrittore)) {
            logger.error("Il soggetto " + sottoscrittore + " e' gia' un sottoscrittore per la categoria evento " + categoriaEventiInterna);
            throw new DAOException("Il soggetto " + sottoscrittore + " e' gia' un sottoscrittore per la categoria evento " + categoriaEventiInterna);
        }
    }

    public static void verificaEsistenzaSottoscrittoreSottoscrizioneInterna(CategoriaEventiInterna categoriaEventiInterna, Sottoscrittore sottoscrittore) throws DAOException {
        if (!daoCategoriaEventiInterna.existsSottoscrittore(categoriaEventiInterna, sottoscrittore)) {
            logger.error("Il soggetto " + sottoscrittore + " non e' un sottoscrittore per la categoria evento " + categoriaEventiInterna);
            throw new DAOException("Il soggetto " + sottoscrittore + " non e' un sottoscrittore per la categoria evento " + categoriaEventiInterna);
        }
    }

    public static void aggiungiSottoscrizioneInterna(CategoriaEventiInterna categoriaEventiInterna, Sottoscrittore sottoscrittore, SottoscrizioneInterna sottoscrizione) throws DAOException {
        verificaNonEsistenzaSottoscrittoreSottoscrizioneInterna(categoriaEventiInterna, sottoscrittore);

        daoSottoscrittore.makePersistent(sottoscrittore);

        sottoscrizione.setSottoscrittore(sottoscrittore);
        sottoscrizione.setCategoriaEventi(categoriaEventiInterna);

        categoriaEventiInterna.getListaSottoscrizioni().add(sottoscrizione);

        daoSottoscrizioneInterna.makePersistent(sottoscrizione);

        for (FiltroPubblicatoreInterno filtroPubblicatore : sottoscrizione.getListaFiltroPublicatore()) {
            if (!daoFiltroPubblicatoreInterno.existsFiltroPubblicatoreInterno(filtroPubblicatore)) {
                daoFiltroPubblicatoreInterno.makePersistent(filtroPubblicatore);
            }
        }
    }

    public static void eliminaSottoscrizioneInterna(CategoriaEventiInterna categoriaEventiInterna, Sottoscrittore sottoscrittore) throws DAOException {
        //Se non esite lancia eccezione
        verificaEsistenzaSottoscrittoreSottoscrizioneInterna(categoriaEventiInterna, sottoscrittore);

        SottoscrizioneInterna sottoscrizione = daoCategoriaEventiInterna.findByCategoriaEventiSottoscrittore(categoriaEventiInterna, sottoscrittore);

        daoFiltroPubblicatoreInterno.removeFiltriPubblicatoreBySottoscrizioneInterna(sottoscrizione);

        daoStatoMessaggioInterno.removeBySottoscrittore(sottoscrittore);


        categoriaEventiInterna.getListaSottoscrizioni().remove(sottoscrizione);
        daoCategoriaEventiInterna.makePersistent(categoriaEventiInterna);
        daoSottoscrizioneInterna.makeTransient(sottoscrizione);

        //Verifica se è da cancellare
        cancellaSottoscrittore(sottoscrizione);


    }

    public static void cancellaSottoscrittore(SottoscrizioneInterna sottoscrizione) throws DAOException {
        logger.debug("Richiesta eliminazione del sottoscrittore" + sottoscrizione.getSottoscrittore());

        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();

        Sottoscrittore sottoscrittore = sottoscrizione.getSottoscrittore();
        List<CategoriaEventiEsterna> listaCategoiraEventiEsterna = daoCategoriaEventiEsterna.findAll();
        int count = 0;
        for (CategoriaEventiEsterna catEventi : listaCategoiraEventiEsterna) {
            if (daoCategoriaEventiEsterna.existsSottoscrittore(catEventi, sottoscrittore)) {
                count++;
            }
        }


        List<CategoriaEventiInterna> listaCategoiraEventiInterna = daoCategoriaEventiInterna.findAll();

        for (CategoriaEventiInterna catEventi : listaCategoiraEventiInterna) {
            if (daoCategoriaEventiInterna.existsSottoscrittore(catEventi, sottoscrittore)) {
                count++;
            }
        }


        if (count <= 0) {
            logger.debug("Sottoscrittore eliminato " + sottoscrittore);
            daoSottoscrittore.makeTransient(sottoscrittore);
        }
    }
}
