
package it.unibas.silvio.ws.istanza.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for avviaIstanza complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="avviaIstanza">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_istanza" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "avviaIstanza", propOrder = {
    "idIstanza"
})
public class AvviaIstanza {

    @XmlElement(name = "id_istanza")
    protected long idIstanza;

    /**
     * Gets the value of the idIstanza property.
     * 
     */
    public long getIdIstanza() {
        return idIstanza;
    }

    /**
     * Sets the value of the idIstanza property.
     * 
     */
    public void setIdIstanza(long value) {
        this.idIstanza = value;
    }

}
