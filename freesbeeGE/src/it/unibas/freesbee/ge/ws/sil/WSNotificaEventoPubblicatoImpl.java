package it.unibas.freesbee.ge.ws.sil;

import it.unibas.freesbee.ge.ws.gestoreeventi.WSGestoreEventiImpl;
import it.unibas.freesbee.ge.stub.NotificaEventoPubblicato;
import it.unibas.freesbee.ge.stub.SOAPFault_Exception;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@WebService(targetNamespace = "http://stub.ge.freesbee.unibas.it/")
@InInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptor"/*, "it.unibas.freesbee.ge.ws.web.WSSecurityInterceptor"*/})
@OutFaultInterceptors(interceptors = {"it.unibas.freesbee.ge.persistenza.hibernate.HibernateInterceptorFault"})
public class WSNotificaEventoPubblicatoImpl implements IWSNotificaEventoPubblicato {

    private static Log logger = LogFactory.getLog(WSGestoreEventiImpl.class);

    public WSNotificaEventoPubblicatoImpl() {
    }



    @WebMethod(operationName = "notificaEventoPubblicato")
    public void notificaEventoPubblicato(@WebParam(name="notificaEventoPubblicato")NotificaEventoPubblicato notificaEventoPubblicato) throws SOAPFault_Exception{
        logger.info("Operazione richiesta: notificaEventoPubblicato");
    }

    


}


