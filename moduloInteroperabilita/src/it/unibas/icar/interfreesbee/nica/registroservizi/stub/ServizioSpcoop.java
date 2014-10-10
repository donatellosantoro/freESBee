
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ServizioSpcoop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServizioSpcoop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accordoServizio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="azione" type="{http://registry.dao.openspcoop.org}ServizioSpcoopAzione" maxOccurs="unbounded"/>
 *         &lt;element name="azioneList" type="{http://ws.registry.openspcoop.org}ArrayOf_tns2_ServizioSpcoopAzione"/>
 *         &lt;element name="byteWsdlImplementativoErogatore" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="byteWsdlImplementativoFruitore" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="confermaRicezione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="connettore" type="{http://registry.dao.openspcoop.org}Connettore"/>
 *         &lt;element name="consegnaInOrdine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="filtroDuplicati" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fruitore" type="{http://registry.dao.openspcoop.org}Fruitore" maxOccurs="unbounded"/>
 *         &lt;element name="fruitoreList" type="{http://ws.registry.openspcoop.org}ArrayOf_tns2_Fruitore"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idAccordo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idSoggetto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nomeSoggettoErogatore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldNomeForUpdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldNomeSoggettoErogatoreForUpdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldTipoForUpdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldTipoSoggettoErogatoreForUpdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="scadenza" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="servizioCorrelato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="superUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoSoggettoErogatore" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "ServizioSpcoop", /*namespace = "http://registry.dao.openspcoop.org",*/ propOrder = {
    "accordoServizio",
    "azione",
    "azioneList",
    "byteWsdlImplementativoErogatore",
    "byteWsdlImplementativoFruitore",
    "confermaRicezione",
    "connettore",
    "consegnaInOrdine",
    "filtroDuplicati",
    "fruitore",
    "fruitoreList",
    "id",
    "idAccordo",
    "idCollaborazione",
    "idSoggetto",
    "nome",
    "nomeSoggettoErogatore",
    "oldNomeForUpdate",
    "oldNomeSoggettoErogatoreForUpdate",
    "oldTipoForUpdate",
    "oldTipoSoggettoErogatoreForUpdate",
    "oraRegistrazione",
    "scadenza",
    "servizioCorrelato",
    "superUser",
    "tipo",
    "tipoSoggettoErogatore",
    "wsdlImplementativoErogatore",
    "wsdlImplementativoFruitore"
})
public class ServizioSpcoop {

    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String accordoServizio;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected List<ServizioSpcoopAzione> azione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected ArrayOfTns2ServizioSpcoopAzione azioneList;
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
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected List<Fruitore> fruitore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected ArrayOfTns2Fruitore fruitoreList;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long idAccordo;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String idCollaborazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long idSoggetto;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nome;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nomeSoggettoErogatore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String oldNomeForUpdate;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String oldNomeSoggettoErogatoreForUpdate;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String oldTipoForUpdate;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String oldTipoSoggettoErogatoreForUpdate;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected XMLGregorianCalendar oraRegistrazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String scadenza;
    @XmlElement(namespace = "http://registry.dao.openspcoop.org")
    protected boolean servizioCorrelato;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String superUser;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String tipo;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String tipoSoggettoErogatore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String wsdlImplementativoErogatore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String wsdlImplementativoFruitore;

    /**
     * Gets the value of the accordoServizio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccordoServizio() {
        return accordoServizio;
    }

    /**
     * Sets the value of the accordoServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccordoServizio(String value) {
        this.accordoServizio = value;
    }

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
     * {@link ServizioSpcoopAzione }
     * 
     * 
     */
    public List<ServizioSpcoopAzione> getAzione() {
        if (azione == null) {
            azione = new ArrayList<ServizioSpcoopAzione>();
        }
        return this.azione;
    }

    /**
     * Gets the value of the azioneList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns2ServizioSpcoopAzione }
     *     
     */
    public ArrayOfTns2ServizioSpcoopAzione getAzioneList() {
        return azioneList;
    }

    /**
     * Sets the value of the azioneList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns2ServizioSpcoopAzione }
     *     
     */
    public void setAzioneList(ArrayOfTns2ServizioSpcoopAzione value) {
        this.azioneList = value;
    }

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
     * Gets the value of the fruitore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fruitore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFruitore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Fruitore }
     * 
     * 
     */
    public List<Fruitore> getFruitore() {
        if (fruitore == null) {
            fruitore = new ArrayList<Fruitore>();
        }
        return this.fruitore;
    }

    /**
     * Gets the value of the fruitoreList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns2Fruitore }
     *     
     */
    public ArrayOfTns2Fruitore getFruitoreList() {
        return fruitoreList;
    }

    /**
     * Sets the value of the fruitoreList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns2Fruitore }
     *     
     */
    public void setFruitoreList(ArrayOfTns2Fruitore value) {
        this.fruitoreList = value;
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
     * Gets the value of the idSoggetto property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdSoggetto() {
        return idSoggetto;
    }

    /**
     * Sets the value of the idSoggetto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdSoggetto(Long value) {
        this.idSoggetto = value;
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
     * Gets the value of the nomeSoggettoErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeSoggettoErogatore() {
        return nomeSoggettoErogatore;
    }

    /**
     * Sets the value of the nomeSoggettoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeSoggettoErogatore(String value) {
        this.nomeSoggettoErogatore = value;
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
     * Gets the value of the oldNomeSoggettoErogatoreForUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldNomeSoggettoErogatoreForUpdate() {
        return oldNomeSoggettoErogatoreForUpdate;
    }

    /**
     * Sets the value of the oldNomeSoggettoErogatoreForUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldNomeSoggettoErogatoreForUpdate(String value) {
        this.oldNomeSoggettoErogatoreForUpdate = value;
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
     * Gets the value of the oldTipoSoggettoErogatoreForUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldTipoSoggettoErogatoreForUpdate() {
        return oldTipoSoggettoErogatoreForUpdate;
    }

    /**
     * Sets the value of the oldTipoSoggettoErogatoreForUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldTipoSoggettoErogatoreForUpdate(String value) {
        this.oldTipoSoggettoErogatoreForUpdate = value;
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
     * Gets the value of the servizioCorrelato property.
     * 
     */
    public boolean isServizioCorrelato() {
        return servizioCorrelato;
    }

    /**
     * Sets the value of the servizioCorrelato property.
     * 
     */
    public void setServizioCorrelato(boolean value) {
        this.servizioCorrelato = value;
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
     * Gets the value of the tipoSoggettoErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSoggettoErogatore() {
        return tipoSoggettoErogatore;
    }

    /**
     * Sets the value of the tipoSoggettoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSoggettoErogatore(String value) {
        this.tipoSoggettoErogatore = value;
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
