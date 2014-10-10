package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.Messaggio;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOMessaggio;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOMessaggioHibernate extends DAOGenericoHibernate<Messaggio> implements IDAOMessaggio {

    private Log logger = LogFactory.getLog(this.getClass());

    public DAOMessaggioHibernate() {
        super(Messaggio.class);
    }

    public Messaggio findResponseAsyncByIdMessageRequest(String idMessaggio) throws DAOException {
        List<Messaggio> lista = super.findByCriteria(Restrictions.eq("idRelatesTo", idMessaggio),Restrictions.eq("tipo", Messaggio.RICEVUTO));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }

    public List<Messaggio> findByData(String data) throws DAOException {
        List<Messaggio> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("data", data));
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return listaMessaggi;
    }

    public List<Messaggio> findByIDMessaggio(String idMessaggio) throws DAOException {
        List<Messaggio> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("idMessaggio", idMessaggio));
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return listaMessaggi;
    }

    public Messaggio findByIDMessaggio(String idMessaggio, String tipo) throws DAOException {

        List<Messaggio> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("idMessaggio", idMessaggio), Restrictions.eq("tipo", tipo));
            logger.info("TROVATI " + listaMessaggi.size() + " MESSAGGI");
            if (listaMessaggi.size() != 0) {
                Hibernate.initialize(listaMessaggi.get(0));
                return listaMessaggi.get(0);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

    public List<Messaggio> findByIDRelatesTo(String idRelatesTo) throws DAOException {
        List<Messaggio> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("idRelatesTo", idRelatesTo));
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return listaMessaggi;
    }

    public Messaggio findByIDRelatesTo(String idRelatesTo, String tipo) throws DAOException {
        List<Messaggio> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("idRelatesTo", idRelatesTo), Restrictions.eq("tipo", tipo));
        } catch (Exception e) {
            throw new DAOException(e);
        }
        if (listaMessaggi.isEmpty()) {
            return null;
        } else {
            return listaMessaggi.get(0);
        }
    }

    public List<Messaggio> findByIstanzaPortType(String istanzaPortType) throws DAOException {
        List<Messaggio> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("istanzaPortType", istanzaPortType));
            if (listaMessaggi.size() != 0) {
                Hibernate.initialize(listaMessaggi.get(0));
                return listaMessaggi;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

    public List<Messaggio> findByTipo(String tipo) throws DAOException {
        List<Messaggio> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("tipo", tipo));
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return listaMessaggi;
    }

    public List<Messaggio> findByTipoIstanza(String tipo, String istanzaPortType) throws DAOException {
        List<Messaggio> listaMessaggi = null;
        try {
            listaMessaggi = super.findByCriteria(Restrictions.eq("tipo", tipo), Restrictions.eq("istanzaPortType", istanzaPortType));
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return listaMessaggi;
    }

    @Override
    public Messaggio makePersistent(Messaggio entity) throws DAOException {
        logger.info("SALVO IL MESSAGGIO " + entity.getId());
        return super.makePersistent(entity);
    }
}
