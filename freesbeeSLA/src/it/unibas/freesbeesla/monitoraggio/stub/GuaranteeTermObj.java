package it.unibas.freesbeesla.monitoraggio.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Contiene l'identificatore del Service Level Objective e l'intervallo di osservazione di questo parametro
 * 
 * <p>Java class for GuaranteeTermObj element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="GuaranteeTermObj">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}GuaranteeTermName"/>
 *           &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}DateFn" minOccurs="0"/>
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
"dateFn"
})
@XmlRootElement(name = "GuaranteeTermObj")
public class GuaranteeTermObj {

    @XmlElement(name = "GuaranteeTermName", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected String guaranteeTermName;
    @XmlSchemaType(name = "dateTime")
    @XmlElement(namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
    protected XMLGregorianCalendar dateFn;

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
     * Gets the value of the dateFn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFn() {
        return dateFn;
    }

    /**
     * Sets the value of the dateFn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFn(XMLGregorianCalendar value) {
        this.dateFn = value;
    }
}
