
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per servizioRSRisposta complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="servizioRSRisposta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accordoServizio" type="{http://icar.unibas.it/freesbee/}accordoServizioRSRisposta" minOccurs="0"/>
 *         &lt;element name="correlato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="erogatore" type="{http://icar.unibas.it/freesbee/}soggettoRS" minOccurs="0"/>
 *         &lt;element name="fruitori" type="{http://icar.unibas.it/freesbee/}soggettoRSRisposta" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldNomeForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldNomeSoggettoErogatoreForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldTipoForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldTipoSoggettoErogatoreForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="privato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "servizioRSRisposta", propOrder = {
    "accordoServizio",
    "correlato",
    "erogatore",
    "fruitori",
    "id",
    "nome",
    "oldNomeForUpdate",
    "oldNomeSoggettoErogatoreForUpdate",
    "oldTipoForUpdate",
    "oldTipoSoggettoErogatoreForUpdate",
    "oraRegistrazione",
    "privato",
    "tipo"
})
public class ServizioRSRisposta {

    protected AccordoServizioRSRisposta accordoServizio;
    protected boolean correlato;
    protected SoggettoRS erogatore;
    protected List<SoggettoRSRisposta> fruitori;
    protected long id;
    protected String nome;
    protected String oldNomeForUpdate;
    protected String oldNomeSoggettoErogatoreForUpdate;
    protected String oldTipoForUpdate;
    protected String oldTipoSoggettoErogatoreForUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar oraRegistrazione;
    protected boolean privato;
    protected String tipo;

    /**
     * Recupera il valore della proprietˆ accordoServizio.
     * 
     * @return
     *     possible object is
     *     {@link AccordoServizioRSRisposta }
     *     
     */
    public AccordoServizioRSRisposta getAccordoServizio() {
        return accordoServizio;
    }

    /**
     * Imposta il valore della proprietˆ accordoServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link AccordoServizioRSRisposta }
     *     
     */
    public void setAccordoServizio(AccordoServizioRSRisposta value) {
        this.accordoServizio = value;
    }

    /**
     * Recupera il valore della proprietˆ correlato.
     * 
     */
    public boolean isCorrelato() {
        return correlato;
    }

    /**
     * Imposta il valore della proprietˆ correlato.
     * 
     */
    public void setCorrelato(boolean value) {
        this.correlato = value;
    }

    /**
     * Recupera il valore della proprietˆ erogatore.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRS }
     *     
     */
    public SoggettoRS getErogatore() {
        return erogatore;
    }

    /**
     * Imposta il valore della proprietˆ erogatore.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRS }
     *     
     */
    public void setErogatore(SoggettoRS value) {
        this.erogatore = value;
    }

    /**
     * Gets the value of the fruitori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fruitori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFruitori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoggettoRSRisposta }
     * 
     * 
     */
    public List<SoggettoRSRisposta> getFruitori() {
        if (fruitori == null) {
            fruitori = new ArrayList<SoggettoRSRisposta>();
        }
        return this.fruitori;
    }

    /**
     * Recupera il valore della proprietˆ id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietˆ id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietˆ nome.
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
     * Imposta il valore della proprietˆ nome.
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
     * Recupera il valore della proprietˆ oldNomeForUpdate.
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
     * Imposta il valore della proprietˆ oldNomeForUpdate.
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
     * Recupera il valore della proprietˆ oldNomeSoggettoErogatoreForUpdate.
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
     * Imposta il valore della proprietˆ oldNomeSoggettoErogatoreForUpdate.
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
     * Recupera il valore della proprietˆ oldTipoForUpdate.
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
     * Imposta il valore della proprietˆ oldTipoForUpdate.
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
     * Recupera il valore della proprietˆ oldTipoSoggettoErogatoreForUpdate.
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
     * Imposta il valore della proprietˆ oldTipoSoggettoErogatoreForUpdate.
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
     * Recupera il valore della proprietˆ oraRegistrazione.
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
     * Imposta il valore della proprietˆ oraRegistrazione.
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
     * Recupera il valore della proprietˆ privato.
     * 
     */
    public boolean isPrivato() {
        return privato;
    }

    /**
     * Imposta il valore della proprietˆ privato.
     * 
     */
    public void setPrivato(boolean value) {
        this.privato = value;
    }

    /**
     * Recupera il valore della proprietˆ tipo.
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
     * Imposta il valore della proprietˆ tipo.
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
