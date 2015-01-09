package it.unibas.icar.freesbee.utilita;

import javax.xml.namespace.QName;
import org.apache.camel.Exchange;
import org.apache.servicemix.soap.marshalers.SoapMarshaler;

public class CostantiSOAP {

    public static final String SCHEMA_INTESTAZIONI_EGOV = "IntestazioniEGov.xsd";
    public static final String JETTY_QUERY_STRING = Exchange.HTTP_QUERY;
    public static final String FRUITORE_QUERY_STRING = "FRUITORE";
    public static final String FRUITORE_TIPO_QUERY_STRING = "TIPO_FRUITORE";
    public static final String EROGATORE_QUERY_STRING = "EROGATORE";
    public static final String EROGATORE_TIPO_QUERY_STRING = "TIPO_EROGATORE";
    public static final String SERVIZIO_QUERY_STRING = "SERVIZIO";
    public static final String SERVIZIO_TIPO_QUERY_STRING = "TIPO_SERVIZIO";
    public static final String AZIONE_QUERY_STRING = "AZIONE";

    public static final String SOAP_HEADERS = "org.apache.servicemix.soap.headers";
//  public static final String SOAP_VERSION = SoapMarshaler.SOAP_12_URI; //SOAP 1.2 - OPENSPCOOP NON LO GESTISCE
    public static final String SOAP_VERSION = SoapMarshaler.SOAP_11_URI;
    public static final String SOAP_ATTACHMENT = "SOAP_ATTACHMENT";
    public static final String SOAP_FAULT= "SOAP_FAULT";
    
    // NAMESPACE XML
    public static final String PREFIX_NAMESPACE_XMLNS = "xmlns";
    public static final String NAMESPACE_XMLNS = "http://www.w3.org/2000/xmlns/";
    
    // NAMESPACE WSADDRESSING
    public static final String PREFIX_WSA = "wsa";
    public static final String NAMESPACE_WSA = "http://schemas.xmlsoap.org/ws/2004/08/addressing";
    public static final QName WSA_MESSAGEID = new QName(NAMESPACE_WSA,"MessageID");
    public static final QName WSA_MESSAGERELATESTOID = new QName(NAMESPACE_WSA,"RelatesTo");
    
    // NAMESPACE INTEGRATIONMANAGER
    public static final String PREFIX_IM = "openspcoop";
    public static final QName IM_HEADER = new QName("http://www.openspcoop.org/integrazione","integrazione");    
    
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
    
    //INTEGRAZIONE - HEADER
    //Con Camle 2.9 vengono salvate in lower case. Quindi li confrontiamo con ignoreCase
    public static final String INTEGRAZIONE_ID_MESSAGGIO = "SPCoopID";
    public static final String INTEGRAZIONE_RIFERIMENTO_MESSAGGIO = "SPCoopRiferimentoMessaggio";
    
    public static final String SOAP_HEADER_MESSAGE_EXCEPTION = "messaggio.eccezione";
    

}
