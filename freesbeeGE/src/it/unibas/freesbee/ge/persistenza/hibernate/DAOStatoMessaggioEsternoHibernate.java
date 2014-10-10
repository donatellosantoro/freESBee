package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.EventoPubblicatoEsterno;
import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.StatoMessaggioEsterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOStatoMessaggioEsterno;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOStatoMessaggioEsternoHibernate extends DAOGenericoHibernate<StatoMessaggioEsterno> implements IDAOStatoMessaggioEsterno {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOStatoMessaggioEsternoHibernate() {
        super(StatoMessaggioEsterno.class);
    }

    public void removeByEentoPubblicatoEsterno(EventoPubblicatoEsterno eventoPubblicatoEsterno) throws DAOException {
        logger.debug("Rimozione degli stato messaggio esterni relativi all'evento pubblicato dal pubblicatore" + eventoPubblicatoEsterno.getPubblicatore());
        List<StatoMessaggioEsterno> lista = super.findByCriteria(Restrictions.eq("eventoPubblicato", eventoPubblicatoEsterno));
        for (StatoMessaggioEsterno s : lista) {
            super.makeTransient(s);
        }
    }

    public void removeBySottoscrittore(Sottoscrittore sottoscrittore) throws DAOException {
        logger.debug("Rimozione degli stato messaggio esterni relativi alla sottoscrizione del sottoscrittore " + sottoscrittore);
        List<StatoMessaggioEsterno> lista = super.findByCriteria(Restrictions.eq("sottoscrittore", sottoscrittore));
        Hibernate.initialize(lista);
        for (StatoMessaggioEsterno s : lista) {
            super.makeTransient(s);
        }
    }
}
