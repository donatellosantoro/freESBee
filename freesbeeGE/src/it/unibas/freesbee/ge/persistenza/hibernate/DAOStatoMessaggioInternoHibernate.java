package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.EventoPubblicatoInterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.StatoMessaggioInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioInterno;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOStatoMessaggioInternoHibernate extends DAOGenericoHibernate<StatoMessaggioInterno> implements IDAOStatoMessaggioInterno {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOStatoMessaggioInternoHibernate() {
        super(StatoMessaggioInterno.class);
    }

    public void removeByEentoPubblicatoInterno(EventoPubblicatoInterno eventoPubblicatoInterno) throws DAOException {
        logger.debug("Rimozione degli stato messaggio interni relativi all'evento pubblicato dal pubblicatore" + eventoPubblicatoInterno.getPubblicatore());
        List<StatoMessaggioInterno> lista = super.findByCriteria(Restrictions.eq("eventoPubblicato", eventoPubblicatoInterno));
        for (StatoMessaggioInterno s : lista) {
            super.makeTransient(s);
        }
    }

    public void removeBySottoscrittore(Sottoscrittore sottoscrittore) throws DAOException {
        logger.debug("Rimozione degli stato messaggio interni relativi alla sottoscrizione del sottoscrittore " + sottoscrittore);
        List<StatoMessaggioInterno> lista = super.findByCriteria(Restrictions.eq("sottoscrittore", sottoscrittore));
        Hibernate.initialize(lista);
        for (StatoMessaggioInterno s : lista) {
            super.makeTransient(s);
        }
    }

//    public StatoMessaggioInterno findById(long id) throws DAOException {
//        List<StatoMessaggioInterno> lista = super.findByCriteria(Restrictions.eq("id", id));
//        if (lista.size() > 0) {
//            return lista.get(0);
//        }
//        return null;
//
//    }
}
