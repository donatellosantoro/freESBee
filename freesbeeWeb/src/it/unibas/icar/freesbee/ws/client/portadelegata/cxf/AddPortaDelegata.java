
package it.unibas.icar.freesbee.ws.client.portadelegata.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addPortaDelegata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPortaDelegata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="portaDelegata" type="{http://icar.unibas.it/freesbee/}portaDelegata" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPortaDelegata", propOrder = {
    "portaDelegata"
})
public class AddPortaDelegata {

    protected PortaDelegata portaDelegata;

    /**
     * Gets the value of the portaDelegata property.
     * 
     * @return
     *     possible object is
     *     {@link PortaDelegata }
     *     
     */
    public PortaDelegata getPortaDelegata() {
        return portaDelegata;
    }

    /**
     * Sets the value of the portaDelegata property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortaDelegata }
     *     
     */
    public void setPortaDelegata(PortaDelegata value) {
        this.portaDelegata = value;
    }

}
