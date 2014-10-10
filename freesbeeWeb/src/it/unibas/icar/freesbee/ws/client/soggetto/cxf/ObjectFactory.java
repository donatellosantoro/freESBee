
package it.unibas.icar.freesbee.ws.client.soggetto.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.soggetto.cxf package. 
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

    private final static QName _GetListaSoggetti_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaSoggetti");
    private final static QName _GetListaSoggettiResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaSoggettiResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _AddSoggetto_QNAME = new QName("http://icar.unibas.it/freesbee/", "addSoggetto");
    private final static QName _GetSoggetto_QNAME = new QName("http://icar.unibas.it/freesbee/", "getSoggetto");
    private final static QName _AddSoggettoResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addSoggettoResponse");
    private final static QName _GetSoggettoResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getSoggettoResponse");
    private final static QName _RemoveSoggetto_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeSoggetto");
    private final static QName _RemoveSoggettoResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeSoggettoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.soggetto.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetListaSoggettiResponse }
     * 
     */
    public GetListaSoggettiResponse createGetListaSoggettiResponse() {
        return new GetListaSoggettiResponse();
    }

    /**
     * Create an instance of {@link GetListaSoggetti }
     * 
     */
    public GetListaSoggetti createGetListaSoggetti() {
        return new GetListaSoggetti();
    }

    /**
     * Create an instance of {@link RemoveSoggettoResponse }
     * 
     */
    public RemoveSoggettoResponse createRemoveSoggettoResponse() {
        return new RemoveSoggettoResponse();
    }

    /**
     * Create an instance of {@link RemoveSoggetto }
     * 
     */
    public RemoveSoggetto createRemoveSoggetto() {
        return new RemoveSoggetto();
    }

    /**
     * Create an instance of {@link GetSoggettoResponse }
     * 
     */
    public GetSoggettoResponse createGetSoggettoResponse() {
        return new GetSoggettoResponse();
    }

    /**
     * Create an instance of {@link AddSoggettoResponse }
     * 
     */
    public AddSoggettoResponse createAddSoggettoResponse() {
        return new AddSoggettoResponse();
    }

    /**
     * Create an instance of {@link GetSoggetto }
     * 
     */
    public GetSoggetto createGetSoggetto() {
        return new GetSoggetto();
    }

    /**
     * Create an instance of {@link AddSoggetto }
     * 
     */
    public AddSoggetto createAddSoggetto() {
        return new AddSoggetto();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link Soggetto }
     * 
     */
    public Soggetto createSoggetto() {
        return new Soggetto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaSoggetti }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaSoggetti")
    public JAXBElement<GetListaSoggetti> createGetListaSoggetti(GetListaSoggetti value) {
        return new JAXBElement<GetListaSoggetti>(_GetListaSoggetti_QNAME, GetListaSoggetti.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaSoggettiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaSoggettiResponse")
    public JAXBElement<GetListaSoggettiResponse> createGetListaSoggettiResponse(GetListaSoggettiResponse value) {
        return new JAXBElement<GetListaSoggettiResponse>(_GetListaSoggettiResponse_QNAME, GetListaSoggettiResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AddSoggetto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addSoggetto")
    public JAXBElement<AddSoggetto> createAddSoggetto(AddSoggetto value) {
        return new JAXBElement<AddSoggetto>(_AddSoggetto_QNAME, AddSoggetto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSoggetto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getSoggetto")
    public JAXBElement<GetSoggetto> createGetSoggetto(GetSoggetto value) {
        return new JAXBElement<GetSoggetto>(_GetSoggetto_QNAME, GetSoggetto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddSoggettoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addSoggettoResponse")
    public JAXBElement<AddSoggettoResponse> createAddSoggettoResponse(AddSoggettoResponse value) {
        return new JAXBElement<AddSoggettoResponse>(_AddSoggettoResponse_QNAME, AddSoggettoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSoggettoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getSoggettoResponse")
    public JAXBElement<GetSoggettoResponse> createGetSoggettoResponse(GetSoggettoResponse value) {
        return new JAXBElement<GetSoggettoResponse>(_GetSoggettoResponse_QNAME, GetSoggettoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSoggetto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeSoggetto")
    public JAXBElement<RemoveSoggetto> createRemoveSoggetto(RemoveSoggetto value) {
        return new JAXBElement<RemoveSoggetto>(_RemoveSoggetto_QNAME, RemoveSoggetto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSoggettoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeSoggettoResponse")
    public JAXBElement<RemoveSoggettoResponse> createRemoveSoggettoResponse(RemoveSoggettoResponse value) {
        return new JAXBElement<RemoveSoggettoResponse>(_RemoveSoggettoResponse_QNAME, RemoveSoggettoResponse.class, null, value);
    }

}
