package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOGestoreEventi;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOGestoreEventiHibernate extends DAOGenericoHibernate<GestoreEventi> implements IDAOGestoreEventi {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOGestoreEventiHibernate() {
        super(GestoreEventi.class);
    }

    public GestoreEventi findByTipoNome(String tipo, String nome) throws DAOException {
        logger.debug("Richiesto il gestore eventi " + tipo + " " + nome);
        List<GestoreEventi> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
}
