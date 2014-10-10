
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRS;

/**
 * <p>Java class for getFruitoreServizioSpcoop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getFruitoreServizioSpcoop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servizio" type="{http://icar.unibas.it/freesbee/}servizioRS" minOccurs="0"/>
 *         &lt;element name="soggettoFruitore" type="{http://icar.unibas.it/freesbee/}soggettoRS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFruitoreServizioSpcoop", propOrder = {
    "servizio",
    "soggettoFruitore"
})
public class GetFruitoreServizioSpcoop {

    protected ServizioRS servizio;
    protected SoggettoRS soggettoFruitore;

    /**
     * Gets the value of the servizio property.
     * 
     * @return
     *     possible object is
     *     {@link ServizioRS }
     *     
     */
    public ServizioRS getServizio() {
        return servizio;
    }

    /**
     * Sets the value of the servizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServizioRS }
     *     
     */
    public void setServizio(ServizioRS value) {
        this.servizio = value;
    }

    /**
     * Gets the value of the soggettoFruitore property.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRS }
     *     
     */
    public SoggettoRS getSoggettoFruitore() {
        return soggettoFruitore;
    }

    /**
     * Sets the value of the soggettoFruitore property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRS }
     *     
     */
    public void setSoggettoFruitore(SoggettoRS value) {
        this.soggettoFruitore = value;
    }

}
