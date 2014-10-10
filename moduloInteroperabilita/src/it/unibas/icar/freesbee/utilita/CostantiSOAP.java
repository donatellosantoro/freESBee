package it.unibas.icar.freesbee.utilita;

import javax.xml.namespace.QName;

public class CostantiSOAP {
    
    
    public static final String SOAP_HEADERS = "org.apache.servicemix.soap.headers";
    
    // NAMESPACE XML
    public static final String PREFIX_NAMESPACE_XMLNS = "xmlns";
    public static final String NAMESPACE_XMLNS = "http://www.w3.org/2000/xmlns/";
    
    // NAMESPACE WSADDRESSING
    public static final String PREFIX_WSA = "wsa";
    public static final QName WSA_MESSAGEID = new QName("http://schemas.xmlsoap.org/ws/2004/08/addressing","MessageID");    
    public static final QName WSA_MESSAGERELATESTOID = new QName("http://schemas.xmlsoap.org/ws/2004/08/addressing","RelatesTo");
    
    // NAMESPACE XMLSCHEMA
    public static final String NAMESPACE_XMLSCHEMA = "http://www.w3.org/2001/XMLSchema";
    public static final String PREFIX_XMLSCHEMA = "xsd";

    // NAMESPACE SOAP
    public static final String NAMESPACE_SOAP_ENVELOPE = "http://schemas.xmlsoap.org/soap/envelope/";
    public static final String PREFIX_SOAP_ENVELOPE = "SOAP_ENV";
    
    // NAMESPACE XMLSCHEMAISTANCE
    public static final String NAMESPACE_XMLSCHEMA_INSTANCE = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String PREFIX_XMLSCHEMA_INSTANCE = "xsi";
    
    // COSTANTI BUSTA EGOV
    public static final String ACTOR_EGOV = "http://www.cnipa.it/eGov_it/portadominio";
    public static final String PREFIX_ACTOR_EGOV = "actor";
    
    public static final String NAMESPACE_EGOV = "http://www.cnipa.it/schemas/2003/eGovIT/Busta1_0/";
    public static final String PREFIX_EGOV = "eGov_IT";
    
    public static final String NAMESPACE_EGOV_TEST = "http://icar.unibas.it/freesbee/schemas/Test/";
    public static final String PREFIX_EGOV_TEST = "frsb_Test";

    public static final String CONTESTO_CODIFICA_ECCEZIONE_VALIDAZIONE = "ErroreIntestazioneMessaggioSPCoop";
    public static final String CONTESTO_CODIFICA_ECCEZIONE_PROCESSAMENTO = "ErroreProcessamentoMessaggioSPCoop";
    
    public static final String MUSTUNDERSTAND_TRUE = "1";
    public static final String MUSTUNDERSTAND_FALSE = "0";
    public static final String PREFIX_MUSTUNDERSTAND = "mustUnderstand";

    public static final String PREFIX_EGOV_ERRORE = "EGOV_IT_";
    public static final String INFO = "INFO";
    public static final String LIEVE = "LIEVE";
    public static final String GRAVE = "GRAVE";
    
    public static final String SERVIZIO_NON_TROVATO = "-Servizio non trovato-";
    

}
