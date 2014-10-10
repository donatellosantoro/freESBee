package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOIstanzaOperation;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOIstanzaOperationHibernate extends DAOGenericoHibernate<IstanzaOperation> implements IDAOIstanzaOperation{
    
    public DAOIstanzaOperationHibernate(){
        super(IstanzaOperation.class);
    }

    public IstanzaOperation findByTipo(String tipo) throws DAOException {
        List<IstanzaOperation> lista = super.findByCriteria(Restrictions.eq("tipo", tipo));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
    
    @Override
    public List<IstanzaOperation> findAll() throws DAOException {
        List<IstanzaOperation> listaIstanzaOperation = super.findAll();
        for(IstanzaOperation operation : listaIstanzaOperation){
            Hibernate.initialize(operation);                  
        }
        return listaIstanzaOperation;
    }
    

}
