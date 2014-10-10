
package it.unibas.icar.freesbee.ws.client.autenticazione.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.autenticazione.cxf package. 
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

    private final static QName _GetRuolo_QNAME = new QName("http://icar.unibas.it/freesbee/", "getRuolo");
    private final static QName _GetRuoloResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getRuoloResponse");
    private final static QName _CambiaPassword_QNAME = new QName("http://icar.unibas.it/freesbee/", "cambiaPassword");
    private final static QName _Autentica_QNAME = new QName("http://icar.unibas.it/freesbee/", "autentica");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _AutenticaResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "autenticaResponse");
    private final static QName _CambiaPasswordResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "cambiaPasswordResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.autenticazione.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Autentica }
     * 
     */
    public Autentica createAutentica() {
        return new Autentica();
    }

    /**
     * Create an instance of {@link CambiaPassword }
     * 
     */
    public CambiaPassword createCambiaPassword() {
        return new CambiaPassword();
    }

    /**
     * Create an instance of {@link GetRuoloResponse }
     * 
     */
    public GetRuoloResponse createGetRuoloResponse() {
        return new GetRuoloResponse();
    }

    /**
     * Create an instance of {@link GetRuolo }
     * 
     */
    public GetRuolo createGetRuolo() {
        return new GetRuolo();
    }

    /**
     * Create an instance of {@link CambiaPasswordResponse }
     * 
     */
    public CambiaPasswordResponse createCambiaPasswordResponse() {
        return new CambiaPasswordResponse();
    }

    /**
     * Create an instance of {@link AutenticaResponse }
     * 
     */
    public AutenticaResponse createAutenticaResponse() {
        return new AutenticaResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRuolo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getRuolo")
    public JAXBElement<GetRuolo> createGetRuolo(GetRuolo value) {
        return new JAXBElement<GetRuolo>(_GetRuolo_QNAME, GetRuolo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRuoloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getRuoloResponse")
    public JAXBElement<GetRuoloResponse> createGetRuoloResponse(GetRuoloResponse value) {
        return new JAXBElement<GetRuoloResponse>(_GetRuoloResponse_QNAME, GetRuoloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiaPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "cambiaPassword")
    public JAXBElement<CambiaPassword> createCambiaPassword(CambiaPassword value) {
        return new JAXBElement<CambiaPassword>(_CambiaPassword_QNAME, CambiaPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Autentica }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "autentica")
    public JAXBElement<Autentica> createAutentica(Autentica value) {
        return new JAXBElement<Autentica>(_Autentica_QNAME, Autentica.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AutenticaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "autenticaResponse")
    public JAXBElement<AutenticaResponse> createAutenticaResponse(AutenticaResponse value) {
        return new JAXBElement<AutenticaResponse>(_AutenticaResponse_QNAME, AutenticaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiaPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "cambiaPasswordResponse")
    public JAXBElement<CambiaPasswordResponse> createCambiaPasswordResponse(CambiaPasswordResponse value) {
        return new JAXBElement<CambiaPasswordResponse>(_CambiaPasswordResponse_QNAME, CambiaPasswordResponse.class, null, value);
    }

}
