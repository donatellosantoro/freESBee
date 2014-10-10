package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiInterna;
import it.unibas.freesbee.ge.persistenza.IDAOFiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneInterna;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOSottoscrizioneInternaHibernate extends DAOGenericoHibernate<SottoscrizioneInterna> implements IDAOSottoscrizioneInterna {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOSottoscrizioneInternaHibernate() {
        super(SottoscrizioneInterna.class);
    }

    public List<SottoscrizioneInterna> removeFiltroPubblicatore(CategoriaEventiInterna categoriaEventi, PubblicatoreInterno pubblicatoreInterno) throws DAOException {
        logger.debug("Richiesta eliminazione dei filtri pubblicatori che riguardano " + pubblicatoreInterno);
        List<SottoscrizioneInterna> listaSottoscrizioniEliminate = new ArrayList<SottoscrizioneInterna>();
        IDAOFiltroPubblicatoreInterno daoFiltroPubblicatoreInterno = new DAOFiltroPubblicatoreInternoHibernate();
        IDAOSottoscrizioneInterna daoSottoscrizioneInterna = new DAOSottoscrizioneInternaHibernate();
        IDAOCategoriaEventiInterna daoCategoriaEvnetiInterna = new DAOCategoriaEventiInternaHibernate();

        List<FiltroPubblicatoreInterno> listaNuova = null;

        //Carico tutte le sottoscrizioni perchè la virifica va fatta su tutte le sottoscrizioni
        List<SottoscrizioneInterna> listaSottoscrizioni = categoriaEventi.getListaSottoscrizioni();

        for (SottoscrizioneInterna sottoscrizioneInterna : listaSottoscrizioni) {
            listaNuova = new ArrayList<FiltroPubblicatoreInterno>();
            boolean modificato = false; //verifico se la sottoscrizione è stata modificata
            List<FiltroPubblicatoreInterno> listaFiltroPubblicatore = sottoscrizioneInterna.getListaFiltroPublicatore();

            for (FiltroPubblicatoreInterno filtroPubblicatore : listaFiltroPubblicatore) {
                if (filtroPubblicatore.getPubblicatore().compareTo(pubblicatoreInterno) == 0) {
                    daoFiltroPubblicatoreInterno.makeTransient(filtroPubblicatore);
                    modificato = true;
                } else {
                    filtroPubblicatore.setSottoscrizione(sottoscrizioneInterna);
                    listaNuova.add(filtroPubblicatore);
                }
            }

            //Solo nel caso in cui la lista è stata modificata va salvata o eliminata
            //altrimrnti verebbero eliminate sempre le sottoscrizioni senza filtri anche se corrette
            if (modificato) {
                if (listaFiltroPubblicatore.size() > 1) {
                    sottoscrizioneInterna.setListaFiltroPublicatore(listaNuova);
                    daoSottoscrizioneInterna.makePersistent(sottoscrizioneInterna);
                } else {
                    sottoscrizioneInterna.setListaFiltroPublicatore(listaNuova);
                    daoSottoscrizioneInterna.makeTransient(sottoscrizioneInterna);
                    cancellaSottoscrittore(sottoscrizioneInterna);
                    listaSottoscrizioniEliminate.add(sottoscrizioneInterna);
                }
            }
        }
        for (SottoscrizioneInterna sottoscrizioneInterna : listaSottoscrizioniEliminate) {
            sottoscrizioneInterna.getCategoriaEventi().getListaSottoscrizioni().remove(sottoscrizioneInterna);
            daoCategoriaEvnetiInterna.makePersistent(categoriaEventi);
        }
        return listaSottoscrizioniEliminate;
    }

    private void cancellaSottoscrittore(SottoscrizioneInterna sottoscrizione) throws DAOException {
        logger.debug("Richiesta eliminazione del sottoscrittore" + sottoscrizione.getSottoscrittore());

        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        IDAOSottoscrittore daoSottoscrittore = new DAOSottoscrittoreHibernate();

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


        if (count <= 1) {
            logger.debug("Sottoscrittore eliminato " + sottoscrittore);
            daoSottoscrittore.makeTransient(sottoscrittore);
        }
    }
}
