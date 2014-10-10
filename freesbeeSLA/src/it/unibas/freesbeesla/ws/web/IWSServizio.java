package it.unibas.freesbeesla.ws.web;

import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.icar.freesbee.ws.registroservizi.client.stub.ServizioRS;
import java.util.List;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace = "http://web.ws.freesbeesla.unibas.it/", name = "ServiceServizio")
@InInterceptors(interceptors={"it.unibas.freesbeesla.ws.web.WSSecurityInterceptor"})
public interface IWSServizio {

    @WebResult(name = "servizio")
    @WebMethod
    public List<Servizio> getServiziInf2Erogati() throws SOAPFault;

    @WebResult(name = "servizio")
    @WebMethod
    public List<Servizio> getServiziInf2Fruiti() throws SOAPFault;

    @WebResult(name = "servizio")
    @WebMethod
    public List<Servizio> getServiziNicaNonMonitorati() throws SOAPFault;

    @WebMethod
    public void updateServizioActive(
            
            @WebParam(name = "servizio") Servizio servizio) throws SOAPFault;

    @WebMethod
    public void addServizio(
            
            @WebParam(name = "servizio") Servizio servizio,  DataHandler attach) throws SOAPFault;

    @WebResult(name = "parametroSla")
    @WebMethod
    public List<String> getParametriSla(
            
            @WebParam(name = "servizio") Servizio servizio) throws SOAPFault;
}
