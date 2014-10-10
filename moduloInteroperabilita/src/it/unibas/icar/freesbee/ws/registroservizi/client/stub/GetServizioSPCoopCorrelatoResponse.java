
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import it.unibas.icar.freesbee.ws.registroservizi.ServizioRSRisposta;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getServizioSPCoopCorrelatoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getServizioSPCoopCorrelatoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://icar.unibas.it/freesbee/}servizioRSRisposta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getServizioSPCoopCorrelatoResponse", propOrder = {
    "_return"
})
public class GetServizioSPCoopCorrelatoResponse {

    @XmlElement(name = "return")
    protected ServizioRSRisposta _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ServizioRSRisposta }
     *     
     */
    public ServizioRSRisposta getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServizioRSRisposta }
     *     
     */
    public void setReturn(ServizioRSRisposta value) {
        this._return = value;
    }

}
