package it.unibas.icar.freesbee.ws.web;

import it.unibas.icar.freesbee.modello.Soggetto;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.web.WSSecurityInterceptor"})
public interface IWSSoggetto {
    
    @WebMethod(operationName="addSoggetto")
    void addSoggetto(@WebParam(name="soggetto")Soggetto soggetto) throws SOAPFault;
    
    @WebMethod(operationName="removeSoggetto")
    void removeSoggetto(@WebParam(name="soggetto")long id) throws SOAPFault;
    
    @WebMethod(operationName="getListaSoggetti")
    List<Soggetto> getListaSoggetti() throws SOAPFault;
    
    @WebMethod(operationName="getSoggetto")
    Soggetto getSoggetto(@WebParam(name="soggetto")long id) throws SOAPFault;    
}
