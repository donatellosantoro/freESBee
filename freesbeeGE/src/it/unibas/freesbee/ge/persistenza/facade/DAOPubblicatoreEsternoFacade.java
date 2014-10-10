package it.unibas.freesbee.ge.persistenza.facade;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOEventoPubblicatoEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneEsternaHibernate;
import it.unibas.freesbee.ge.ws.gestoreeventi.ClientGEControlProtocol;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOPubblicatoreEsternoFacade {

    private DAOPubblicatoreEsternoFacade() {
    }
    private static Log logger = LogFactory.getLog(DAOPubblicatoreEsternoFacade.class);
    private static IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
    private static IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
    private static IDAOEventoPubblicatoEsterno daoEventoPubblicatoEsterno = new DAOEventoPubblicatoEsternoHibernate();
    private static IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna = new DAOSottoscrizioneEsternaHibernate();

    public static boolean isPubblicatoreEsternoPerCategoriaEventiEsterna(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) {
        return daoCategoriaEventiEsterna.existsPubblicatoreEsterno(categoriaEventi, pubblicatore);
    }

    public static boolean isPubblicatoreEsternoDaConfermare(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) throws DAOException {
        //Il pubblicatore deve esistere altrimenti il metodo non rileva l'errore e torna false
        if (daoPubblicatoreEsterno.findByTipoNome(pubblicatore.getTipo(), pubblicatore.getNome()) == null) {
            throw new DAOException("Il soggetto" + pubblicatore + " non e' un pubblicatore esterno ");
        }

        List<SottoscrizioneEsterna> lista = categoriaEventi.getListaSottoscrizioni();
        for (SottoscrizioneEsterna sottoscrizione : lista) {
            for (FiltroPubblicatoreEsterno filtroPubblicatore : sottoscrizione.getListaFiltroPublicatore()) {
                if (filtroPubblicatore.getPubblicatore().compareTo(pubblicatore) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void verificaEsistenzaPubblicatoreEsterno(String tipo, String nome, String catogiraInterna) throws DAOException {
        if (daoPubblicatoreEsterno.findByTipoNome(tipo, nome) == null) {
            logger.error("Il soggetto " + tipo + " " + nome + " non e' un pubblicatore esterno");
            throw new DAOException("Il soggetto " + tipo + " " + nome + " non e' un pubblicatore esterno");
        }
        DAOCategoriaEventiEsternaFacade.verificaEsistenzaCategoriaEventiEsternaAttavia(catogiraInterna);

        verificaEsistenzaPubblicatoreEsterno(daoCategoriaEventiEsterna.findByNome(catogiraInterna), daoPubblicatoreEsterno.findByTipoNome(tipo, nome));
    }

    public static void verificaEsistenzaPubblicatoreEsterno(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) throws DAOException {
        if (!daoCategoriaEventiEsterna.existsPubblicatoreEsterno(categoriaEventi, pubblicatore)) {
            logger.error("Il soggetto " + pubblicatore + " non e' un pubblicatore esterno per la categoria eventi " + categoriaEventi);
            throw new DAOException("Il soggetto " + pubblicatore + " non e' un pubblicatore esterno per la categoria eventi " + categoriaEventi);

        }
    }

    public static void verificaNonEsistenzaPubblicatoreEsterno(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) throws DAOException {
        if (daoCategoriaEventiEsterna.existsPubblicatoreEsterno(categoriaEventi, pubblicatore)) {
            logger.error("Il soggetto " + pubblicatore + " e'gia' un pubblicatore esterno per la categoria eventi " + categoriaEventi);
            throw new DAOException("Il soggetto " + pubblicatore + " e' gia' un pubblicatore esterno per la categoria eventi " + categoriaEventi);

        }
    }

    public static boolean isInAttesaPubblicatoreEsternoPerCategoriaEventi(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) throws DAOException {
        if (daoPubblicatoreEsterno.findByTipoNome(pubblicatore.getTipo(), pubblicatore.getNome()) == null) {
            throw new DAOException("Il soggetto" + pubblicatore + " non e' un pubblicatore esterno ");
        }


        return !daoCategoriaEventiEsterna.existsPubblicatoreEsterno(categoriaEventi, pubblicatore);
    }

    public static void eliminaPubblicatoreEsterno(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) throws DAOException {
        //rimuove il pubblicatore della categoria evento e tutti i filtro pubblicatori che lo riguradano
        if (DAOPubblicatoreEsternoFacade.isInAttesaPubblicatoreEsternoPerCategoriaEventi(categoriaEventi, pubblicatore)) {
            //Il pubblicatore non è confermato ma esiste verifico se è da confermare

            if (!DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoDaConfermare(categoriaEventi, pubblicatore)) {
                throw new DAOException("Il soggetto" + pubblicatore + " non e' un pubblicatore esterno da confermare per la categoria eventi " + categoriaEventi.getNome());
            }
            logger.info("Eliminazione pubblicatore non confermato");
            //Il pubblicatore è da confermare
            rimuoviPubblicatoreEsternoNonConfermato(categoriaEventi, pubblicatore);
        } else {
            logger.info("Eliminazione pubblicatore confermato");
            verificaEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatore);
            rimuoviPubblicatoreEsternoConfermato(categoriaEventi, pubblicatore);
        }

    }

    private static void rimuoviPubblicatoreEsternoNonConfermato(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) throws DAOException {
        List<CategoriaEventiEsterna> listaCategoiraEventi = daoCategoriaEventiEsterna.findAll();
        int count1 = 0; //conta per quante categorie è un pubblicatore confermato
        for (CategoriaEventiEsterna cate : listaCategoiraEventi) {
            if (daoCategoriaEventiEsterna.existsPubblicatoreEsterno(cate, pubblicatore)) {
                count1++;
            }
        }


        List<SottoscrizioneEsterna> listaSottoscrizioni = daoSottoscrizioneEsterna.findAll();
        int count = 0; //conta per quante categorie è un pubblicatore non confermatao
        for (SottoscrizioneEsterna sott : listaSottoscrizioni) {
            for (FiltroPubblicatoreEsterno pubb : sott.getListaFiltroPublicatore()) {
                if (pubb.getPubblicatore().compareTo(pubblicatore) == 0 && sott.getCategoriaEventi().compareTo(categoriaEventi) != 0) {
                    count++;
                }
            }
        }

        //Rimozione dei filtri pubblicatore e nel caso delle relative sottoscrizioni
        List<SottoscrizioneEsterna> listaSottoscrizioniEliminate = daoSottoscrizioneEsterna.removeFiltroPubblicatore(categoriaEventi, pubblicatore);

        //Rimozione degli eventi pubblicati
        daoEventoPubblicatoEsterno.removeByPubblicatoreEsterno(pubblicatore);


        //Cancello il pubblicatore solo se non è pubblicatore confermato per nessuna categoria
        //e se risulta in un unico filtro pubbulicatore cioè quello che sto eliminando
        if (count < 1 && count1 <= 1) {
            pubblicatore.getListaCatgoriaEventi().remove(categoriaEventi);
            categoriaEventi.getListaPubblicatori().remove(pubblicatore);
            daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
            daoPubblicatoreEsterno.makeTransient(pubblicatore);
        } else {
            pubblicatore.getListaCatgoriaEventi().remove(categoriaEventi);
            categoriaEventi.getListaPubblicatori().remove(pubblicatore);
            daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
            daoPubblicatoreEsterno.makePersistent(pubblicatore);
        }

        for (SottoscrizioneEsterna sottoscrizioneEliminata : listaSottoscrizioniEliminate) {
            ClientGEControlProtocol.pubblicaInternamenteComunicazioneRimozioneSottoscrizione("Eliminazione di tutti i pubblicatori specificati nei filtri", sottoscrizioneEliminata.getSottoscrittore(), categoriaEventi);
        }

    }

    private static void rimuoviPubblicatoreEsternoConfermato(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) throws DAOException {
        List<CategoriaEventiEsterna> listaCategoiraEventi = daoCategoriaEventiEsterna.findAll();
        int count1 = 0; //conta per quante categorie è un pubblicatore confermato
        for (CategoriaEventiEsterna cate : listaCategoiraEventi) {
            if (daoCategoriaEventiEsterna.existsPubblicatoreEsterno(cate, pubblicatore)) {
                count1++;
            }
        }


        List<SottoscrizioneEsterna> listaSottoscrizioni = daoSottoscrizioneEsterna.findAll();
        int count = 0; //conta per quante categorie è un pubblicatore non confermatao
        for (SottoscrizioneEsterna sott : listaSottoscrizioni) {
            for (FiltroPubblicatoreEsterno pubb : sott.getListaFiltroPublicatore()) {
                if (pubb.getPubblicatore().compareTo(pubblicatore) == 0 && sott.getCategoriaEventi().compareTo(categoriaEventi) != 0) {
                    count++;
                }
            }
        }

        //Rimozione dei filtri pubblicatore e nel caso delle relative sottoscrizioni
        List<SottoscrizioneEsterna> listaSottoscrizioniEliminate = daoSottoscrizioneEsterna.removeFiltroPubblicatore(categoriaEventi, pubblicatore);

        //Rimozione degli eventi pubblicati
        daoEventoPubblicatoEsterno.removeByPubblicatoreEsterno(pubblicatore);

        //Cancello il pubblicatore solo se non risulta in nessun filtro pubbulicatore
        //e se è pubblicatore confermato solo per una categoria
        if (count1 <= 1 && count < 1) {
            pubblicatore.getListaCatgoriaEventi().remove(categoriaEventi);
            categoriaEventi.getListaPubblicatori().remove(pubblicatore);
            daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
            daoPubblicatoreEsterno.makeTransient(pubblicatore);
        } else {
            pubblicatore.getListaCatgoriaEventi().remove(categoriaEventi);
            categoriaEventi.getListaPubblicatori().remove(pubblicatore);
            daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
            daoPubblicatoreEsterno.makePersistent(pubblicatore);
        }

        for (SottoscrizioneEsterna sottoscrizioneEliminata : listaSottoscrizioniEliminate) {
            ClientGEControlProtocol.pubblicaInternamenteComunicazioneRimozioneSottoscrizione("Eliminazione di tutti i pubblicatori specificati nei filtri", sottoscrizioneEliminata.getSottoscrittore(), categoriaEventi);
        }
    }

    public static void aggiungiPubblicatoreEsterno(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatore) throws DAOException {
        //rimuove il pubblicatore della categoria evento e tutti i filtro pubblicatori che lo riguradano
        verificaNonEsistenzaPubblicatoreEsterno(categoriaEventi, pubblicatore);
        PubblicatoreEsterno soggettoTrov = daoPubblicatoreEsterno.findByTipoNome(pubblicatore.getTipo(), pubblicatore.getNome());
        if (soggettoTrov == null) {
            daoPubblicatoreEsterno.makePersistent(pubblicatore);
        } else {
            pubblicatore = soggettoTrov;
        }
        pubblicatore.getListaCatgoriaEventi().add(categoriaEventi);
        categoriaEventi.getListaPubblicatori().add(pubblicatore);
        daoCategoriaEventiEsterna.makePersistent(categoriaEventi);
    }
}
