
package it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf package. 
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

    private final static QName _GetServizioApplicativoResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getServizioApplicativoResponse");
    private final static QName _RemoveServizioApplicativo_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeServizioApplicativo");
    private final static QName _AddServizioApplicativoResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addServizioApplicativoResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _RemoveServizioApplicativoResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeServizioApplicativoResponse");
    private final static QName _GetListaServiziApplicativi_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaServiziApplicativi");
    private final static QName _AddServizioApplicativo_QNAME = new QName("http://icar.unibas.it/freesbee/", "addServizioApplicativo");
    private final static QName _GetServizioApplicativo_QNAME = new QName("http://icar.unibas.it/freesbee/", "getServizioApplicativo");
    private final static QName _GetListaServiziApplicativiResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaServiziApplicativiResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddServizioApplicativoResponse }
     * 
     */
    public AddServizioApplicativoResponse createAddServizioApplicativoResponse() {
        return new AddServizioApplicativoResponse();
    }

    /**
     * Create an instance of {@link RemoveServizioApplicativo }
     * 
     */
    public RemoveServizioApplicativo createRemoveServizioApplicativo() {
        return new RemoveServizioApplicativo();
    }

    /**
     * Create an instance of {@link GetServizioApplicativoResponse }
     * 
     */
    public GetServizioApplicativoResponse createGetServizioApplicativoResponse() {
        return new GetServizioApplicativoResponse();
    }

    /**
     * Create an instance of {@link GetListaServiziApplicativiResponse }
     * 
     */
    public GetListaServiziApplicativiResponse createGetListaServiziApplicativiResponse() {
        return new GetListaServiziApplicativiResponse();
    }

    /**
     * Create an instance of {@link GetServizioApplicativo }
     * 
     */
    public GetServizioApplicativo createGetServizioApplicativo() {
        return new GetServizioApplicativo();
    }

    /**
     * Create an instance of {@link AddServizioApplicativo }
     * 
     */
    public AddServizioApplicativo createAddServizioApplicativo() {
        return new AddServizioApplicativo();
    }

    /**
     * Create an instance of {@link GetListaServiziApplicativi }
     * 
     */
    public GetListaServiziApplicativi createGetListaServiziApplicativi() {
        return new GetListaServiziApplicativi();
    }

    /**
     * Create an instance of {@link RemoveServizioApplicativoResponse }
     * 
     */
    public RemoveServizioApplicativoResponse createRemoveServizioApplicativoResponse() {
        return new RemoveServizioApplicativoResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link ServizioApplicativo }
     * 
     */
    public ServizioApplicativo createServizioApplicativo() {
        return new ServizioApplicativo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServizioApplicativoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getServizioApplicativoResponse")
    public JAXBElement<GetServizioApplicativoResponse> createGetServizioApplicativoResponse(GetServizioApplicativoResponse value) {
        return new JAXBElement<GetServizioApplicativoResponse>(_GetServizioApplicativoResponse_QNAME, GetServizioApplicativoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveServizioApplicativo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeServizioApplicativo")
    public JAXBElement<RemoveServizioApplicativo> createRemoveServizioApplicativo(RemoveServizioApplicativo value) {
        return new JAXBElement<RemoveServizioApplicativo>(_RemoveServizioApplicativo_QNAME, RemoveServizioApplicativo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddServizioApplicativoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addServizioApplicativoResponse")
    public JAXBElement<AddServizioApplicativoResponse> createAddServizioApplicativoResponse(AddServizioApplicativoResponse value) {
        return new JAXBElement<AddServizioApplicativoResponse>(_AddServizioApplicativoResponse_QNAME, AddServizioApplicativoResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveServizioApplicativoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeServizioApplicativoResponse")
    public JAXBElement<RemoveServizioApplicativoResponse> createRemoveServizioApplicativoResponse(RemoveServizioApplicativoResponse value) {
        return new JAXBElement<RemoveServizioApplicativoResponse>(_RemoveServizioApplicativoResponse_QNAME, RemoveServizioApplicativoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaServiziApplicativi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaServiziApplicativi")
    public JAXBElement<GetListaServiziApplicativi> createGetListaServiziApplicativi(GetListaServiziApplicativi value) {
        return new JAXBElement<GetListaServiziApplicativi>(_GetListaServiziApplicativi_QNAME, GetListaServiziApplicativi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddServizioApplicativo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addServizioApplicativo")
    public JAXBElement<AddServizioApplicativo> createAddServizioApplicativo(AddServizioApplicativo value) {
        return new JAXBElement<AddServizioApplicativo>(_AddServizioApplicativo_QNAME, AddServizioApplicativo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServizioApplicativo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getServizioApplicativo")
    public JAXBElement<GetServizioApplicativo> createGetServizioApplicativo(GetServizioApplicativo value) {
        return new JAXBElement<GetServizioApplicativo>(_GetServizioApplicativo_QNAME, GetServizioApplicativo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaServiziApplicativiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaServiziApplicativiResponse")
    public JAXBElement<GetListaServiziApplicativiResponse> createGetListaServiziApplicativiResponse(GetListaServiziApplicativiResponse value) {
        return new JAXBElement<GetListaServiziApplicativiResponse>(_GetListaServiziApplicativiResponse_QNAME, GetListaServiziApplicativiResponse.class, null, value);
    }

}
