
package it.unibas.freesbeesla.monitoraggio.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Intervallo di tempo in cui è necessario si inizia a calcolare  il paramentro Service Level Objective
 * 
 * <p>Java class for ObserveInterval element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="ObserveInterval">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="DateIn" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *           &lt;element ref="{http://sistemamonitoraggio.freesbee.unibas.it/}DateFn"/>
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
@XmlType(name = "", propOrder = {
    "dateIn",
    "dateFn"
})
@XmlRootElement(name = "ObserveInterval")
public class ObserveInterval {

    @XmlElement(name = "DateIn", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
       @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateIn;
    @XmlElement(name = "DateFn", namespace = "http://sistemamonitoraggio.freesbee.unibas.it/", required = true)
           @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateFn;

    /**
     * Gets the value of the dateIn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateIn() {
        return dateIn;
    }

    /**
     * Sets the value of the dateIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateIn(XMLGregorianCalendar value) {
        this.dateIn = value;
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
