
package com.ws.comune;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ws.comune package. 
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

    private final static QName _CercaCittadino_QNAME = new QName("http://comune.ws.com/", "cercaCittadino");
    private final static QName _CercaCittadinoResponse_QNAME = new QName("http://comune.ws.com/", "cercaCittadinoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ws.comune
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CercaCittadino }
     * 
     */
    public CercaCittadino createCercaCittadino() {
        return new CercaCittadino();
    }

    /**
     * Create an instance of {@link CercaCittadinoResponse }
     * 
     */
    public CercaCittadinoResponse createCercaCittadinoResponse() {
        return new CercaCittadinoResponse();
    }

    /**
     * Create an instance of {@link Cittadino }
     * 
     */
    public Cittadino createCittadino() {
        return new Cittadino();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CercaCittadino }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://comune.ws.com/", name = "cercaCittadino")
    public JAXBElement<CercaCittadino> createCercaCittadino(CercaCittadino value) {
        return new JAXBElement<CercaCittadino>(_CercaCittadino_QNAME, CercaCittadino.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CercaCittadinoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://comune.ws.com/", name = "cercaCittadinoResponse")
    public JAXBElement<CercaCittadinoResponse> createCercaCittadinoResponse(CercaCittadinoResponse value) {
        return new JAXBElement<CercaCittadinoResponse>(_CercaCittadinoResponse_QNAME, CercaCittadinoResponse.class, null, value);
    }

}
