
package it.unibas.icar.freesbee.ws.client.profiloegov.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.profiloegov.cxf package. 
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

    private final static QName _GetProfiloEGov_QNAME = new QName("http://icar.unibas.it/freesbee/", "getProfiloEGov");
    private final static QName _GetListaProfiliEGov_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaProfiliEGov");
    private final static QName _GetListaProfiliEGovResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaProfiliEGovResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _AddProfiloEGovResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addProfiloEGovResponse");
    private final static QName _GetProfiloEGovResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getProfiloEGovResponse");
    private final static QName _RemoveProfiloEGov_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeProfiloEGov");
    private final static QName _AddProfiloEGov_QNAME = new QName("http://icar.unibas.it/freesbee/", "addProfiloEGov");
    private final static QName _RemoveProfiloEGovResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeProfiloEGovResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.profiloegov.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetListaProfiliEGovResponse }
     * 
     */
    public GetListaProfiliEGovResponse createGetListaProfiliEGovResponse() {
        return new GetListaProfiliEGovResponse();
    }

    /**
     * Create an instance of {@link GetListaProfiliEGov }
     * 
     */
    public GetListaProfiliEGov createGetListaProfiliEGov() {
        return new GetListaProfiliEGov();
    }

    /**
     * Create an instance of {@link GetProfiloEGov }
     * 
     */
    public GetProfiloEGov createGetProfiloEGov() {
        return new GetProfiloEGov();
    }

    /**
     * Create an instance of {@link RemoveProfiloEGovResponse }
     * 
     */
    public RemoveProfiloEGovResponse createRemoveProfiloEGovResponse() {
        return new RemoveProfiloEGovResponse();
    }

    /**
     * Create an instance of {@link AddProfiloEGov }
     * 
     */
    public AddProfiloEGov createAddProfiloEGov() {
        return new AddProfiloEGov();
    }

    /**
     * Create an instance of {@link RemoveProfiloEGov }
     * 
     */
    public RemoveProfiloEGov createRemoveProfiloEGov() {
        return new RemoveProfiloEGov();
    }

    /**
     * Create an instance of {@link GetProfiloEGovResponse }
     * 
     */
    public GetProfiloEGovResponse createGetProfiloEGovResponse() {
        return new GetProfiloEGovResponse();
    }

    /**
     * Create an instance of {@link AddProfiloEGovResponse }
     * 
     */
    public AddProfiloEGovResponse createAddProfiloEGovResponse() {
        return new AddProfiloEGovResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link ProfiloEGov }
     * 
     */
    public ProfiloEGov createProfiloEGov() {
        return new ProfiloEGov();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProfiloEGov }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getProfiloEGov")
    public JAXBElement<GetProfiloEGov> createGetProfiloEGov(GetProfiloEGov value) {
        return new JAXBElement<GetProfiloEGov>(_GetProfiloEGov_QNAME, GetProfiloEGov.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaProfiliEGov }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaProfiliEGov")
    public JAXBElement<GetListaProfiliEGov> createGetListaProfiliEGov(GetListaProfiliEGov value) {
        return new JAXBElement<GetListaProfiliEGov>(_GetListaProfiliEGov_QNAME, GetListaProfiliEGov.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaProfiliEGovResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaProfiliEGovResponse")
    public JAXBElement<GetListaProfiliEGovResponse> createGetListaProfiliEGovResponse(GetListaProfiliEGovResponse value) {
        return new JAXBElement<GetListaProfiliEGovResponse>(_GetListaProfiliEGovResponse_QNAME, GetListaProfiliEGovResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProfiloEGovResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addProfiloEGovResponse")
    public JAXBElement<AddProfiloEGovResponse> createAddProfiloEGovResponse(AddProfiloEGovResponse value) {
        return new JAXBElement<AddProfiloEGovResponse>(_AddProfiloEGovResponse_QNAME, AddProfiloEGovResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProfiloEGovResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getProfiloEGovResponse")
    public JAXBElement<GetProfiloEGovResponse> createGetProfiloEGovResponse(GetProfiloEGovResponse value) {
        return new JAXBElement<GetProfiloEGovResponse>(_GetProfiloEGovResponse_QNAME, GetProfiloEGovResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveProfiloEGov }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeProfiloEGov")
    public JAXBElement<RemoveProfiloEGov> createRemoveProfiloEGov(RemoveProfiloEGov value) {
        return new JAXBElement<RemoveProfiloEGov>(_RemoveProfiloEGov_QNAME, RemoveProfiloEGov.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProfiloEGov }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addProfiloEGov")
    public JAXBElement<AddProfiloEGov> createAddProfiloEGov(AddProfiloEGov value) {
        return new JAXBElement<AddProfiloEGov>(_AddProfiloEGov_QNAME, AddProfiloEGov.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveProfiloEGovResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeProfiloEGovResponse")
    public JAXBElement<RemoveProfiloEGovResponse> createRemoveProfiloEGovResponse(RemoveProfiloEGovResponse value) {
        return new JAXBElement<RemoveProfiloEGovResponse>(_RemoveProfiloEGovResponse_QNAME, RemoveProfiloEGovResponse.class, null, value);
    }

}
