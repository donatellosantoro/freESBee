package it.unibas.icar.freesbee.ws.logger;

import it.unibas.icar.freesbee.persistenza.SOAPFault;
import java.util.Date;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://icar.unibas.it/freesbee/")
public interface IWSLogger {

    @WebMethod(operationName = "getLogDaDataLivello")
    String getLogDaDataLivello(Date inizio, Date fine, String livello) throws SOAPFault;

    @WebMethod(operationName = "getLogDaLivello")
    String getLogDaLivello(String livello) throws SOAPFault;

    @WebMethod(operationName = "getLogDaData")
    String getLogDaData(Date inizio, Date fine) throws SOAPFault;

    @WebMethod(operationName = "getLogFile")
    DataHandler getLogFile() throws SOAPFault;
}
