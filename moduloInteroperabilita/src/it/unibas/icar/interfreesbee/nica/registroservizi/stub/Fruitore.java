
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Fruitore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Fruitore">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="byteWsdlImplementativoErogatore" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="byteWsdlImplementativoFruitore" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="confermaRicezione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="connettore" type="{http://registry.dao.openspcoop.org}Connettore"/>
 *         &lt;element name="consegnaInOrdine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="filtroDuplicati" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="scadenza" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="servizioApplicativo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="servizioApplicativoList" type="{http://ws.registry.openspcoop.org}ArrayOf_xsd_string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsdlImplementativoErogatore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsdlImplementativoFruitore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fruitore", /*namespace = "http://registry.dao.openspcoop.org",*/ propOrder = {
    "byteWsdlImplementativoErogatore",
    "byteWsdlImplementativoFruitore",
    "confermaRicezione",
    "connettore",
    "consegnaInOrdine",
    "filtroDuplicati",
    "id",
    "idCollaborazione",
    "nome",
    "oraRegistrazione",
    "scadenza",
    "servizioApplicativo",
    "servizioApplicativoList",
    "tipo",
    "wsdlImplementativoErogatore",
    "wsdlImplementativoFruitore"
})
public class Fruitore {

    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected byte[] byteWsdlImplementativoErogatore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected byte[] byteWsdlImplementativoFruitore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String confermaRicezione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected Connettore connettore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String consegnaInOrdine;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String filtroDuplicati;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String idCollaborazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nome;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected XMLGregorianCalendar oraRegistrazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String scadenza;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected List<String> servizioApplicativo;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected ArrayOfXsdString servizioApplicativoList;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String tipo;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String wsdlImplementativoErogatore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String wsdlImplementativoFruitore;

    /**
     * Gets the value of the byteWsdlImplementativoErogatore property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getByteWsdlImplementativoErogatore() {
        return byteWsdlImplementativoErogatore;
    }

    /**
     * Sets the value of the byteWsdlImplementativoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setByteWsdlImplementativoErogatore(byte[] value) {
        this.byteWsdlImplementativoErogatore = ((byte[]) value);
    }

    /**
     * Gets the value of the byteWsdlImplementativoFruitore property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getByteWsdlImplementativoFruitore() {
        return byteWsdlImplementativoFruitore;
    }

    /**
     * Sets the value of the byteWsdlImplementativoFruitore property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setByteWsdlImplementativoFruitore(byte[] value) {
        this.byteWsdlImplementativoFruitore = ((byte[]) value);
    }

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
     * Gets the value of the connettore property.
     * 
     * @return
     *     possible object is
     *     {@link Connettore }
     *     
     */
    public Connettore getConnettore() {
        return connettore;
    }

    /**
     * Sets the value of the connettore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Connettore }
     *     
     */
    public void setConnettore(Connettore value) {
        this.connettore = value;
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

    /**
     * Gets the value of the servizioApplicativo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servizioApplicativo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServizioApplicativo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getServizioApplicativo() {
        if (servizioApplicativo == null) {
            servizioApplicativo = new ArrayList<String>();
        }
        return this.servizioApplicativo;
    }

    /**
     * Gets the value of the servizioApplicativoList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfXsdString }
     *     
     */
    public ArrayOfXsdString getServizioApplicativoList() {
        return servizioApplicativoList;
    }

    /**
     * Sets the value of the servizioApplicativoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfXsdString }
     *     
     */
    public void setServizioApplicativoList(ArrayOfXsdString value) {
        this.servizioApplicativoList = value;
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

    /**
     * Gets the value of the wsdlImplementativoErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlImplementativoErogatore() {
        return wsdlImplementativoErogatore;
    }

    /**
     * Sets the value of the wsdlImplementativoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlImplementativoErogatore(String value) {
        this.wsdlImplementativoErogatore = value;
    }

    /**
     * Gets the value of the wsdlImplementativoFruitore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlImplementativoFruitore() {
        return wsdlImplementativoFruitore;
    }

    /**
     * Sets the value of the wsdlImplementativoFruitore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlImplementativoFruitore(String value) {
        this.wsdlImplementativoFruitore = value;
    }

}
