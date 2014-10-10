
package it.unibas.freesbee.websla.ws.web.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibas.freesbeesla.ws.web package. 
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

    private final static QName _GetServiziInf2ErogatiResponse_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "getServiziInf2ErogatiResponse");
    private final static QName _UpdateServizioActive_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "updateServizioActive");
    private final static QName _GetParametriSla_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "getParametriSla");
    private final static QName _GetParametriSlaResponse_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "getParametriSlaResponse");
    private final static QName _GetServiziNicaNonMonitoratiResponse_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "getServiziNicaNonMonitoratiResponse");
    private final static QName _GetServiziInf2Fruiti_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "getServiziInf2Fruiti");
    private final static QName _AddServizio_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "addServizio");
    private final static QName _GetServiziNicaNonMonitorati_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "getServiziNicaNonMonitorati");
    private final static QName _GetServiziInf2FruitiResponse_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "getServiziInf2FruitiResponse");
    private final static QName _GetServiziInf2Erogati_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "getServiziInf2Erogati");
    private final static QName _UpdateServizioActiveResponse_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "updateServizioActiveResponse");
    private final static QName _SOAPFault_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "SOAPFault");
    private final static QName _AddServizioResponse_QNAME = new QName("http://web.ws.freesbeesla.unibas.it/", "addServizioResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibas.freesbeesla.ws.web
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateServizioActive }
     * 
     */
    public UpdateServizioActive createUpdateServizioActive() {
        return new UpdateServizioActive();
    }

    /**
     * Create an instance of {@link GetServiziInf2ErogatiResponse }
     * 
     */
    public GetServiziInf2ErogatiResponse createGetServiziInf2ErogatiResponse() {
        return new GetServiziInf2ErogatiResponse();
    }

    /**
     * Create an instance of {@link GetServiziInf2Fruiti }
     * 
     */
    public GetServiziInf2Fruiti createGetServiziInf2Fruiti() {
        return new GetServiziInf2Fruiti();
    }

    /**
     * Create an instance of {@link AddServizio }
     * 
     */
    public AddServizio createAddServizio() {
        return new AddServizio();
    }

    /**
     * Create an instance of {@link GetParametriSla }
     * 
     */
    public GetParametriSla createGetParametriSla() {
        return new GetParametriSla();
    }

    /**
     * Create an instance of {@link GetParametriSlaResponse }
     * 
     */
    public GetParametriSlaResponse createGetParametriSlaResponse() {
        return new GetParametriSlaResponse();
    }

    /**
     * Create an instance of {@link GetServiziNicaNonMonitoratiResponse }
     * 
     */
    public GetServiziNicaNonMonitoratiResponse createGetServiziNicaNonMonitoratiResponse() {
        return new GetServiziNicaNonMonitoratiResponse();
    }

    /**
     * Create an instance of {@link GetServiziInf2Erogati }
     * 
     */
    public GetServiziInf2Erogati createGetServiziInf2Erogati() {
        return new GetServiziInf2Erogati();
    }

    /**
     * Create an instance of {@link GetServiziInf2FruitiResponse }
     * 
     */
    public GetServiziInf2FruitiResponse createGetServiziInf2FruitiResponse() {
        return new GetServiziInf2FruitiResponse();
    }

    /**
     * Create an instance of {@link GetServiziNicaNonMonitorati }
     * 
     */
    public GetServiziNicaNonMonitorati createGetServiziNicaNonMonitorati() {
        return new GetServiziNicaNonMonitorati();
    }

    /**
     * Create an instance of {@link AddServizioResponse }
     * 
     */
    public AddServizioResponse createAddServizioResponse() {
        return new AddServizioResponse();
    }

    /**
     * Create an instance of {@link UpdateServizioActiveResponse }
     * 
     */
    public UpdateServizioActiveResponse createUpdateServizioActiveResponse() {
        return new UpdateServizioActiveResponse();
    }

    /**
     * Create an instance of {@link SOAPFault }
     * 
     */
    public SOAPFault createSOAPFault() {
        return new SOAPFault();
    }

    /**
     * Create an instance of {@link Servizio }
     * 
     */
    public Servizio createServizio() {
        return new Servizio();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiziInf2ErogatiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "getServiziInf2ErogatiResponse")
    public JAXBElement<GetServiziInf2ErogatiResponse> createGetServiziInf2ErogatiResponse(GetServiziInf2ErogatiResponse value) {
        return new JAXBElement<GetServiziInf2ErogatiResponse>(_GetServiziInf2ErogatiResponse_QNAME, GetServiziInf2ErogatiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateServizioActive }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "updateServizioActive")
    public JAXBElement<UpdateServizioActive> createUpdateServizioActive(UpdateServizioActive value) {
        return new JAXBElement<UpdateServizioActive>(_UpdateServizioActive_QNAME, UpdateServizioActive.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetParametriSla }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "getParametriSla")
    public JAXBElement<GetParametriSla> createGetParametriSla(GetParametriSla value) {
        return new JAXBElement<GetParametriSla>(_GetParametriSla_QNAME, GetParametriSla.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetParametriSlaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "getParametriSlaResponse")
    public JAXBElement<GetParametriSlaResponse> createGetParametriSlaResponse(GetParametriSlaResponse value) {
        return new JAXBElement<GetParametriSlaResponse>(_GetParametriSlaResponse_QNAME, GetParametriSlaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiziNicaNonMonitoratiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "getServiziNicaNonMonitoratiResponse")
    public JAXBElement<GetServiziNicaNonMonitoratiResponse> createGetServiziNicaNonMonitoratiResponse(GetServiziNicaNonMonitoratiResponse value) {
        return new JAXBElement<GetServiziNicaNonMonitoratiResponse>(_GetServiziNicaNonMonitoratiResponse_QNAME, GetServiziNicaNonMonitoratiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiziInf2Fruiti }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "getServiziInf2Fruiti")
    public JAXBElement<GetServiziInf2Fruiti> createGetServiziInf2Fruiti(GetServiziInf2Fruiti value) {
        return new JAXBElement<GetServiziInf2Fruiti>(_GetServiziInf2Fruiti_QNAME, GetServiziInf2Fruiti.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "addServizio")
    public JAXBElement<AddServizio> createAddServizio(AddServizio value) {
        return new JAXBElement<AddServizio>(_AddServizio_QNAME, AddServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiziNicaNonMonitorati }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "getServiziNicaNonMonitorati")
    public JAXBElement<GetServiziNicaNonMonitorati> createGetServiziNicaNonMonitorati(GetServiziNicaNonMonitorati value) {
        return new JAXBElement<GetServiziNicaNonMonitorati>(_GetServiziNicaNonMonitorati_QNAME, GetServiziNicaNonMonitorati.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiziInf2FruitiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "getServiziInf2FruitiResponse")
    public JAXBElement<GetServiziInf2FruitiResponse> createGetServiziInf2FruitiResponse(GetServiziInf2FruitiResponse value) {
        return new JAXBElement<GetServiziInf2FruitiResponse>(_GetServiziInf2FruitiResponse_QNAME, GetServiziInf2FruitiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiziInf2Erogati }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "getServiziInf2Erogati")
    public JAXBElement<GetServiziInf2Erogati> createGetServiziInf2Erogati(GetServiziInf2Erogati value) {
        return new JAXBElement<GetServiziInf2Erogati>(_GetServiziInf2Erogati_QNAME, GetServiziInf2Erogati.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateServizioActiveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "updateServizioActiveResponse")
    public JAXBElement<UpdateServizioActiveResponse> createUpdateServizioActiveResponse(UpdateServizioActiveResponse value) {
        return new JAXBElement<UpdateServizioActiveResponse>(_UpdateServizioActiveResponse_QNAME, UpdateServizioActiveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SOAPFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "SOAPFault")
    public JAXBElement<SOAPFault> createSOAPFault(SOAPFault value) {
        return new JAXBElement<SOAPFault>(_SOAPFault_QNAME, SOAPFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddServizioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.ws.freesbeesla.unibas.it/", name = "addServizioResponse")
    public JAXBElement<AddServizioResponse> createAddServizioResponse(AddServizioResponse value) {
        return new JAXBElement<AddServizioResponse>(_AddServizioResponse_QNAME, AddServizioResponse.class, null, value);
    }

}
