package it.unibas.icar.freesbee.persistenza.hibernate;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOPortaApplicativa;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

@Singleton
public class DAOPortaApplicativaHibernate extends DAOGenericoHibernate<PortaApplicativa> implements IDAOPortaApplicativa {

//    private static Log logger = LogFactory.getLog(DAOPortaApplicativaHibernate.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOPortaApplicativaHibernate.class.getName());

    public DAOPortaApplicativaHibernate() {
        super(PortaApplicativa.class);
    }

    public PortaApplicativa findByNome(String nome) throws DAOException {
        List<PortaApplicativa> lista = super.findByCriteria(Restrictions.eq("nome", nome));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public PortaApplicativa findByServizio(Servizio servizio, Azione azione) throws DAOException {
        List<PortaApplicativa> lista;
        if (azione == null) {
            lista = super.findByCriteria(Restrictions.eq("servizio.id", servizio.getId()));
        } else {
            lista = super.findByCriteria(Restrictions.eq("servizio.id", servizio.getId()),Restrictions.eq("azione.id", azione.getId()));
        }

        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public List<PortaApplicativa> findByServizioApplicativo(ServizioApplicativo servizioApplicativo) throws DAOException {
        return super.findByCriteria(Restrictions.eq("servizioApplicativo", servizioApplicativo));
    }

    public PortaApplicativa findByNomeServizio(String nomeServizio, String tipoServizio, String nomeErogatore, String tipoErogatore, String azione) throws DAOException {
        List<PortaApplicativa> listaPA;
        if (azione == null) {
            listaPA = super.findByCriteria(Restrictions.eq("stringaServizio", nomeServizio), Restrictions.eq("stringaTipoServizio", tipoServizio),
                    Restrictions.eq("stringaErogatore", nomeErogatore), Restrictions.eq("stringaTipoErogatore", tipoErogatore));
        } else {
            listaPA = super.findByCriteria(Restrictions.eq("stringaServizio", nomeServizio), Restrictions.eq("stringaTipoServizio", tipoServizio),
                    Restrictions.eq("stringaErogatore", nomeErogatore), Restrictions.eq("stringaTipoErogatore", tipoErogatore), Restrictions.eq("stringaAzione", azione));
        }
        if (!listaPA.isEmpty()) {
            return listaPA.get(0);
        }
        return null;
    }
}
