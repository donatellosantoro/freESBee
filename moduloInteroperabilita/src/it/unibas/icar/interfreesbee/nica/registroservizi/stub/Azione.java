
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Azione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Azione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confermaRicezione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consegnaInOrdine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="filtroDuplicati" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idAccordo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profAzione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profiloCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scadenza" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Azione", /*namespace = "http://registry.dao.openspcoop.org",*/ propOrder = {
    "confermaRicezione",
    "consegnaInOrdine",
    "filtroDuplicati",
    "id",
    "idAccordo",
    "idCollaborazione",
    "nome",
    "profAzione",
    "profiloCollaborazione",
    "scadenza"
})
public class Azione {

    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String confermaRicezione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String consegnaInOrdine;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String filtroDuplicati;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long idAccordo;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String idCollaborazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nome;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String profAzione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String profiloCollaborazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String scadenza;

    /**
     * Gets the value of the confermaRicezione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfermaRicezione() {
        return confermaRicezione;
    }

    /**
     * Sets the value of the confermaRicezione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfermaRicezione(String value) {
        this.confermaRicezione = value;
    }

    /**
     * Gets the value of the consegnaInOrdine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsegnaInOrdine() {
        return consegnaInOrdine;
    }

    /**
     * Sets the value of the consegnaInOrdine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsegnaInOrdine(String value) {
        this.consegnaInOrdine = value;
    }

    /**
     * Gets the value of the filtroDuplicati property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiltroDuplicati() {
        return filtroDuplicati;
    }

    /**
     * Sets the value of the filtroDuplicati property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiltroDuplicati(String value) {
        this.filtroDuplicati = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the idAccordo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAccordo() {
        return idAccordo;
    }

    /**
     * Sets the value of the idAccordo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAccordo(Long value) {
        this.idAccordo = value;
    }

    /**
     * Gets the value of the idCollaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCollaborazione() {
        return idCollaborazione;
    }

    /**
     * Sets the value of the idCollaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCollaborazione(String value) {
        this.idCollaborazione = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the profAzione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfAzione() {
        return profAzione;
    }

    /**
     * Sets the value of the profAzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfAzione(String value) {
        this.profAzione = value;
    }

    /**
     * Gets the value of the profiloCollaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    /**
     * Sets the value of the profiloCollaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfiloCollaborazione(String value) {
        this.profiloCollaborazione = value;
    }

    /**
     * Gets the value of the scadenza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScadenza() {
        return scadenza;
    }

    /**
     * Sets the value of the scadenza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScadenza(String value) {
        this.scadenza = value;
    }

}
