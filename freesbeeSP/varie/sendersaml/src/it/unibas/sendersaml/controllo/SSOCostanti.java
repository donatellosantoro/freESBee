package it.unibas.sendersaml.controllo;

public class SSOCostanti {
    
    // SSOCostanti per SAML
    public static final String SAMLNAMESPACE = "urn:oasis:names:tc:SAML:2.0:assertion";
    public static final String SAMLNAMESPACEPREFIX = "saml";
    
    // SSOCostanti per WSSecurity
    public static final String WSSECURITYNAMESPACE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    public static final String WSSECURITYNAMESPACEPREFIX = "wsse";

    public static final String ESITO_PERMIT = "Permit";
    public static final String ESITO_DENY = "Deny";
    public static final String ESITO_INDETERMINATE = "Indeterminate";
    public static final String ESITO_NOT_APPLICABLE = "NotApplicable";
}
