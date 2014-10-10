
package it.unibas.icar.freesbee.ws.client.servizio.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.servizio.cxf package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetListaServizi_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaServizi");
    private final static QName _GetServizio_QNAME = new QName("http://icar.unibas.it/freesbee/", "getServizio");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _RemoveServizio_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeServizio");
    private final static QName _RemoveServizioResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeServizioResponse");
    private final static QName _GetServizioResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getServizioResponse");
    private final static QName _AddServizio_QNAME = new QName("http://icar.unibas.it/freesbee/", "addServizio");
    private final static QName _AddServizioResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addServizioResponse");
    private final static QName _GetListaServiziResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaServiziResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.servizio.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetServizio }
     * 
     */
    public GetServizio createGetServizio() {
        return new GetServizio();
    }

    /**
     * Create an instance of {@link GetListaServizi }
     * 
     */
    public GetListaServizi createGetListaServizi() {
        return new GetListaServizi();
    }

    /**
     * Create an instance of {@link GetListaServiziResponse }
     * 
     */
    public GetListaServiziResponse createGetListaServiziResponse() {
        return new GetListaServiziResponse();
    }

    /**
     * Create an instance of {@link AddServizioResponse }
     * 
     */
    public AddServizioResponse createAddServizioResponse() {
        return new AddServizioResponse();
    }

    /**
     * Create an instance of {@link AddServizio }
     * 
     */
    public AddServizio createAddServizio() {
        return new AddServizio();
    }

    /**
     * Create an instance of {@link GetServizioResponse }
     * 
     */
    public GetServizioResponse createGetServizioResponse() {
        return new GetServizioResponse();
    }

    /**
     * Create an instance of {@link RemoveServizioResponse }
     * 
     */
    public RemoveServizioResponse createRemoveServizioResponse() {
        return new RemoveServizioResponse();
    }

    /**
     * Create an instance of {@link RemoveServizio }
     * 
     */
    public RemoveServizio createRemoveServizio() {
        return new RemoveServizio();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link Servizio }
     * 
     */
    public Servizio createServizio() {
        return new Servizio();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaServizi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaServizi")
    public JAXBElement<GetListaServizi> createGetListaServizi(GetListaServizi value) {
        return new JAXBElement<GetListaServizi>(_GetListaServizi_QNAME, GetListaServizi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getServizio")
    public JAXBElement<GetServizio> createGetServizio(GetServizio value) {
        return new JAXBElement<GetServizio>(_GetServizio_QNAME, GetServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SOAPFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "SOAPFault")
    public JAXBElement<SOAPFault> createSOAPFault(SOAPFault value) {
        return new JAXBElement<SOAPFault>(_SOAPFault_QNAME, SOAPFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeServizio")
    public JAXBElement<RemoveServizio> createRemoveServizio(RemoveServizio value) {
        return new JAXBElement<RemoveServizio>(_RemoveServizio_QNAME, RemoveServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveServizioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeServizioResponse")
    public JAXBElement<RemoveServizioResponse> createRemoveServizioResponse(RemoveServizioResponse value) {
        return new JAXBElement<RemoveServizioResponse>(_RemoveServizioResponse_QNAME, RemoveServizioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServizioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getServizioResponse")
    public JAXBElement<GetServizioResponse> createGetServizioResponse(GetServizioResponse value) {
        return new JAXBElement<GetServizioResponse>(_GetServizioResponse_QNAME, GetServizioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addServizio")
    public JAXBElement<AddServizio> createAddServizio(AddServizio value) {
        return new JAXBElement<AddServizio>(_AddServizio_QNAME, AddServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddServizioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addServizioResponse")
    public JAXBElement<AddServizioResponse> createAddServizioResponse(AddServizioResponse value) {
        return new JAXBElement<AddServizioResponse>(_AddServizioResponse_QNAME, AddServizioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaServiziResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaServiziResponse")
    public JAXBElement<GetListaServiziResponse> createGetListaServiziResponse(GetListaServiziResponse value) {
        return new JAXBElement<GetListaServiziResponse>(_GetListaServiziResponse_QNAME, GetListaServiziResponse.class, null, value);
    }

}
