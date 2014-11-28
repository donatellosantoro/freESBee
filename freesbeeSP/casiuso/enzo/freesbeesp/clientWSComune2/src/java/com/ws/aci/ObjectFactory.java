
package com.ws.aci;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ws.aci package. 
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

    private final static QName _CercaImmatricolazione_QNAME = new QName("http://aci.ws.com/", "cercaImmatricolazione");
    private final static QName _CercaImmatricolazioneResponse_QNAME = new QName("http://aci.ws.com/", "cercaImmatricolazioneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ws.aci
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CercaImmatricolazioneResponse }
     * 
     */
    public CercaImmatricolazioneResponse createCercaImmatricolazioneResponse() {
        return new CercaImmatricolazioneResponse();
    }

    /**
     * Create an instance of {@link CercaImmatricolazione }
     * 
     */
    public CercaImmatricolazione createCercaImmatricolazione() {
        return new CercaImmatricolazione();
    }

    /**
     * Create an instance of {@link Immatricolazione }
     * 
     */
    public Immatricolazione createImmatricolazione() {
        return new Immatricolazione();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CercaImmatricolazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aci.ws.com/", name = "cercaImmatricolazione")
    public JAXBElement<CercaImmatricolazione> createCercaImmatricolazione(CercaImmatricolazione value) {
        return new JAXBElement<CercaImmatricolazione>(_CercaImmatricolazione_QNAME, CercaImmatricolazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CercaImmatricolazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aci.ws.com/", name = "cercaImmatricolazioneResponse")
    public JAXBElement<CercaImmatricolazioneResponse> createCercaImmatricolazioneResponse(CercaImmatricolazioneResponse value) {
        return new JAXBElement<CercaImmatricolazioneResponse>(_CercaImmatricolazioneResponse_QNAME, CercaImmatricolazioneResponse.class, null, value);
    }

}
