
package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configurazioneSP complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configurazioneSP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="autenticazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nomeUtenteSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passwordSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="risorsa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="risorsaPdConsegna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="risorsaPdNotifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="risorsaPdPubblica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlFreesbeeSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configurazioneSP", propOrder = {
    "autenticazione",
    "id",
    "nomeUtenteSP",
    "passwordSP",
    "risorsa",
    "risorsaPdConsegna",
    "risorsaPdNotifica",
    "risorsaPdPubblica",
    "urlFreesbeeSP"
})
public class ConfigurazioneSP {

    protected String autenticazione;
    protected long id;
    protected String nomeUtenteSP;
    protected String passwordSP;
    protected String risorsa;
    protected String risorsaPdConsegna;
    protected String risorsaPdNotifica;
    protected String risorsaPdPubblica;
    protected String urlFreesbeeSP;

    /**
     * Gets the value of the autenticazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutenticazione() {
        return autenticazione;
    }

    /**
     * Sets the value of the autenticazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutenticazione(String value) {
        this.autenticazione = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nomeUtenteSP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeUtenteSP() {
        return nomeUtenteSP;
    }

    /**
     * Sets the value of the nomeUtenteSP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeUtenteSP(String value) {
        this.nomeUtenteSP = value;
    }

    /**
     * Gets the value of the passwordSP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswordSP() {
        return passwordSP;
    }

    /**
     * Sets the value of the passwordSP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswordSP(String value) {
        this.passwordSP = value;
    }

    /**
     * Gets the value of the risorsa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisorsa() {
        return risorsa;
    }

    /**
     * Sets the value of the risorsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisorsa(String value) {
        this.risorsa = value;
    }

    /**
     * Gets the value of the risorsaPdConsegna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisorsaPdConsegna() {
        return risorsaPdConsegna;
    }

    /**
     * Sets the value of the risorsaPdConsegna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisorsaPdConsegna(String value) {
        this.risorsaPdConsegna = value;
    }

    /**
     * Gets the value of the risorsaPdNotifica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisorsaPdNotifica() {
        return risorsaPdNotifica;
    }

    /**
     * Sets the value of the risorsaPdNotifica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisorsaPdNotifica(String value) {
        this.risorsaPdNotifica = value;
    }

    /**
     * Gets the value of the risorsaPdPubblica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisorsaPdPubblica() {
        return risorsaPdPubblica;
    }

    /**
     * Sets the value of the risorsaPdPubblica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisorsaPdPubblica(String value) {
        this.risorsaPdPubblica = value;
    }

    /**
     * Gets the value of the urlFreesbeeSP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlFreesbeeSP() {
        return urlFreesbeeSP;
    }

    /**
     * Sets the value of the urlFreesbeeSP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlFreesbeeSP(String value) {
        this.urlFreesbeeSP = value;
    }

}
