
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
 * 2013-12-12T22:13:17.675+01:00
 * Generated source version: 2.7.7
 * 
 */

@javax.jws.WebService(
                      serviceName = "ServiceSoggetto",
                      portName = "ServiceSoggetto",
                      targetNamespace = "http://web.ws.freesbeesla.unibas.it/",
                      wsdlLocation = "http://localhost:28080/freesbee-Sla/ws/soggetto/ServiceSoggetto?wsdl",
                      endpointInterface = "it.unibas.freesbeesla.ws.web.ServiceSoggetto")
                      
public class ServiceSoggettoImpl implements ServiceSoggetto {

    private static final Logger LOG = Logger.getLogger(ServiceSoggettoImpl.class.getName());

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceSoggetto#getSoggettiInf2(*
     */
    public java.util.List<Soggetto> getSoggettiInf2() throws SOAPFault_Exception    { 
        LOG.info("Executing operation getSoggettiInf2");
        try {
            java.util.List<Soggetto> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceSoggetto#getSoggettiNica(*
     */
    public java.util.List<Soggetto> getSoggettiNica() throws SOAPFault_Exception    { 
        LOG.info("Executing operation getSoggettiNica");
        try {
            java.util.List<Soggetto> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceSoggetto#getSoggettiSLA(java.lang.String  nomeAccordoServizio ,)java.lang.String  nomeServizio ,)java.lang.String  tipoServizio )*
     */
    public java.util.List<Soggetto> getSoggettiSLA(java.lang.String nomeAccordoServizio,java.lang.String nomeServizio,java.lang.String tipoServizio) throws SOAPFault_Exception    { 
        LOG.info("Executing operation getSoggettiSLA");
        System.out.println(nomeAccordoServizio);
        System.out.println(nomeServizio);
        System.out.println(tipoServizio);
        try {
            java.util.List<Soggetto> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceSoggetto#removeSoggettoInf2(it.unibas.freesbeesla.ws.web.Soggetto  soggetto )*
     */
    public void removeSoggettoInf2(Soggetto soggetto)    { 
        LOG.info("Executing operation removeSoggettoInf2");
        System.out.println(soggetto);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

    /* (non-Javadoc)
     * @see it.unibas.freesbeesla.ws.web.ServiceSoggetto#addSoggettoInf2(it.unibas.freesbeesla.ws.web.Soggetto  soggetto )*
     */
    public void addSoggettoInf2(Soggetto soggetto)    { 
        LOG.info("Executing operation addSoggettoInf2");
        System.out.println(soggetto);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SOAPFault_Exception("SOAPFault...");
    }

}
