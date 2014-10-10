package it.unibas.icar.interfreesbee.freesbee.registroservizi;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRSRisposta;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@ImplementedBy(WSRegistroServiziImpl.class)
@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
public interface IWSRegistroServizi {
    
    @WebMethod(operationName="existsAccordoServizio")
    boolean existsAccordoServizio(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo, @WebParam(name="accordoServizio")AccordoServizioRS accordo) throws SOAPFault;
    
    @WebMethod(operationName="existsFruitoreServizioSpcoop")
    boolean existsFruitoreServizioSpcoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio,@WebParam(name="soggettoFruitore")SoggettoRS soggettoFruitore) throws SOAPFault;

    @WebMethod(operationName="existsFruitoreServizioSpcoopCorrelato")
    boolean existsFruitoreServizioSpcoopCorrelato(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio,@WebParam(name="soggettoFruitore")SoggettoRS soggettoFruitore) throws SOAPFault;
    
    @WebMethod(operationName="existsServizioSpcoop")
    boolean existsServizioSpcoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault;
    
    @WebMethod(operationName="existsServizioSpcoopCorrelato")
    boolean existsServizioSpcoopCorrelato(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault;
    
    @WebMethod(operationName="existsSoggettoSPCoop")
    boolean existsSoggettoSPCoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="soggetto")SoggettoRS soggetto) throws SOAPFault;
    
    @WebMethod(operationName="getAllIdAccordiServizio")
    List<AccordoServizioRS> getAllIdAccordiServizio(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate, @WebParam(name="accordoServizio")AccordoServizioRS accordo) throws SOAPFault;
    
    @WebMethod(operationName="getAllIdServiziSPCoop")
    List<ServizioRS> getAllIdServiziSPCoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault;
    
    @WebMethod(operationName="getAllIdServiziSPCoopCorrelati")
    List<ServizioRS> getAllIdServiziSPCoopCorrelati(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="soggetto")ServizioRS servizio) throws SOAPFault;
    
    @WebMethod(operationName="getAllIdSoggettiSPCoop")
    List<SoggettoRSRisposta> getAllIdSoggettiSPCoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="soggetto")SoggettoRS soggetto) throws SOAPFault;
    
    @WebMethod(operationName="getAccordoServizio")
    AccordoServizioRSRisposta getAccordoServizio(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="accordoServizio")AccordoServizioRS accordo) throws SOAPFault;
    
    @WebMethod(operationName="getFruitoreServizioSpcoop")
    SoggettoRSRisposta getFruitoreServizioSpcoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio,@WebParam(name="soggettoFruitore")SoggettoRS soggetto) throws SOAPFault;
    
    @WebMethod(operationName="getFruitoreServizioSpcoopCorrelato")
    SoggettoRSRisposta getFruitoreServizioSpcoopCorrelato(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio,@WebParam(name="soggettoFruitore")SoggettoRS soggetto) throws SOAPFault;
    
    @WebMethod(operationName="getFruitoriServizioSpcoop")
    List<SoggettoRSRisposta> getFruitoriServizioSpcoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault;
    
    @WebMethod(operationName="getFruitoriServizioSpcoopCorrelato")
    List<SoggettoRSRisposta> getFruitoriServizioSpcoopCorrelato(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault;
    
    @WebMethod(operationName="getServizioSPCoop")
    ServizioRSRisposta getServizioSPCoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault;
    
    @WebMethod(operationName="getServizioSPCoopCorrelato")
    ServizioRSRisposta getServizioSPCoopCorrelato(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault;
    
    @WebMethod(operationName="getServizioSPCoopCorrelatoByAccordo")
    ServizioRSRisposta getServizioSPCoopCorrelatoByAccordo(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="soggetto")SoggettoRS soggetto,@WebParam(name="accordoServizio")AccordoServizioRS accordo) throws SOAPFault;
    
    @WebMethod(operationName="getSoggettoSPCoop")
    SoggettoRSRisposta getSoggettoSPCoop(@WebParam(name="indirizzoRegistro" , header=true)String indirizzo,@WebParam(name="soggetto")SoggettoRS soggetto) throws SOAPFault;
}
