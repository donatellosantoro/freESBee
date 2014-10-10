package it.unibas.freesbee.ge.ws.web;

import it.unibas.freesbee.ge.modello.DatiConfigurazione;
import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/", name = "IWSConfigura")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor", "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public interface IWSConfigura {

    @WebMethod
    public void modificaConfigurazioneGE(@WebParam(name = "datiConfigurazione") DatiConfigurazione datiConfigurazione) throws SOAPFault;

    @WebMethod
    public void modificaConfigurazioneSP(
            @WebParam(name = "datiConfigurazione") DatiConfigurazione datiConfigurazione) throws SOAPFault;

    @WebResult(name = "datiConfigurazione")
    @WebMethod
    public DatiConfigurazione caricaConfigurazione() throws SOAPFault;
}
