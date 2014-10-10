
package it.unibas.icar.freesbee.ws.client.azione.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addAzione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addAzione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="azione" type="{http://icar.unibas.it/freesbee/}azione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addAzione", propOrder = {
    "azione"
})
public class AddAzione {

    protected Azione azione;

    /**
     * Gets the value of the azione property.
     * 
     * @return
     *     possible object is
     *     {@link Azione }
     *     
     */
    public Azione getAzione() {
        return azione;
    }

    /**
     * Sets the value of the azione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Azione }
     *     
     */
    public void setAzione(Azione value) {
        this.azione = value;
    }

}
