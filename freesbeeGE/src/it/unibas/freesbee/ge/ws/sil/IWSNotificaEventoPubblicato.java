package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.stub.NotificaEventoPubblicato;
import it.unibas.freesbee.ge.stub.SOAPFault_Exception;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://stub.ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public interface IWSNotificaEventoPubblicato {

    @WebMethod(operationName = "notificaEventoPubblicato")
    void notificaEventoPubblicato(@WebParam(name="notificaEventoPubblicato")NotificaEventoPubblicato notificaEventoPubblicato) throws SOAPFault_Exception;
}