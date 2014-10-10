
package it.unibas.freesbeesla.monitoraggio.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestServiceTermStateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestServiceTermStateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}IdService"/>
 *         &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}IdInitiator"/>
 *         &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}IdResponder"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "idService",
    "idInitiator",
    "idResponder"
})
public class RequestServiceTermStateType {

    @XmlElement(name = "IdService", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected String idService;
    @XmlElement(name = "IdInitiator", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected String idInitiator;
    @XmlElement(name = "IdResponder", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected String idResponder;

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

}
