package it.unibas.icar.freesbee.persistenza.soap;

import javax.xml.namespace.QName;

public class DAOCostanti {

    public static final QName SERVICE_NAME_AUTENTICAZIONE = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSAutenticazioneImplService");
    public final static String URL_WSDL_AUTENTICAZIONE = "autenticazione?wsdl";
    
    public final static QName SERVICE_NAME_SERVIZIO_APPLICATIVO = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSServizioApplicativoImplService");
    public final static String URL_WSDL_SERVIZIO_APPLICATIVO = "applicativo?wsdl";
    
    public static final QName SERVICE_NAME_PROFILO_EGOV = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSProfiloEGovImplService");
    public static final String URL_WSDL_PROFILO_EGOV = "profiloEGov?wsdl";
    
    public static final QName SERVICE_NAME_PORTA_DELEGATA = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSPortaDelegataImplService");
    public static final String URL_WSDL_PORTA_DELEGATA = "portaDelegata?wsdl";
    
    public static final QName SERVICE_NAME_AZIONE = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSAzioneImplService");
    public static final String URL_WSDL_AZIONE = "azione?wsdl";
    
    public static final QName SERVICE_NAME_ACCORDO_SERVIZIO = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSAccordoServizioImplService");
    public static final String URL_WSDL_ACCORDO_SERVIZIO = "accordoServizio?wsdl";
    
    public static final QName SERVICE_NAME_CONFIGURAZIONE = new QName("http://web.ws.freesbee.icar.unibas.it/", "Configurazione");
    public static final String URL_WSDL_CONFIGURAZIONE = "configurazione?wsdl";
    
    public static final QName SERVICE_NAME_SERVIZIO = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSServizioImplService");
    public static final String URL_WSDL_SERVIZIO = "servizio?wsdl";
    
    public static final QName SERVICE_NAME_PORTA_APPLICATIVA = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSPortaApplicativaImplService");
    public static final String URL_WSDL_PORTA_APPLICATIVA = "portaApplicativa?wsdl";
    
    public static final QName SERVICE_NAME_SOGGETTO = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSSoggettoImplService");
    public static final String URL_WSDL_SOGGETTO = "soggetto?wsdl";
}
