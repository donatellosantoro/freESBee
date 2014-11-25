package it.unibas.icar.freesbee.ws.client.logger.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2013-05-29T00:57:22.058+02:00
 * Generated source version: 2.5.2
 * 
 */
@WebService(targetNamespace = "http://icar.unibas.it/freesbee/", name = "IWSLogger")
@XmlSeeAlso({ObjectFactory.class})
public interface IWSLogger {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getLogDaLivello", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.logger.cxf.GetLogDaLivello")
    @WebMethod
    @ResponseWrapper(localName = "getLogDaLivelloResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.logger.cxf.GetLogDaLivelloResponse")
    public java.lang.String getLogDaLivello(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws SOAPFault_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getLogFile", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.logger.cxf.GetLogFile")
    @WebMethod
    @ResponseWrapper(localName = "getLogFileResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.logger.cxf.GetLogFileResponse")
    public byte[] getLogFile() throws SOAPFault_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getLogDaDataLivello", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.logger.cxf.GetLogDaDataLivello")
    @WebMethod
    @ResponseWrapper(localName = "getLogDaDataLivelloResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.logger.cxf.GetLogDaDataLivelloResponse")
    public java.lang.String getLogDaDataLivello(
        @WebParam(name = "arg0", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    ) throws SOAPFault_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getLogDaData", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.logger.cxf.GetLogDaData")
    @WebMethod
    @ResponseWrapper(localName = "getLogDaDataResponse", targetNamespace = "http://icar.unibas.it/freesbee/", className = "it.unibas.icar.freesbee.ws.client.logger.cxf.GetLogDaDataResponse")
    public java.lang.String getLogDaData(
        @WebParam(name = "arg0", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg1
    ) throws SOAPFault_Exception;
}