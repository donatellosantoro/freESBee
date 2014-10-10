
package it.unibas.freesbee.ge.web.ws.stub;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF (incubator) 2.0.4-incubator
 * Tue Aug 04 11:32:34 CEST 2009
 * Generated source version: 2.0.4-incubator
 * 
 */

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/", name = "IWSGestioneGestoreEventi")

public interface IWSGestioneGestoreEventi {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getListaGestoriEventi", targetNamespace = "http://ge.freesbee.unibas.it/", className = "it.unibas.freesbee.ge.GetListaGestoriEventi")
    @ResponseWrapper(localName = "getListaGestoriEventiResponse", targetNamespace = "http://ge.freesbee.unibas.it/", className = "it.unibas.freesbee.ge.GetListaGestoriEventiResponse")
    @WebMethod
    public java.util.List<GestoreEventi> getListaGestoriEventi() throws SOAPFaultException;

    @RequestWrapper(localName = "addGestoreEventi", targetNamespace = "http://ge.freesbee.unibas.it/", className = "it.unibas.freesbee.ge.AddGestoreEventi")
    @ResponseWrapper(localName = "addGestoreEventiResponse", targetNamespace = "http://ge.freesbee.unibas.it/", className = "it.unibas.freesbee.ge.AddGestoreEventiResponse")
    @WebMethod
    public void addGestoreEventi(
        @WebParam(name = "gestoreEventi", targetNamespace = "")GestoreEventi gestoreEventi) throws SOAPFaultException;

    @RequestWrapper(localName = "updateGestoriEventi", targetNamespace = "http://ge.freesbee.unibas.it/", className = "it.unibas.freesbee.ge.UpdateGestoriEventi")
    @ResponseWrapper(localName = "updateGestoriEventiResponse", targetNamespace = "http://ge.freesbee.unibas.it/", className = "it.unibas.freesbee.ge.UpdateGestoriEventiResponse")
    @WebMethod
    public void updateGestoriEventi(
        @WebParam(name = "gestoreEventi", targetNamespace = "")GestoreEventi gestoreEventi) throws SOAPFaultException;
}
