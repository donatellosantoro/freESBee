package it.unibas.silvio.util;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;

public class CostantiSOAP {
    public static final String SOAP_VERSION = SOAPConstants.SOAP_1_1_PROTOCOL;
    
    public static final String SOAP_HEADER_LIST = "SOAP_HEADER_LIST";
    public static final String SOAP_ATTACHMENT_LIST = "SOAP_ATTACHMENT_LIST";
    
    // NAMESPACE WSADDRESSING
    public static final String PREFIX_WSA = "wsaw";
    public static final String NAMESPACE_WSA = "http://schemas.xmlsoap.org/ws/2004/08/addressing";
    public static final QName WSA_MESSAGEID = new QName(NAMESPACE_WSA,"MessageID");
    public static final QName WSA_MESSAGERELATESTOID = new QName(NAMESPACE_WSA,"RelatesTo");
    public static final QName WSA_TO = new QName(NAMESPACE_WSA,"To");
    public static final QName WSA_ACTION = new QName(NAMESPACE_WSA,"Action");
    
    public static final QName FAULT_CODE = new QName("http://silvio.unibas.it/", "Server", "ns");
    
    //INTESTAZIONI SILVIO
    public static final QName ISTANZA_TEST = new QName("http://silvio.unibas.it/", "istanza_test", "sil");
    public static final QName NOME_OPERATION = new QName("http://silvio.unibas.it/", "nome_operation", "sil");
    public static final QName INDIRIZZO_ASCOLTO_RISPOSTA = new QName("http://silvio.unibas.it/", "indirizzo_ascolto_risposta", "sil");

    //INTEGRAZIONE - HEADER
    public static final String INTEGRAZIONE_ID_MESSAGGIO = "SPCoopID";
    public static final String INTEGRAZIONE_RIFERIMENTO_MESSAGGIO = "SPCoopRiferimentoMessaggio";

    //INTESTAZIONI - SLA
    public static final String SLA_MITTENTE = "SPCoopMittente";
    public static final String SLA_DESTINATARIO = "SPCoopDestinatario";
    public static final String SLA_SERVIZIO = "SPCoopServizio";

}
