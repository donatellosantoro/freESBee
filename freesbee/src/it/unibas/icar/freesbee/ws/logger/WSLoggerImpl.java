package it.unibas.icar.freesbee.ws.logger;

import com.google.inject.Singleton;
import it.unibas.icar.freesbee.persistenza.SOAPFault;
import it.unibas.icar.freesbee.utilita.ParserLogger;
import java.util.Date;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Singleton
@WebService(targetNamespace = "http://icar.unibas.it/freesbee/")
public class WSLoggerImpl implements IWSLogger {

    @WebMethod(operationName = "getLogDaDataLivello")
    public String getLogDaDataLivello(Date inizio, Date fine, String livello) throws SOAPFault {
        ParserLogger instance = ParserLogger.getInstance();
        return instance.getLog(inizio, fine, livello);
    }

    @WebMethod(operationName = "getLogDaLivello")
    public String getLogDaLivello(String livello) throws SOAPFault {
        ParserLogger instance = ParserLogger.getInstance();
        return instance.getLog(livello);
    }

    @WebMethod(operationName = "getLogDaData")
    public String getLogDaData(Date inizio, Date fine) throws SOAPFault {
        ParserLogger instance = ParserLogger.getInstance();
        return instance.getLog(inizio, fine);
    }

    @WebMethod(operationName = "getLogFile")
    public DataHandler getLogFile() throws SOAPFault {
        ParserLogger instance = ParserLogger.getInstance();
        return instance.getLog();
    }
}
