package it.unibas.freesbeesla.monitoraggio.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestServiceGuaranteeTermStateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestServiceGuaranteeTermStateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}IdService"/>
 *         &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}IdInitiator"/>
 *         &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}IdResponder"/>
 *         &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}GuaranteeTermObj" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="RequestServiceGuaranteeTermStateType", propOrder = {
    "idService",
    "idInitiator",
    "idResponder",
    "guaranteeTermObj"
})
public class RequestServiceGuaranteeTermStateType {

    @XmlElement(name = "IdService", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected String idService;
    @XmlElement(name = "IdInitiator", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected String idInitiator;
    @XmlElement(name = "IdResponder", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected String idResponder;
    @XmlElement(name = "GuaranteeTermObj", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected List<GuaranteeTermObj> guaranteeTermObj;

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
     * Gets the value of the guaranteeTermObj property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the guaranteeTermObj property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGuaranteeTermObj().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GuaranteeTermObj }
     * 
     * 
     */
    public List<GuaranteeTermObj> getGuaranteeTermObj() {
        if (guaranteeTermObj == null) {
            guaranteeTermObj = new ArrayList<GuaranteeTermObj>();
        }
        return this.guaranteeTermObj;
    }

}
