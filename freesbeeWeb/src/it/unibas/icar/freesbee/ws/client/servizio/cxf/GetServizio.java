
package it.unibas.icar.freesbee.ws.client.servizio.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getServizio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getServizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servizio" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getServizio", propOrder = {
    "servizio"
})
public class GetServizio {

    protected long servizio;

    /**
     * Gets the value of the servizio property.
     * 
     */
    public long getServizio() {
        return servizio;
    }

    /**
     * Sets the value of the servizio property.
     * 
     */
    public void setServizio(long value) {
        this.servizio = value;
    }

}
