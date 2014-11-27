/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.comune;

import com.ws.comune.persistenza.DAOCittadino;
import com.ws.comune.persistenza.DAOException;
import com.ws.comune.persistenza.DAOUtilHibernate;
import com.ws.comune.persistenza.IDAOCittadino;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebService(serviceName="ComuneWS")
public class ComuneWS
{
  private IDAOCittadino dAOCittadino = new DAOCittadino();
  private Log logger = LogFactory.getLog(getClass());

  @WebMethod(operationName="cercaCittadino")
  public Cittadino cercaCittadino(@WebParam(name="codiceFiscale") String codiceFiscale) {
    Cittadino cittadino = null;
    try {
      DAOUtilHibernate.beginTransaction();
      cittadino = this.dAOCittadino.findByCodiceFiscale(codiceFiscale);
      DAOUtilHibernate.commit();
    }
    catch (DAOException ex)
    {
      this.logger.error("Errore durante la lettura nel DBMS" + ex);
    }
    return cittadino;
  }
    
}
