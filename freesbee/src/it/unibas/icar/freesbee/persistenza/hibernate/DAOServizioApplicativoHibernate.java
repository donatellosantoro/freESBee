package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOServizioApplicativo;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

@Singleton
public class DAOServizioApplicativoHibernate extends DAOGenericoHibernate<ServizioApplicativo> implements IDAOServizioApplicativo {

    private static Log logger = LogFactory.getLog(DAOServizioApplicativoHibernate.class);

    public DAOServizioApplicativoHibernate() {
        super(ServizioApplicativo.class);
    }

    public ServizioApplicativo findByNome(String nome, String connettore) throws DAOException {
        List<ServizioApplicativo> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("connettore", connettore));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public ServizioApplicativo findByNome(String nome, String connettore, String descrizione) throws DAOException {
        List<ServizioApplicativo> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("connettore", connettore), Restrictions.eq("descrizione", descrizione));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public List<ServizioApplicativo> findAllNoLazy() throws DAOException {
        List<ServizioApplicativo> lista = this.findAll();
        for (ServizioApplicativo servizioApplicativo : lista) {
            Hibernate.initialize(servizioApplicativo);
        }
        return lista;
    }

    public ServizioApplicativo findByNome(String nome) throws DAOException {
        List<ServizioApplicativo> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }
}
