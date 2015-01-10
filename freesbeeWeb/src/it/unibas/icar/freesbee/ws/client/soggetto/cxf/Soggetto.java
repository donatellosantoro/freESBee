
package it.unibas.icar.freesbee.ws.client.soggetto.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for soggetto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="soggetto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mutuaAutenticazione" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldNomeForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldTipoForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="portaDominio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "soggetto", propOrder = {
    "descrizione",
    "id",
    "mutuaAutenticazione",
    "nome",
    "oldNomeForUpdate",
    "oldTipoForUpdate",
    "oraRegistrazione",
    "portaDominio",
    "tipo"
})
public class Soggetto {

    protected String descrizione;
    protected long id;
    protected boolean mutuaAutenticazione;
    protected String nome;
    protected String oldNomeForUpdate;
    protected String oldTipoForUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar oraRegistrazione;
    protected String portaDominio;
    protected String tipo;

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
     * Gets the value of the mutuaAutenticazione property.
     * 
     */
    public boolean isMutuaAutenticazione() {
        return mutuaAutenticazione;
    }

    /**
     * Sets the value of the mutuaAutenticazione property.
     * 
     */
    public void setMutuaAutenticazione(boolean value) {
        this.mutuaAutenticazione = value;
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
     * Gets the value of the oldNomeForUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    /**
     * Sets the value of the oldNomeForUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldNomeForUpdate(String value) {
        this.oldNomeForUpdate = value;
    }

    /**
     * Gets the value of the oldTipoForUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldTipoForUpdate() {
        return oldTipoForUpdate;
    }

    /**
     * Sets the value of the oldTipoForUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldTipoForUpdate(String value) {
        this.oldTipoForUpdate = value;
    }

    /**
     * Gets the value of the oraRegistrazione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOraRegistrazione() {
        return oraRegistrazione;
    }

    /**
     * Sets the value of the oraRegistrazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOraRegistrazione(XMLGregorianCalendar value) {
        this.oraRegistrazione = value;
    }

    /**
     * Gets the value of the portaDominio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortaDominio() {
        return portaDominio;
    }

    /**
     * Sets the value of the portaDominio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortaDominio(String value) {
        this.portaDominio = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}
