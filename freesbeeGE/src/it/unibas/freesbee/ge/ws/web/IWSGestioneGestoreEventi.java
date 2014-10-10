package it.unibas.freesbee.ge.ws.web;


import it.unibas.freesbee.ge.modello.GestoreEventi;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public interface IWSGestioneGestoreEventi {

    @WebMethod
    public void addGestoreEventi(@WebParam(name = "gestoreEventi") GestoreEventi gestoreEventi) throws SOAPFault;

    @WebMethod
    public java.util.List<GestoreEventi> getListaGestoriEventi() throws SOAPFault;

    @WebMethod
    public void updateGestoriEventi(@WebParam(name = "gestoreEventi") GestoreEventi gestoreEventi) throws SOAPFault;
}
