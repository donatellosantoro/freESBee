package it.unibas.icar.freesbee.exception;

public class FreesbeeException extends Exception {

    private int codiceErroreEGov;

    public FreesbeeException(Exception e) {
        super(e);
    }

    public FreesbeeException(String msg) {
        super(msg);
    }
//    public FreesbeeException(String msg,int codiceErroreEGov) {
//        super(msg);
//        this.codiceErroreEGov = codiceErroreEGov;
//    }
//
//    public int getCodiceErroreEGov() {
//        return codiceErroreEGov;
//    }
}
