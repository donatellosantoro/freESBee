package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.PortaApplicativa;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSPortaApplicativa {
    
    @WebMethod(operationName="addPortaApplicativa")
    void addPortaApplicativa(@WebParam(name="portaApplicativa")PortaApplicativa pa) throws SOAPFault;
    
    @WebMethod(operationName="removePortaApplicativa")
    void removePortaApplicativa(@WebParam(name="portaApplicativa")PortaApplicativa pa) throws SOAPFault;
    
    @WebMethod(operationName="getListaPorteApplicative")
    List<PortaApplicativa> getListaPorteApplicative() throws SOAPFault;
    
    @WebMethod(operationName="getPortaApplicativa")
    PortaApplicativa getPortaApplicativa(@WebParam(name="portaApplicativa")long id) throws SOAPFault;
}
