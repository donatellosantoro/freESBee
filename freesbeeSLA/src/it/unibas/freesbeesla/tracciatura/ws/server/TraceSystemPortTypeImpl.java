package it.unibas.freesbeesla.tracciatura.ws.server;

import it.unibas.freesbeesla.tracciatura.persistenza.DAOTracciatura;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.ResponseTraceSystemType;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.RequestInsertServiceBasicMetricType;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.RequestUpdateServiceTermStateType;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.ResultType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@javax.jws.WebService(serviceName = "TraceSystem", portName = "TraceSystem", targetNamespace = "http://sistematracciatura.freesbee.unibas.it/", endpointInterface = "it.unibas.freesbeesla.tracciatura.ws.server.TraceSystemPortType")
public class TraceSystemPortTypeImpl implements TraceSystemPortType {

    private Log logger = LogFactory.getLog(this.getClass());
    private DAOTracciatura daoTracciatura = new DAOTracciatura();

    public ResponseTraceSystemType updateServiceTermState(RequestUpdateServiceTermStateType richiesta) throws SOAPFault {
        logger.info(("Richiesta operazione: updateServiceTermState"));
        logger.debug( richiesta.getServiceState().value());
        ResultType resultType = daoTracciatura.updateServiceTermState(richiesta.getIdService(), richiesta.getIdInitiator(), richiesta.getIdResponder(), richiesta.getServiceState().value());
        ResponseTraceSystemType response = new ResponseTraceSystemType();
        response.setResult(resultType);
        return response;

    }

    public ResponseTraceSystemType insertServiceBasicMetric(RequestInsertServiceBasicMetricType richiesta) throws SOAPFault {
        logger.info("Richiesta operazione: insertServiceBasicMetric");
        ResultType resultType = daoTracciatura.insertServiceBasicMetric(richiesta.getIdService(), richiesta.getIdInitiator(), richiesta.getIdResponder(), richiesta.getBasicMetric(), richiesta.getBasicMetricValue(), richiesta.getDate());
        ResponseTraceSystemType response = new ResponseTraceSystemType();
        response.setResult(resultType);
        return response;

    }
}
