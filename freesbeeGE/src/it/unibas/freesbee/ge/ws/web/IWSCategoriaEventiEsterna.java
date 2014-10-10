package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.modello.CategoriaEventiEsterna;

import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public interface IWSCategoriaEventiEsterna {

    @WebMethod
    public void addCategoriaEventiEsterna(@WebParam(name = "categoriaEventi") CategoriaEventiEsterna categoriaEventi) throws SOAPFault;

    @WebMethod
    public java.util.List<CategoriaEventiEsterna> getListaCategoriaEventiEsterne() throws SOAPFault;

    @WebMethod
    public void updateCategoriaEventiEsterna(@WebParam(name = "categoriaEventi") CategoriaEventiEsterna categoriaEventi) throws SOAPFault;

      @WebMethod
    public CategoriaEventiEsterna getCategoriaEventiEsterna(@WebParam(name = "categoriaEventi")String categoriaEventi ) throws SOAPFault;

}
