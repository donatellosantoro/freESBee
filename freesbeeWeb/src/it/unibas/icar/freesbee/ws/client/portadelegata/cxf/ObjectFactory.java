
package it.unibas.icar.freesbee.ws.client.portadelegata.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.portadelegata.cxf package. 
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

    private final static QName _AddPortaDelegata_QNAME = new QName("http://icar.unibas.it/freesbee/", "addPortaDelegata");
    private final static QName _AddPortaDelegataResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addPortaDelegataResponse");
    private final static QName _GetListaPorteDelegate_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaPorteDelegate");
    private final static QName _GetListaPorteDelegateResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaPorteDelegateResponse");
    private final static QName _GetPortaDelegata_QNAME = new QName("http://icar.unibas.it/freesbee/", "getPortaDelegata");
    private final static QName _RemovePortaDelegataResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "removePortaDelegataResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _RemovePortaDelegata_QNAME = new QName("http://icar.unibas.it/freesbee/", "removePortaDelegata");
    private final static QName _GetPortaDelegataResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getPortaDelegataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.portadelegata.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RemovePortaDelegataResponse }
     * 
     */
    public RemovePortaDelegataResponse createRemovePortaDelegataResponse() {
        return new RemovePortaDelegataResponse();
    }

    /**
     * Create an instance of {@link GetPortaDelegata }
     * 
     */
    public GetPortaDelegata createGetPortaDelegata() {
        return new GetPortaDelegata();
    }

    /**
     * Create an instance of {@link GetListaPorteDelegateResponse }
     * 
     */
    public GetListaPorteDelegateResponse createGetListaPorteDelegateResponse() {
        return new GetListaPorteDelegateResponse();
    }

    /**
     * Create an instance of {@link GetListaPorteDelegate }
     * 
     */
    public GetListaPorteDelegate createGetListaPorteDelegate() {
        return new GetListaPorteDelegate();
    }

    /**
     * Create an instance of {@link AddPortaDelegataResponse }
     * 
     */
    public AddPortaDelegataResponse createAddPortaDelegataResponse() {
        return new AddPortaDelegataResponse();
    }

    /**
     * Create an instance of {@link AddPortaDelegata }
     * 
     */
    public AddPortaDelegata createAddPortaDelegata() {
        return new AddPortaDelegata();
    }

    /**
     * Create an instance of {@link GetPortaDelegataResponse }
     * 
     */
    public GetPortaDelegataResponse createGetPortaDelegataResponse() {
        return new GetPortaDelegataResponse();
    }

    /**
     * Create an instance of {@link RemovePortaDelegata }
     * 
     */
    public RemovePortaDelegata createRemovePortaDelegata() {
        return new RemovePortaDelegata();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link PortaDelegata }
     * 
     */
    public PortaDelegata createPortaDelegata() {
        return new PortaDelegata();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPortaDelegata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addPortaDelegata")
    public JAXBElement<AddPortaDelegata> createAddPortaDelegata(AddPortaDelegata value) {
        return new JAXBElement<AddPortaDelegata>(_AddPortaDelegata_QNAME, AddPortaDelegata.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPortaDelegataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addPortaDelegataResponse")
    public JAXBElement<AddPortaDelegataResponse> createAddPortaDelegataResponse(AddPortaDelegataResponse value) {
        return new JAXBElement<AddPortaDelegataResponse>(_AddPortaDelegataResponse_QNAME, AddPortaDelegataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaPorteDelegate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaPorteDelegate")
    public JAXBElement<GetListaPorteDelegate> createGetListaPorteDelegate(GetListaPorteDelegate value) {
        return new JAXBElement<GetListaPorteDelegate>(_GetListaPorteDelegate_QNAME, GetListaPorteDelegate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaPorteDelegateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaPorteDelegateResponse")
    public JAXBElement<GetListaPorteDelegateResponse> createGetListaPorteDelegateResponse(GetListaPorteDelegateResponse value) {
        return new JAXBElement<GetListaPorteDelegateResponse>(_GetListaPorteDelegateResponse_QNAME, GetListaPorteDelegateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPortaDelegata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getPortaDelegata")
    public JAXBElement<GetPortaDelegata> createGetPortaDelegata(GetPortaDelegata value) {
        return new JAXBElement<GetPortaDelegata>(_GetPortaDelegata_QNAME, GetPortaDelegata.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemovePortaDelegataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removePortaDelegataResponse")
    public JAXBElement<RemovePortaDelegataResponse> createRemovePortaDelegataResponse(RemovePortaDelegataResponse value) {
        return new JAXBElement<RemovePortaDelegataResponse>(_RemovePortaDelegataResponse_QNAME, RemovePortaDelegataResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RemovePortaDelegata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removePortaDelegata")
    public JAXBElement<RemovePortaDelegata> createRemovePortaDelegata(RemovePortaDelegata value) {
        return new JAXBElement<RemovePortaDelegata>(_RemovePortaDelegata_QNAME, RemovePortaDelegata.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPortaDelegataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getPortaDelegataResponse")
    public JAXBElement<GetPortaDelegataResponse> createGetPortaDelegataResponse(GetPortaDelegataResponse value) {
        return new JAXBElement<GetPortaDelegataResponse>(_GetPortaDelegataResponse_QNAME, GetPortaDelegataResponse.class, null, value);
    }

}
