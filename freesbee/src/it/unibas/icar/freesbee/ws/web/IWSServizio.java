package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSServizio {
    
    @WebMethod(operationName="addServizio")
    void addServizio(@WebParam(name="servizio")Servizio servizio) throws SOAPFault;
    
    @WebMethod(operationName="removeServizio")
    void removeServizio(@WebParam(name="servizio")long id) throws SOAPFault;
    
    @WebMethod(operationName="getListaServizi")
    List<Servizio> getListaServizi() throws SOAPFault;
    
    @WebMethod(operationName="getServizio")
    Servizio getServizio(@WebParam(name="servizio")long id) throws SOAPFault;    
}
