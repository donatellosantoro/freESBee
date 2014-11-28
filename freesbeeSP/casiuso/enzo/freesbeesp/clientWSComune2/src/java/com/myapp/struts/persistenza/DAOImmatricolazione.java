
package com.myapp.struts.persistenza;

import com.myapp.struts.modello.Immatricolazione;
import java.util.List;
import org.hibernate.criterion.Restrictions;


public class DAOImmatricolazione extends DAOGenericoHibernate<Immatricolazione> implements IDAOImmatricolazione{

    public DAOImmatricolazione() {
        super(Immatricolazione.class);
    }

    public Immatricolazione findByTarga(String targa) throws DAOException {
         List<Immatricolazione> lista=super.findByCriteria(Restrictions.eq("targa",targa));
        if(lista.size()!=0){
            return lista.get(0);
        }
        else{
            return null;
        }
    }
    
    
}
