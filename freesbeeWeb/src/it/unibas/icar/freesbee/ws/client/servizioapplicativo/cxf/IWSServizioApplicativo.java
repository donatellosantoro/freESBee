package it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-06-22T18:05:18.366+02:00
 * Generated source version: 2.5.2
 * 
 */
@WebService(targetNamespace = "http://icar.unibas.it/freesbee/", name = "IWSServizioApplicativo")
@XmlSeeAlso({ObjectFactory.class})
public interface IWSServizioApplicativo {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getServizioApplicativo", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.GetServizioApplicativo")
    @WebMethod
    @ResponseWrapper(localName = "getServizioApplicativoResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.GetServizioApplicativoResponse")
    public it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo getServizioApplicativo(
        @WebParam(name = "servizioApplicativo", targetNamespace = "")
        long servizioApplicativo
    ) throws SOAPFault_Exception;

    @RequestWrapper(localName = "addServizioApplicativo", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.AddServizioApplicativo")
    @WebMethod
    @ResponseWrapper(localName = "addServizioApplicativoResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.AddServizioApplicativoResponse")
    public void addServizioApplicativo(
        @WebParam(name = "servizioApplicativo", targetNamespace = "")
        it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo servizioApplicativo
    ) throws SOAPFault_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getListaServiziApplicativi", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.GetListaServiziApplicativi")
    @WebMethod
    @ResponseWrapper(localName = "getListaServiziApplicativiResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.GetListaServiziApplicativiResponse")
    public java.util.List<it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.ServizioApplicativo> getListaServiziApplicativi() throws SOAPFault_Exception;

    @RequestWrapper(localName = "removeServizioApplicativo", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.RemoveServizioApplicativo")
    @WebMethod
    @ResponseWrapper(localName = "removeServizioApplicativoResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf.RemoveServizioApplicativoResponse")
    public void removeServizioApplicativo(
        @WebParam(name = "servizioApplicativo", targetNamespace = "")
        long servizioApplicativo
    ) throws SOAPFault_Exception;
}
