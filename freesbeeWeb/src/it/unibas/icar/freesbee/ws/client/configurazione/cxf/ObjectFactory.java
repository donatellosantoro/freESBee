
package it.unibas.icar.freesbee.ws.client.configurazione.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbe.ws.client.configurazione.cxf package. 
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

    private final static QName _GetConfigurazione_QNAME = new QName("http://icar.unibas.it/freesbee/", "getConfigurazione");
    private final static QName _AddConfigurazione_QNAME = new QName("http://icar.unibas.it/freesbee/", "addConfigurazione");
    private final static QName _GetFreesbeeVersion_QNAME = new QName("http://icar.unibas.it/freesbee/", "getFreesbeeVersion");
    private final static QName _AddConfigurazioneResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "addConfigurazioneResponse");
    private final static QName _GetConfigurazioneResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getConfigurazioneResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _GetFreesbeeVersionResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getFreesbeeVersionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbe.ws.client.configurazione.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetConfigurazioneResponse }
     * 
     */
    public GetConfigurazioneResponse createGetConfigurazioneResponse() {
        return new GetConfigurazioneResponse();
    }

    /**
     * Create an instance of {@link AddConfigurazioneResponse }
     * 
     */
    public AddConfigurazioneResponse createAddConfigurazioneResponse() {
        return new AddConfigurazioneResponse();
    }

    /**
     * Create an instance of {@link GetFreesbeeVersion }
     * 
     */
    public GetFreesbeeVersion createGetFreesbeeVersion() {
        return new GetFreesbeeVersion();
    }

    /**
     * Create an instance of {@link AddConfigurazione }
     * 
     */
    public AddConfigurazione createAddConfigurazione() {
        return new AddConfigurazione();
    }

    /**
     * Create an instance of {@link GetConfigurazione }
     * 
     */
    public GetConfigurazione createGetConfigurazione() {
        return new GetConfigurazione();
    }

    /**
     * Create an instance of {@link GetFreesbeeVersionResponse }
     * 
     */
    public GetFreesbeeVersionResponse createGetFreesbeeVersionResponse() {
        return new GetFreesbeeVersionResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link Configurazione }
     * 
     */
    public Configurazione createConfigurazione() {
        return new Configurazione();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConfigurazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getConfigurazione")
    public JAXBElement<GetConfigurazione> createGetConfigurazione(GetConfigurazione value) {
        return new JAXBElement<GetConfigurazione>(_GetConfigurazione_QNAME, GetConfigurazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddConfigurazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addConfigurazione")
    public JAXBElement<AddConfigurazione> createAddConfigurazione(AddConfigurazione value) {
        return new JAXBElement<AddConfigurazione>(_AddConfigurazione_QNAME, AddConfigurazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreesbeeVersion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getFreesbeeVersion")
    public JAXBElement<GetFreesbeeVersion> createGetFreesbeeVersion(GetFreesbeeVersion value) {
        return new JAXBElement<GetFreesbeeVersion>(_GetFreesbeeVersion_QNAME, GetFreesbeeVersion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddConfigurazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "addConfigurazioneResponse")
    public JAXBElement<AddConfigurazioneResponse> createAddConfigurazioneResponse(AddConfigurazioneResponse value) {
        return new JAXBElement<AddConfigurazioneResponse>(_AddConfigurazioneResponse_QNAME, AddConfigurazioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConfigurazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getConfigurazioneResponse")
    public JAXBElement<GetConfigurazioneResponse> createGetConfigurazioneResponse(GetConfigurazioneResponse value) {
        return new JAXBElement<GetConfigurazioneResponse>(_GetConfigurazioneResponse_QNAME, GetConfigurazioneResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreesbeeVersionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getFreesbeeVersionResponse")
    public JAXBElement<GetFreesbeeVersionResponse> createGetFreesbeeVersionResponse(GetFreesbeeVersionResponse value) {
        return new JAXBElement<GetFreesbeeVersionResponse>(_GetFreesbeeVersionResponse_QNAME, GetFreesbeeVersionResponse.class, null, value);
    }

}
