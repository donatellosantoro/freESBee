
package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filtroPubblicatoreEsterno complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filtroPubblicatoreEsterno">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pubblicatore" type="{http://ge.freesbee.unibas.it/}pubblicatoreEsterno" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroPubblicatoreEsterno", propOrder = {
    "pubblicatore"
})
public class FiltroPubblicatoreEsterno {

    protected PubblicatoreEsterno pubblicatore;

    /**
     * Gets the value of the pubblicatore property.
     * 
     * @return
     *     possible object is
     *     {@link PubblicatoreEsterno }
     *     
     */
    public PubblicatoreEsterno getPubblicatore() {
        return pubblicatore;
    }

    /**
     * Sets the value of the pubblicatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link PubblicatoreEsterno }
     *     
     */
    public void setPubblicatore(PubblicatoreEsterno value) {
        this.pubblicatore = value;
    }

}
