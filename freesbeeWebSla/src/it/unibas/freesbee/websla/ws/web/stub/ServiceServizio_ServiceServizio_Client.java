
package it.unibas.freesbee.websla.ws.web.stub;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2013-12-12T23:04:16.089+01:00
 * Generated source version: 2.7.7
 * 
 */
public final class ServiceServizio_ServiceServizio_Client {

    private static final QName SERVICE_NAME = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceServizio");

    private ServiceServizio_ServiceServizio_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ServiceServizio_Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ServiceServizio_Service ss = new ServiceServizio_Service(wsdlURL, SERVICE_NAME);
        ServiceServizio port = ss.getServiceServizio();  
        
        {
        System.out.println("Invoking getServiziInf2Fruiti...");
        try {
            java.util.List<Servizio> _getServiziInf2Fruiti__return = port.getServiziInf2Fruiti();
            System.out.println("getServiziInf2Fruiti.result=" + _getServiziInf2Fruiti__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking updateServizioActive...");
        Servizio _updateServizioActive_servizio = null;
        try {
            port.updateServizioActive(_updateServizioActive_servizio);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getServiziInf2Erogati...");
        try {
            java.util.List<Servizio> _getServiziInf2Erogati__return = port.getServiziInf2Erogati();
            System.out.println("getServiziInf2Erogati.result=" + _getServiziInf2Erogati__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getServiziNicaNonMonitorati...");
        try {
            java.util.List<Servizio> _getServiziNicaNonMonitorati__return = port.getServiziNicaNonMonitorati();
            System.out.println("getServiziNicaNonMonitorati.result=" + _getServiziNicaNonMonitorati__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking addServizio...");
        Servizio _addServizio_servizio = null;
        byte[] _addServizio_arg1 = new byte[0];
        try {
            port.addServizio(_addServizio_servizio, _addServizio_arg1);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getParametriSla...");
        Servizio _getParametriSla_servizio = null;
        try {
            java.util.List<java.lang.String> _getParametriSla__return = port.getParametriSla(_getParametriSla_servizio);
            System.out.println("getParametriSla.result=" + _getParametriSla__return);

        } catch (SOAPFault_Exception e) { 
            System.out.println("Expected exception: SOAPFault has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
