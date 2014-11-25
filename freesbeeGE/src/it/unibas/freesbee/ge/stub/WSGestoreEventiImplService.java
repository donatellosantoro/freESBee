
package it.unibas.freesbee.ge.stub;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import it.unibas.freesbee.ge.ws.gestoreeventi.IWSGestoreEventi;

/**
 * This class was generated by Apache CXF (incubator) 2.0.4-incubator
 * Wed Jul 29 02:33:58 CEST 2009
 * Generated source version: 2.0.4-incubator
 * 
 */

@WebServiceClient(name = "WSGestoreEventiImplService", targetNamespace = "http://ge.freesbee.unibas.it/", wsdlLocation = "file:gestoreEventi.wsdl")
public class WSGestoreEventiImplService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://ge.freesbee.unibas.it/", "WSGestoreEventiImplService");
    public final static QName WSGestoreEventiImplPort = new QName("http://ge.freesbee.unibas.it/", "WSGestoreEventiImplPort");
    static {
        URL url = null;
        try {
            url = new URL("file:gestoreEventi.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:gestoreEventi.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public WSGestoreEventiImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSGestoreEventiImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSGestoreEventiImplService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns WSGestoreEventiImplPort
     */
    @WebEndpoint(name = "WSGestoreEventiImplPort")
    public IWSGestoreEventi getWSGestoreEventiImplPort() {
        return (IWSGestoreEventi)super.getPort(WSGestoreEventiImplPort, IWSGestoreEventi.class);
    }

}