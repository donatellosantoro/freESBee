package it.unibas.freesbee.websla.persistenza.soap;

import javax.xml.namespace.QName;

public class DAOCostanti {

    public static final String URL_WSDL = "freesbee-Sla";

    public static final String URL_WSDL_AUTENTICAZIONE = URL_WSDL + "/ws/autenticazione?wsdl";
    public static final QName SERVICE_NAME_AUTENTICAZIONE = new QName("http://web.ws.freesbeesla.unibas.it/", "WSAutenticazioneImplService");

    public static final String URL_WSDL_CONFIGURAZIONE = URL_WSDL + "/ws/configurazione/ServiceConfigura?wsdl";
    public static final QName SERVICE_NAME_CONFIGURAZIONE = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceConfigura");
    
    public static final String URL_WSDL_SOGGETTO = URL_WSDL + "/ws/soggetto/ServiceSoggetto?wsdl";
    public static final QName SERVICE_NAME_SOGGETTO = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceSoggetto");    
    
    public static final String URL_WSDL_SERVIZIO = URL_WSDL + "/ws/servizio/ServiceServizio?wsdl";
    public static final QName SERVICE_NAME_SERVIZIO = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceServizio");    
    
    
    public static final String URL_WSDL_MONITORAGGIO_STATISTICHE = URL_WSDL + "/ws/monitoraggio/ServiceMonitoraggioStatistiche?wsdl";
    public static final QName SERVICE_NAME_MONITORAGGIO_STATISTICHE = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceMonitoraggioStatistiche");
    
    public static final String URL_WSDL_GUARANTEE_TERM_STATE = URL_WSDL + "/ws/monitoraggio/guaranteetermstate/ServiceGuaranteeTermState?wsdl";
    public static final QName SERVICE_NAME_GUARANTEE_TERM_STATE  = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "ServiceGuaranteeTermState");
}