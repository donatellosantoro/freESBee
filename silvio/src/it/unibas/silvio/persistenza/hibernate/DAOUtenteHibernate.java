package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.Utente;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOUtente;
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

    public Utente findByNomeUtente(String nome) throws DAOException {
        List<Utente> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
}
