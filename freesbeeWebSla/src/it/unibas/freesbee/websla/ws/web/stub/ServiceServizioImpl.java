
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package it.unibas.freesbee.websla.ws.web.stub;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2013-12-12T23:04:16.123+01:00
 * Generated source version: 2.7.7
 * 
 */

@javax.jws.WebService(
                      serviceName = "ServiceServizio",
                      portName = "ServiceServizio",
                      targetNamespace = "http://web.ws.freesbeesla.unibas.it/",
                      wsdlLocation = "http://localhost:28080/freesbee-Sla/ws/servizio/ServiceServizio?wsdl",
                      endpointInterface = "it.unibas.freesbeesla.ws.web.ServiceServizio")
                      
public class ServiceServizioImpl implements ServiceServizio {

    private static final Logger LOG = Logger.getLogger(ServiceServizioImpl.class.getName());

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceServizio#getServiziInf2Fruiti(*
     */
    public java.util.List<Servizio> getServiziInf2Fruiti() throws SOAPFault_Exception    { 
        LOG.info("Executing operation getServiziInf2Fruiti");
        try {
            java.util.List<Servizio> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceServizio#updateServizioActive(Servizio  servizio )*
     */
    public void updateServizioActive(Servizio servizio) throws SOAPFault_Exception    { 
        LOG.info("Executing operation updateServizioActive");
        System.out.println(servizio);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceServizio#getServiziInf2Erogati(*
     */
    public java.util.List<Servizio> getServiziInf2Erogati() throws SOAPFault_Exception    { 
        LOG.info("Executing operation getServiziInf2Erogati");
        try {
            java.util.List<Servizio> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceServizio#getServiziNicaNonMonitorati(*
     */
    public java.util.List<Servizio> getServiziNicaNonMonitorati() throws SOAPFault_Exception    { 
        LOG.info("Executing operation getServiziNicaNonMonitorati");
        try {
            java.util.List<Servizio> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceServizio#addServizio(Servizio  servizio ,)byte[]  arg1 )*
     */
    public void addServizio(Servizio servizio,byte[] arg1) throws SOAPFault_Exception    { 
        LOG.info("Executing operation addServizio");
        System.out.println(servizio);
        System.out.println(arg1);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceServizio#getParametriSla(Servizio  servizio )*
     */
    public java.util.List<java.lang.String> getParametriSla(Servizio servizio) throws SOAPFault_Exception    { 
        LOG.info("Executing operation getParametriSla");
        System.out.println(servizio);
        try {
            java.util.List<java.lang.String> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

}