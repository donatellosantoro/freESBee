
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for existsSoggettoSPCoopResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="existsSoggettoSPCoopResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="existsSoggettoSPCoopReturn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "existsSoggettoSPCoopReturn"
})
@XmlRootElement(name = "existsSoggettoSPCoopResponse")
public class ExistsSoggettoSPCoopResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org")
    protected boolean existsSoggettoSPCoopReturn;

    /**
     * Gets the value of the existsSoggettoSPCoopReturn property.
     * 
     */
    public boolean isExistsSoggettoSPCoopReturn() {
        return existsSoggettoSPCoopReturn;
    }

    /**
     * Sets the value of the existsSoggettoSPCoopReturn property.
     * 
     */
    public void setExistsSoggettoSPCoopReturn(boolean value) {
        this.existsSoggettoSPCoopReturn = value;
    }

}
