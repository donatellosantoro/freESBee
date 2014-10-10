
package it.unibas.freesbee.ge.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.freesbee.ge package. 
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

    private final static QName _PubblicaMessaggio_QNAME = new QName("http://ge.freesbee.unibas.it/", "pubblicaMessaggio");
    private final static QName _PubblicaMessaggioResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "pubblicaMessaggioResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://ge.freesbee.unibas.it/", "SOAPFault");
    private final static QName _PrelevaMessaggioResponse_QNAME = new QName("http://ge.freesbee.unibas.it/", "prelevaMessaggioResponse");
    private final static QName _PrelevaMessaggio_QNAME = new QName("http://ge.freesbee.unibas.it/", "prelevaMessaggio");
    private final static QName _NotificaEventoPubblicato_QNAME = new QName("http://ge.freesbee.unibas.it/", "NotificaEventoPubblicato");

      public ObjectFactory() {
    }

    public NotificaEventoPubblicato createNotificaEventoPubblicato() {
        return new NotificaEventoPubblicato();
    }

    public PrelevaMessaggio createPrelevaMessaggio() {
        return new PrelevaMessaggio();
    }

    public PubblicaMessaggio createPubblicaMessaggio() {
        return new PubblicaMessaggio();
    }

//     public ConsegnaEventoPubblicato createConsegnaEventoPubblicato() {
//        return new ConsegnaEventoPubblicato();
//    }

    public PrelevaMessaggioResponse createPrelevaMessaggioResponse() {
        return new PrelevaMessaggioResponse();
    }

    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    public PubblicaMessaggioResponse createPubblicaMessaggioResponse() {
        return new PubblicaMessaggioResponse();
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "pubblicaMessaggio")
    public JAXBElement<PubblicaMessaggio> createPubblicaMessaggio(PubblicaMessaggio value) {
        return new JAXBElement<PubblicaMessaggio>(_PubblicaMessaggio_QNAME, PubblicaMessaggio.class, null, value);
    }

  
    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "pubblicaMessaggioResponse")
    public JAXBElement<PubblicaMessaggioResponse> createPubblicaMessaggioResponse(PubblicaMessaggioResponse value) {
        return new JAXBElement<PubblicaMessaggioResponse>(_PubblicaMessaggioResponse_QNAME, PubblicaMessaggioResponse.class, null, value);
    }


    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "SOAPFault")
    public JAXBElement<SOAPFault> createSOAPFault(SOAPFault value) {
        return new JAXBElement<SOAPFault>(_SOAPFault_QNAME, SOAPFault.class, null, value);
    }

 
    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "prelevaMessaggioResponse")
    public JAXBElement<PrelevaMessaggioResponse> createPrelevaMessaggioResponse(PrelevaMessaggioResponse value) {
        return new JAXBElement<PrelevaMessaggioResponse>(_PrelevaMessaggioResponse_QNAME, PrelevaMessaggioResponse.class, null, value);
    }

  
    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "prelevaMessaggio")
    public JAXBElement<PrelevaMessaggio> createPrelevaMessaggio(PrelevaMessaggio value) {
        return new JAXBElement<PrelevaMessaggio>(_PrelevaMessaggio_QNAME, PrelevaMessaggio.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ge.freesbee.unibas.it/", name = "NotificaEventoPubblicato")
    public JAXBElement<NotificaEventoPubblicato> createNotificaEventoPubblicato(NotificaEventoPubblicato value) {
        return new JAXBElement<NotificaEventoPubblicato>(_NotificaEventoPubblicato_QNAME, NotificaEventoPubblicato.class, null, value);
    }
}
