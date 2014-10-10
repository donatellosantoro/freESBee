
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSoggettoSPCoopResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getSoggettoSPCoopResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="getSoggettoSPCoopReturn" type="{http://registry.dao.openspcoop.org}SoggettoSpcoop"/>
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
    "getSoggettoSPCoopReturn"
})
@XmlRootElement(name = "getSoggettoSPCoopResponse")
public class GetSoggettoSPCoopResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected SoggettoSpcoop getSoggettoSPCoopReturn;

    /**
     * Gets the value of the getSoggettoSPCoopReturn property.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoSpcoop }
     *     
     */
    public SoggettoSpcoop getGetSoggettoSPCoopReturn() {
        return getSoggettoSPCoopReturn;
    }

    /**
     * Sets the value of the getSoggettoSPCoopReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoSpcoop }
     *     
     */
    public void setGetSoggettoSPCoopReturn(SoggettoSpcoop value) {
        this.getSoggettoSPCoopReturn = value;
    }

}
