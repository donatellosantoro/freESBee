package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public interface IWSAutenticazione {

    @WebMethod(operationName = "autentica")
    void autentica(@WebParam(name = "nomeUtente") String nomeUtente) throws SOAPFault;

    @WebMethod(operationName = "cambiaPassword")
    void cambiaPassword(@WebParam(name = "nuovaPassword") String nuovaPassword, @WebParam(name = "nomeUtente") String nomeUtente) throws SOAPFault;
}