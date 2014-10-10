
package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datiConfigurazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="datiConfigurazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configurazione" type="{http://ge.freesbee.unibas.it/}configurazione" minOccurs="0"/>
 *         &lt;element name="configurazioneSP" type="{http://ge.freesbee.unibas.it/}configurazioneSP" minOccurs="0"/>
 *         &lt;element name="protezioneSP" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datiConfigurazione", propOrder = {
    "configurazione",
    "configurazioneSP",
    "protezioneSP"
})
public class DatiConfigurazione {

    protected Configurazione configurazione;
    protected ConfigurazioneSP configurazioneSP;
    protected boolean protezioneSP;

    /**
     * Gets the value of the configurazione property.
     * 
     * @return
     *     possible object is
     *     {@link Configurazione }
     *     
     */
    public Configurazione getConfigurazione() {
        return configurazione;
    }

    /**
     * Sets the value of the configurazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Configurazione }
     *     
     */
    public void setConfigurazione(Configurazione value) {
        this.configurazione = value;
    }

    /**
     * Gets the value of the configurazioneSP property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurazioneSP }
     *     
     */
    public ConfigurazioneSP getConfigurazioneSP() {
        return configurazioneSP;
    }

    /**
     * Sets the value of the configurazioneSP property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurazioneSP }
     *     
     */
    public void setConfigurazioneSP(ConfigurazioneSP value) {
        this.configurazioneSP = value;
    }

    /**
     * Gets the value of the protezioneSP property.
     * 
     */
    public boolean isProtezioneSP() {
        return protezioneSP;
    }

    /**
     * Sets the value of the protezioneSP property.
     * 
     */
    public void setProtezioneSP(boolean value) {
        this.protezioneSP = value;
    }

}
