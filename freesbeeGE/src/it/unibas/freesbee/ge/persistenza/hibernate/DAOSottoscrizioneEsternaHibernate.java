package it.unibas.freesbee.ge.persistenza.hibernate;

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
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOSottoscrizioneEsternaHibernate extends DAOGenericoHibernate<SottoscrizioneEsterna> implements IDAOSottoscrizioneEsterna {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOSottoscrizioneEsternaHibernate() {
        super(SottoscrizioneEsterna.class);
    }

    public List<SottoscrizioneEsterna> removeFiltroPubblicatore(CategoriaEventiEsterna categoriaEventi, PubblicatoreEsterno pubblicatoreEsterno) throws DAOException {
        logger.debug("Richiesta eliminazione dei filtri pubblicatori che riguardano " + pubblicatoreEsterno);
        List<SottoscrizioneEsterna> listaSottoscrizioniEliminate = new ArrayList<SottoscrizioneEsterna>();
        IDAOFiltroPubblicatoreEsterno daoFiltroPubblicatoreEsterno = new DAOFiltroPubblicatoreEsternoHibernate();
        IDAOSottoscrizioneEsterna daoSottoscrizioneEsterno = new DAOSottoscrizioneEsternaHibernate();
        IDAOCategoriaEventiEsterna daoCategoriaEvnetiEsterna = new DAOCategoriaEventiEsternaHibernate();

        List<FiltroPubblicatoreEsterno> listaNuova = null;

        //Carico tutte le sottoscrizioni perchè la virifica va fatta su tutte le sottoscrizioni
        List<SottoscrizioneEsterna> listaSottoscrizioni = categoriaEventi.getListaSottoscrizioni();

        for (SottoscrizioneEsterna sottoscrizioneEsterna : listaSottoscrizioni) {
            listaNuova = new ArrayList<FiltroPubblicatoreEsterno>();

            boolean modificato = false; //verifico se la sottoscrizione è stata modificata
            List<FiltroPubblicatoreEsterno> listaFiltroPubblicatore = sottoscrizioneEsterna.getListaFiltroPublicatore();

            for (FiltroPubblicatoreEsterno filtroPubblicatore : listaFiltroPubblicatore) {
                if (filtroPubblicatore.getPubblicatore().compareTo(pubblicatoreEsterno) == 0) {
                    daoFiltroPubblicatoreEsterno.makeTransient(filtroPubblicatore);
                    modificato = true;
                } else {
                    filtroPubblicatore.setSottoscrizione(sottoscrizioneEsterna);
                    listaNuova.add(filtroPubblicatore);
                }
            }

            //Solo nel caso in cui la lista è stata modificata va salvata o eliminata
            //altrimrnti verebbero eliminate sempre le sottoscrizioni senza filtri anche se corrette
            if (modificato) {
                if (listaFiltroPubblicatore.size() > 1) {
                    sottoscrizioneEsterna.setListaFiltroPublicatore(listaNuova);
                    daoSottoscrizioneEsterno.makePersistent(sottoscrizioneEsterna);
                } else {
                    sottoscrizioneEsterna.setListaFiltroPublicatore(listaNuova);
                    daoSottoscrizioneEsterno.makeTransient(sottoscrizioneEsterna);
                    cancellaSottoscrittore(sottoscrizioneEsterna);
                    listaSottoscrizioniEliminate.add(sottoscrizioneEsterna);
                }
            }
        }

        for (SottoscrizioneEsterna sottoscrizioneEsterna : listaSottoscrizioniEliminate) {
            sottoscrizioneEsterna.getCategoriaEventi().getListaSottoscrizioni().remove(sottoscrizioneEsterna);
            daoCategoriaEvnetiEsterna.makePersistent(categoriaEventi);
        }

        return listaSottoscrizioniEliminate;
    }

    private void cancellaSottoscrittore(SottoscrizioneEsterna sottoscrizione) throws DAOException {
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
