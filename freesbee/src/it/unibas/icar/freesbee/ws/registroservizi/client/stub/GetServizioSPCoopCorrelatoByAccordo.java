
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.unibas.icar.freesbee.ws.registroservizi.AccordoServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;

/**
 * <p>Java class for getServizioSPCoopCorrelatoByAccordo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getServizioSPCoopCorrelatoByAccordo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="soggetto" type="{http://icar.unibas.it/freesbee/}soggettoRS" minOccurs="0"/>
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
@XmlType(name = "getServizioSPCoopCorrelatoByAccordo", propOrder = {
    "soggetto",
    "accordoServizio"
})
public class GetServizioSPCoopCorrelatoByAccordo {

    protected SoggettoRS soggetto;
    protected AccordoServizioRS accordoServizio;

    /**
     * Gets the value of the soggetto property.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRS }
     *     
     */
    public SoggettoRS getSoggetto() {
        return soggetto;
    }

    /**
     * Sets the value of the soggetto property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRS }
     *     
     */
    public void setSoggetto(SoggettoRS value) {
        this.soggetto = value;
    }

    /**
     * Gets the value of the accordoServizio property.
     * 
     * @return
     *     possible object is
     *     {@link AccordoServizio }
     *     
     */
    public AccordoServizioRS getAccordoServizio() {
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
    public void setAccordoServizio(AccordoServizioRS value) {
        this.accordoServizio = value;
    }

}
