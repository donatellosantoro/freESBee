
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for existsServizioSpcoopCorrelatoResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="existsServizioSpcoopCorrelatoResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="existsServizioSpcoopCorrelatoReturn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "existsServizioSpcoopCorrelatoReturn"
})
@XmlRootElement(name = "existsServizioSpcoopCorrelatoResponse")
public class ExistsServizioSpcoopCorrelatoResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org")
    protected boolean existsServizioSpcoopCorrelatoReturn;

    /**
     * Gets the value of the existsServizioSpcoopCorrelatoReturn property.
     * 
     */
    public boolean isExistsServizioSpcoopCorrelatoReturn() {
        return existsServizioSpcoopCorrelatoReturn;
    }

    /**
     * Sets the value of the existsServizioSpcoopCorrelatoReturn property.
     * 
     */
    public void setExistsServizioSpcoopCorrelatoReturn(boolean value) {
        this.existsServizioSpcoopCorrelatoReturn = value;
    }

}
