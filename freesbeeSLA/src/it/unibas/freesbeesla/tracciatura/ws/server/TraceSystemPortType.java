package it.unibas.freesbeesla.tracciatura.ws.server;

import it.unibas.freesbeesla.tracciatura.ws.server.stub.ResponseTraceSystemType;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.RequestInsertServiceBasicMetricType;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.RequestUpdateServiceTermStateType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://sistematracciatura.freesbee.unibas.it/", name = "TraceSystemPortType")
@SOAPBinding(use = SOAPBinding.Use.ENCODED, parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface TraceSystemPortType {

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "risposta", targetNamespace = "", partName = "risposta")
    @WebMethod(operationName = "update_service_term_state")
    public ResponseTraceSystemType updateServiceTermState(
            
            @WebParam(partName = "richiesta", name = "richiesta", targetNamespace = "") RequestUpdateServiceTermStateType richiesta) throws SOAPFault;

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "rispostaInsert", targetNamespace = "", partName = "rispostaInsert")
    @WebMethod(operationName = "insert_service_basic_metric")
    public ResponseTraceSystemType insertServiceBasicMetric(
            
            @WebParam(partName = "richiestaInsert", name = "richiestaInsert", targetNamespace = "") RequestInsertServiceBasicMetricType richiesta) throws SOAPFault;
}
