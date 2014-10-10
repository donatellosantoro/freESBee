
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AccordoServizio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccordoServizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="azione" type="{http://registry.dao.openspcoop.org}Azione" maxOccurs="unbounded"/>
 *         &lt;element name="azioneList" type="{http://ws.registry.openspcoop.org}ArrayOf_tns2_Azione"/>
 *         &lt;element name="byteWsdlConcettuale" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="byteWsdlDefinitorio" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="byteWsdlLogicoErogatore" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="byteWsdlLogicoFruitore" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="confermaRicezione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consegnaInOrdine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="filtroDuplicati" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldNomeForUpdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="profiloCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scadenza" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="superUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="utilizzoSenzaAzione" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="wsdlConcettuale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsdlDefinitorio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsdlLogicoErogatore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsdlLogicoFruitore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccordoServizio", /*namespace = "http://registry.dao.openspcoop.org",*/ propOrder = {
    "azione",
    "azioneList",
    "byteWsdlConcettuale",
    "byteWsdlDefinitorio",
    "byteWsdlLogicoErogatore",
    "byteWsdlLogicoFruitore",
    "confermaRicezione",
    "consegnaInOrdine",
    "descrizione",
    "filtroDuplicati",
    "id",
    "idCollaborazione",
    "nome",
    "oldNomeForUpdate",
    "oraRegistrazione",
    "profiloCollaborazione",
    "scadenza",
    "superUser",
    "utilizzoSenzaAzione",
    "wsdlConcettuale",
    "wsdlDefinitorio",
    "wsdlLogicoErogatore",
    "wsdlLogicoFruitore"
})
public class AccordoServizio {

    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected List<Azione> azione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected ArrayOfTns2Azione azioneList;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected byte[] byteWsdlConcettuale;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected byte[] byteWsdlDefinitorio;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected byte[] byteWsdlLogicoErogatore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected byte[] byteWsdlLogicoFruitore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String confermaRicezione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String consegnaInOrdine;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String descrizione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String filtroDuplicati;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String idCollaborazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nome;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String oldNomeForUpdate;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected XMLGregorianCalendar oraRegistrazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String profiloCollaborazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String scadenza;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String superUser;
    @XmlElement(namespace = "http://registry.dao.openspcoop.org")
    protected boolean utilizzoSenzaAzione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String wsdlConcettuale;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String wsdlDefinitorio;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String wsdlLogicoErogatore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String wsdlLogicoFruitore;

    /**
     * Gets the value of the azione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the azione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAzione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Azione }
     * 
     * 
     */
    public List<Azione> getAzione() {
        if (azione == null) {
            azione = new ArrayList<Azione>();
        }
        return this.azione;
    }

    /**
     * Gets the value of the azioneList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns2Azione }
     *     
     */
    public ArrayOfTns2Azione getAzioneList() {
        return azioneList;
    }

    /**
     * Sets the value of the azioneList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns2Azione }
     *     
     */
    public void setAzioneList(ArrayOfTns2Azione value) {
        this.azioneList = value;
    }

    /**
     * Gets the value of the byteWsdlConcettuale property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getByteWsdlConcettuale() {
        return byteWsdlConcettuale;
    }

    /**
     * Sets the value of the byteWsdlConcettuale property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setByteWsdlConcettuale(byte[] value) {
        this.byteWsdlConcettuale = ((byte[]) value);
    }

    /**
     * Gets the value of the byteWsdlDefinitorio property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getByteWsdlDefinitorio() {
        return byteWsdlDefinitorio;
    }

    /**
     * Sets the value of the byteWsdlDefinitorio property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setByteWsdlDefinitorio(byte[] value) {
        this.byteWsdlDefinitorio = ((byte[]) value);
    }

    /**
     * Gets the value of the byteWsdlLogicoErogatore property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getByteWsdlLogicoErogatore() {
        return byteWsdlLogicoErogatore;
    }

    /**
     * Sets the value of the byteWsdlLogicoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setByteWsdlLogicoErogatore(byte[] value) {
        this.byteWsdlLogicoErogatore = ((byte[]) value);
    }

    /**
     * Gets the value of the byteWsdlLogicoFruitore property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getByteWsdlLogicoFruitore() {
        return byteWsdlLogicoFruitore;
    }

    /**
     * Sets the value of the byteWsdlLogicoFruitore property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setByteWsdlLogicoFruitore(byte[] value) {
        this.byteWsdlLogicoFruitore = ((byte[]) value);
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

    /**
     * Gets the value of the superUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperUser() {
        return superUser;
    }

    /**
     * Sets the value of the superUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperUser(String value) {
        this.superUser = value;
    }

    /**
     * Gets the value of the utilizzoSenzaAzione property.
     * 
     */
    public boolean isUtilizzoSenzaAzione() {
        return utilizzoSenzaAzione;
    }

    /**
     * Sets the value of the utilizzoSenzaAzione property.
     * 
     */
    public void setUtilizzoSenzaAzione(boolean value) {
        this.utilizzoSenzaAzione = value;
    }

    /**
     * Gets the value of the wsdlConcettuale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlConcettuale() {
        return wsdlConcettuale;
    }

    /**
     * Sets the value of the wsdlConcettuale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlConcettuale(String value) {
        this.wsdlConcettuale = value;
    }

    /**
     * Gets the value of the wsdlDefinitorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlDefinitorio() {
        return wsdlDefinitorio;
    }

    /**
     * Sets the value of the wsdlDefinitorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlDefinitorio(String value) {
        this.wsdlDefinitorio = value;
    }

    /**
     * Gets the value of the wsdlLogicoErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlLogicoErogatore() {
        return wsdlLogicoErogatore;
    }

    /**
     * Sets the value of the wsdlLogicoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlLogicoErogatore(String value) {
        this.wsdlLogicoErogatore = value;
    }

    /**
     * Gets the value of the wsdlLogicoFruitore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlLogicoFruitore() {
        return wsdlLogicoFruitore;
    }

    /**
     * Sets the value of the wsdlLogicoFruitore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlLogicoFruitore(String value) {
        this.wsdlLogicoFruitore = value;
    }

}
