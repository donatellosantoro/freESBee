
package it.unibas.freesbee.websla.ws.web.stub;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.7
 * 2013-12-12T23:04:16.149+01:00
 * Generated source version: 2.7.7
 * 
 */
@WebServiceClient(name = "ServiceServizio", 
                  wsdlLocation = "http://localhost:28080/freesbee-Sla/ws/servizio/ServiceServizio?wsdl",
                  targetNamespace = "http://web.ws.freesbeesla.unibas.it/") 
public class ServiceServizio_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceServizio");
    public final static QName ServiceServizio = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceServizio");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:28080/freesbee-Sla/ws/servizio/ServiceServizio?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ServiceServizio_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:28080/freesbee-Sla/ws/servizio/ServiceServizio?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ServiceServizio_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ServiceServizio_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceServizio_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     *
     * @return
     *     returns ServiceServizio
     */
    @WebEndpoint(name = "ServiceServizio")
    public ServiceServizio getServiceServizio() {
        return super.getPort(ServiceServizio, ServiceServizio.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServiceServizio
     */
    @WebEndpoint(name = "ServiceServizio")
    public ServiceServizio getServiceServizio(WebServiceFeature... features) {
        return super.getPort(ServiceServizio, ServiceServizio.class, features);
    }

}
