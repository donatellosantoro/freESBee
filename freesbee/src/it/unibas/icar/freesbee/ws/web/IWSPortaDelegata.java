package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.PortaDelegata;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSPortaDelegata {
    
    @WebMethod(operationName="addPortaDelegata")
    void addPortaDelegata(@WebParam(name="portaDelegata")PortaDelegata pd) throws SOAPFault;
    
    @WebMethod(operationName="removePortaDelegata")
    void removePortaDelegata(@WebParam(name="portaDelegata")long id) throws SOAPFault;
    
    @WebMethod(operationName="getListaPorteDelegate")
    List<PortaDelegata> getListaPorteDelegate() throws SOAPFault;
    
    @WebMethod(operationName="getPortaDelegata")
    PortaDelegata getPortaDelegata(@WebParam(name="portaDelegata")long id) throws SOAPFault;    
}
