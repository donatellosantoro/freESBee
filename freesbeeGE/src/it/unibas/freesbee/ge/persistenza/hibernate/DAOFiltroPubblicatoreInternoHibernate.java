package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.FiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOFiltroPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrizioneInterna;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

public class DAOFiltroPubblicatoreInternoHibernate extends DAOGenericoHibernate<FiltroPubblicatoreInterno> implements IDAOFiltroPubblicatoreInterno {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOFiltroPubblicatoreInternoHibernate() {
        super(FiltroPubblicatoreInterno.class);
    }

    public boolean existsFiltroPubblicatoreInterno(FiltroPubblicatoreInterno filtroPubblicatoreInterno) throws DAOException {
        logger.debug("Verifica esistenza filtro pubblicatore interno relativo al pubblicatore " + filtroPubblicatoreInterno.getPubblicatore());
        List<FiltroPubblicatoreInterno> listaFiltri = super.findByCriteria(Restrictions.eq("sottoscrizione", filtroPubblicatoreInterno.getSottoscrizione()), Restrictions.eq("pubblicatore", filtroPubblicatoreInterno.getPubblicatore()));
        if (listaFiltri.size() > 0) {
            return true;
        }
        return false;
    }

    public void removeFiltriPubblicatoreBySottoscrizioneInterna(SottoscrizioneInterna sottoscrizioneInterna) throws DAOException {
        logger.debug("Rimozione filtri pubblicatore interni relativi alla sottoscrizione del sottoscrittore " + sottoscrizioneInterna.getSottoscrittore());
        IDAOFiltroPubblicatoreInterno daoFiltroPubblicatoreInterno = new DAOFiltroPubblicatoreInternoHibernate();
        IDAOSottoscrizioneInterna daoSottoscrizioneInterna = new DAOSottoscrizioneInternaHibernate();
        List<FiltroPubblicatoreInterno> listaFiltroPubblicatore = sottoscrizioneInterna.getListaFiltroPublicatore();
        for (FiltroPubblicatoreInterno filtroPubblicatore : listaFiltroPubblicatore) {
            daoFiltroPubblicatoreInterno.makeTransient(filtroPubblicatore);
        }
        List<FiltroPubblicatoreInterno> listaNuova = new ArrayList<FiltroPubblicatoreInterno>();
        sottoscrizioneInterna.setListaFiltroPublicatore(listaNuova);
        daoSottoscrizioneInterna.makePersistent(sottoscrizioneInterna);
    }
}
