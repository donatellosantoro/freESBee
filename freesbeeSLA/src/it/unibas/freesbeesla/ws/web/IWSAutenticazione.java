package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace = "http://web.ws.freesbeesla.unibas.it/")
@InInterceptors(interceptors={"it.unibas.freesbeesla.ws.web.WSSecurityInterceptor"})
public interface IWSAutenticazione {
    
    @WebMethod(operationName="autentica")
    void autentica(@WebParam(name="nomeUtente")String nomeUtente) throws SOAPFault;
    
    @WebMethod(operationName="cambiaPassword")
    void cambiaPassword(@WebParam(name="nuovaPassword")String nuovaPassword,@WebParam(name="nomeUtente")String nomeUtente) throws SOAPFault; 
    
}