package it.unibas.icar.freesbeesp.modello;

import org.jdom.Element;

public class Utente {
    
    private String nomeUtente;
    private String password;
    private Element portafoglioAsserzioni;
    private String silSessionId;
    private String ssoSession;

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Element getPortafoglioAsserzioni() {
        return portafoglioAsserzioni;
    }

    public void setPortafoglioAsserzioni(Element portafoglioAsserzioni) {
        this.portafoglioAsserzioni = portafoglioAsserzioni;
    }

    public String getSilSessionId() {
        return silSessionId;
    }

    public void setSilSessionId(String silSessionId) {
        this.silSessionId = silSessionId;
    }

    public String getSsoSession() {
        return ssoSession;
    }

    public void setSsoSession(String shibSession) {
        this.ssoSession = shibSession;
    }
    
}
