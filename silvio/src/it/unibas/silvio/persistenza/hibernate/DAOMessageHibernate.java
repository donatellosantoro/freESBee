package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.Message;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOMessage;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOMessageHibernate extends DAOGenericoHibernate<Message> implements IDAOMessage{
    
    public DAOMessageHibernate(){
        super(Message.class);
    }

    public Message findByNameType(String name, String type) throws DAOException {
        List<Message> lista = super.findByCriteria(Restrictions.eq("nome", name), Restrictions.eq("types", type));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }        

}
