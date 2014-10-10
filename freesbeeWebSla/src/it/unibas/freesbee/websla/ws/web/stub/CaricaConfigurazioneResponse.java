
package it.unibas.freesbee.websla.ws.web.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for caricaConfigurazioneResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="caricaConfigurazioneResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datiConfigurazione" type="{http://web.ws.freesbeesla.unibas.it/}datiConfigurazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "caricaConfigurazioneResponse", propOrder = {
    "datiConfigurazione"
})
public class CaricaConfigurazioneResponse {

    protected DatiConfigurazione datiConfigurazione;

    /**
     * Gets the value of the datiConfigurazione property.
     * 
     * @return
     *     possible object is
     *     {@link DatiConfigurazione }
     *     
     */
    public DatiConfigurazione getDatiConfigurazione() {
        return datiConfigurazione;
    }

    /**
     * Sets the value of the datiConfigurazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiConfigurazione }
     *     
     */
    public void setDatiConfigurazione(DatiConfigurazione value) {
        this.datiConfigurazione = value;
    }

}
