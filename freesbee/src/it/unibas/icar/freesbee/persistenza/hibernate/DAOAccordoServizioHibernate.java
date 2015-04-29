package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.DBManager;
import it.unibas.icar.freesbee.persistenza.IDAOAccordoServizio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

@Singleton
public class DAOAccordoServizioHibernate extends DAOGenericoHibernate<AccordoServizio> implements IDAOAccordoServizio {

//    private static Log logger = LogFactory.getLog(DAOAccordoServizioHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOAccordoServizioHibernate.class.getName());

    public DAOAccordoServizioHibernate() {
        super(AccordoServizio.class);
    }

    public AccordoServizio findByNome(String nome) throws DAOException {
        List<AccordoServizio> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public List<AccordoServizio> findAllFilter(Date maxDate, Date minDate, String nome) throws DAOException {
        if (maxDate == null || minDate == null) {
            if (nome != null) {
                List<AccordoServizio> accordi = new ArrayList<AccordoServizio>();
                AccordoServizio accordo = this.findByNome(nome);
                if (accordo != null) {
                    accordi.add(accordo);
                }
                return accordi;
            } else {
                return super.findAll();
            }
        }


        if (nome != null) {
            return super.findByCriteria(Restrictions.between("oraRegistrazione", minDate, maxDate),Restrictions.eq("nome", nome));
        } else {
            return super.findByCriteria(Restrictions.between("oraRegistrazione", minDate, maxDate));
        }
    }
}
