
package com.ws.aci.persistenza;

import com.ws.aci.Immatricolazione;


public interface IDAOImmatricolazione extends IDAOGenerico<Immatricolazione> {
    
    public Immatricolazione findByTarga(String targa)throws DAOException;
}
