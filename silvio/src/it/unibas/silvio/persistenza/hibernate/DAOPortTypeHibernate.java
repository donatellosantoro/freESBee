package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.PortType;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOPortType;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOPortTypeHibernate extends DAOGenericoHibernate<PortType> implements IDAOPortType{
    
    public DAOPortTypeHibernate(){
        super(PortType.class);
    }

    public PortType findByName(String name) throws DAOException {
        List<PortType> lista = super.findByCriteria(Restrictions.eq("nome", name));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }

    public PortType findByNameTipo(String name, String tipo) throws DAOException {
        List<PortType> lista = super.findByCriteria(Restrictions.eq("nome", name), Restrictions.eq("tipo", tipo));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
    
    @Override
    public List<PortType> findAll() throws DAOException{
        List<PortType> listaPortType = super.findAll();
        for(PortType portType : listaPortType){
            Hibernate.initialize(portType);
            Hibernate.initialize(portType.getListaOperation());
            Hibernate.initialize(portType.getListaIstanzaPortType());
        }
        return listaPortType;
    }

}
