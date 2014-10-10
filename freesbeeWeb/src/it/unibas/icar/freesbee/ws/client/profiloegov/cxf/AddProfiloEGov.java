
package it.unibas.icar.freesbee.ws.client.profiloegov.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addProfiloEGov complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addProfiloEGov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="profiloEGov" type="{http://icar.unibas.it/freesbee/}profiloEGov" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addProfiloEGov", propOrder = {
    "profiloEGov"
})
public class AddProfiloEGov {

    protected ProfiloEGov profiloEGov;

    /**
     * Gets the value of the profiloEGov property.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloEGov }
     *     
     */
    public ProfiloEGov getProfiloEGov() {
        return profiloEGov;
    }

    /**
     * Sets the value of the profiloEGov property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloEGov }
     *     
     */
    public void setProfiloEGov(ProfiloEGov value) {
        this.profiloEGov = value;
    }

}
