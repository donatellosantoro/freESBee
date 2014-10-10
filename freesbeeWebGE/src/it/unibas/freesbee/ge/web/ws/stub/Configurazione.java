
package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configurazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configurazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nomeGestoreEventi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeServizioConsegna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeServizioNotifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeServizioPubblica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pdConsegna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pdNotifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pdPubblica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scadenzaMessaggi" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoGestoreEventi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoServizioConsegna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoServizioNotifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoServizioPubblica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configurazione", propOrder = {
    "id",
    "azioneServizioConsegna",
    "azioneServizioNotifica",
    "nomeGestoreEventi",
    "nomeServizioConsegna",
    "nomeServizioNotifica",
    "nomeServizioPubblica",
    "pdConsegna",
    "pdNotifica",
    "pdPubblica",
    "scadenzaMessaggi",
    "tipoGestoreEventi",
    "tipoServizioConsegna",
    "tipoServizioNotifica",
    "tipoServizioPubblica"
})
public class Configurazione {

    protected long id;
    protected String azioneServizioConsegna;
    protected String azioneServizioNotifica;
    protected String nomeGestoreEventi;
    protected String nomeServizioConsegna;
    protected String nomeServizioNotifica;
    protected String nomeServizioPubblica;
    protected String pdConsegna;
    protected String pdNotifica;
    protected String pdPubblica;
    protected int scadenzaMessaggi;
    protected String tipoGestoreEventi;
    protected String tipoServizioConsegna;
    protected String tipoServizioNotifica;
    protected String tipoServizioPubblica;

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
     * Gets the value of the nomeGestoreEventi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeGestoreEventi() {
        return nomeGestoreEventi;
    }

    /**
     * Sets the value of the nomeGestoreEventi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeGestoreEventi(String value) {
        this.nomeGestoreEventi = value;
    }

    /**
     * Gets the value of the nomeServizioConsegna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeServizioConsegna() {
        return nomeServizioConsegna;
    }

    /**
     * Sets the value of the nomeServizioConsegna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeServizioConsegna(String value) {
        this.nomeServizioConsegna = value;
    }

    /**
     * Gets the value of the nomeServizioNotifica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeServizioNotifica() {
        return nomeServizioNotifica;
    }

    /**
     * Sets the value of the nomeServizioNotifica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeServizioNotifica(String value) {
        this.nomeServizioNotifica = value;
    }

    /**
     * Gets the value of the nomeServizioPubblica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeServizioPubblica() {
        return nomeServizioPubblica;
    }

    /**
     * Sets the value of the nomeServizioPubblica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeServizioPubblica(String value) {
        this.nomeServizioPubblica = value;
    }

    /**
     * Gets the value of the pdConsegna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdConsegna() {
        return pdConsegna;
    }

    /**
     * Sets the value of the pdConsegna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdConsegna(String value) {
        this.pdConsegna = value;
    }

    /**
     * Gets the value of the pdNotifica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdNotifica() {
        return pdNotifica;
    }

    /**
     * Sets the value of the pdNotifica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdNotifica(String value) {
        this.pdNotifica = value;
    }

    /**
     * Gets the value of the pdPubblica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdPubblica() {
        return pdPubblica;
    }

    /**
     * Sets the value of the pdPubblica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdPubblica(String value) {
        this.pdPubblica = value;
    }

    /**
     * Gets the value of the scadenzaMessaggi property.
     * 
     */
    public int getScadenzaMessaggi() {
        return scadenzaMessaggi;
    }

    /**
     * Sets the value of the scadenzaMessaggi property.
     * 
     */
    public void setScadenzaMessaggi(int value) {
        this.scadenzaMessaggi = value;
    }

    /**
     * Gets the value of the tipoGestoreEventi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoGestoreEventi() {
        return tipoGestoreEventi;
    }

    /**
     * Sets the value of the tipoGestoreEventi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoGestoreEventi(String value) {
        this.tipoGestoreEventi = value;
    }

    /**
     * Gets the value of the tipoServizioConsegna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServizioConsegna() {
        return tipoServizioConsegna;
    }

    /**
     * Sets the value of the tipoServizioConsegna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServizioConsegna(String value) {
        this.tipoServizioConsegna = value;
    }

    /**
     * Gets the value of the tipoServizioNotifica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServizioNotifica() {
        return tipoServizioNotifica;
    }

    /**
     * Sets the value of the tipoServizioNotifica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServizioNotifica(String value) {
        this.tipoServizioNotifica = value;
    }

    /**
     * Gets the value of the tipoServizioPubblica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServizioPubblica() {
        return tipoServizioPubblica;
    }

    /**
     * Sets the value of the tipoServizioPubblica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServizioPubblica(String value) {
        this.tipoServizioPubblica = value;
    }

    public String getAzioneServizioConsegna() {
        return azioneServizioConsegna;
    }

    public void setAzioneServizioConsegna(String azioneServizioConsegna) {
        this.azioneServizioConsegna = azioneServizioConsegna;
    }

    public String getAzioneServizioNotifica() {
        return azioneServizioNotifica;
    }

    public void setAzioneServizioNotifica(String azioneServizioNotifica) {
        this.azioneServizioNotifica = azioneServizioNotifica;
    }

}
