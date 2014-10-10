package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.ws.gestoreeventi.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor"})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public interface IWSConsegnaMessaggio {

    @WebMethod(operationName = "consegnaMessaggio")
    void consegna(@WebParam(name = "messaggioSoap") Object messaggioSoap, @WebParam(name = "nomePubblicatore") String nomePubblicatore,@WebParam(name = "tipoPubblicatore") String tipoPubblicatore, @WebParam(name = "categoriaEventi")String categoriaEventi) throws SOAPFault;

}