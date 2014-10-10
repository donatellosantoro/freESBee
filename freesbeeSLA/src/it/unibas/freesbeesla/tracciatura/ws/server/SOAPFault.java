package it.unibas.freesbeesla.tracciatura.ws.server;


import javax.xml.ws.WebFault;

@WebFault()
public class SOAPFault extends Exception {

    protected String code;
    protected String description;

    public SOAPFault() {
    }

    public SOAPFault(String s) {
        description = s;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }
}
