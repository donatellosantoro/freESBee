
package it.unibas.icar.freesbee.ws.client.accordoservizio.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for accordoServizio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accordoServizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idProfiloEGov" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldNomeForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="policyXACML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="profiloCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accordoServizio", propOrder = {
    "descrizione",
    "id",
    "idProfiloEGov",
    "nome",
    "oldNomeForUpdate",
    "oraRegistrazione",
    "policyXACML",
    "privato",
    "profiloCollaborazione"
})
public class AccordoServizio {

    protected String descrizione;
    protected long id;
    protected long idProfiloEGov;
    protected String nome;
    protected String oldNomeForUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar oraRegistrazione;
    protected String policyXACML;
    protected boolean privato;
    protected String profiloCollaborazione;

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
     * Gets the value of the idProfiloEGov property.
     * 
     */
    public long getIdProfiloEGov() {
        return idProfiloEGov;
    }

    /**
     * Sets the value of the idProfiloEGov property.
     * 
     */
    public void setIdProfiloEGov(long value) {
        this.idProfiloEGov = value;
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
     * Gets the value of the policyXACML property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyXACML() {
        return policyXACML;
    }

    /**
     * Sets the value of the policyXACML property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyXACML(String value) {
        this.policyXACML = value;
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

}
