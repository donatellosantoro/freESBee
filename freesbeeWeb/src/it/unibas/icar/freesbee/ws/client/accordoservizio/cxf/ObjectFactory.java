
package it.unibas.icar.freesbee.ws.client.accordoservizio.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.accordoservizio.cxf package. 
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

    private final static QName _RemoveAccordoServizio_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeAccordoServizio");
    private final static QName _GetListaAccordiServizioResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaAccordiServizioResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _AddAccordoServizioResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addAccordoServizioResponse");
    private final static QName _RemoveAccordoServizioResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeAccordoServizioResponse");
    private final static QName _GetListaAccordiServizio_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaAccordiServizio");
    private final static QName _AddAccordoServizio_QNAME = new QName("http://icar.unibas.it/freesbee/", "addAccordoServizio");
    private final static QName _GetAccordoServizio_QNAME = new QName("http://icar.unibas.it/freesbee/", "getAccordoServizio");
    private final static QName _GetAccordoServizioResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getAccordoServizioResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.accordoservizio.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetListaAccordiServizioResponse }
     * 
     */
    public GetListaAccordiServizioResponse createGetListaAccordiServizioResponse() {
        return new GetListaAccordiServizioResponse();
    }

    /**
     * Create an instance of {@link RemoveAccordoServizio }
     * 
     */
    public RemoveAccordoServizio createRemoveAccordoServizio() {
        return new RemoveAccordoServizio();
    }

    /**
     * Create an instance of {@link GetAccordoServizioResponse }
     * 
     */
    public GetAccordoServizioResponse createGetAccordoServizioResponse() {
        return new GetAccordoServizioResponse();
    }

    /**
     * Create an instance of {@link GetAccordoServizio }
     * 
     */
    public GetAccordoServizio createGetAccordoServizio() {
        return new GetAccordoServizio();
    }

    /**
     * Create an instance of {@link AddAccordoServizio }
     * 
     */
    public AddAccordoServizio createAddAccordoServizio() {
        return new AddAccordoServizio();
    }

    /**
     * Create an instance of {@link GetListaAccordiServizio }
     * 
     */
    public GetListaAccordiServizio createGetListaAccordiServizio() {
        return new GetListaAccordiServizio();
    }

    /**
     * Create an instance of {@link RemoveAccordoServizioResponse }
     * 
     */
    public RemoveAccordoServizioResponse createRemoveAccordoServizioResponse() {
        return new RemoveAccordoServizioResponse();
    }

    /**
     * Create an instance of {@link AddAccordoServizioResponse }
     * 
     */
    public AddAccordoServizioResponse createAddAccordoServizioResponse() {
        return new AddAccordoServizioResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link AccordoServizio }
     * 
     */
    public AccordoServizio createAccordoServizio() {
        return new AccordoServizio();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAccordoServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeAccordoServizio")
    public JAXBElement<RemoveAccordoServizio> createRemoveAccordoServizio(RemoveAccordoServizio value) {
        return new JAXBElement<RemoveAccordoServizio>(_RemoveAccordoServizio_QNAME, RemoveAccordoServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaAccordiServizioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaAccordiServizioResponse")
    public JAXBElement<GetListaAccordiServizioResponse> createGetListaAccordiServizioResponse(GetListaAccordiServizioResponse value) {
        return new JAXBElement<GetListaAccordiServizioResponse>(_GetListaAccordiServizioResponse_QNAME, GetListaAccordiServizioResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAccordoServizioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addAccordoServizioResponse")
    public JAXBElement<AddAccordoServizioResponse> createAddAccordoServizioResponse(AddAccordoServizioResponse value) {
        return new JAXBElement<AddAccordoServizioResponse>(_AddAccordoServizioResponse_QNAME, AddAccordoServizioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAccordoServizioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeAccordoServizioResponse")
    public JAXBElement<RemoveAccordoServizioResponse> createRemoveAccordoServizioResponse(RemoveAccordoServizioResponse value) {
        return new JAXBElement<RemoveAccordoServizioResponse>(_RemoveAccordoServizioResponse_QNAME, RemoveAccordoServizioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaAccordiServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaAccordiServizio")
    public JAXBElement<GetListaAccordiServizio> createGetListaAccordiServizio(GetListaAccordiServizio value) {
        return new JAXBElement<GetListaAccordiServizio>(_GetListaAccordiServizio_QNAME, GetListaAccordiServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAccordoServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addAccordoServizio")
    public JAXBElement<AddAccordoServizio> createAddAccordoServizio(AddAccordoServizio value) {
        return new JAXBElement<AddAccordoServizio>(_AddAccordoServizio_QNAME, AddAccordoServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccordoServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getAccordoServizio")
    public JAXBElement<GetAccordoServizio> createGetAccordoServizio(GetAccordoServizio value) {
        return new JAXBElement<GetAccordoServizio>(_GetAccordoServizio_QNAME, GetAccordoServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccordoServizioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getAccordoServizioResponse")
    public JAXBElement<GetAccordoServizioResponse> createGetAccordoServizioResponse(GetAccordoServizioResponse value) {
        return new JAXBElement<GetAccordoServizioResponse>(_GetAccordoServizioResponse_QNAME, GetAccordoServizioResponse.class, null, value);
    }

}
