
package it.unibas.freesbee.ge.stub;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF (incubator) 2.0.5-incubator
 * Thu Jan 29 12:06:00 CET 2009
 * Generated source version: 2.0.5-incubator
 * 
 */

@WebFault(name = "SOAPFault", targetNamespace = "http://ge.freesbee.unibas.it/")

public class SOAPFault_Exception extends Exception {
    public static final long serialVersionUID = 20090129120600L;
    
    private SOAPFault soapFault;

    public SOAPFault_Exception() {
        super();
    }
    
    public SOAPFault_Exception(String message) {
        super(message);
    }
    
    public SOAPFault_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public SOAPFault_Exception(String message, SOAPFault soapFault) {
        super(message);
        this.soapFault = soapFault;
    }

    public SOAPFault_Exception(String message, SOAPFault soapFault, Throwable cause) {
        super(message, cause);
        this.soapFault = soapFault;
    }

    public SOAPFault getFaultInfo() {
        return this.soapFault;
    }
}
