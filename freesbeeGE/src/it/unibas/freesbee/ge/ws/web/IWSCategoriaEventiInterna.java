package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.modello.CategoriaEventiInterna;

import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public interface IWSCategoriaEventiInterna {

    @WebMethod
    public void addCategoriaEventiInterna(@WebParam(name = "categoriaEventi") CategoriaEventiInterna categoriaEventi) throws SOAPFault;

    @WebMethod
    public java.util.List<CategoriaEventiInterna> getListaCategoriaEventiInterne() throws SOAPFault;

    @WebMethod
    public void updateCategoriaEventiInterna(@WebParam(name = "categoriaEventi") CategoriaEventiInterna categoriaEventi) throws SOAPFault;

        @WebMethod
    public CategoriaEventiInterna getCategoriaEventiInterna(@WebParam(name = "categoriaEventi") String categoriaEventi) throws SOAPFault;

}
