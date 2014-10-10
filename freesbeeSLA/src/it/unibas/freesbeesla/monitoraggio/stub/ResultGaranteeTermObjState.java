
package it.unibas.freesbeesla.monitoraggio.stub;

import it.unibas.freesbeesla.monitoraggio.stub.ObserveInterval;
import it.unibas.freesbeesla.monitoraggio.stub.GuaranteeStateType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Elemento che raggruppa il Service Level Objective e il suo stato
 * 
 * <p>Java class for ResultGaranteeTermObjState element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="ResultGaranteeTermObjState">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}GuaranteeTermName"/>
 *           &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}GuaranteeState"/>
 *           &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}ObserveInterval"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "guaranteeTermName",
    "guaranteeState",
    "observeInterval"
})
@XmlRootElement(name = "ResultGaranteeTermObjState")
public class ResultGaranteeTermObjState {

    @XmlElement(name = "GuaranteeTermName", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected String guaranteeTermName;
    @XmlElement(name = "GuaranteeState", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected GuaranteeStateType guaranteeState;
    @XmlElement(name = "ObserveInterval", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected ObserveInterval observeInterval;

    /**
     * Gets the value of the guaranteeTermName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteeTermName() {
        return guaranteeTermName;
    }

    /**
     * Sets the value of the guaranteeTermName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteeTermName(String value) {
        this.guaranteeTermName = value;
    }

    /**
     * Gets the value of the guaranteeState property.
     * 
     * @return
     *     possible object is
     *     {@link GuaranteeStateType }
     *     
     */
    public GuaranteeStateType getGuaranteeState() {
        return guaranteeState;
    }

    /**
     * Sets the value of the guaranteeState property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuaranteeStateType }
     *     
     */
    public void setGuaranteeState(GuaranteeStateType value) {
        this.guaranteeState = value;
    }

    /**
     * Gets the value of the observeInterval property.
     * 
     * @return
     *     possible object is
     *     {@link ObserveInterval }
     *     
     */
    public ObserveInterval getObserveInterval() {
        return observeInterval;
    }

    /**
     * Sets the value of the observeInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObserveInterval }
     *     
     */
    public void setObserveInterval(ObserveInterval value) {
        this.observeInterval = value;
    }

}
