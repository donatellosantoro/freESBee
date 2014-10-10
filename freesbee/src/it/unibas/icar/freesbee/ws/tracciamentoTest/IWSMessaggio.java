package it.unibas.icar.freesbee.ws.tracciamentoTest;

import it.unibas.icar.freesbee.modello.Messaggio;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
public interface IWSMessaggio {
    
    @WebMethod(operationName="getMessaggioInviatoByIDSil")
    Messaggio getMessaggioInviatoByIDSil(@WebParam(name="id")String id) throws SOAPFault;
    
    @WebMethod(operationName="getMessaggioInviatoByIDsilRelatesTo")
    Messaggio getMessaggioInviatoByIDsilRelatesTo(@WebParam(name="id")String id) throws SOAPFault;
    
    @WebMethod(operationName="getMessaggioRicevutoByIDSil")
    Messaggio getMessaggioRicevutoByIDSil(@WebParam(name="id")String id) throws SOAPFault;
    
    @WebMethod(operationName="getMessaggioRicevutoByIDsilRelatesTo")
    Messaggio getMessaggioRicevutoByIDsilRelatesTo(@WebParam(name="id")String id) throws SOAPFault;
    
    @WebMethod(operationName="svuotaDB")
    void svuotaDB() throws SOAPFault;

}
