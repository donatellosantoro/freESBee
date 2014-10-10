package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSAccordoServizio {
    
    @WebMethod(operationName="addAccordoServizio")
    void addAccordoServizio(@WebParam(name="accordoServizio")AccordoServizio accordoServizio) throws SOAPFault;
    
    @WebMethod(operationName="removeAccordoServizio")
    void removeAccordoServizio(@WebParam(name="accordoServizio")long id) throws SOAPFault; 
    
    @WebMethod(operationName="getListaAccordiServizio")
    List<AccordoServizio> getListaAccordiServizio() throws SOAPFault;
    
    @WebMethod(operationName="getAccordoServizio")
    AccordoServizio getAccordoServizio(@WebParam(name="accordoServizio")long id) throws SOAPFault;    
}
