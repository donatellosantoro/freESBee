
package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateGestoriEventi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateGestoriEventi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gestoreEventi" type="{http://ge.freesbee.unibas.it/}gestoreEventi" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateGestoriEventi", propOrder = {
    "gestoreEventi"
})
public class UpdateGestoriEventi {

    protected GestoreEventi gestoreEventi;

    /**
     * Gets the value of the gestoreEventi property.
     * 
     * @return
     *     possible object is
     *     {@link GestoreEventi }
     *     
     */
    public GestoreEventi getGestoreEventi() {
        return gestoreEventi;
    }

    /**
     * Sets the value of the gestoreEventi property.
     * 
     * @param value
     *     allowed object is
     *     {@link GestoreEventi }
     *     
     */
    public void setGestoreEventi(GestoreEventi value) {
        this.gestoreEventi = value;
    }

}
