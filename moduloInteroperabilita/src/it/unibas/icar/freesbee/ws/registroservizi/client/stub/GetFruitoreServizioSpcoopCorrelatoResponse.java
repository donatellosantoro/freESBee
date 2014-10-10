
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.unibas.icar.freesbee.ws.registroservizi.SoggettoRSRisposta;

/**
 * <p>Java class for getFruitoreServizioSpcoopCorrelatoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getFruitoreServizioSpcoopCorrelatoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://icar.unibas.it/freesbee/}soggettoRSRisposta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFruitoreServizioSpcoopCorrelatoResponse", propOrder = {
    "_return"
})
public class GetFruitoreServizioSpcoopCorrelatoResponse {

    @XmlElement(name = "return")
    protected SoggettoRSRisposta _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRSRisposta }
     *     
     */
    public SoggettoRSRisposta getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRSRisposta }
     *     
     */
    public void setReturn(SoggettoRSRisposta value) {
        this._return = value;
    }

}
