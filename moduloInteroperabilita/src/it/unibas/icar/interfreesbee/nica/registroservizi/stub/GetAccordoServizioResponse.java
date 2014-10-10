
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAccordoServizioResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getAccordoServizioResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="getAccordoServizioReturn" type="{http://registry.dao.openspcoop.org}AccordoServizio"/>
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
    "getAccordoServizioReturn"
})
@XmlRootElement(name = "getAccordoServizioResponse")
public class GetAccordoServizioResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected AccordoServizio getAccordoServizioReturn;

    /**
     * Gets the value of the getAccordoServizioReturn property.
     * 
     * @return
     *     possible object is
     *     {@link AccordoServizio }
     *     
     */
    public AccordoServizio getGetAccordoServizioReturn() {
        return getAccordoServizioReturn;
    }

    /**
     * Sets the value of the getAccordoServizioReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccordoServizio }
     *     
     */
    public void setGetAccordoServizioReturn(AccordoServizio value) {
        this.getAccordoServizioReturn = value;
    }

}
