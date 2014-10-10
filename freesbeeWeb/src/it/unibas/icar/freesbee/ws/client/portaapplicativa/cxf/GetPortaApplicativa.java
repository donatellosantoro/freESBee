
package it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPortaApplicativa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPortaApplicativa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="portaApplicativa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPortaApplicativa", propOrder = {
    "portaApplicativa"
})
public class GetPortaApplicativa {

    protected long portaApplicativa;

    /**
     * Gets the value of the portaApplicativa property.
     * 
     */
    public long getPortaApplicativa() {
        return portaApplicativa;
    }

    /**
     * Sets the value of the portaApplicativa property.
     * 
     */
    public void setPortaApplicativa(long value) {
        this.portaApplicativa = value;
    }

}
