
package it.unibas.icar.freesbee.ws.client.profiloegov.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for removeProfiloEGov complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="removeProfiloEGov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="profiloEGov" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeProfiloEGov", propOrder = {
    "profiloEGov"
})
public class RemoveProfiloEGov {

    protected long profiloEGov;

    /**
     * Gets the value of the profiloEGov property.
     * 
     */
    public long getProfiloEGov() {
        return profiloEGov;
    }

    /**
     * Sets the value of the profiloEGov property.
     * 
     */
    public void setProfiloEGov(long value) {
        this.profiloEGov = value;
    }

}
