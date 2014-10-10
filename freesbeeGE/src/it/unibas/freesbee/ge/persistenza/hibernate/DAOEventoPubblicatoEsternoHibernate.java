package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.EventoPubblicatoEsterno;
import it.unibas.freesbee.ge.modello.PubblicatoreEsterno;
import it.unibas.freesbee.ge.modello.StatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOEventoPubblicatoEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioEsterno;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

public class DAOEventoPubblicatoEsternoHibernate extends DAOGenericoHibernate<EventoPubblicatoEsterno> implements IDAOEventoPubblicatoEsterno {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOEventoPubblicatoEsternoHibernate() {
        super(EventoPubblicatoEsterno.class);
    }

    public void removeByPubblicatoreEsterno(PubblicatoreEsterno pubblicatoreEsterno) throws DAOException {
        logger.debug("Rimozione degli eventi esterni pubbicati da " + pubblicatoreEsterno);
        List<EventoPubblicatoEsterno> lista = super.findByCriteria(Restrictions.eq("pubblicatore", pubblicatoreEsterno));

        IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno = new DAOStatoMessaggioEsternoHibernate();
        for (EventoPubblicatoEsterno evento : lista) {
            daoStatoMessaggioEsterno.removeByEentoPubblicatoEsterno(evento);
            super.makeTransient(evento);
        }

    }

    public void removeById(Long id) throws DAOException {
        logger.debug("Rimozione dell'evento esterno " + id);
        EventoPubblicatoEsterno evento = super.findById(id, false);
        IDAOStatoMessaggioEsterno daoStatoMessaggioEsterno = new DAOStatoMessaggioEsternoHibernate();
        for (StatoMessaggioEsterno statoMessaggio : evento.getListaStatoMessaggio()) {
            daoStatoMessaggioEsterno.makeTransient(statoMessaggio);
        }
    }
}
