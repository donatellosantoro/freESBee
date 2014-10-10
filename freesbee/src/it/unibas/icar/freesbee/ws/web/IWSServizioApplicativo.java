package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.ServizioApplicativo;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSServizioApplicativo {
    
    @WebMethod(operationName="addServizioApplicativo")
    void addServizioApplicativo(@WebParam(name="servizioApplicativo")ServizioApplicativo servizioApplicativo) throws SOAPFault;
    
    @WebMethod(operationName="removeServizioApplicativo")
    void removeServizioApplicativo(@WebParam(name="servizioApplicativo")long id) throws SOAPFault;
    
    @WebMethod(operationName="getListaServiziApplicativi")
    List<ServizioApplicativo> getListaServiziApplicativi() throws SOAPFault;
    
    @WebMethod(operationName="getServizioApplicativo")
    ServizioApplicativo getServizioApplicativo(@WebParam(name="servizioApplicativo")long id) throws SOAPFault;    
}
