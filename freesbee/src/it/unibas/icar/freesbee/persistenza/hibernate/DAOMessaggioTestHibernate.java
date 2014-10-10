package it.unibas.icar.freesbee.persistenza.hibernate;

import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.IDAOMessaggio;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

public class DAOMessaggioTestHibernate extends DAOGenericoTestHibernate<Messaggio> implements IDAOMessaggio {

    private static Log logger = LogFactory.getLog(DAOMessaggioTestHibernate.class);

    public DAOMessaggioTestHibernate() {
        super(Messaggio.class);
    }    
    
    public Messaggio findByIDSil(String idSil, String tipo) throws DAOException {        
        List<Messaggio> lista;
        if(tipo==null){
            lista = super.findByCriteria(Restrictions.eq("idSil", idSil));
        }else{
            lista = super.findByCriteria(Restrictions.eq("idSil", idSil), Restrictions.eq("tipo", tipo));
        }
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }    

    public Messaggio findByIDSilRelatesTo(String idSilRelatesTo, String tipo) throws DAOException {
        List<Messaggio> lista;
        if(tipo==null){
            lista = super.findByCriteria(Restrictions.eq("silRelatesTo", idSilRelatesTo));
        }else{
            lista = super.findByCriteria(Restrictions.eq("silRelatesTo", idSilRelatesTo), Restrictions.eq("tipo", tipo));
        }
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

    public Messaggio findByIdMessaggio(String idMessaggio, String tipo) throws DAOException { 
        List<Messaggio> lista;
        if(tipo==null){
            lista = super.findByCriteria(Restrictions.eq("idMessaggio", idMessaggio));
        }else{
            lista = super.findByCriteria(Restrictions.eq("idMessaggio", idMessaggio), Restrictions.eq("tipo", tipo));
        }
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }
}
