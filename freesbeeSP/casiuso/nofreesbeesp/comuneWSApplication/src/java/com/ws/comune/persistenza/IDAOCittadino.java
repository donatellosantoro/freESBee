/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.comune.persistenza;

import com.ws.comune.Cittadino;


public interface IDAOCittadino extends IDAOGenerico<Cittadino> {
    
    public Cittadino findByCodiceFiscale(String codiceFiscale) throws DAOException;
}
