package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.ws.WebFault;

/**
 * This class was generated by Apache CXF (incubator) 2.0.4-incubator
 * Mon Aug 03 19:25:25 CEST 2009
 * Generated source version: 2.0.4-incubator
 * 
 */
@WebFault(name = "SOAPFault", targetNamespace = "http://ge.freesbee.unibas.it/")
public class SOAPFaultException extends Exception {

    public static final long serialVersionUID = 20090803192525L;
    private SOAPFault soapFault;

    public SOAPFaultException() {
        super();
    }

    public SOAPFaultException(String message) {
        super(message);
    }

    public SOAPFaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public SOAPFaultException(String message, SOAPFault soapFault) {
        super(message);
        this.soapFault = soapFault;
    }

    public SOAPFaultException(String message, SOAPFault soapFault, Throwable cause) {
        super(message, cause);
        this.soapFault = soapFault;
    }

    public SOAPFault getFaultInfo() {
        return this.soapFault;
    }
}