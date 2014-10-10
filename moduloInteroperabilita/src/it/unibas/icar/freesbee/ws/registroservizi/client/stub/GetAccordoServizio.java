
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.unibas.icar.freesbee.modello.AccordoServizio;

/**
 * <p>Java class for getAccordoServizio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAccordoServizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "getAccordoServizio", propOrder = {
    "accordoServizio"
})
public class GetAccordoServizio {

    protected AccordoServizio accordoServizio;

    /**
     * Gets the value of the accordoServizio property.
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
     * Sets the value of the accordoServizio property.
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
