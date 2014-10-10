package it.unibas.icar.freesbeesp.sso;

import org.opensaml.xml.util.Base64;

public class SAMLResponse {

    private String samlResponse;
    private String indirizzo;
    private String relayState;

    public SAMLResponse() {
    }

    public SAMLResponse(String samlResponse, String indirizzo, String relayState) {
        this.samlResponse = samlResponse;
        this.indirizzo = indirizzo;
        this.relayState = relayState;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getRelayState() {
        return relayState;
    }

    public void setRelayState(String relayState) {
        this.relayState = relayState;
    }

    public String getSamlResponse() {
        return samlResponse;
    }

    public void setSamlResponse(String samlResponse) {
        this.samlResponse = samlResponse;
    }
    

    public String getSamlResponseDecoded() {
        if(samlResponse==null){
            return "NULL";
        }
        return new String(Base64.decode(samlResponse));
    }

    public String toString() {
        String s = "SAML Response";
        s += "\nSamlResponse " + getSamlResponseDecoded();
        s += "\nrelayState " + relayState;
        s += "\nindirizzo " + indirizzo;
        return s;
    }
}
