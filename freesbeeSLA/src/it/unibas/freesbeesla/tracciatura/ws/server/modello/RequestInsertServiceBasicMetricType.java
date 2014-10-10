package it.unibas.freesbeesla.tracciatura.ws.server.modello;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for Request_insert_service_basic_metricType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Request_insert_service_basic_metricType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdInitiator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdResponder" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BasicMetric" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BasicMetricValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request_insert_service_basic_metricType", propOrder = {
"idService",
"idInitiator",
"idResponder",
"basicMetric",
"basicMetricValue",
"date"
})
public class RequestInsertServiceBasicMetricType {

    @XmlElement(name = "IdService", required = true, nillable = true)
    protected String idService;
    @XmlElement(name = "IdInitiator", required = true, nillable = true)
    protected String idInitiator;
    @XmlElement(name = "IdResponder", required = true, nillable = true)
    protected String idResponder;
    @XmlElement(name = "BasicMetric", required = true, nillable = true)
    protected String basicMetric;
    @XmlElement(name = "BasicMetricValue")
    protected double basicMetricValue;
    @XmlElement(name = "Date", required = true, nillable = true)
    protected XMLGregorianCalendar date;

    /**
     * Gets the value of the idService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdService() {
        return idService;
    }

    /**
     * Sets the value of the idService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdService(String value) {
        this.idService = value;
    }

    /**
     * Gets the value of the idInitiator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdInitiator() {
        return idInitiator;
    }

    /**
     * Sets the value of the idInitiator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdInitiator(String value) {
        this.idInitiator = value;
    }

    /**
     * Gets the value of the idResponder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdResponder() {
        return idResponder;
    }

    /**
     * Sets the value of the idResponder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdResponder(String value) {
        this.idResponder = value;
    }

    /**
     * Gets the value of the basicMetric property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasicMetric() {
        return basicMetric;
    }

    /**
     * Sets the value of the basicMetric property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasicMetric(String value) {
        this.basicMetric = value;
    }

    /**
     * Gets the value of the basicMetricValue property.
     * 
     */
    public double getBasicMetricValue() {
        return basicMetricValue;
    }

    /**
     * Sets the value of the basicMetricValue property.
     * 
     */
    public void setBasicMetricValue(double value) {
        this.basicMetricValue = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }
}
