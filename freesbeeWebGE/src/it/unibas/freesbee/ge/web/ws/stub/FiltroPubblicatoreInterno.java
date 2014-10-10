
package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filtroPubblicatoreInterno complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filtroPubblicatoreInterno">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pubblicatore" type="{http://ge.freesbee.unibas.it/}pubblicatoreInterno" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroPubblicatoreInterno", propOrder = {
    "pubblicatore"
})
public class FiltroPubblicatoreInterno {

    protected PubblicatoreInterno pubblicatore;


    /**
     * Gets the value of the pubblicatore property.
     * 
     * @return
     *     possible object is
     *     {@link PubblicatoreInterno }
     *     
     */
    public PubblicatoreInterno getPubblicatore() {
        return pubblicatore;
    }

    /**
     * Sets the value of the pubblicatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link PubblicatoreInterno }
     *     
     */
    public void setPubblicatore(PubblicatoreInterno value) {
        this.pubblicatore = value;
    }

}
