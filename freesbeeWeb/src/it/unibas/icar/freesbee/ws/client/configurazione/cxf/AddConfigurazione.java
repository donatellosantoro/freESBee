
package it.unibas.icar.freesbee.ws.client.configurazione.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addConfigurazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addConfigurazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configurazione" type="{http://icar.unibas.it/freesbee/}configurazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addConfigurazione", propOrder = {
    "configurazione"
})
public class AddConfigurazione {

    protected Configurazione configurazione;

    /**
     * Gets the value of the configurazione property.
     * 
     * @return
     *     possible object is
     *     {@link Configurazione }
     *     
     */
    public Configurazione getConfigurazione() {
        return configurazione;
    }

    /**
     * Sets the value of the configurazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Configurazione }
     *     
     */
    public void setConfigurazione(Configurazione value) {
        this.configurazione = value;
    }

}
