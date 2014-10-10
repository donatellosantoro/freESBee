package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbeesla.DatiConfigurazione;
import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace = "http://web.ws.freesbeesla.unibas.it/", name = "ServiceConfigura")
@InInterceptors(interceptors = {"it.unibas.freesbeesla.ws.web.WSSecurityInterceptor"})
public interface IWSConfigura {

    @WebMethod
    public String verificaNica() throws SOAPFault;

    @WebMethod
    @WebResult(name = "datiConfigurazione")
    public DatiConfigurazione caricaConfigurazione() throws SOAPFault;

    @WebMethod
    public void modificaConfigurazioneSLA(@WebParam(name="datiConfigurazione") DatiConfigurazione datiConfigurazione) throws SOAPFault;

    @WebMethod
    public void modificaConfigurazioneSP(@WebParam(name="datiConfigurazione") DatiConfigurazione datiConfigurazione) throws SOAPFault;
    
}
