package it.unibas.freesbee.ge.ws.gestoreeventi;

import it.unibas.freesbee.ge.modello.Sottoscrittore;
import it.unibas.freesbee.ge.modello.SottoscrizioneEsterna;
import it.unibas.freesbee.ge.modello.SottoscrizioneInterna;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://ge.freesbee.unibas.it/")
public interface IWSGestioneSottoscrizioni {

    @WebMethod(operationName = "aggiungiSottoscrizione")
    void aggiungiSottoscrizione(@WebParam(name = "sottoscrizione") SottoscrizioneInterna sottoscrizioneInterna) throws SOAPFault;

    @WebMethod(operationName = "aggiungiSottoscrizione")
    void aggiungiSottoscrizione(@WebParam(name = "sottoscrizione") SottoscrizioneEsterna sottoscrizioneEsterna) throws SOAPFault;

    @WebMethod(operationName = "eliminaSottoscrizione")
    void eliminaSottoscrizione(@WebParam(name = "sottoscrittore") Sottoscrittore sottoscrittore) throws SOAPFault;
}