package it.unibas.freesbee.monitoraggio.exception.inf2exception;

public class INF2NotDeterminatedAlgorithmException extends Exception {

    private static final long serialVersionUID = -1418878297616382901L;
    public static String MSG_ERROR_001 = "INF2_ERROR_001: impossibile determinare l'algoritmo dati non presenti";
    public static String MSG_ERROR_002 = "INF2_ERROR_002: impossibile determinare l'algoritmo divisore 0";
    private String messaggio = "";

    public INF2NotDeterminatedAlgorithmException(String messaggio) {
        super();
        this.messaggio = messaggio;
    }

    @Override
    public String getMessage() {
        return messaggio;
    }
}
