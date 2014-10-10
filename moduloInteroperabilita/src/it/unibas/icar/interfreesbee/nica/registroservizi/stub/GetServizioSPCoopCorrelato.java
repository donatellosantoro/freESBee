
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getServizioSPCoopCorrelato element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getServizioSPCoopCorrelato">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="idService" type="{http://driver.registry.dao.openspcoop.org}IDServizio"/>
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
    "idService"
})
@XmlRootElement(name = "getServizioSPCoopCorrelato")
public class GetServizioSPCoopCorrelato {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected IDServizio idService;

    /**
     * Gets the value of the idService property.
     * 
     * @return
     *     possible object is
     *     {@link IDServizio }
     *     
     */
    public IDServizio getIdService() {
        return idService;
    }

    /**
     * Sets the value of the idService property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDServizio }
     *     
     */
    public void setIdService(IDServizio value) {
        this.idService = value;
    }

}
