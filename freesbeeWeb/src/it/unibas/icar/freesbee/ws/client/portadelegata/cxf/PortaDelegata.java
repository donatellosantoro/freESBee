
package it.unibas.icar.freesbee.ws.client.portadelegata.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for portaDelegata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="portaDelegata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fruitoreQueryString" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idAzione" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idServizio" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idSoggettoFruitore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaAzione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaErogatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaFruitore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaTipoErogatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stringaTipoFruitore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "portaDelegata", propOrder = {
    "descrizione",
    "fruitoreQueryString",
    "id",
    "idAzione",
    "idServizio",
    "idSoggettoFruitore",
    "nome",
    "stringaAzione",
    "stringaErogatore",
    "stringaFruitore",
    "stringaServizio",
    "stringaTipoErogatore",
    "stringaTipoFruitore",
    "stringaTipoServizio"
})
public class PortaDelegata {

    protected String descrizione;
    protected boolean fruitoreQueryString;
    protected long id;
    protected long idAzione;
    protected long idServizio;
    protected long idSoggettoFruitore;
    protected String nome;
    protected String stringaAzione;
    protected String stringaErogatore;
    protected String stringaFruitore;
    protected String stringaServizio;
    protected String stringaTipoErogatore;
    protected String stringaTipoFruitore;
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
     * Gets the value of the fruitoreQueryString property.
     * 
     */
    public boolean isFruitoreQueryString() {
        return fruitoreQueryString;
    }

    /**
     * Sets the value of the fruitoreQueryString property.
     * 
     */
    public void setFruitoreQueryString(boolean value) {
        this.fruitoreQueryString = value;
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
     * Gets the value of the idAzione property.
     * 
     */
    public long getIdAzione() {
        return idAzione;
    }

    /**
     * Sets the value of the idAzione property.
     * 
     */
    public void setIdAzione(long value) {
        this.idAzione = value;
    }

    /**
     * Gets the value of the idServizio property.
     * 
     */
    public long getIdServizio() {
        return idServizio;
    }

    /**
     * Sets the value of the idServizio property.
     * 
     */
    public void setIdServizio(long value) {
        this.idServizio = value;
    }

    /**
     * Gets the value of the idSoggettoFruitore property.
     * 
     */
    public long getIdSoggettoFruitore() {
        return idSoggettoFruitore;
    }

    /**
     * Sets the value of the idSoggettoFruitore property.
     * 
     */
    public void setIdSoggettoFruitore(long value) {
        this.idSoggettoFruitore = value;
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
     * Gets the value of the stringaFruitore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringaFruitore() {
        return stringaFruitore;
    }

    /**
     * Sets the value of the stringaFruitore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringaFruitore(String value) {
        this.stringaFruitore = value;
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
     * Gets the value of the stringaTipoFruitore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringaTipoFruitore() {
        return stringaTipoFruitore;
    }

    /**
     * Sets the value of the stringaTipoFruitore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringaTipoFruitore(String value) {
        this.stringaTipoFruitore = value;
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
