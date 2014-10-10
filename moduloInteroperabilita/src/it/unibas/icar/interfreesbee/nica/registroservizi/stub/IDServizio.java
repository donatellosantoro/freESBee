
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IDServizio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDServizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accordo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="azione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="correlato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="servizio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="soggettoErogatore" type="{http://driver.registry.dao.openspcoop.org}IDSoggetto"/>
 *         &lt;element name="tipoServizio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDServizio", /*namespace = "http://driver.registry.dao.openspcoop.org",*/ propOrder = {
    "accordo",
    "azione",
    "correlato",
    "servizio",
    "soggettoErogatore",
    "tipoServizio"
})
public class IDServizio {

    @XmlElement(/*namespace = "http://driver.registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String accordo;
    @XmlElement(/*namespace = "http://driver.registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String azione;
    @XmlElement(namespace = "http://driver.registry.dao.openspcoop.org")
    protected boolean correlato;
    @XmlElement(/*namespace = "http://driver.registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String servizio;
    @XmlElement(/*namespace = "http://driver.registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected IDSoggetto soggettoErogatore;
    @XmlElement(/*namespace = "http://driver.registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String tipoServizio;

    /**
     * Gets the value of the accordo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccordo() {
        return accordo;
    }

    /**
     * Sets the value of the accordo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccordo(String value) {
        this.accordo = value;
    }

    /**
     * Gets the value of the azione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAzione() {
        return azione;
    }

    /**
     * Sets the value of the azione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAzione(String value) {
        this.azione = value;
    }

    /**
     * Gets the value of the correlato property.
     * 
     */
    public boolean isCorrelato() {
        return correlato;
    }

    /**
     * Sets the value of the correlato property.
     * 
     */
    public void setCorrelato(boolean value) {
        this.correlato = value;
    }

    /**
     * Gets the value of the servizio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServizio() {
        return servizio;
    }

    /**
     * Sets the value of the servizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServizio(String value) {
        this.servizio = value;
    }

    /**
     * Gets the value of the soggettoErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link IDSoggetto }
     *     
     */
    public IDSoggetto getSoggettoErogatore() {
        return soggettoErogatore;
    }

    /**
     * Sets the value of the soggettoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDSoggetto }
     *     
     */
    public void setSoggettoErogatore(IDSoggetto value) {
        this.soggettoErogatore = value;
    }

    /**
     * Gets the value of the tipoServizio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServizio() {
        return tipoServizio;
    }

    /**
     * Sets the value of the tipoServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServizio(String value) {
        this.tipoServizio = value;
    }

}
