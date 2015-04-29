package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Utente;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOUtente;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

@Singleton
public class DAOUtenteHibernate extends DAOGenericoHibernate<Utente> implements IDAOUtente {

//    private static Log logger = LogFactory.getLog(DAOUtenteHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOUtenteHibernate.class.getName());

    public DAOUtenteHibernate() {
        super(Utente.class);
    }

    public Utente findByNomeUtente(String nomeUtente) throws DAOException {
        List<Utente> lista = super.findByCriteria(Restrictions.eq("nomeUtente", nomeUtente));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
}
