package it.unibas.icar.freesbee.ws.echoservice;

import it.unibas.icar.freesbee.persistenza.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

//@ImplementedBy(WSEchoServiceImpl.class)
@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
public interface IWSEchoService {

    @WebMethod(operationName="echoService")
    String echoService(@WebParam(name="id") String id,@WebParam(name="payload") String payload) throws SOAPFault;
    
}
