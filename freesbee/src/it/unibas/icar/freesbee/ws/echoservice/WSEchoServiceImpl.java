package it.unibas.icar.freesbee.ws.echoservice;

import it.unibas.icar.freesbee.persistenza.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

//@Singleton
@WebService(targetNamespace = "http://icar.unibas.it/freesbee/")
public class WSEchoServiceImpl implements IWSEchoService {

//    private static Log logger = LogFactory.getLog(WSEchoServiceImpl.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WSEchoServiceImpl.class.getName());

    @WebMethod(operationName = "echoService")
    public String echoService(String id, String payload) throws SOAPFault {
        return id + "|" + payload;
    }
}
