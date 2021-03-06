
package it.unibas.icar.freesbee.ws.client.azione.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAzione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAzione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="azione" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAzione", propOrder = {
    "azione"
})
public class GetAzione {

    protected long azione;

    /**
     * Gets the value of the azione property.
     * 
     */
    public long getAzione() {
        return azione;
    }

    /**
     * Sets the value of the azione property.
     * 
     */
    public void setAzione(long value) {
        this.azione = value;
    }

}
