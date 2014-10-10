package it.unibas.freesbeesla.monitoraggio.serviceguaranteetermstate.ws.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import it.unibas.freesbeesla.monitoraggio.stub.*;


@WebService(targetNamespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "ServiceGuaranteeTermState")
public interface ServiceGuaranteeTermState {

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "responseServiceGuaranteeTermStateTypeSuper", targetNamespace = "http://sistemamonitoraggio.freesbee.unibas.it/", partName = "responseServiceGuaranteeTermStateTypeSuper")
    @WebMethod
    public ResponseServiceGuaranteeTermStateTypeSuper getServiceGuaranteeTermState (
        @WebParam(partName = "requestServiceGuaranteeTermStateType", name = "requestServiceGuaranteeTermStateType", targetNamespace = "http://sistemamonitoraggio.freesbee.unibas.it/")
        RequestServiceGuaranteeTermStateType richiesta
     
    ) throws SOAPFault;
}
