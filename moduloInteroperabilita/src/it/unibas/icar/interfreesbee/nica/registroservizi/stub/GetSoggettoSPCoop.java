
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSoggettoSPCoop element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getSoggettoSPCoop">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="idSoggetto" type="{http://driver.registry.dao.openspcoop.org}IDSoggetto"/>
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
    "idSoggetto"
})
@XmlRootElement(name = "getSoggettoSPCoop")
public class GetSoggettoSPCoop {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected IDSoggetto idSoggetto;

    /**
     * Gets the value of the idSoggetto property.
     * 
     * @return
     *     possible object is
     *     {@link IDSoggetto }
     *     
     */
    public IDSoggetto getIdSoggetto() {
        return idSoggetto;
    }

    /**
     * Sets the value of the idSoggetto property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDSoggetto }
     *     
     */
    public void setIdSoggetto(IDSoggetto value) {
        this.idSoggetto = value;
    }

}
