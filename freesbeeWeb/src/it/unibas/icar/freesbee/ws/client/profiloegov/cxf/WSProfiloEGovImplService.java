package it.unibas.icar.freesbee.ws.client.profiloegov.cxf;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2012-06-22T17:59:26.866+02:00
 * Generated source version: 2.5.2
 * 
 */
@WebServiceClient(name = "WSProfiloEGovImplService", 
                  wsdlLocation = "http://localhost:8191/ws/profiloEGov?wsdl",
                  targetNamespace = "http://web.ws.freesbee.icar.unibas.it/") 
public class WSProfiloEGovImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSProfiloEGovImplService");
    public final static QName WSProfiloEGovImplPort = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSProfiloEGovImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8191/ws/profiloEGov?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WSProfiloEGovImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8191/ws/profiloEGov?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WSProfiloEGovImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSProfiloEGovImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSProfiloEGovImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSProfiloEGovImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSProfiloEGovImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSProfiloEGovImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName);
    }

    /**
     *
     * @return
     *     returns IWSProfiloEGov
     */
    @WebEndpoint(name = "WSProfiloEGovImplPort")
    public IWSProfiloEGov getWSProfiloEGovImplPort() {
        return super.getPort(WSProfiloEGovImplPort, IWSProfiloEGov.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IWSProfiloEGov
     */
    @WebEndpoint(name = "WSProfiloEGovImplPort")
    public IWSProfiloEGov getWSProfiloEGovImplPort(WebServiceFeature... features) {
        return super.getPort(WSProfiloEGovImplPort, IWSProfiloEGov.class, features);
    }

}
