
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for existsServizioSpcoopResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="existsServizioSpcoopResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="existsServizioSpcoopReturn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "existsServizioSpcoopReturn"
})
@XmlRootElement(name = "existsServizioSpcoopResponse")
public class ExistsServizioSpcoopResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org")
    protected boolean existsServizioSpcoopReturn;

    /**
     * Gets the value of the existsServizioSpcoopReturn property.
     * 
     */
    public boolean isExistsServizioSpcoopReturn() {
        return existsServizioSpcoopReturn;
    }

    /**
     * Sets the value of the existsServizioSpcoopReturn property.
     * 
     */
    public void setExistsServizioSpcoopReturn(boolean value) {
        this.existsServizioSpcoopReturn = value;
    }

}
