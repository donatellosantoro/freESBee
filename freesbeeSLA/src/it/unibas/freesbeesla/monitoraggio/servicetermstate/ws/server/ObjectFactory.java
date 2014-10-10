
package it.unibas.freesbeesla.monitoraggio.servicetermstate.ws.server;

import it.unibas.freesbeesla.monitoraggio.stub.ResponseServiceTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.ServiceTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.GuaranteeStateType;
import it.unibas.freesbeesla.monitoraggio.stub.GuaranteeTermObj;
import it.unibas.freesbeesla.monitoraggio.stub.ObserveInterval;
import it.unibas.freesbeesla.monitoraggio.stub.RequestServiceGuaranteeTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.RequestServiceTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.ResponseServiceGuaranteeTermStateType;
import it.unibas.freesbeesla.monitoraggio.stub.ResultGaranteeTermObjState;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the icarsla.sistemamonitoraggio package. 
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

    private final static QName _GetServiceGuaranteeTermState_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "getServiceGuaranteeTermState");
    private final static QName _GuaranteeTermName_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "GuaranteeTermName");
    private final static QName _IdInitiator_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "IdInitiator");
    private final static QName _IdService_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "IdService");
    private final static QName _GetServiceTermState_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "getServiceTermState");
    private final static QName _GuaranteeState_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "GuaranteeState");
    private final static QName _ResponsegetServiceGuaranteeTermState_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "ResponsegetServiceGuaranteeTermState");
    private final static QName _IdResponder_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "IdResponder");
    private final static QName _ServiceTermState_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "ServiceTermState");
    private final static QName _DateFn_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "DateFn");
    private final static QName _ResponsegetServiceTermState_QNAME = new QName("http://sistemamonitoraggio.freesbee.unibas.it/", "ResponsegetServiceTermState");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: icarsla.sistemamonitoraggio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GuaranteeTermObj }
     * 
     */
    public GuaranteeTermObj createGuaranteeTermObj() {
        return new GuaranteeTermObj();
    }

    /**
     * Create an instance of {@link ResultGaranteeTermObjState }
     * 
     */
    public ResultGaranteeTermObjState createResultGaranteeTermObjState() {
        return new ResultGaranteeTermObjState();
    }

    /**
     * Create an instance of {@link ResponseServiceTermStateType }
     * 
     */
    public ResponseServiceTermStateType createResponseServiceTermStateType() {
        return new ResponseServiceTermStateType();
    }

    /**
     * Create an instance of {@link ResponseServiceGuaranteeTermStateType }
     * 
     */
    public ResponseServiceGuaranteeTermStateType createResponseServiceGuaranteeTermStateType() {
        return new ResponseServiceGuaranteeTermStateType();
    }

    /**
     * Create an instance of {@link RequestServiceGuaranteeTermStateType }
     * 
     */
    public RequestServiceGuaranteeTermStateType createRequestServiceGuaranteeTermStateType() {
        return new RequestServiceGuaranteeTermStateType();
    }

    /**
     * Create an instance of {@link RequestServiceTermStateType }
     * 
     */
    public RequestServiceTermStateType createRequestServiceTermStateType() {
        return new RequestServiceTermStateType();
    }

    /**
     * Create an instance of {@link ObserveInterval }
     * 
     */
    public ObserveInterval createObserveInterval() {
        return new ObserveInterval();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestServiceGuaranteeTermStateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "getServiceGuaranteeTermState")
    public JAXBElement<RequestServiceGuaranteeTermStateType> createGetServiceGuaranteeTermState(RequestServiceGuaranteeTermStateType value) {
        return new JAXBElement<RequestServiceGuaranteeTermStateType>(_GetServiceGuaranteeTermState_QNAME, RequestServiceGuaranteeTermStateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "GuaranteeTermName")
    public JAXBElement<String> createGuaranteeTermName(String value) {
        return new JAXBElement<String>(_GuaranteeTermName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "IdInitiator")
    public JAXBElement<String> createIdInitiator(String value) {
        return new JAXBElement<String>(_IdInitiator_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "IdService")
    public JAXBElement<String> createIdService(String value) {
        return new JAXBElement<String>(_IdService_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestServiceTermStateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "getServiceTermState")
    public JAXBElement<RequestServiceTermStateType> createGetServiceTermState(RequestServiceTermStateType value) {
        return new JAXBElement<RequestServiceTermStateType>(_GetServiceTermState_QNAME, RequestServiceTermStateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GuaranteeStateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "GuaranteeState")
    public JAXBElement<GuaranteeStateType> createGuaranteeState(GuaranteeStateType value) {
        return new JAXBElement<GuaranteeStateType>(_GuaranteeState_QNAME, GuaranteeStateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseServiceGuaranteeTermStateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "ResponsegetServiceGuaranteeTermState")
    public JAXBElement<ResponseServiceGuaranteeTermStateType> createResponsegetServiceGuaranteeTermState(ResponseServiceGuaranteeTermStateType value) {
        return new JAXBElement<ResponseServiceGuaranteeTermStateType>(_ResponsegetServiceGuaranteeTermState_QNAME, ResponseServiceGuaranteeTermStateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "IdResponder")
    public JAXBElement<String> createIdResponder(String value) {
        return new JAXBElement<String>(_IdResponder_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceTermStateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "ServiceTermState")
    public JAXBElement<ServiceTermStateType> createServiceTermState(ServiceTermStateType value) {
        return new JAXBElement<ServiceTermStateType>(_ServiceTermState_QNAME, ServiceTermStateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "DateFn")
    public JAXBElement<XMLGregorianCalendar> createDateFn(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateFn_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseServiceTermStateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", name = "ResponsegetServiceTermState")
    public JAXBElement<ResponseServiceTermStateType> createResponsegetServiceTermState(ResponseServiceTermStateType value) {
        return new JAXBElement<ResponseServiceTermStateType>(_ResponsegetServiceTermState_QNAME, ResponseServiceTermStateType.class, null, value);
    }

}
