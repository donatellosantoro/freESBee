package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.ProfiloEGov;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSProfiloEGov {
    
    @WebMethod(operationName="addProfiloEGov")
    long addProfiloEGov(@WebParam(name="profiloEGov")ProfiloEGov profilo) throws SOAPFault;
    
    @WebMethod(operationName="removeProfiloEGov")
    void removeProfiloEGov(@WebParam(name="profiloEGov")long id) throws SOAPFault;
    
    @WebMethod(operationName="getListaProfiliEGov")
    List<ProfiloEGov> getListaProfiliEGov() throws SOAPFault;
    
    @WebMethod(operationName="getProfiloEGov")
    ProfiloEGov getProfiloEGov(@WebParam(name="profiloEGov")long id) throws SOAPFault;    
}
