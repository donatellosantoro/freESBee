package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.persistenza.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSAutenticazione {
    
    @WebMethod(operationName="autentica")
    void autentica(@WebParam(name="nomeUtente")String nomeUtente) throws SOAPFault;
    
    @WebMethod(operationName="cambiaPassword")
    void cambiaPassword(@WebParam(name="nuovaPassword")String nuovaPassword,@WebParam(name="nomeUtente")String nomeUtente) throws SOAPFault;
    
    @WebMethod(operationName="getRuolo")
    String getRuolo(@WebParam(name="nomeUtente")String nomeUtente) throws SOAPFault;
    
}
