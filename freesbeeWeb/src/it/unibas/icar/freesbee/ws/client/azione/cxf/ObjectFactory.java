
package it.unibas.icar.freesbee.ws.client.azione.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.azione.cxf package. 
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

    private final static QName _GetAzione_QNAME = new QName("http://icar.unibas.it/freesbee/", "getAzione");
    private final static QName _GetListaAzioniResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaAzioniResponse");
    private final static QName _GetListaAzioni_QNAME = new QName("http://icar.unibas.it/freesbee/", "getListaAzioni");
    private final static QName _AddAzione_QNAME = new QName("http://icar.unibas.it/freesbee/", "addAzione");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _GetAzioneResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getAzioneResponse");
    private final static QName _RemoveAzione_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeAzione");
    private final static QName _AddAzioneResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addAzioneResponse");
    private final static QName _RemoveAzioneResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "removeAzioneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.azione.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddAzione }
     * 
     */
    public AddAzione createAddAzione() {
        return new AddAzione();
    }

    /**
     * Create an instance of {@link GetListaAzioni }
     * 
     */
    public GetListaAzioni createGetListaAzioni() {
        return new GetListaAzioni();
    }

    /**
     * Create an instance of {@link GetListaAzioniResponse }
     * 
     */
    public GetListaAzioniResponse createGetListaAzioniResponse() {
        return new GetListaAzioniResponse();
    }

    /**
     * Create an instance of {@link GetAzione }
     * 
     */
    public GetAzione createGetAzione() {
        return new GetAzione();
    }

    /**
     * Create an instance of {@link RemoveAzioneResponse }
     * 
     */
    public RemoveAzioneResponse createRemoveAzioneResponse() {
        return new RemoveAzioneResponse();
    }

    /**
     * Create an instance of {@link AddAzioneResponse }
     * 
     */
    public AddAzioneResponse createAddAzioneResponse() {
        return new AddAzioneResponse();
    }

    /**
     * Create an instance of {@link RemoveAzione }
     * 
     */
    public RemoveAzione createRemoveAzione() {
        return new RemoveAzione();
    }

    /**
     * Create an instance of {@link GetAzioneResponse }
     * 
     */
    public GetAzioneResponse createGetAzioneResponse() {
        return new GetAzioneResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link Azione }
     * 
     */
    public Azione createAzione() {
        return new Azione();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAzione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getAzione")
    public JAXBElement<GetAzione> createGetAzione(GetAzione value) {
        return new JAXBElement<GetAzione>(_GetAzione_QNAME, GetAzione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaAzioniResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaAzioniResponse")
    public JAXBElement<GetListaAzioniResponse> createGetListaAzioniResponse(GetListaAzioniResponse value) {
        return new JAXBElement<GetListaAzioniResponse>(_GetListaAzioniResponse_QNAME, GetListaAzioniResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaAzioni }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getListaAzioni")
    public JAXBElement<GetListaAzioni> createGetListaAzioni(GetListaAzioni value) {
        return new JAXBElement<GetListaAzioni>(_GetListaAzioni_QNAME, GetListaAzioni.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAzione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addAzione")
    public JAXBElement<AddAzione> createAddAzione(AddAzione value) {
        return new JAXBElement<AddAzione>(_AddAzione_QNAME, AddAzione.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAzioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getAzioneResponse")
    public JAXBElement<GetAzioneResponse> createGetAzioneResponse(GetAzioneResponse value) {
        return new JAXBElement<GetAzioneResponse>(_GetAzioneResponse_QNAME, GetAzioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAzione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeAzione")
    public JAXBElement<RemoveAzione> createRemoveAzione(RemoveAzione value) {
        return new JAXBElement<RemoveAzione>(_RemoveAzione_QNAME, RemoveAzione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAzioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addAzioneResponse")
    public JAXBElement<AddAzioneResponse> createAddAzioneResponse(AddAzioneResponse value) {
        return new JAXBElement<AddAzioneResponse>(_AddAzioneResponse_QNAME, AddAzioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAzioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "removeAzioneResponse")
    public JAXBElement<RemoveAzioneResponse> createRemoveAzioneResponse(RemoveAzioneResponse value) {
        return new JAXBElement<RemoveAzioneResponse>(_RemoveAzioneResponse_QNAME, RemoveAzioneResponse.class, null, value);
    }

}
