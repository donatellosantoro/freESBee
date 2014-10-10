package it.unibas.silvio.ws.istanza;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService(targetNamespace="http://freesbee.unibas.it")
public interface IWSIstanza {
    
    @WebMethod(operationName="avviaIstanza")
    void avviaIstanza(@WebParam(name="id_istanza")long id) throws Exception;

}
