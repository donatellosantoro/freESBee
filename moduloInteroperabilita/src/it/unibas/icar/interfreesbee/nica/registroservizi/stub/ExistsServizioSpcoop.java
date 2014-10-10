
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for existsServizioSpcoop element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="existsServizioSpcoop">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="idServizio" type="{http://driver.registry.dao.openspcoop.org}IDServizio"/>
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
    "idServizio"
})
@XmlRootElement(name = "existsServizioSpcoop")
public class ExistsServizioSpcoop {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected IDServizio idServizio;

    /**
     * Gets the value of the idServizio property.
     * 
     * @return
     *     possible object is
     *     {@link IDServizio }
     *     
     */
    public IDServizio getIdServizio() {
        return idServizio;
    }

    /**
     * Sets the value of the idServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDServizio }
     *     
     */
    public void setIdServizio(IDServizio value) {
        this.idServizio = value;
    }

}
