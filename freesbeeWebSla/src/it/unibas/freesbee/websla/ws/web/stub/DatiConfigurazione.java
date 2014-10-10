
package it.unibas.freesbee.websla.ws.web.stub;

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
 *         &lt;element name="accordoServizioSLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accordoServizioStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indirizzoRegistroServizi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeSLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeServizioMonitoraggioSLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeServizioMonitoraggioStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pdMonitoraggioSLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pdMonitoraggioStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="protezioneSP" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="risorsa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="risorsaPdMonitoraggioSLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="risorsaPdMonitoraggioStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoSLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoServizioMonitoraggioSLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoServizioMonitoraggioStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "datiConfigurazione", propOrder = {
    "accordoServizioSLA",
    "accordoServizioStato",
    "indirizzoRegistroServizi",
    "nomeSLA",
    "nomeServizioMonitoraggioSLA",
    "nomeServizioMonitoraggioStato",
    "pdMonitoraggioSLA",
    "pdMonitoraggioStato",
    "protezioneSP",
    "risorsa",
    "risorsaPdMonitoraggioSLA",
    "risorsaPdMonitoraggioStato",
    "tipoSLA",
    "tipoServizioMonitoraggioSLA",
    "tipoServizioMonitoraggioStato",
    "urlFreesbeeSP"
})
public class DatiConfigurazione {

    protected String accordoServizioSLA;
    protected String accordoServizioStato;
    protected String indirizzoRegistroServizi;
    protected String nomeSLA;
    protected String nomeServizioMonitoraggioSLA;
    protected String nomeServizioMonitoraggioStato;
    protected String pdMonitoraggioSLA;
    protected String pdMonitoraggioStato;
    protected boolean protezioneSP;
    protected String risorsa;
    protected String risorsaPdMonitoraggioSLA;
    protected String risorsaPdMonitoraggioStato;
    protected String tipoSLA;
    protected String tipoServizioMonitoraggioSLA;
    protected String tipoServizioMonitoraggioStato;
    protected String urlFreesbeeSP;

    /**
     * Gets the value of the accordoServizioSLA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccordoServizioSLA() {
        return accordoServizioSLA;
    }

    /**
     * Sets the value of the accordoServizioSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccordoServizioSLA(String value) {
        this.accordoServizioSLA = value;
    }

    /**
     * Gets the value of the accordoServizioStato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccordoServizioStato() {
        return accordoServizioStato;
    }

    /**
     * Sets the value of the accordoServizioStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccordoServizioStato(String value) {
        this.accordoServizioStato = value;
    }

    /**
     * Gets the value of the indirizzoRegistroServizi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoRegistroServizi() {
        return indirizzoRegistroServizi;
    }

    /**
     * Sets the value of the indirizzoRegistroServizi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoRegistroServizi(String value) {
        this.indirizzoRegistroServizi = value;
    }

    /**
     * Gets the value of the nomeSLA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeSLA() {
        return nomeSLA;
    }

    /**
     * Sets the value of the nomeSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeSLA(String value) {
        this.nomeSLA = value;
    }

    /**
     * Gets the value of the nomeServizioMonitoraggioSLA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeServizioMonitoraggioSLA() {
        return nomeServizioMonitoraggioSLA;
    }

    /**
     * Sets the value of the nomeServizioMonitoraggioSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeServizioMonitoraggioSLA(String value) {
        this.nomeServizioMonitoraggioSLA = value;
    }

    /**
     * Gets the value of the nomeServizioMonitoraggioStato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeServizioMonitoraggioStato() {
        return nomeServizioMonitoraggioStato;
    }

    /**
     * Sets the value of the nomeServizioMonitoraggioStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeServizioMonitoraggioStato(String value) {
        this.nomeServizioMonitoraggioStato = value;
    }

    /**
     * Gets the value of the pdMonitoraggioSLA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdMonitoraggioSLA() {
        return pdMonitoraggioSLA;
    }

    /**
     * Sets the value of the pdMonitoraggioSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdMonitoraggioSLA(String value) {
        this.pdMonitoraggioSLA = value;
    }

    /**
     * Gets the value of the pdMonitoraggioStato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdMonitoraggioStato() {
        return pdMonitoraggioStato;
    }

    /**
     * Sets the value of the pdMonitoraggioStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdMonitoraggioStato(String value) {
        this.pdMonitoraggioStato = value;
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
     * Gets the value of the risorsaPdMonitoraggioSLA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisorsaPdMonitoraggioSLA() {
        return risorsaPdMonitoraggioSLA;
    }

    /**
     * Sets the value of the risorsaPdMonitoraggioSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisorsaPdMonitoraggioSLA(String value) {
        this.risorsaPdMonitoraggioSLA = value;
    }

    /**
     * Gets the value of the risorsaPdMonitoraggioStato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisorsaPdMonitoraggioStato() {
        return risorsaPdMonitoraggioStato;
    }

    /**
     * Sets the value of the risorsaPdMonitoraggioStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisorsaPdMonitoraggioStato(String value) {
        this.risorsaPdMonitoraggioStato = value;
    }

    /**
     * Gets the value of the tipoSLA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSLA() {
        return tipoSLA;
    }

    /**
     * Sets the value of the tipoSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSLA(String value) {
        this.tipoSLA = value;
    }

    /**
     * Gets the value of the tipoServizioMonitoraggioSLA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServizioMonitoraggioSLA() {
        return tipoServizioMonitoraggioSLA;
    }

    /**
     * Sets the value of the tipoServizioMonitoraggioSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServizioMonitoraggioSLA(String value) {
        this.tipoServizioMonitoraggioSLA = value;
    }

    /**
     * Gets the value of the tipoServizioMonitoraggioStato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServizioMonitoraggioStato() {
        return tipoServizioMonitoraggioStato;
    }

    /**
     * Sets the value of the tipoServizioMonitoraggioStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServizioMonitoraggioStato(String value) {
        this.tipoServizioMonitoraggioStato = value;
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
