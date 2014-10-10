
package it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf package. 
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

    private final static QName _GetListaPorteApplicativeResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaPorteApplicativeResponse");
    private final static QName _GetPortaApplicativa_QNAME = new QName("http://icar.unibas.it/freesbee/", "getPortaApplicativa");
    private final static QName _GetPortaApplicativaResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getPortaApplicativaResponse");
    private final static QName _GetListaPorteApplicative_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaPorteApplicative");
    private final static QName _RemovePortaApplicativa_QNAME = new QName("http://icar.unibas.it/freesbee/", "removePortaApplicativa");
    private final static QName _AddPortaApplicativa_QNAME = new QName("http://icar.unibas.it/freesbee/", "addPortaApplicativa");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _RemovePortaApplicativaResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "removePortaApplicativaResponse");
    private final static QName _AddPortaApplicativaResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addPortaApplicativaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPortaApplicativa }
     * 
     */
    public AddPortaApplicativa createAddPortaApplicativa() {
        return new AddPortaApplicativa();
    }

    /**
     * Create an instance of {@link RemovePortaApplicativa }
     * 
     */
    public RemovePortaApplicativa createRemovePortaApplicativa() {
        return new RemovePortaApplicativa();
    }

    /**
     * Create an instance of {@link GetListaPorteApplicative }
     * 
     */
    public GetListaPorteApplicative createGetListaPorteApplicative() {
        return new GetListaPorteApplicative();
    }

    /**
     * Create an instance of {@link GetPortaApplicativaResponse }
     * 
     */
    public GetPortaApplicativaResponse createGetPortaApplicativaResponse() {
        return new GetPortaApplicativaResponse();
    }

    /**
     * Create an instance of {@link GetPortaApplicativa }
     * 
     */
    public GetPortaApplicativa createGetPortaApplicativa() {
        return new GetPortaApplicativa();
    }

    /**
     * Create an instance of {@link GetListaPorteApplicativeResponse }
     * 
     */
    public GetListaPorteApplicativeResponse createGetListaPorteApplicativeResponse() {
        return new GetListaPorteApplicativeResponse();
    }

    /**
     * Create an instance of {@link AddPortaApplicativaResponse }
     * 
     */
    public AddPortaApplicativaResponse createAddPortaApplicativaResponse() {
        return new AddPortaApplicativaResponse();
    }

    /**
     * Create an instance of {@link RemovePortaApplicativaResponse }
     * 
     */
    public RemovePortaApplicativaResponse createRemovePortaApplicativaResponse() {
        return new RemovePortaApplicativaResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link PortaApplicativa }
     * 
     */
    public PortaApplicativa createPortaApplicativa() {
        return new PortaApplicativa();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaPorteApplicativeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaPorteApplicativeResponse")
    public JAXBElement<GetListaPorteApplicativeResponse> createGetListaPorteApplicativeResponse(GetListaPorteApplicativeResponse value) {
        return new JAXBElement<GetListaPorteApplicativeResponse>(_GetListaPorteApplicativeResponse_QNAME, GetListaPorteApplicativeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPortaApplicativa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getPortaApplicativa")
    public JAXBElement<GetPortaApplicativa> createGetPortaApplicativa(GetPortaApplicativa value) {
        return new JAXBElement<GetPortaApplicativa>(_GetPortaApplicativa_QNAME, GetPortaApplicativa.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPortaApplicativaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getPortaApplicativaResponse")
    public JAXBElement<GetPortaApplicativaResponse> createGetPortaApplicativaResponse(GetPortaApplicativaResponse value) {
        return new JAXBElement<GetPortaApplicativaResponse>(_GetPortaApplicativaResponse_QNAME, GetPortaApplicativaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaPorteApplicative }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaPorteApplicative")
    public JAXBElement<GetListaPorteApplicative> createGetListaPorteApplicative(GetListaPorteApplicative value) {
        return new JAXBElement<GetListaPorteApplicative>(_GetListaPorteApplicative_QNAME, GetListaPorteApplicative.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemovePortaApplicativa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removePortaApplicativa")
    public JAXBElement<RemovePortaApplicativa> createRemovePortaApplicativa(RemovePortaApplicativa value) {
        return new JAXBElement<RemovePortaApplicativa>(_RemovePortaApplicativa_QNAME, RemovePortaApplicativa.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPortaApplicativa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addPortaApplicativa")
    public JAXBElement<AddPortaApplicativa> createAddPortaApplicativa(AddPortaApplicativa value) {
        return new JAXBElement<AddPortaApplicativa>(_AddPortaApplicativa_QNAME, AddPortaApplicativa.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RemovePortaApplicativaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removePortaApplicativaResponse")
    public JAXBElement<RemovePortaApplicativaResponse> createRemovePortaApplicativaResponse(RemovePortaApplicativaResponse value) {
        return new JAXBElement<RemovePortaApplicativaResponse>(_RemovePortaApplicativaResponse_QNAME, RemovePortaApplicativaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPortaApplicativaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addPortaApplicativaResponse")
    public JAXBElement<AddPortaApplicativaResponse> createAddPortaApplicativaResponse(AddPortaApplicativaResponse value) {
        return new JAXBElement<AddPortaApplicativaResponse>(_AddPortaApplicativaResponse_QNAME, AddPortaApplicativaResponse.class, null, value);
    }

}
