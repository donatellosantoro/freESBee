package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOSoggetto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

@Singleton
public class DAOSoggettoHibernate extends DAOGenericoHibernate<Soggetto> implements IDAOSoggetto {

//    private static Log logger = LogFactory.getLog(DAOSoggettoHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOSoggettoHibernate.class.getName());

    public DAOSoggettoHibernate() {
        super(Soggetto.class);
    }

    public Soggetto findByNome(String nome, String tipo) throws DAOException {
        List<Soggetto> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }

    @Override
    public List<Soggetto> findAll() throws DAOException {
        List<Soggetto> listaSoggetti = super.findAll();
        for (Soggetto soggetto : listaSoggetti) {
            Hibernate.initialize(soggetto);
            Hibernate.initialize(soggetto.getListaServiziErogatore());
        }
        return listaSoggetti;
    }

    public List<Soggetto> findAllFilter(Date maxDate, Date minDate, String nome, String tipo) throws DAOException {
        if (maxDate == null || minDate == null) {
            if (nome != null && tipo!=null) {
                List<Soggetto> soggetti = new ArrayList<Soggetto>();
                Soggetto soggetto = this.findByNome(nome,tipo);
                if (soggetto != null) {
                    soggetti.add(soggetto);
                }
                return soggetti;
            } else {
                return super.findAll();
            }
        }
        if (nome != null && tipo!=null) {
            return super.findByCriteria(Restrictions.between("oraRegistrazione", minDate, maxDate),Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo));
        } else {
            return super.findByCriteria(Restrictions.between("oraRegistrazione", minDate, maxDate));
        }
    }
}
