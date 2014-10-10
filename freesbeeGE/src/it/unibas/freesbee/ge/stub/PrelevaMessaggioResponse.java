
package it.unibas.freesbee.ge.stub;

import it.unibas.freesbee.ge.modello.ConsegnaEventoPubblicato;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prelevaMessaggioResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prelevaMessaggioResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consegnaMessaggioNotificato" type="{http://ge.freesbee.unibas.it/}ConsegnaEventoPubblicato" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prelevaMessaggioResponse", propOrder = {
    "consegnaMessaggioNotificato"
})
public class PrelevaMessaggioResponse {

    protected ConsegnaEventoPubblicato consegnaMessaggioNotificato;

    /**
     * Gets the value of the consegnaMessaggioNotificato property.
     * 
     * @return
     *     possible object is
     *     {@link ConsegnaEventoPubblicato }
     *     
     */
    public ConsegnaEventoPubblicato getConsegnaMessaggioNotificato() {
        return consegnaMessaggioNotificato;
    }

    /**
     * Sets the value of the consegnaMessaggioNotificato property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsegnaEventoPubblicato }
     *     
     */
    public void setConsegnaMessaggioNotificato(ConsegnaEventoPubblicato value) {
        this.consegnaMessaggioNotificato = value;
    }

}
