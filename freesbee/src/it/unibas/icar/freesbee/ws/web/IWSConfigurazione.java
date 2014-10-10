package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Configurazione;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSConfigurazione {
    
    @WebMethod(operationName="addConfigurazione")
    void addConfigurazione(@WebParam(name="configurazione")Configurazione freesbeeConfig) throws SOAPFault;
    
    @WebMethod(operationName="getConfigurazione")
    Configurazione getConfigurazione() throws SOAPFault;
    
    @WebMethod(operationName="getFreesbeeVersion")
    String getFreesbeeVersion() throws SOAPFault;
    
}
