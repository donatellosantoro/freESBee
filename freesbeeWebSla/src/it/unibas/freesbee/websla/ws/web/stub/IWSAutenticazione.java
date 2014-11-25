
package it.unibas.freesbee.websla.ws.web.stub;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF (incubator) 2.0.4-incubator
 * Wed Sep 03 15:13:45 CEST 2008
 * Generated source version: 2.0.4-incubator
 * 
 */

@WebService(targetNamespace = "http://web.ws.freesbeesla.unibas.it/", name = "IWSAutenticazione")

public interface IWSAutenticazione {

    @RequestWrapper(localName = "autentica", targetNamespace = "http://web.ws.freesbeesla.unibas.it/", className = "it.unibas.freesbeesla.ws.web.Autentica")
    @ResponseWrapper(localName = "autenticaResponse", targetNamespace = "http://web.ws.freesbeesla.unibas.it/", className = "it.unibas.freesbeesla.ws.web.AutenticaResponse")
    @WebMethod
    public void autentica(
        @WebParam(name = "nomeUtente", targetNamespace = "http://web.ws.freesbeesla.unibas.it/")
        java.lang.String nomeUtente
    ) throws SOAPFault_Exception;

    @RequestWrapper(localName = "cambiaPassword", targetNamespace = "http://web.ws.freesbeesla.unibas.it/", className = "it.unibas.freesbeesla.ws.web.CambiaPassword")
    @ResponseWrapper(localName = "cambiaPasswordResponse", targetNamespace = "http://web.ws.freesbeesla.unibas.it/", className = "it.unibas.freesbeesla.ws.web.CambiaPasswordResponse")
    @WebMethod
    public void cambiaPassword(
        @WebParam(name = "nuovaPassword", targetNamespace = "http://web.ws.freesbeesla.unibas.it/")
        java.lang.String nuovaPassword,
        @WebParam(name = "nomeUtente", targetNamespace = "http://web.ws.freesbeesla.unibas.it/")
        java.lang.String nomeUtente
    ) throws SOAPFault_Exception;
}