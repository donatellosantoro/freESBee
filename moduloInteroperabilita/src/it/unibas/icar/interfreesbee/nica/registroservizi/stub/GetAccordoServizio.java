
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAccordoServizio element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getAccordoServizio">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="nomeAccordo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "nomeAccordo"
})
@XmlRootElement(name = "getAccordoServizio")
public class GetAccordoServizio {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected String nomeAccordo;

    /**
     * Gets the value of the nomeAccordo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeAccordo() {
        return nomeAccordo;
    }

    /**
     * Sets the value of the nomeAccordo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeAccordo(String value) {
        this.nomeAccordo = value;
    }

}
