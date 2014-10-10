
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for profiloEGov complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="profiloEGov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confermaRicezione" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="consegnaInOrdine" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="gestioneDuplicati" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCollaborazione" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="scadenza" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "profiloEGovWs" ,propOrder = {
    "confermaRicezione",
    "consegnaInOrdine",
    "gestioneDuplicati",
    "id",
    "idCollaborazione",
    "scadenza"
})
public class ProfiloEGov {

    protected boolean confermaRicezione;
    protected boolean consegnaInOrdine;
    protected boolean gestioneDuplicati;
    protected long id;
    protected boolean idCollaborazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scadenza;

    /**
     * Gets the value of the confermaRicezione property.
     * 
     */
    public boolean isConfermaRicezione() {
        return confermaRicezione;
    }

    /**
     * Sets the value of the confermaRicezione property.
     * 
     */
    public void setConfermaRicezione(boolean value) {
        this.confermaRicezione = value;
    }

    /**
     * Gets the value of the consegnaInOrdine property.
     * 
     */
    public boolean isConsegnaInOrdine() {
        return consegnaInOrdine;
    }

    /**
     * Sets the value of the consegnaInOrdine property.
     * 
     */
    public void setConsegnaInOrdine(boolean value) {
        this.consegnaInOrdine = value;
    }

    /**
     * Gets the value of the gestioneDuplicati property.
     * 
     */
    public boolean isGestioneDuplicati() {
        return gestioneDuplicati;
    }

    /**
     * Sets the value of the gestioneDuplicati property.
     * 
     */
    public void setGestioneDuplicati(boolean value) {
        this.gestioneDuplicati = value;
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
     * Gets the value of the idCollaborazione property.
     * 
     */
    public boolean isIdCollaborazione() {
        return idCollaborazione;
    }

    /**
     * Sets the value of the idCollaborazione property.
     * 
     */
    public void setIdCollaborazione(boolean value) {
        this.idCollaborazione = value;
    }

    /**
     * Gets the value of the scadenza property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScadenza() {
        return scadenza;
    }

    /**
     * Sets the value of the scadenza property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScadenza(XMLGregorianCalendar value) {
        this.scadenza = value;
    }

}
