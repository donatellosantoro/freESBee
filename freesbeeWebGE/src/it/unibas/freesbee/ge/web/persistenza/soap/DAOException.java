package it.unibas.freesbee.ge.web.persistenza.soap;

public class DAOException extends Exception {

    public DAOException() {
        super();
    }

    public DAOException(String s){
        super(s);
    }

    public DAOException(Exception e){
        super(e);
    }

    public DAOException(Throwable e){
        super(e);
    }

}
