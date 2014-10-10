package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.modello.FiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOFiltroPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneEsterna;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreEsternoFacade;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

public class DAOFiltroPubblicatoreEsternoHibernate extends DAOGenericoHibernate<FiltroPubblicatoreEsterno> implements IDAOFiltroPubblicatoreEsterno {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOFiltroPubblicatoreEsternoHibernate() {
        super(FiltroPubblicatoreEsterno.class);
    }

    public boolean existsFiltroPubblicatoreEsterno(FiltroPubblicatoreEsterno filtroPubblicatoreEsterno) throws DAOException {
        logger.debug("Verifica esistenza filtro pubblicatore esterno relativo al pubblicatore " + filtroPubblicatoreEsterno.getPubblicatore());
        List<FiltroPubblicatoreEsterno> listaFiltri = super.findByCriteria(Restrictions.eq("sottoscrizione", filtroPubblicatoreEsterno.getSottoscrizione()), Restrictions.eq("pubblicatore", filtroPubblicatoreEsterno.getPubblicatore()));
        if (listaFiltri.size() > 0) {
            return true;
        }
        return false;
    }

    public void removeFiltriPubblicatoreBySottoscrizioneEsterna(SottoscrizioneEsterna sottoscrizioneEsterna) throws DAOException {
        logger.debug("Rimozione filtri pubblicatore esterni relativi alla sottoscrizione del sottoscrittore " + sottoscrizioneEsterna.getSottoscrittore());
        IDAOFiltroPubblicatoreEsterno daoFiltroPubblicatoreEsterno = new DAOFiltroPubblicatoreEsternoHibernate();
        IDAOSottoscrizioneEsterna daoSottoscrizioneEsterna = new DAOSottoscrizioneEsternaHibernate();
        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();

        List<FiltroPubblicatoreEsterno> listaFiltroPubblicatore = sottoscrizioneEsterna.getListaFiltroPublicatore();
        for (FiltroPubblicatoreEsterno filtroPubblicatore : listaFiltroPubblicatore) {
            //elimino il filtro pubblicatore
            daoFiltroPubblicatoreEsterno.makeTransient(filtroPubblicatore);
            //verifico se il soggetto pubblicatore è da eliminare in quanto non appartenente più a nessun filtro
            if (!verificaPubblicatore(filtroPubblicatore.getPubblicatore())) {
                daoPubblicatoreEsterno.deletePubblicatoreEsterno(filtroPubblicatore.getPubblicatore());
            }
//            //verifico se pubblicatore non è presente più in alcun filtro pubblicatore per la categira eventi
//            //in tal caso perde il diritto di essere pubblicatore
//            daoCategoriaEventiEsterna.removePubblicatoreEsternoPerCategoriaEventi(sottoscrizioneEsterna.getCategoriaEventi(), filtroPubblicatore.getPubblicatore());

        }
        List<FiltroPubblicatoreEsterno> listaNuova = new ArrayList<FiltroPubblicatoreEsterno>();
        sottoscrizioneEsterna.setListaFiltroPublicatore(listaNuova);
        daoSottoscrizioneEsterna.makePersistent(sottoscrizioneEsterna);

    }

    private boolean verificaPubblicatore(PubblicatoreEsterno pubblicatore) throws DAOException {
        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        List<CategoriaEventiEsterna> listaCateogriaEventiEsterna = daoCategoriaEventiEsterna.findAll();
        boolean flag = false;

        for (CategoriaEventiEsterna cat : listaCateogriaEventiEsterna) {
            if (DAOPubblicatoreEsternoFacade.isPubblicatoreEsternoPerCategoriaEventiEsterna(cat, pubblicatore)) {
                flag = true;
            }
        }
        return flag;
    }
}
