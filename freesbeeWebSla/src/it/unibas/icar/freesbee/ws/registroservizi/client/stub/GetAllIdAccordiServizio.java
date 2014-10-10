
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per getAllIdAccordiServizio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getAllIdAccordiServizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="maxDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="minDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="accordoServizio" type="{http://icar.unibas.it/freesbee/}AccordoServizio" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllIdAccordiServizio", propOrder = {
    "maxDate",
    "minDate",
    "accordoServizio"
})
public class GetAllIdAccordiServizio {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar maxDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar minDate;
    protected AccordoServizio accordoServizio;

    /**
     * Recupera il valore della proprietˆ maxDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMaxDate() {
        return maxDate;
    }

    /**
     * Imposta il valore della proprietˆ maxDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMaxDate(XMLGregorianCalendar value) {
        this.maxDate = value;
    }

    /**
     * Recupera il valore della proprietˆ minDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMinDate() {
        return minDate;
    }

    /**
     * Imposta il valore della proprietˆ minDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMinDate(XMLGregorianCalendar value) {
        this.minDate = value;
    }

    /**
     * Recupera il valore della proprietˆ accordoServizio.
     * 
     * @return
     *     possible object is
     *     {@link AccordoServizio }
     *     
     */
    public AccordoServizio getAccordoServizio() {
        return accordoServizio;
    }

    /**
     * Imposta il valore della proprietˆ accordoServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link AccordoServizio }
     *     
     */
    public void setAccordoServizio(AccordoServizio value) {
        this.accordoServizio = value;
    }

}
