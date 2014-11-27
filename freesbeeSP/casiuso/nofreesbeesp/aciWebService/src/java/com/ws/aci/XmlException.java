package com.ws.aci;

public class XmlException extends Exception {

    public XmlException() {
    }

    public XmlException(String msg) {
        super(msg);
    }

    public XmlException(Exception e) {
        super(e);
    }
}
