package it.unibas.freesbeesla.ws.web;

import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace = "http://web.ws.freesbeesla.unibas.it/", name = "ServiceSoggetto")
@InInterceptors(interceptors={"it.unibas.freesbeesla.ws.web.WSSecurityInterceptor"})
public interface IWSSoggetto {

  
    @WebResult(name = "soggetto")
    @WebMethod
    public List<Soggetto> getSoggettiNica() throws SOAPFault;

    @WebResult(name = "soggetto")
    @WebMethod
    public List<Soggetto> getSoggettiInf2() throws SOAPFault;

    @WebResult(name = "soggetto")
    @WebMethod
    public List<Soggetto> getSoggettiSLA(@WebParam(name = "nomeAccordoServizio") String nomeAccordoServizio, @WebParam(name = "nomeServizio") String servizio, @WebParam(name = "tipoServizio") String tipo) throws SOAPFault;

    @WebMethod
    public void addSoggettoInf2(
            
            @WebParam(name = "soggetto") Soggetto richiesta) throws SOAPFault;

    @WebMethod
    public void removeSoggettoInf2(
            
            @WebParam(name = "soggetto") Soggetto richiesta) throws SOAPFault;



}