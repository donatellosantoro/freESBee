package it.unibas.icar.freesbee.ws.client.configurazione.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2013-03-08T12:22:17.234+01:00
 * Generated source version: 2.5.2
 * 
 */
@WebService(targetNamespace = "http://icar.unibas.it/freesbee/", name = "IWSConfigurazione")
@XmlSeeAlso({ObjectFactory.class})
public interface IWSConfigurazione {

    @RequestWrapper(localName = "addConfigurazione", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbe.ws.client.configurazione.cxf.AddConfigurazione")
    @WebMethod
    @ResponseWrapper(localName = "addConfigurazioneResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbe.ws.client.configurazione.cxf.AddConfigurazioneResponse")
    public void addConfigurazione(
        @WebParam(name = "configurazione", targetNamespace = "")
        it.unibas.icar.freesbee.ws.client.configurazione.cxf.Configurazione configurazione
    ) throws SOAPFault_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getFreesbeeVersion", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbe.ws.client.configurazione.cxf.GetFreesbeeVersion")
    @WebMethod
    @ResponseWrapper(localName = "getFreesbeeVersionResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbe.ws.client.configurazione.cxf.GetFreesbeeVersionResponse")
    public java.lang.String getFreesbeeVersion() throws SOAPFault_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getConfigurazione", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbe.ws.client.configurazione.cxf.GetConfigurazione")
    @WebMethod
    @ResponseWrapper(localName = "getConfigurazioneResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbe.ws.client.configurazione.cxf.GetConfigurazioneResponse")
    public it.unibas.icar.freesbee.ws.client.configurazione.cxf.Configurazione getConfigurazione() throws SOAPFault_Exception;
}
