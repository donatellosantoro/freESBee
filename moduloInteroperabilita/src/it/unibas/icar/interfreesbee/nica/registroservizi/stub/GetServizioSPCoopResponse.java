
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getServizioSPCoopResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getServizioSPCoopResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="getServizioSPCoopReturn" type="{http://registry.dao.openspcoop.org}ServizioSpcoop"/>
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
    "getServizioSPCoopReturn"
})
@XmlRootElement(name = "getServizioSPCoopResponse")
public class GetServizioSPCoopResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected ServizioSpcoop getServizioSPCoopReturn;

    /**
     * Gets the value of the getServizioSPCoopReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ServizioSpcoop }
     *     
     */
    public ServizioSpcoop getGetServizioSPCoopReturn() {
        return getServizioSPCoopReturn;
    }

    /**
     * Sets the value of the getServizioSPCoopReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServizioSpcoop }
     *     
     */
    public void setGetServizioSPCoopReturn(ServizioSpcoop value) {
        this.getServizioSPCoopReturn = value;
    }

}
