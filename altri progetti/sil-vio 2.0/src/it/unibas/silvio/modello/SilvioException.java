package it.unibas.silvio.modello;

public class SilvioException extends Exception {

    public SilvioException() {
    }

    public SilvioException(String msg) {
        super(msg);
    }

    public SilvioException(Exception e) {
        super(e);
    }
}
