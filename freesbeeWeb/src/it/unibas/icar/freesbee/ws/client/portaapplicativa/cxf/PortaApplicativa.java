
package it.unibas.icar.freesbee.ws.client.portaapplicativa.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for portaApplicativa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="portaApplicativa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idAzione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idServizio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idServizioApplicativo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaAzione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaErogatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaTipoErogatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaTipoServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "portaApplicativa", propOrder = {
    "descrizione",
    "id",
    "idAzione",
    "idServizio",
    "idServizioApplicativo",
    "nome",
    "stringaAzione",
    "stringaErogatore",
    "stringaServizio",
    "stringaTipoErogatore",
    "stringaTipoServizio"
})
public class PortaApplicativa {

    protected String descrizione;
    protected Long id;
    protected Long idAzione;
    protected Long idServizio;
    protected Long idServizioApplicativo;
    protected String nome;
    protected String stringaAzione;
    protected String stringaErogatore;
    protected String stringaServizio;
    protected String stringaTipoErogatore;
    protected String stringaTipoServizio;

    /**
     * Gets the value of the descrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the value of the descrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
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
     * Gets the value of the idAzione property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAzione() {
        return idAzione;
    }

    /**
     * Sets the value of the idAzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAzione(Long value) {
        this.idAzione = value;
    }

    /**
     * Gets the value of the idServizio property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdServizio() {
        return idServizio;
    }

    /**
     * Sets the value of the idServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdServizio(Long value) {
        this.idServizio = value;
    }

    /**
     * Gets the value of the idServizioApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdServizioApplicativo() {
        return idServizioApplicativo;
    }

    /**
     * Sets the value of the idServizioApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdServizioApplicativo(Long value) {
        this.idServizioApplicativo = value;
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
     * Gets the value of the stringaAzione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringaAzione() {
        return stringaAzione;
    }

    /**
     * Sets the value of the stringaAzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringaAzione(String value) {
        this.stringaAzione = value;
    }

    /**
     * Gets the value of the stringaErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringaErogatore() {
        return stringaErogatore;
    }

    /**
     * Sets the value of the stringaErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringaErogatore(String value) {
        this.stringaErogatore = value;
    }

    /**
     * Gets the value of the stringaServizio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringaServizio() {
        return stringaServizio;
    }

    /**
     * Sets the value of the stringaServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringaServizio(String value) {
        this.stringaServizio = value;
    }

    /**
     * Gets the value of the stringaTipoErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringaTipoErogatore() {
        return stringaTipoErogatore;
    }

    /**
     * Sets the value of the stringaTipoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringaTipoErogatore(String value) {
        this.stringaTipoErogatore = value;
    }

    /**
     * Gets the value of the stringaTipoServizio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringaTipoServizio() {
        return stringaTipoServizio;
    }

    /**
     * Sets the value of the stringaTipoServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringaTipoServizio(String value) {
        this.stringaTipoServizio = value;
    }

}
