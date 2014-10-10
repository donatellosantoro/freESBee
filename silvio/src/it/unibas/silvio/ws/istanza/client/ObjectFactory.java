
package it.unibas.silvio.ws.istanza.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.silvio.ws.istanza.client package. 
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

    private final static QName _AvviaIstanza_QNAME = new QName("http://freesbee.unibas.it", "avviaIstanza");
    private final static QName _AvviaIstanzaResponse_QNAME = new QName("http://freesbee.unibas.it", "avviaIstanzaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.silvio.ws.istanza.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AvviaIstanzaResponse }
     * 
     */
    public AvviaIstanzaResponse createAvviaIstanzaResponse() {
        return new AvviaIstanzaResponse();
    }

    /**
     * Create an instance of {@link AvviaIstanza }
     * 
     */
    public AvviaIstanza createAvviaIstanza() {
        return new AvviaIstanza();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvviaIstanza }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://freesbee.unibas.it", name = "avviaIstanza")
    public JAXBElement<AvviaIstanza> createAvviaIstanza(AvviaIstanza value) {
        return new JAXBElement<AvviaIstanza>(_AvviaIstanza_QNAME, AvviaIstanza.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvviaIstanzaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://freesbee.unibas.it", name = "avviaIstanzaResponse")
    public JAXBElement<AvviaIstanzaResponse> createAvviaIstanzaResponse(AvviaIstanzaResponse value) {
        return new JAXBElement<AvviaIstanzaResponse>(_AvviaIstanzaResponse_QNAME, AvviaIstanzaResponse.class, null, value);
    }

}
