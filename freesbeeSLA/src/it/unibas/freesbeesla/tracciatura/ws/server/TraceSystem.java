package it.unibas.freesbeesla.tracciatura.ws.server;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

@WebServiceClient(name = "TraceSystem", targetNamespace = "http://sistematracciatura.freesbee.unibas.it/", wsdlLocation = "file:TraceSystem.wsdl")
public class TraceSystem extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://sistematracciatura.freesbee.unibas.it/", "TraceSystem");
    public final static QName TraceSystem = new QName("http://sistematracciatura.freesbee.unibas.it/", "TraceSystem");

    static {
        URL url = null;
        try {
            url = new URL("file:TraceSystem.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:TraceSystem.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public TraceSystem(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public TraceSystem(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TraceSystem() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns TraceSystem
     */
    @WebEndpoint(name = "TraceSystem")
    public TraceSystemPortType getTraceSystem() {
        return (TraceSystemPortType) super.getPort(TraceSystem, TraceSystemPortType.class);
    }
}
