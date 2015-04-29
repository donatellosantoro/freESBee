package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOServizio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

@Singleton
public class DAOServizioHibernate extends DAOGenericoHibernate<Servizio> implements IDAOServizio {

//    private static Log logger = LogFactory.getLog(DAOServizioHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOServizioHibernate.class.getName());

    public DAOServizioHibernate() {
        super(Servizio.class);
    }

    public Servizio findByNome(String nome, String tipo, long erogatoreId) throws DAOException {
        List<Servizio> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo));
            Hibernate.initialize(lista);
        for (Servizio s : lista) {
            Hibernate.initialize(s);
            if (s.getErogatore().getId() == erogatoreId) {
                return s;
            }
        }
        return null;
    }

    public List<Servizio> findByNome(String nome, String tipo) throws DAOException {
        return super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo));
    }

    public Servizio findByNome(String nome, String tipo, Soggetto erogatore) throws DAOException {
        List<Servizio> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo), Restrictions.eq("erogatore", erogatore));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public Servizio findByNome(String nome, String tipo, Soggetto erogatore, boolean correlato) throws DAOException {
        List<Servizio> lista = super.findByCriteria(Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo), Restrictions.eq("erogatore", erogatore), Restrictions.eq("correlato", correlato));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public Servizio findCorrelato(AccordoServizio accordo, Soggetto fruitore) throws DAOException {
        List<Servizio> lista = super.findByCriteria(Restrictions.eq("accordoServizio", accordo), Restrictions.eq("erogatore", fruitore), Restrictions.eq("correlato", true));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public Servizio findByAccordoErogatore(AccordoServizio accordoServizio, Soggetto erogatore) throws DAOException {
        List<Servizio> lista = super.findByCriteria(Restrictions.eq("accordoServizio", accordoServizio), Restrictions.eq("erogatore", erogatore));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public List<Servizio> findAllCorrelati() throws DAOException {
        return super.findByCriteria(Restrictions.eq("correlato", true));
    }

    public List<Servizio> findAllFilter(Date maxDate, Date minDate, String nome, String tipo, Soggetto erogatore) throws DAOException {
        if (maxDate == null || minDate == null) {
            if (nome != null && tipo != null && erogatore != null) {
                List<Servizio> servizi = new ArrayList<Servizio>();
                Servizio servizio = this.findByNome(nome, tipo, erogatore, false);
                if (servizio != null) {
                    servizi.add(servizio);
                }
                return servizi;
            } else {
                return this.findAllServizi();
            }
        }
        if (nome != null && tipo != null && erogatore != null) {
            return super.findByCriteria(Restrictions.between("oraRegistrazione", minDate, maxDate), Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo), Restrictions.eq("erogatore", erogatore), Restrictions.eq("correlato", false));
        } else {
            return super.findByCriteria(Restrictions.between("oraRegistrazione", minDate, maxDate), Restrictions.eq("correlato", false));
        }
    }

    public List<Servizio> findAllCorrelatiFilter(Date maxDate, Date minDate, String nome, String tipo, Soggetto erogatore) throws DAOException {
        if (maxDate == null || minDate == null) {
            if (nome != null && tipo != null && erogatore != null) {
                List<Servizio> servizi = new ArrayList<Servizio>();
                Servizio servizio = this.findByNome(nome, tipo, erogatore, true);
                if (servizio != null) {
                    servizi.add(servizio);
                }
                return servizi;
            } else {
                return this.findAllCorrelati();
            }
        }
        if (nome != null && tipo != null && erogatore != null) {
            return super.findByCriteria(Restrictions.between("oraRegistrazione", minDate, maxDate), Restrictions.eq("nome", nome), Restrictions.eq("tipo", tipo), Restrictions.eq("erogatore", erogatore), Restrictions.eq("correlato", true));
        } else {
            return super.findByCriteria(Restrictions.between("oraRegistrazione", minDate, maxDate), Restrictions.eq("correlato", true));
        }
    }

    public List<Servizio> findAllServizi() throws DAOException {
        return super.findByCriteria(Restrictions.eq("correlato", false));
    }
}
    
