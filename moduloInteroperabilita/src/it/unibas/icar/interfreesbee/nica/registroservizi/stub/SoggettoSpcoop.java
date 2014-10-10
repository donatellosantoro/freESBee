
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SoggettoSpcoop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SoggettoSpcoop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connettore" type="{http://registry.dao.openspcoop.org}Connettore"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="identificativoPorta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isRouter" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldNomeForUpdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldTipoForUpdate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="server" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="servizio" type="{http://registry.dao.openspcoop.org}ServizioSpcoop" maxOccurs="unbounded"/>
 *         &lt;element name="servizioCorrelato" type="{http://registry.dao.openspcoop.org}ServizioSpcoop" maxOccurs="unbounded"/>
 *         &lt;element name="servizioCorrelatoList" type="{http://ws.registry.openspcoop.org}ArrayOf_tns2_ServizioSpcoop"/>
 *         &lt;element name="servizioList" type="{http://ws.registry.openspcoop.org}ArrayOf_tns2_ServizioSpcoop"/>
 *         &lt;element name="superUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoSpcoop", /*namespace = "http://registry.dao.openspcoop.org",*/ propOrder = {
    "connettore",
    "descrizione",
    "id",
    "identificativoPorta",
    "isRouter",
    "nome",
    "oldNomeForUpdate",
    "oldTipoForUpdate",
    "oraRegistrazione",
    "server",
    "servizio",
    "servizioCorrelato",
    "servizioCorrelatoList",
    "servizioList",
    "superUser",
    "tipo"
})
public class SoggettoSpcoop {

    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected Connettore connettore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String descrizione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String identificativoPorta;
    @XmlElement(namespace = "http://registry.dao.openspcoop.org")
    protected boolean isRouter;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nome;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String oldNomeForUpdate;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String oldTipoForUpdate;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected XMLGregorianCalendar oraRegistrazione;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String server;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected List<ServizioSpcoop> servizio;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected List<ServizioSpcoop> servizioCorrelato;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected ArrayOfTns2ServizioSpcoop servizioCorrelatoList;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected ArrayOfTns2ServizioSpcoop servizioList;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String superUser;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String tipo;

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
     * Gets the value of the identificativoPorta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoPorta() {
        return identificativoPorta;
    }

    /**
     * Sets the value of the identificativoPorta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoPorta(String value) {
        this.identificativoPorta = value;
    }

    /**
     * Gets the value of the isRouter property.
     * 
     */
    public boolean isIsRouter() {
        return isRouter;
    }

    /**
     * Sets the value of the isRouter property.
     * 
     */
    public void setIsRouter(boolean value) {
        this.isRouter = value;
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
     * Gets the value of the server property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets the value of the server property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServer(String value) {
        this.server = value;
    }

    /**
     * Gets the value of the servizio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servizio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServizio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServizioSpcoop }
     * 
     * 
     */
    public List<ServizioSpcoop> getServizio() {
        if (servizio == null) {
            servizio = new ArrayList<ServizioSpcoop>();
        }
        return this.servizio;
    }

    /**
     * Gets the value of the servizioCorrelato property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servizioCorrelato property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServizioCorrelato().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServizioSpcoop }
     * 
     * 
     */
    public List<ServizioSpcoop> getServizioCorrelato() {
        if (servizioCorrelato == null) {
            servizioCorrelato = new ArrayList<ServizioSpcoop>();
        }
        return this.servizioCorrelato;
    }

    /**
     * Gets the value of the servizioCorrelatoList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns2ServizioSpcoop }
     *     
     */
    public ArrayOfTns2ServizioSpcoop getServizioCorrelatoList() {
        return servizioCorrelatoList;
    }

    /**
     * Sets the value of the servizioCorrelatoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns2ServizioSpcoop }
     *     
     */
    public void setServizioCorrelatoList(ArrayOfTns2ServizioSpcoop value) {
        this.servizioCorrelatoList = value;
    }

    /**
     * Gets the value of the servizioList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns2ServizioSpcoop }
     *     
     */
    public ArrayOfTns2ServizioSpcoop getServizioList() {
        return servizioList;
    }

    /**
     * Sets the value of the servizioList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns2ServizioSpcoop }
     *     
     */
    public void setServizioList(ArrayOfTns2ServizioSpcoop value) {
        this.servizioList = value;
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

}
