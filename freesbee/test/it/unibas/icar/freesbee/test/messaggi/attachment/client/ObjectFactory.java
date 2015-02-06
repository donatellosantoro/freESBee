
package it.unibas.icar.freesbee.test.messaggi.attachment.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.test.messaggi.attachment.client package. 
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

    private final static QName _TestAttachmentEchoMessage_QNAME = new QName("http://icar.unibas.it/freesbee/", "testAttachmentEchoMessage");
    private final static QName _TestAttachmentResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "testAttachmentResponse");
    private final static QName _TestAttachmentEchoMessageResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "testAttachmentEchoMessageResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _TestAttachmentEchoResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "testAttachmentEchoResponse");
    private final static QName _TestAttachment_QNAME = new QName("http://icar.unibas.it/freesbee/", "testAttachment");
    private final static QName _TestAttachmentEcho_QNAME = new QName("http://icar.unibas.it/freesbee/", "testAttachmentEcho");
    private final static QName _VerificaInstallazioneResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "verificaInstallazioneResponse");
    private final static QName _VerificaInstallazione_QNAME = new QName("http://icar.unibas.it/freesbee/", "verificaInstallazione");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.test.messaggi.attachment.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestAttachmentEchoMessageResponse }
     * 
     */
    public TestAttachmentEchoMessageResponse createTestAttachmentEchoMessageResponse() {
        return new TestAttachmentEchoMessageResponse();
    }

    /**
     * Create an instance of {@link TestAttachmentResponse }
     * 
     */
    public TestAttachmentResponse createTestAttachmentResponse() {
        return new TestAttachmentResponse();
    }

    /**
     * Create an instance of {@link TestAttachmentEchoMessage }
     * 
     */
    public TestAttachmentEchoMessage createTestAttachmentEchoMessage() {
        return new TestAttachmentEchoMessage();
    }

    /**
     * Create an instance of {@link VerificaInstallazione }
     * 
     */
    public VerificaInstallazione createVerificaInstallazione() {
        return new VerificaInstallazione();
    }

    /**
     * Create an instance of {@link VerificaInstallazioneResponse }
     * 
     */
    public VerificaInstallazioneResponse createVerificaInstallazioneResponse() {
        return new VerificaInstallazioneResponse();
    }

    /**
     * Create an instance of {@link TestAttachmentEcho }
     * 
     */
    public TestAttachmentEcho createTestAttachmentEcho() {
        return new TestAttachmentEcho();
    }

    /**
     * Create an instance of {@link TestAttachment }
     * 
     */
    public TestAttachment createTestAttachment() {
        return new TestAttachment();
    }

    /**
     * Create an instance of {@link TestAttachmentEchoResponse }
     * 
     */
    public TestAttachmentEchoResponse createTestAttachmentEchoResponse() {
        return new TestAttachmentEchoResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link MessaggioInstallazione }
     * 
     */
    public MessaggioInstallazione createMessaggioInstallazione() {
        return new MessaggioInstallazione();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAttachmentEchoMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "testAttachmentEchoMessage")
    public JAXBElement<TestAttachmentEchoMessage> createTestAttachmentEchoMessage(TestAttachmentEchoMessage value) {
        return new JAXBElement<TestAttachmentEchoMessage>(_TestAttachmentEchoMessage_QNAME, TestAttachmentEchoMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAttachmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "testAttachmentResponse")
    public JAXBElement<TestAttachmentResponse> createTestAttachmentResponse(TestAttachmentResponse value) {
        return new JAXBElement<TestAttachmentResponse>(_TestAttachmentResponse_QNAME, TestAttachmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAttachmentEchoMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "testAttachmentEchoMessageResponse")
    public JAXBElement<TestAttachmentEchoMessageResponse> createTestAttachmentEchoMessageResponse(TestAttachmentEchoMessageResponse value) {
        return new JAXBElement<TestAttachmentEchoMessageResponse>(_TestAttachmentEchoMessageResponse_QNAME, TestAttachmentEchoMessageResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAttachmentEchoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "testAttachmentEchoResponse")
    public JAXBElement<TestAttachmentEchoResponse> createTestAttachmentEchoResponse(TestAttachmentEchoResponse value) {
        return new JAXBElement<TestAttachmentEchoResponse>(_TestAttachmentEchoResponse_QNAME, TestAttachmentEchoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAttachment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "testAttachment")
    public JAXBElement<TestAttachment> createTestAttachment(TestAttachment value) {
        return new JAXBElement<TestAttachment>(_TestAttachment_QNAME, TestAttachment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestAttachmentEcho }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "testAttachmentEcho")
    public JAXBElement<TestAttachmentEcho> createTestAttachmentEcho(TestAttachmentEcho value) {
        return new JAXBElement<TestAttachmentEcho>(_TestAttachmentEcho_QNAME, TestAttachmentEcho.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificaInstallazioneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "verificaInstallazioneResponse")
    public JAXBElement<VerificaInstallazioneResponse> createVerificaInstallazioneResponse(VerificaInstallazioneResponse value) {
        return new JAXBElement<VerificaInstallazioneResponse>(_VerificaInstallazioneResponse_QNAME, VerificaInstallazioneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificaInstallazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "verificaInstallazione")
    public JAXBElement<VerificaInstallazione> createVerificaInstallazione(VerificaInstallazione value) {
        return new JAXBElement<VerificaInstallazione>(_VerificaInstallazione_QNAME, VerificaInstallazione.class, null, value);
    }

}
