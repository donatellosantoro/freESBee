package it.unibas.freesbee.monitoraggio.exception.inf2exception;

public class INF2SystemException extends Exception {

    private static final long serialVersionUID = -8801445005418841991L;
    public static String MSG_ERROR_001 = "INF2_ERROR_001: INF2 errore di sistema ";
    private String messaggio = "";

    public INF2SystemException(String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }

    @Override
    public String getMessage() {
        return messaggio;
    }
}
