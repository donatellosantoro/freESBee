
package it.unibas.icar.freesbee.ws.client.soggetto.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for removeSoggetto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="removeSoggetto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="soggetto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeSoggetto", propOrder = {
    "soggetto"
})
public class RemoveSoggetto {

    protected long soggetto;

    /**
     * Gets the value of the soggetto property.
     * 
     */
    public long getSoggetto() {
        return soggetto;
    }

    /**
     * Sets the value of the soggetto property.
     * 
     */
    public void setSoggetto(long value) {
        this.soggetto = value;
    }

}
