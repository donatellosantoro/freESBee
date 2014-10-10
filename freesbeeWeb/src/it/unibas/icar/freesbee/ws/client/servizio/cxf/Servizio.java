
package it.unibas.icar.freesbee.ws.client.servizio.cxf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for servizio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="servizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="azioni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="correlato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idAccordoServizio" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idErogatore" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idFruitori" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="idPortaApplicativa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldNomeForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldNomeSoggettoErogatoreForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldTipoForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldTipoSoggettoErogatoreForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="privato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servizio", propOrder = {
    "azioni",
    "correlato",
    "id",
    "idAccordoServizio",
    "idErogatore",
    "idFruitori",
    "idPortaApplicativa",
    "nome",
    "oldNomeForUpdate",
    "oldNomeSoggettoErogatoreForUpdate",
    "oldTipoForUpdate",
    "oldTipoSoggettoErogatoreForUpdate",
    "oraRegistrazione",
    "privato",
    "tipo",
    "urlServizio"
})
public class Servizio {

    protected String azioni;
    protected boolean correlato;
    protected long id;
    protected long idAccordoServizio;
    protected long idErogatore;
    @XmlElement(nillable = true)
    protected List<Long> idFruitori;
    protected long idPortaApplicativa;
    protected String nome;
    protected String oldNomeForUpdate;
    protected String oldNomeSoggettoErogatoreForUpdate;
    protected String oldTipoForUpdate;
    protected String oldTipoSoggettoErogatoreForUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar oraRegistrazione;
    protected boolean privato;
    protected String tipo;
    protected String urlServizio;

    /**
     * Gets the value of the azioni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAzioni() {
        return azioni;
    }

    /**
     * Sets the value of the azioni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAzioni(String value) {
        this.azioni = value;
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
     * Gets the value of the idAccordoServizio property.
     * 
     */
    public long getIdAccordoServizio() {
        return idAccordoServizio;
    }

    /**
     * Sets the value of the idAccordoServizio property.
     * 
     */
    public void setIdAccordoServizio(long value) {
        this.idAccordoServizio = value;
    }

    /**
     * Gets the value of the idErogatore property.
     * 
     */
    public long getIdErogatore() {
        return idErogatore;
    }

    /**
     * Sets the value of the idErogatore property.
     * 
     */
    public void setIdErogatore(long value) {
        this.idErogatore = value;
    }

    /**
     * Gets the value of the idFruitori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idFruitori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdFruitori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getIdFruitori() {
        if (idFruitori == null) {
            idFruitori = new ArrayList<Long>();
        }
        return this.idFruitori;
    }

    /**
     * Gets the value of the idPortaApplicativa property.
     * 
     */
    public long getIdPortaApplicativa() {
        return idPortaApplicativa;
    }

    /**
     * Sets the value of the idPortaApplicativa property.
     * 
     */
    public void setIdPortaApplicativa(long value) {
        this.idPortaApplicativa = value;
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
     * Gets the value of the privato property.
     * 
     */
    public boolean isPrivato() {
        return privato;
    }

    /**
     * Sets the value of the privato property.
     * 
     */
    public void setPrivato(boolean value) {
        this.privato = value;
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
     * Gets the value of the urlServizio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlServizio() {
        return urlServizio;
    }

    /**
     * Sets the value of the urlServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlServizio(String value) {
        this.urlServizio = value;
    }

}
