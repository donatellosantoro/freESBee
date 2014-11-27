/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.aci;

import com.ws.aci.persistenza.DAOException;
import com.ws.aci.persistenza.DAOImmatricolazione;
import com.ws.aci.persistenza.DAOUtilHibernate;
import com.ws.aci.persistenza.IDAOImmatricolazione;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Danilo
 */
@WebService(serviceName = "AciWS")
@HandlerChain(file = "handler-chain.xml")
public class AciWS {

    private Log logger = LogFactory.getLog(this.getClass());
    private IDAOImmatricolazione daoImmatricolazione = new DAOImmatricolazione();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cercaImmatricolazione")
    public Immatricolazione cercaImmatricolazione(@WebParam(name = "targa") String targa) {
        Immatricolazione immatricolazione = null;
        try {
            DAOUtilHibernate.beginTransaction();
            immatricolazione = this.daoImmatricolazione.findByTarga(targa);
            DAOUtilHibernate.commit();
        } catch (DAOException ex) {
            this.logger.error("Errore durante la lettura nel DBMS" + ex);
        }
        return immatricolazione;
    }
}
