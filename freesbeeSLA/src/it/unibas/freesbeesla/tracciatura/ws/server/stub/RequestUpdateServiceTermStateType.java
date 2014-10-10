package it.unibas.freesbeesla.tracciatura.ws.server.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for Request_update_service_term_stateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Request_update_service_term_stateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdInitiator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdResponder" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceState" type="{http://sistematracciatura.freesbee.unibas.it/}ServiceTermStateType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request_update_service_term_stateType", propOrder = {
"idService",
"idInitiator",
"idResponder",
"serviceState"
})
public class RequestUpdateServiceTermStateType {

    @XmlElement(name = "IdService", required = true, nillable = true)
    protected String idService;
    @XmlElement(name = "IdInitiator", required = true, nillable = true)
    protected String idInitiator;
    @XmlElement(name = "IdResponder", required = true, nillable = true)
    protected String idResponder;
    @XmlElement(name = "ServiceState", required = true, nillable = true)
    protected ServiceTermStateType serviceState;

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
     * Gets the value of the serviceState property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTermStateType }
     *     
     */
    public ServiceTermStateType getServiceState() {
        return serviceState;
    }

    /**
     * Sets the value of the serviceState property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTermStateType }
     *     
     */
    public void setServiceState(ServiceTermStateType value) {
        this.serviceState = value;
    }
}
