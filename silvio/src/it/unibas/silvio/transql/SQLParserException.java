package it.unibas.silvio.transql;


public class SQLParserException extends Exception {

    public SQLParserException() {
    }

    public SQLParserException(String msg) {
        super(msg);
    }

    public SQLParserException(Exception e) {
        super(e);
    }
}
