package it.unibas.silvio.persistenza.hibernate;

import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDAOOperation;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;

public class DAOOperationHibernate extends DAOGenericoHibernate<Operation> implements IDAOOperation{
    
    public DAOOperationHibernate(){
        super(Operation.class);
    }

    public Operation findByNameProfiloTipo(String name, String profilo, String tipo) throws DAOException {
        List<Operation> lista = super.findByCriteria(Restrictions.eq("nome", name), Restrictions.eq("profiloDiCollaborazione", profilo), Restrictions.eq("tipo", tipo));
        if (lista.size() != 0) {
            Hibernate.initialize(lista.get(0));
            return lista.get(0);
        }
        return null;
    }
    
    @Override
    public List<Operation> findAll() throws DAOException {
        List<Operation> listaOperation = super.findAll();
        for(Operation operation : listaOperation){
            Hibernate.initialize(operation);
            Hibernate.initialize(operation.getListaIstanzaOperation());
        }
        return listaOperation;
    }

}
