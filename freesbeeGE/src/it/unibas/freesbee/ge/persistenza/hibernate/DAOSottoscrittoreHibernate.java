package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOSottoscrittore;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOSottoscrittoreHibernate extends DAOGenericoHibernate<Sottoscrittore> implements IDAOSottoscrittore {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOSottoscrittoreHibernate() {
        super(Sottoscrittore.class);
    }

    public Sottoscrittore findByTipoNome(String tipo, String nome) throws DAOException {
        logger.debug("Richiesto il sottoscrittore " + tipo + " " + nome);
        List<Sottoscrittore> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo));
        if (lista.size() != 0) {
            Sottoscrittore s = lista.get(0);
            Hibernate.initialize(s);
            return s;
        }
        return null;
    }
}
