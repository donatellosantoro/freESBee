package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOUtente;
import it.unibas.freesbee.ge.modello.Utente;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOUtenteHibernate extends DAOGenericoHibernate<Utente> implements IDAOUtente {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOUtenteHibernate() {
        super(Utente.class);
    }

    public Utente findByNomeUtente(String nomeUtente) throws DAOException {
        logger.debug("Richiesto utente " + nomeUtente);
        List<Utente> lista = super.findByCriteria(Restrictions.eq("nomeUtente", nomeUtente));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
}
