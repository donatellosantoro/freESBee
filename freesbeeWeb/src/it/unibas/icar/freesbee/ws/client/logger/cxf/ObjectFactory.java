
package it.unibas.icar.freesbee.ws.client.logger.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.icar.freesbee.ws.client.logger.cxf package. 
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

    private final static QName _GetLogFile_QNAME = new QName("http://icar.unibas.it/freesbee/", "getLogFile");
    private final static QName _GetLogDaDataLivelloResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getLogDaDataLivelloResponse");
    private final static QName _GetLogFileResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getLogFileResponse");
    private final static QName _GetLogDaDataResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getLogDaDataResponse");
    private final static QName _GetLogDaLivelloResponse_QNAME = new QName("http://icar.unibas.it/freesbee/", "getLogDaLivelloResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://icar.unibas.it/freesbee/", "SOAPFault");
    private final static QName _GetLogDaData_QNAME = new QName("http://icar.unibas.it/freesbee/", "getLogDaData");
    private final static QName _GetLogDaDataLivello_QNAME = new QName("http://icar.unibas.it/freesbee/", "getLogDaDataLivello");
    private final static QName _GetLogDaLivello_QNAME = new QName("http://icar.unibas.it/freesbee/", "getLogDaLivello");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.icar.freesbee.ws.client.logger.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLogDaLivelloResponse }
     * 
     */
    public GetLogDaLivelloResponse createGetLogDaLivelloResponse() {
        return new GetLogDaLivelloResponse();
    }

    /**
     * Create an instance of {@link GetLogDaDataResponse }
     * 
     */
    public GetLogDaDataResponse createGetLogDaDataResponse() {
        return new GetLogDaDataResponse();
    }

    /**
     * Create an instance of {@link GetLogFileResponse }
     * 
     */
    public GetLogFileResponse createGetLogFileResponse() {
        return new GetLogFileResponse();
    }

    /**
     * Create an instance of {@link GetLogDaDataLivelloResponse }
     * 
     */
    public GetLogDaDataLivelloResponse createGetLogDaDataLivelloResponse() {
        return new GetLogDaDataLivelloResponse();
    }

    /**
     * Create an instance of {@link GetLogFile }
     * 
     */
    public GetLogFile createGetLogFile() {
        return new GetLogFile();
    }

    /**
     * Create an instance of {@link GetLogDaLivello }
     * 
     */
    public GetLogDaLivello createGetLogDaLivello() {
        return new GetLogDaLivello();
    }

    /**
     * Create an instance of {@link GetLogDaDataLivello }
     * 
     */
    public GetLogDaDataLivello createGetLogDaDataLivello() {
        return new GetLogDaDataLivello();
    }

    /**
     * Create an instance of {@link GetLogDaData }
     * 
     */
    public GetLogDaData createGetLogDaData() {
        return new GetLogDaData();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getLogFile")
    public JAXBElement<GetLogFile> createGetLogFile(GetLogFile value) {
        return new JAXBElement<GetLogFile>(_GetLogFile_QNAME, GetLogFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogDaDataLivelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getLogDaDataLivelloResponse")
    public JAXBElement<GetLogDaDataLivelloResponse> createGetLogDaDataLivelloResponse(GetLogDaDataLivelloResponse value) {
        return new JAXBElement<GetLogDaDataLivelloResponse>(_GetLogDaDataLivelloResponse_QNAME, GetLogDaDataLivelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getLogFileResponse")
    public JAXBElement<GetLogFileResponse> createGetLogFileResponse(GetLogFileResponse value) {
        return new JAXBElement<GetLogFileResponse>(_GetLogFileResponse_QNAME, GetLogFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogDaDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getLogDaDataResponse")
    public JAXBElement<GetLogDaDataResponse> createGetLogDaDataResponse(GetLogDaDataResponse value) {
        return new JAXBElement<GetLogDaDataResponse>(_GetLogDaDataResponse_QNAME, GetLogDaDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogDaLivelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getLogDaLivelloResponse")
    public JAXBElement<GetLogDaLivelloResponse> createGetLogDaLivelloResponse(GetLogDaLivelloResponse value) {
        return new JAXBElement<GetLogDaLivelloResponse>(_GetLogDaLivelloResponse_QNAME, GetLogDaLivelloResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogDaData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getLogDaData")
    public JAXBElement<GetLogDaData> createGetLogDaData(GetLogDaData value) {
        return new JAXBElement<GetLogDaData>(_GetLogDaData_QNAME, GetLogDaData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogDaDataLivello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getLogDaDataLivello")
    public JAXBElement<GetLogDaDataLivello> createGetLogDaDataLivello(GetLogDaDataLivello value) {
        return new JAXBElement<GetLogDaDataLivello>(_GetLogDaDataLivello_QNAME, GetLogDaDataLivello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogDaLivello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://icar.unibas.it/freesbee/", name = "getLogDaLivello")
    public JAXBElement<GetLogDaLivello> createGetLogDaLivello(GetLogDaLivello value) {
        return new JAXBElement<GetLogDaLivello>(_GetLogDaLivello_QNAME, GetLogDaLivello.class, null, value);
    }

}
