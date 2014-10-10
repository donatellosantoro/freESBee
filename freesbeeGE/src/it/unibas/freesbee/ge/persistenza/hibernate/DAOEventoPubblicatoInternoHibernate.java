package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.EventoPubblicatoInterno;
import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoInterno;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioInterno;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

public class DAOEventoPubblicatoInternoHibernate extends DAOGenericoHibernate<EventoPubblicatoInterno> implements IDAOEventoPubblicatoInterno {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOEventoPubblicatoInternoHibernate() {
        super(EventoPubblicatoInterno.class);
    }

    public void removeByPubblicatoreInterno(PubblicatoreInterno pubblicatoreInterno) throws DAOException {
        logger.debug("Rimozione degli eventi interni pubbicati da " + pubblicatoreInterno);
        List<EventoPubblicatoInterno> lista = super.findByCriteria(Restrictions.eq("pubblicatore", pubblicatoreInterno));

        IDAOStatoMessaggioInterno daoStatoMessaggioInterno = new DAOStatoMessaggioInternoHibernate();
        for (EventoPubblicatoInterno evento : lista) {
            daoStatoMessaggioInterno.removeByEentoPubblicatoInterno(evento);
            super.makeTransient(evento);
        }
    }

    public void findByCategoriaEventi(PubblicatoreInterno pubblicatoreInterno) throws DAOException {
        logger.debug("Rimozione degli eventi interni pubbicati da " + pubblicatoreInterno);
        List<EventoPubblicatoInterno> lista = super.findByCriteria(Restrictions.eq("pubblicatore", pubblicatoreInterno));

        IDAOStatoMessaggioInterno daoStatoMessaggioInterno = new DAOStatoMessaggioInternoHibernate();
        for (EventoPubblicatoInterno evento : lista) {
            daoStatoMessaggioInterno.removeByEentoPubblicatoInterno(evento);
            super.makeTransient(evento);
        }
    }
}
