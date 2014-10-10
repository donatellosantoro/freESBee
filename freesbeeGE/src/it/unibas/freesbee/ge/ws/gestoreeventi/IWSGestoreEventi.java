package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.modello.ConsegnaEventoPubblicato;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor"/*, "it.unibas.freesbee.ge.ws.gestoreeventi.InterceptorWsdl"*/})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public interface IWSGestoreEventi {

    @WebResult(name = "consegnaMessaggioNotificato")
    @WebMethod(operationName = "prelevaMessaggio")
    ConsegnaEventoPubblicato prelevaMessaggio(@WebParam(name = "idMessaggio") String idMessaggio, @WebParam(name = "nomeSottoscrittore") String nomeSottoscrittore, @WebParam(name = "tipoSottoscrittore") String tipoSottoscrittore, @WebParam(name = "categoriaEventi") String categoriaEventi) throws Exception;

    @WebMethod(operationName = "pubblicaMessaggio")
    String pubblicaMessaggio(@WebParam(name = "messaggioSoap") Object messaggioSoap, @WebParam(name = "nomePubblicatore") String nomePubblicatore, @WebParam(name = "tipoPubblicatore") String tipoPubblicatore, @WebParam(name = "categoriaEventi") String categoriaEventi, @WebParam(name = "nomePubblicatoreEsterno") String nomePubblicatoreEsterno, @WebParam(name = "tipoPubblicatoreEsterno") String tipoPubblicatoreEsterno) throws SOAPFault;
}