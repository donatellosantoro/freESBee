package it.unibas.freesbeesla.ws.web.persistenza.soap;

public class DAOException extends Exception {
    
    public static String ERRORE_METRICA_NON_DEFINITA = "La finestra di osservazione non contiene una metrica definita";
    public static String ERRORE_FINESTRE_OSSERVAZIONE_ANNIDATE = "Sono state rilevate piu' finestre di osservazione annidate";
    public static String ERRORE_ANOMALIA_FINESTRE_OSSERVAZIONE_ANNIDATE = "E' stata rilevata un'anomalia nelle finestre di osservazione annidate";
    public static String ERRORE_VALORI_NON_VALIDI_FINESTRA_OSSERVAZIONE = "Sono stati riscontrati valori non validi nella finestra di osservazione";
    
    public DAOException() {
        super();
    }
    
    public DAOException(String s){
        super(s);
    }
    
    public DAOException(Exception e){
        super(e);
    }
    
}
