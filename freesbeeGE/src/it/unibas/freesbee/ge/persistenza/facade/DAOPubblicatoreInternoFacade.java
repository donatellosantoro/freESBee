package it.unibas.freesbee.ge.persistenza.facade;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiInternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOEventoPubblicatoInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOSottoscrizioneInternaHibernate;
import it.unibas.freesbee.ge.ws.gestoreeventi.ClientGEControlProtocol;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOPubblicatoreInternoFacade {

    private DAOPubblicatoreInternoFacade() {
    }
    private static Log logger = LogFactory.getLog(DAOPubblicatoreInternoFacade.class);
    private static IDAOCategoriaEventiInterna daoCategoriaEventiInterna = new DAOCategoriaEventiInternaHibernate();
    private static IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
    private static IDAOEventoPubblicatoInterno daoEventoPubblicatoInterno = new DAOEventoPubblicatoInternoHibernate();
    private static IDAOSottoscrizioneInterna daoSottoscrizioneInterna = new DAOSottoscrizioneInternaHibernate();

    public static void verificaEsistenzaPubblicatoreInterno(String tipo, String nome, String catogiraInterna) throws DAOException {
        if (daoPubblicatoreInterno.findByTipoNome(tipo, nome) == null) {
            logger.error("Il soggetto " + tipo + " " + nome + " non e' un pubblicatore interno");
            throw new DAOException("Il soggetto " + tipo + " " + nome + " non e' un pubblicatore interno");
        }
        DAOCategoriaEventiInternaFacade.verificaEsistenzaCategoriaEventiInternaAttavia(catogiraInterna);

        verificaEsistenzaPubblicatoreInterno(daoCategoriaEventiInterna.findByNome(catogiraInterna), daoPubblicatoreInterno.findByTipoNome(tipo, nome));
    }

    public static void verificaNonEsistenzaPubblicatoreInterno(CategoriaEventiInterna categoriaEventi, PubblicatoreInterno pubblicatore) throws DAOException {
        if (daoCategoriaEventiInterna.existsPubblicatoreInterno(categoriaEventi, pubblicatore)) {
            logger.error("Il soggetto " + pubblicatore + " e' gia' un pubblicatore interno per la categoria eventi " + categoriaEventi);
            throw new DAOException("Il soggetto " + pubblicatore + " e' gia' un pubblicatore interno per la categoria eventi " + categoriaEventi);
        }
    }

    public static void verificaEsistenzaPubblicatoreInterno(CategoriaEventiInterna categoriaEventi, PubblicatoreInterno pubblicatore) throws DAOException {
        if (!daoCategoriaEventiInterna.existsPubblicatoreInterno(categoriaEventi, pubblicatore)) {
            logger.error("Il soggetto " + pubblicatore + " non e' un pubblicatore interno per la categoria eventi " + categoriaEventi);
            throw new DAOException("Il soggetto " + pubblicatore + " non e' un pubblicatore interno per la categoria eventi " + categoriaEventi);
        }
    }

    public static boolean isPubblicatoreInternoPerCategoriaEventiInterna(CategoriaEventiInterna categoriaEventi, PubblicatoreInterno pubblicatore) {
        return daoCategoriaEventiInterna.existsPubblicatoreInterno(categoriaEventi, pubblicatore);
    }

    public static void aggiungiPubblicatoreInterno(CategoriaEventiInterna categoriaEventi, PubblicatoreInterno pubblicatore) throws DAOException {
        verificaNonEsistenzaPubblicatoreInterno(categoriaEventi, pubblicatore);
        pubblicatore.getListaCatgoriaEventi().add(categoriaEventi);
        categoriaEventi.getListaPubblicatori().add(pubblicatore);
        daoPubblicatoreInterno.makePersistent(pubblicatore);
        daoCategoriaEventiInterna.makePersistent(categoriaEventi);
    }

    public static void eliminaPubblicatoreInterno(CategoriaEventiInterna categoriaEventi, PubblicatoreInterno pubblicatore) throws DAOException {
        //rimuove il pubblicatore della categoria evento e tutti i filtro pubblicatori che lo riguradano
        verificaEsistenzaPubblicatoreInterno(categoriaEventi, pubblicatore);


        //Verifico per quante categorie è pubblicatore il soggetto
        List<CategoriaEventiInterna> listaCategoiraEventi = daoCategoriaEventiInterna.findAll();
        int count = 0;
        for (CategoriaEventiInterna cate : listaCategoiraEventi) {
            if (daoCategoriaEventiInterna.existsPubblicatoreInterno(cate, pubblicatore)) {
                count++;
            }
        }

        //Rimozione dei filtri pubblicatore e nel caso delle relative sottoscrizioni
        List<SottoscrizioneInterna> listaSottoscrizioniEliminate = daoSottoscrizioneInterna.removeFiltroPubblicatore(categoriaEventi, pubblicatore);


        //Rimozione degli eventi pubblicati
        daoEventoPubblicatoInterno.removeByPubblicatoreInterno(pubblicatore);


        if (count <= 1) {
            pubblicatore.getListaCatgoriaEventi().remove(categoriaEventi);
            categoriaEventi.getListaPubblicatori().remove(pubblicatore);
            daoCategoriaEventiInterna.makePersistent(categoriaEventi);
            daoPubblicatoreInterno.makeTransient(pubblicatore);
        } else {
            pubblicatore.getListaCatgoriaEventi().remove(categoriaEventi);
            categoriaEventi.getListaPubblicatori().remove(pubblicatore);
            daoCategoriaEventiInterna.makePersistent(categoriaEventi);
            daoPubblicatoreInterno.makePersistent(pubblicatore);
        }

        for (SottoscrizioneInterna sottoscrizioneEliminata : listaSottoscrizioniEliminate) {
            ClientGEControlProtocol.pubblicaInternamenteComunicazioneRimozioneSottoscrizione("Eliminazione di tutti i pubblicatori specificati nei filtri", sottoscrizioneEliminata.getSottoscrittore(), categoriaEventi);
        }

    }
}
