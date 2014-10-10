
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getServizioSPCoopCorrelatoByAccordoResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getServizioSPCoopCorrelatoByAccordoResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="getServizioSPCoopCorrelatoByAccordoReturn" type="{http://registry.dao.openspcoop.org}ServizioSpcoop"/>
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
    "getServizioSPCoopCorrelatoByAccordoReturn"
})
@XmlRootElement(name = "getServizioSPCoopCorrelatoByAccordoResponse")
public class GetServizioSPCoopCorrelatoByAccordoResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected ServizioSpcoop getServizioSPCoopCorrelatoByAccordoReturn;

    /**
     * Gets the value of the getServizioSPCoopCorrelatoByAccordoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ServizioSpcoop }
     *     
     */
    public ServizioSpcoop getGetServizioSPCoopCorrelatoByAccordoReturn() {
        return getServizioSPCoopCorrelatoByAccordoReturn;
    }

    /**
     * Sets the value of the getServizioSPCoopCorrelatoByAccordoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServizioSpcoop }
     *     
     */
    public void setGetServizioSPCoopCorrelatoByAccordoReturn(ServizioSpcoop value) {
        this.getServizioSPCoopCorrelatoByAccordoReturn = value;
    }

}
