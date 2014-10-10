package it.unibas.freesbeesla.tracciatura.ws.server;

import it.unibas.freesbeesla.tracciatura.ws.server.stub.ResponseTraceSystemType;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.RequestInsertServiceBasicMetricType;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.RequestUpdateServiceTermStateType;
import it.unibas.freesbeesla.tracciatura.ws.server.stub.ResultType;
import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: icarsla.sistematracciatura
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseTraceSystemType }
     * 
     */
    public ResponseTraceSystemType createResponseTraceSystemType() {
        return new ResponseTraceSystemType();
    }

    /**
     * Create an instance of {@link ResultType }
     * 
     */
    public ResultType createResultType() {
        return new ResultType();
    }

    /**
     * Create an instance of {@link RequestInsertServiceBasicMetricType }
     * 
     */
    public RequestInsertServiceBasicMetricType createRequestInsertServiceBasicMetricType() {
        return new RequestInsertServiceBasicMetricType();
    }

    /**
     * Create an instance of {@link RequestUpdateServiceTermStateType }
     * 
     */
    public RequestUpdateServiceTermStateType createRequestUpdateServiceTermStateType() {
        return new RequestUpdateServiceTermStateType();
    }
}
