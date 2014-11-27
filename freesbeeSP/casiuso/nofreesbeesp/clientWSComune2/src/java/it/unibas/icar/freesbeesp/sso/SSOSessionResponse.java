package it.unibas.icar.freesbeesp.sso;

public class SSOSessionResponse {

    private String session;
    private String indirizzo;
    private org.jdom.Element elementAssertion;

    public SSOSessionResponse() {
    }

    public SSOSessionResponse(String session, String indirizzo) {
        this.session = session;
        this.indirizzo = indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public org.jdom.Element getElementAssertion() {
        return elementAssertion;
    }

    public void setElementAssertion(org.jdom.Element elementAssertion) {
        this.elementAssertion = elementAssertion;
    }

    @Override
    public String toString() {
        String s = "Shibboleth Session\n";
        s += " Session: " + session + "\n";
        s += " Indirizzo: " + indirizzo + "\n";
        return s;
    }
}
