package it.unibas.freesbeesla.monitoraggio.servicetermstate.ws.server;


import it.unibas.freesbeesla.monitoraggio.stub.RequestServiceTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.ResponseServiceTermStateTypeSuper;
import it.unibas.freesbeesla.monitoraggio.stub.SOAPFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "ServiceTermState")
public interface ServiceTermState {

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "responseServiceTermStateTypeSuper", targetNamespace = "http://sistemamonitoraggio.freesbee.unibas.it/", partName = "responseServiceTermStateTypeSuper")
    @WebMethod
    public ResponseServiceTermStateTypeSuper getServiceTermState(
            @WebParam(name = "requestServiceTermStateType", targetNamespace = "http://sistemamonitoraggio.freesbee.unibas.it/", partName = "requestServiceTermStateType") 
            RequestServiceTermStateType richiesta) throws SOAPFault;
}
