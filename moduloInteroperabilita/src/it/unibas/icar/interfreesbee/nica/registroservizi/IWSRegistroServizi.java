
package it.unibas.icar.interfreesbee.nica.registroservizi;

import it.unibas.icar.interfreesbee.nica.registroservizi.stub.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://ws.registry.openspcoop.org", name = "WSRegistrySearch")

public interface IWSRegistroServizi {

//    @WebResult(name = "existsServizioSpcoopCorrelatoReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "existsServizioSpcoopCorrelato", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.ExistsServizioSpcoopCorrelato")
//    @ResponseWrapper(localName = "existsServizioSpcoopCorrelatoResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.ExistsServizioSpcoopCorrelatoResponse")
    @WebMethod
    public boolean existsServizioSpcoopCorrelato(
        @WebParam(name = "idServizio", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDServizio idServizio
    ) throws WSRegistrySearchException_Exception;

//    @WebResult(name = "getAllIdServiziSPCoopReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getAllIdServiziSPCoop", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAllIdServiziSPCoop")
//    @ResponseWrapper(localName = "getAllIdServiziSPCoopResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAllIdServiziSPCoopResponse")
    @WebMethod
    public java.util.List<it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDServizio> getAllIdServiziSPCoop(
        @WebParam(name = "filtroRicerca", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.FiltroServiziSPCoop filtroRicerca
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;

//    @WebResult(name = "existsSoggettoSPCoopReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "existsSoggettoSPCoop", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.ExistsSoggettoSPCoop")
//    @ResponseWrapper(localName = "existsSoggettoSPCoopResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.ExistsSoggettoSPCoopResponse")
    @WebMethod
    public boolean existsSoggettoSPCoop(
        @WebParam(name = "idSoggetto", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDSoggetto idSoggetto
    ) throws WSRegistrySearchException_Exception;

//    @WebResult(name = "getServizioSPCoopCorrelatoReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getServizioSPCoopCorrelato", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetServizioSPCoopCorrelato")
//    @ResponseWrapper(localName = "getServizioSPCoopCorrelatoResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetServizioSPCoopCorrelatoResponse")
    @WebMethod
    public it.unibas.icar.interfreesbee.nica.registroservizi.stub.ServizioSpcoop getServizioSPCoopCorrelato(
        @WebParam(name = "idService", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDServizio idService
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;

//    @WebResult(name = "getAllIdAccordiServizioReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getAllIdAccordiServizio", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAllIdAccordiServizio")
//    @ResponseWrapper(localName = "getAllIdAccordiServizioResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAllIdAccordiServizioResponse")
    @WebMethod
    public java.util.List<java.lang.String> getAllIdAccordiServizio(
        @WebParam(name = "filtroRicerca", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.FiltroSPCoop filtroRicerca
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;

//    @WebResult(name = "getServizioSPCoopReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getServizioSPCoop", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetServizioSPCoop")
//    @ResponseWrapper(localName = "getServizioSPCoopResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetServizioSPCoopResponse")
    @WebMethod
    public it.unibas.icar.interfreesbee.nica.registroservizi.stub.ServizioSpcoop getServizioSPCoop(
        @WebParam(name = "idService", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDServizio idService
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;

//    @WebResult(name = "getAllIdServiziSPCoopCorrelatiReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getAllIdServiziSPCoopCorrelati", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAllIdServiziSPCoopCorrelati")
//    @ResponseWrapper(localName = "getAllIdServiziSPCoopCorrelatiResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAllIdServiziSPCoopCorrelatiResponse")
    @WebMethod
    public java.util.List<it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDServizio> getAllIdServiziSPCoopCorrelati(
        @WebParam(name = "filtroRicerca", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.FiltroServiziSPCoop filtroRicerca
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;

//    @WebResult(name = "getSoggettoSPCoopReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getSoggettoSPCoop", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetSoggettoSPCoop")
//    @ResponseWrapper(localName = "getSoggettoSPCoopResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetSoggettoSPCoopResponse")
    @WebMethod
    public it.unibas.icar.interfreesbee.nica.registroservizi.stub.SoggettoSpcoop getSoggettoSPCoop(
        @WebParam(name = "idSoggetto", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDSoggetto idSoggetto
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;

//    @WebResult(name = "getServizioSPCoopCorrelatoByAccordoReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getServizioSPCoopCorrelatoByAccordo", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetServizioSPCoopCorrelatoByAccordo")
//    @ResponseWrapper(localName = "getServizioSPCoopCorrelatoByAccordoResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetServizioSPCoopCorrelatoByAccordoResponse")
    @WebMethod
    public it.unibas.icar.interfreesbee.nica.registroservizi.stub.ServizioSpcoop getServizioSPCoopCorrelatoByAccordo(
        @WebParam(name = "idSoggetto", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDSoggetto idSoggetto,
        @WebParam(name = "nomeAccordo", targetNamespace = "http://ws.registry.openspcoop.org")
        java.lang.String nomeAccordo
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;

//    @WebResult(name = "existsAccordoServizioReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "existsAccordoServizio", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.ExistsAccordoServizio")
//    @ResponseWrapper(localName = "existsAccordoServizioResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.ExistsAccordoServizioResponse")
    @WebMethod
    public boolean existsAccordoServizio(
        @WebParam(name = "nome", targetNamespace = "http://ws.registry.openspcoop.org")
        java.lang.String nome
    ) throws WSRegistrySearchException_Exception;

//    @WebResult(name = "existsServizioSpcoopReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "existsServizioSpcoop", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.ExistsServizioSpcoop")
//    @ResponseWrapper(localName = "existsServizioSpcoopResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.ExistsServizioSpcoopResponse")
    @WebMethod
    public boolean existsServizioSpcoop(
        @WebParam(name = "idServizio", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDServizio idServizio
    ) throws WSRegistrySearchException_Exception;

//    @WebResult(name = "getAllIdSoggettiSPCoopReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getAllIdSoggettiSPCoop", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAllIdSoggettiSPCoop")
//    @ResponseWrapper(localName = "getAllIdSoggettiSPCoopResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAllIdSoggettiSPCoopResponse")
    @WebMethod
    public java.util.List<it.unibas.icar.interfreesbee.nica.registroservizi.stub.IDSoggetto> getAllIdSoggettiSPCoop(
        @WebParam(name = "filtroRicerca", targetNamespace = "http://ws.registry.openspcoop.org")
        it.unibas.icar.interfreesbee.nica.registroservizi.stub.FiltroSPCoop filtroRicerca
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;

//    @WebResult(name = "getAccordoServizioReturn", targetNamespace = "http://ws.registry.openspcoop.org")
//    @RequestWrapper(localName = "getAccordoServizio", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAccordoServizio")
//    @ResponseWrapper(localName = "getAccordoServizioResponse", targetNamespace = "http://ws.registry.openspcoop.org", className = "it.unibas.icar.interfreesbee.sincronizzatore.GetAccordoServizioResponse")
    @WebMethod
    public it.unibas.icar.interfreesbee.nica.registroservizi.stub.AccordoServizio getAccordoServizio(
        @WebParam(name = "nomeAccordo", targetNamespace = "http://ws.registry.openspcoop.org")
        java.lang.String nomeAccordo
    ) throws WSRegistryNotFound_Exception, WSRegistrySearchException_Exception;
}
