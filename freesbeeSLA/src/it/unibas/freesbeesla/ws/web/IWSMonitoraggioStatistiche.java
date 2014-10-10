package it.unibas.freesbeesla.ws.web;


import it.unibas.freesbeesla.tracciatura.ws.server.SOAPFault;
import it.unibas.freesbeesla.ws.web.stub.StatisticheSLAErogatore;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@WebService(targetNamespace = "http://web.ws.freesbeesla.unibas.it/", name = "ServiceMonitoraggioStatistiche")
@InInterceptors(interceptors={"it.unibas.freesbeesla.ws.web.WSSecurityInterceptor"})
public interface IWSMonitoraggioStatistiche {


    @WebMethod
    DataHandler statisticheErogatore(         
            @WebParam(partName = "StatisticheSLAErogatore", name = "StatisticheSLAErogatore", targetNamespace = "") StatisticheSLAErogatore richiesta) throws SOAPFault;

    
}
