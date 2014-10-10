package it.unibas.freesbee.ge.persistenza.hibernate;

import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.DAOException;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOPubblicatoreInternoHibernate extends DAOGenericoHibernate<PubblicatoreInterno> implements IDAOPubblicatoreInterno {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOPubblicatoreInternoHibernate() {
        super(PubblicatoreInterno.class);
    }

    public PubblicatoreInterno findByTipoNome(String tipo, String nome) throws DAOException {
        logger.debug("Richiesto il pubblicatore interno " + tipo + " " + nome);
        List<PubblicatoreInterno> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
}
