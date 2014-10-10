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
 * 2013-12-12T22:13:17.703+01:00
 * Generated source version: 2.7.7
 * 
 */
@WebServiceClient(name = "ServiceSoggetto", 
                  wsdlLocation = "http://localhost:28080/freesbee-Sla/ws/soggetto/ServiceSoggetto?wsdl",
                  targetNamespace = "http://web.ws.freesbeesla.unibas.it/") 
public class ServiceSoggetto_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceSoggetto");
    public final static QName ServiceSoggetto = new QName("http://web.ws.freesbeesla.unibas.it/", "ServiceSoggetto");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:28080/freesbee-Sla/ws/soggetto/ServiceSoggetto?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ServiceSoggetto_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:28080/freesbee-Sla/ws/soggetto/ServiceSoggetto?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ServiceSoggetto_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ServiceSoggetto_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceSoggetto_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     *
     * @return
     *     returns ServiceSoggetto
     */
    @WebEndpoint(name = "ServiceSoggetto")
    public ServiceSoggetto getServiceSoggetto() {
        return super.getPort(ServiceSoggetto, ServiceSoggetto.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServiceSoggetto
     */
    @WebEndpoint(name = "ServiceSoggetto")
    public ServiceSoggetto getServiceSoggetto(WebServiceFeature... features) {
        return super.getPort(ServiceSoggetto, ServiceSoggetto.class, features);
    }

}
