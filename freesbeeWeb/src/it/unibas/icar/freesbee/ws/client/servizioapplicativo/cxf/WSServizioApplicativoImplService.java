package it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.2 2012-06-22T18:05:18.377+02:00
 * Generated source version: 2.5.2
 *
 */
@WebServiceClient(name = "WSServizioApplicativoImplService",
wsdlLocation = "http://localhost:8191/ws/applicativo?wsdl",
targetNamespace = "http://web.ws.freesbee.icar.unibas.it/")
public class WSServizioApplicativoImplService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSServizioApplicativoImplService");
    public final static QName WSServizioApplicativoImplPort = new QName("http://web.ws.freesbee.icar.unibas.it/", "WSServizioApplicativoImplPort");

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8191/ws/applicativo?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WSServizioApplicativoImplService.class.getName()).log(java.util.logging.Level.INFO,
                    "Can not initialize the default wsdl from {0}", "http://localhost:8191/ws/applicativo?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WSServizioApplicativoImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSServizioApplicativoImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSServizioApplicativoImplService() {
        super(WSDL_LOCATION, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSServizioApplicativoImplService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSServizioApplicativoImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSServizioApplicativoImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName);
    }

    /**
     *
     * @return returns IWSServizioApplicativo
     */
    @WebEndpoint(name = "WSServizioApplicativoImplPort")
    public IWSServizioApplicativo getWSServizioApplicativoImplPort() {
        return super.getPort(WSServizioApplicativoImplPort, IWSServizioApplicativo.class);
    }

    /**
     *
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to
     * configure on the proxy. Supported features not in the
     * <code>features</code> parameter will have their default values.
     * @return returns IWSServizioApplicativo
     */
    @WebEndpoint(name = "WSServizioApplicativoImplPort")
    public IWSServizioApplicativo getWSServizioApplicativoImplPort(WebServiceFeature... features) {
        return super.getPort(WSServizioApplicativoImplPort, IWSServizioApplicativo.class, features);
    }
}