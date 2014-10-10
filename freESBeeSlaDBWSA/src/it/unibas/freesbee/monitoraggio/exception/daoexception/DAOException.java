package it.unibas.freesbee.monitoraggio.exception.daoexception;

public class DAOException extends Exception {
    
    public DAOException() {
        super();
    }
    
    public DAOException(String s) {
        super(s);
    }
    
    public DAOException(Exception e) {
        super(e);
    }    

}
