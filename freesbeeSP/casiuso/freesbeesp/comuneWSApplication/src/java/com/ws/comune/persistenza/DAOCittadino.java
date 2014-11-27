/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.comune.persistenza;

import com.ws.comune.Cittadino;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Danilo
 */
public class DAOCittadino extends DAOGenericoHibernate<Cittadino> implements IDAOCittadino{

    public DAOCittadino() {
        super(Cittadino.class);
    }

    public Cittadino findByCodiceFiscale(String codiceFiscale) throws DAOException {
        List<Cittadino> lista=super.findByCriteria(Restrictions.eq("codiceFiscale",codiceFiscale));
        if(lista.size()!=0){
            return lista.get(0);
        }
        else{
            return null;
        }
    }
    
    
    
}
