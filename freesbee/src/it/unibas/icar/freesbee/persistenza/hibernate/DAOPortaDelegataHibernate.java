package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOPortaDelegata;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@Singleton
public class DAOPortaDelegataHibernate extends DAOGenericoHibernate<PortaDelegata> implements IDAOPortaDelegata {

    private static Log logger = LogFactory.getLog(DAOPortaDelegataHibernate.class);

    public DAOPortaDelegataHibernate() {
        super(PortaDelegata.class);
    }

    public PortaDelegata findByNome(String nome) throws DAOException {
        List<PortaDelegata> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (!lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }

    public List<PortaDelegata> findBySoggetto(Soggetto soggetto) throws DAOException {
        Criterion orRestr = Restrictions.or(Restrictions.eq("soggettoErogatore", soggetto), Restrictions.eq("soggettoFruitore", soggetto));
        List<PortaDelegata> lista = super.findByCriteria(orRestr);
        return lista;
    }
}
