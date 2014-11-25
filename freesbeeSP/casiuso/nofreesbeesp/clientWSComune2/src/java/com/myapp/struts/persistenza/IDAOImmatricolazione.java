
package com.myapp.struts.persistenza;

import com.myapp.struts.modello.Immatricolazione;


public interface IDAOImmatricolazione extends IDAOGenerico<Immatricolazione> {
    
    public Immatricolazione findByTarga(String targa)throws DAOException;
}
