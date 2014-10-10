package it.unibas.freesbee.monitoraggio.exception.inf2exception;

public class INF2ResourceNotFoundException extends Exception {

    private static final long serialVersionUID = -3389472718186156999L;
    public static String MSG_ERROR_001 = "INF2_ERROR_001: INF2 record non trovato ";
    public static String MSG_ERROR_002 = "INF2_ERROR_002: INF2 guarantee term non trovato ";
    public static String MSG_ERROR_003 = "INF2_ERROR_003: INF2 occorrenza di servizio non presente ";
    private String messaggio = "";

    public INF2ResourceNotFoundException(String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }

    @Override
    public String getMessage() {
        return messaggio;
    }
}
