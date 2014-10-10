package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.modello.PubblicatoreInterno;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
public interface IWSGestionePubblicatori {

    @WebMethod(operationName = "aggiungiPubblicatore")
    void aggiungiPubblicatore(@WebParam(name = "pubblicatore") PubblicatoreInterno pubblicatore) throws SOAPFault;

    @WebMethod(operationName = "eliminaPubblicatore")
    void eliminaPubblicatore(@WebParam(name = "pubblicatore") PubblicatoreInterno pubblicatore) throws SOAPFault;
}