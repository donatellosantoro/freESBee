package it.unibas.freesbee.ge.persistenza.facade;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOFiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOFiltroPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrittoreHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOStatoMessaggioEsternoHibernate;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOSottoscrizioneEsternaFacade {

    private DAOSottoscrizioneEsternaFacade() {
    }
    private static Log logger = LogFactory.getLog(DAOSottoscrizioneEsternaFacade.class);
    private static IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
    private static IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();
    private static IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna = new DAOSottoscrizioneEsternaHibernate();
    private static IDAOFiltroPubblicatoreEsterno daoFiltroPubblicatoreEsterno = new DAOFiltroPubblicatoreEsternoHibernate();
    private static IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
    private static IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno = new DAOStatoMessaggioEsternoHibernate();

    public static void verificaNonEsistenzaSottoscrittoreSottoscrizioneEsterna(CategoriaEventiEsterna categoriaEventiEsterna, Sottoscrittore sottoscrittore) throws DAOException {
        if (daoCategoriaEventiEsterna.existsSottoscrittore(categoriaEventiEsterna, sottoscrittore)) {
            logger.error("Il soggetto " + sottoscrittore + " e' gia' un sottoscrittore per la categoria evento " + categoriaEventiEsterna);
            throw new DAOException("Il soggetto " + sottoscrittore + " e' gia' un sottoscrittore per la categoria evento " + categoriaEventiEsterna);
        }
    }

    public static void verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(CategoriaEventiEsterna categoriaEventiEsterna, Sottoscrittore sottoscrittore) throws DAOException {
        if (!daoCategoriaEventiEsterna.existsSottoscrittore(categoriaEventiEsterna, sottoscrittore)) {
            logger.error("Il soggetto " + sottoscrittore + " non e'un sottoscrittore per la categoria evento " + categoriaEventiEsterna);
            throw new DAOException("Il soggetto " + sottoscrittore + " non e' un sottoscrittore per la categoria evento " + categoriaEventiEsterna);
        }
    }

    public static void aggiungiSottoscrizioneEsterna(CategoriaEventiEsterna categoriaEventiEsterna, Sottoscrittore sottoscrittore, SottoscrizioneEsterna sottoscrizione) throws DAOException {
        //Se  esite lancia eccezione
        verificaNonEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventiEsterna, sottoscrittore);

        daoSottoscrittore.makePersistent(sottoscrittore);

        sottoscrizione.setSottoscrittore(sottoscrittore);
        sottoscrizione.setCategoriaEventi(categoriaEventiEsterna);

        categoriaEventiEsterna.getListaSottoscrizioni().add(sottoscrizione);

        daoSottoscrizioneEsterna.makePersistent(sottoscrizione);

        for (FiltroPubblicatoreEsterno filtroPubblicatore : sottoscrizione.getListaFiltroPublicatore()) {
            if (!daoFiltroPubblicatoreEsterno.existsFiltroPubblicatoreEsterno(filtroPubblicatore)) {
                PubblicatoreEsterno p = filtroPubblicatore.getPubblicatore();
                if (daoPubblicatoreEsterno.findByTipoNome(p.getTipo(), p.getNome()) == null) {
                    daoPubblicatoreEsterno.makePersistent(p);
                }
                daoFiltroPubblicatoreEsterno.makePersistent(filtroPubblicatore);
            }
        }


    }

    public static void eliminaSottoscrizioneEsterna(CategoriaEventiEsterna categoriaEventiEsterna, Sottoscrittore sottoscrittore) throws DAOException {
        //Se non esite lancia eccezione
        verificaEsistenzaSottoscrittoreSottoscrizioneEsterna(categoriaEventiEsterna, sottoscrittore);

        SottoscrizioneEsterna sottoscrizione = daoCategoriaEventiEsterna.findByCategoriaEventiSottoscrittore(categoriaEventiEsterna, sottoscrittore);

        //Elimina anche il pubblicatore esterno del filtro pubblicatore se tale publicatore
        //non risulta in nessun altro filtro (se in un filtro in attesa non viene cancellato)
        daoFiltroPubblicatoreEsterno.removeFiltriPubblicatoreBySottoscrizioneEsterna(sottoscrizione);

        daoStatoMessaggioEsterno.removeBySottoscrittore(sottoscrittore);


        categoriaEventiEsterna.getListaSottoscrizioni().remove(sottoscrizione);
        daoCategoriaEventiEsterna.makePersistent(categoriaEventiEsterna);
        daoSottoscrizioneEsterna.makeTransient(sottoscrizione);

        //Verifica se è da cancellare
        cancellaSottoscrittore(sottoscrizione);


    }

    public static boolean isInAttesaConfermaPubblicatori(SottoscrizioneEsterna sottoscrizione) throws DAOException {
        if (sottoscrizione.getListaFiltroPublicatore() == null || sottoscrizione.getListaFiltroPublicatore().isEmpty()) {
            return false;
        }
        for (FiltroPubblicatoreEsterno filtroPubblicatore : sottoscrizione.getListaFiltroPublicatore()) {
            if (!DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoPerCategoriaEventiEsterna(sottoscrizione.getCategoriaEventi(), filtroPubblicatore.getPubblicatore()) && sottoscrizione.getScadenzaAttesa() != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInAttesaConfermaCategoriaEventi(SottoscrizioneEsterna sottoscrizione) {
        if (sottoscrizione.getCategoriaEventi().isInAttesa() && sottoscrizione.getScadenzaAttesa() != null) {
            return true;
        }
        return false;
    }

    private static void cancellaSottoscrittore(SottoscrizioneEsterna sottoscrizione) throws DAOException {
        logger.debug("Richiesta eliminazione del sottoscrittore" + sottoscrizione.getSottoscrittore());

        Sottoscrittore sottoscrittore = sottoscrizione.getSottoscrittore();
        List<CategoriaEventiEsterna> listaCategoiraEventiEsterna = daoCategoriaEventiEsterna.findAll();
        int count = 0;
        for (CategoriaEventiEsterna catEventi : listaCategoiraEventiEsterna) {
            if (daoCategoriaEventiEsterna.existsSottoscrittore(catEventi, sottoscrittore)) {
                count++;
            }
        }

        IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();

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
