package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Azione;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSAzione {
    
    @WebMethod(operationName="addAzione")
    void addAzione(@WebParam(name="azione")Azione azione) throws SOAPFault;
    
    @WebMethod(operationName="removeAzione")
    void removeAzione(@WebParam(name="azione")long id) throws SOAPFault;
    
    @WebMethod(operationName="getListaAzioni")
    List<Azione> getListaAzioni() throws SOAPFault;
    
    @WebMethod(operationName="getAzione")
    Azione getAzione(@WebParam(name="azione")long id) throws SOAPFault;    
}
