package it.unibas.icar.freesbee.ws.registroservizi;

import com.google.inject.ImplementedBy;
import it.unibas.icar.freesbee.persistenza.DAOException;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;

@ImplementedBy(WSRegistroServiziImpl.class)
@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
@InInterceptors(interceptors={"it.unibas.icar.freesbee.ws.registroservizi.HibernateInterceptor"})
@OutFaultInterceptors(interceptors={"it.unibas.icar.freesbee.ws.registroservizi.HibernateInterceptorFault"})
@SOAPBinding(use=SOAPBinding.Use.LITERAL,style=SOAPBinding.Style.DOCUMENT,parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public interface IWSRegistroServizi {
    
    @WebMethod(operationName="existsAccordoServizio")
    boolean existsAccordoServizio(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="accordoServizio")AccordoServizioRS accordo) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="existsFruitoreServizioSpcoop")
    boolean existsFruitoreServizioSpcoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio,@WebParam(name="soggettoFruitore")SoggettoRS soggettoFruitore) throws SOAPFault, DAOException;

    @WebMethod(operationName="existsFruitoreServizioSpcoopCorrelato")
    boolean existsFruitoreServizioSpcoopCorrelato(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio,@WebParam(name="soggettoFruitore")SoggettoRS soggettoFruitore) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="existsServizioSpcoop")
    boolean existsServizioSpcoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="existsServizioSpcoopCorrelato")
    boolean existsServizioSpcoopCorrelato(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="existsSoggettoSPCoop")
    boolean existsSoggettoSPCoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="soggetto",targetNamespace="http://prova.com")SoggettoRS soggetto) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getAllIdAccordiServizio")
    List<AccordoServizioRS> getAllIdAccordiServizio(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate, @WebParam(name="accordoServizio")AccordoServizioRS accordo) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getAllIdServiziSPCoop")
    List<ServizioRS> getAllIdServiziSPCoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getAllIdServiziSPCoopCorrelati")
    List<ServizioRS> getAllIdServiziSPCoopCorrelati(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getAllIdSoggettiSPCoop")
    List<SoggettoRSRisposta> getAllIdSoggettiSPCoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="soggetto")SoggettoRS soggetto) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getAccordoServizio")
    AccordoServizioRSRisposta getAccordoServizio(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="accordoServizio")AccordoServizioRS accordo) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getFruitoreServizioSpcoop")
    SoggettoRSRisposta getFruitoreServizioSpcoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio,@WebParam(name="soggettoFruitore")SoggettoRS soggetto) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getFruitoreServizioSpcoopCorrelato")
    SoggettoRSRisposta getFruitoreServizioSpcoopCorrelato(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio,@WebParam(name="soggettoFruitore")SoggettoRS soggetto) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getFruitoriServizioSpcoop")
    List<SoggettoRSRisposta> getFruitoriServizioSpcoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getFruitoriServizioSpcoopCorrelato")
    List<SoggettoRSRisposta> getFruitoriServizioSpcoopCorrelato(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="maxDate")Date maxDate, @WebParam(name="minDate")Date minDate,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getServizioSPCoop")
    ServizioRSRisposta getServizioSPCoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getServizioSPCoopCorrelato")
    ServizioRSRisposta getServizioSPCoopCorrelato(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="servizio")ServizioRS servizio) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getServizioSPCoopCorrelatoByAccordo")
    ServizioRSRisposta getServizioSPCoopCorrelatoByAccordo(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="soggetto")SoggettoRS soggetto,@WebParam(name="accordoServizio")AccordoServizioRS accordo) throws SOAPFault, DAOException;
    
    @WebMethod(operationName="getSoggettoSPCoop")
    SoggettoRSRisposta getSoggettoSPCoop(@WebParam(name="indirizzoRegistro",header=true)String indirizzo,@WebParam(name="soggetto")SoggettoRS soggetto) throws SOAPFault, DAOException;
}
