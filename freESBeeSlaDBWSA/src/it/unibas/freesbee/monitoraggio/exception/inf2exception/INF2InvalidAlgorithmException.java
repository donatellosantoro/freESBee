package it.unibas.freesbee.monitoraggio.exception.inf2exception;

public class INF2InvalidAlgorithmException extends Exception {

    private static final long serialVersionUID = -4139399918160596384L;
    public static String MSG_ERROR_001 = "INF2_ERROR_001: impossibile calcolare l'algoritmo dati non omogenei";
    public static String MSG_ERROR_003 = "INF2_ERROR_003: impossibile calcolare l'algoritmo";
    private String messaggio = "";

    public INF2InvalidAlgorithmException(String messaggio) {
        super();
        this.messaggio = messaggio;
    }

    @Override
    public String getMessage() {
        return messaggio;
    }
}
