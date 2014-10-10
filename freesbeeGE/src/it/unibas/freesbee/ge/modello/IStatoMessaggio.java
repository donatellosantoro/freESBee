package it.unibas.freesbee.ge.modello;

public interface IStatoMessaggio {
    // notifica

    public static final String IN_CODA_NOTIFICA = "IN_CODA_NOTIFICA";
    public static final String ERRORE_NOTIFICA = "ERRORE_NOTIFICA";
    public static final String NOTIFICATO = "NOTIFICATO";

    // consegna
    public static final String IN_CODA_CONSEGNA = "IN_CODA_CONSEGNA";
    public static final String ERRORE_CONSEGNA = "ERRORE_CONSEGNA";
    public static final String CONSEGNATO = "CONSEGNATO";

    public static final String IN_CODA_PUBBLICA = "IN_CODA_PUBBLICA";
    public static final String ERRORE_PUBBLICA = "ERRORE_PUBBLICA";
    public static final String PUBBLICATO = "PUBBLICATO";


    IEventoPubblicato getIEventoPubblicato();
}
