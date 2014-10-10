
package it.unibas.icar.freesbee.ws.client.soggetto.cxf;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-06-22T18:14:44.576+02:00
 * Generated source version: 2.5.2
 */

@WebFault(name = "SOAPFault", targetNamespace = "http://icar.unibas.it/freesbee/")
public class SOAPFault_Exception extends Exception {
    
    private it.unibas.icar.freesbee.ws.client.soggetto.cxf.SOAPFault soapFault;

    public SOAPFault_Exception() {
        super();
    }
    
    public SOAPFault_Exception(String message) {
        super(message);
    }
    
    public SOAPFault_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public SOAPFault_Exception(String message, it.unibas.icar.freesbee.ws.client.soggetto.cxf.SOAPFault soapFault) {
        super(message);
        this.soapFault = soapFault;
    }

    public SOAPFault_Exception(String message, it.unibas.icar.freesbee.ws.client.soggetto.cxf.SOAPFault soapFault, Throwable cause) {
        super(message, cause);
        this.soapFault = soapFault;
    }

    public it.unibas.icar.freesbee.ws.client.soggetto.cxf.SOAPFault getFaultInfo() {
        return this.soapFault;
    }
}
