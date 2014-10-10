
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getServizioSPCoopCorrelatoResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getServizioSPCoopCorrelatoResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="getServizioSPCoopCorrelatoReturn" type="{http://registry.dao.openspcoop.org}ServizioSpcoop"/>
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
    "getServizioSPCoopCorrelatoReturn"
})
@XmlRootElement(name = "getServizioSPCoopCorrelatoResponse")
public class GetServizioSPCoopCorrelatoResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected ServizioSpcoop getServizioSPCoopCorrelatoReturn;

    /**
     * Gets the value of the getServizioSPCoopCorrelatoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ServizioSpcoop }
     *     
     */
    public ServizioSpcoop getGetServizioSPCoopCorrelatoReturn() {
        return getServizioSPCoopCorrelatoReturn;
    }

    /**
     * Sets the value of the getServizioSPCoopCorrelatoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServizioSpcoop }
     *     
     */
    public void setGetServizioSPCoopCorrelatoReturn(ServizioSpcoop value) {
        this.getServizioSPCoopCorrelatoReturn = value;
    }

}
