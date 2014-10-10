package it.unibas.icar.freesbee.exception;

public class SOAPFaultException extends Exception {

    public SOAPFaultException() {
    }

    public SOAPFaultException(String msg) {
        super(msg);
    }
}
