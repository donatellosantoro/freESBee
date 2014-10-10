package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOIstanzaPortType;
import java.util.List;
import org.hibernate.criterion.Restrictions;

public class DAOIstanzaPortTypeHibernate extends DAOGenericoHibernate<IstanzaPortType> implements IDAOIstanzaPortType{
    
    public DAOIstanzaPortTypeHibernate(){
        super(IstanzaPortType.class);
    }

    public IstanzaPortType findById(long id) throws DAOException {
        List<IstanzaPortType> lista = super.findByCriteria(Restrictions.eq("id", id));
        if (lista.size() != 0) {
            return lista.get(0);
        }
        return null;
    }

}
