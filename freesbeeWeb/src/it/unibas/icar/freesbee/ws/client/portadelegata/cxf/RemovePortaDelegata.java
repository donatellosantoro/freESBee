
package it.unibas.icar.freesbee.ws.client.portadelegata.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for removePortaDelegata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="removePortaDelegata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="portaDelegata" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removePortaDelegata", propOrder = {
    "portaDelegata"
})
public class RemovePortaDelegata {

    protected long portaDelegata;

    /**
     * Gets the value of the portaDelegata property.
     * 
     */
    public long getPortaDelegata() {
        return portaDelegata;
    }

    /**
     * Sets the value of the portaDelegata property.
     * 
     */
    public void setPortaDelegata(long value) {
        this.portaDelegata = value;
    }

}
