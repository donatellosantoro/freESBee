package it.unibas.icar.freesbee.ws.verificainstallazione;

import it.unibas.icar.freesbee.persistenza.SOAPFault;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://icar.unibas.it/freesbee/")
public interface IWSVerificaInstallazione {

    @WebMethod(operationName="verificaInstallazione")
    String verificaInstallazione() throws SOAPFault;

    @WebMethod(operationName="testAttachment")
    DataHandler testAttachment(DataHandler attachment) throws SOAPFault;

}
