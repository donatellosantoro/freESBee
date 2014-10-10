
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per profiloEGov complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
@XmlType(name = "profiloEGov", propOrder = {
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
     * Recupera il valore della proprietˆ confermaRicezione.
     * 
     */
    public boolean isConfermaRicezione() {
        return confermaRicezione;
    }

    /**
     * Imposta il valore della proprietˆ confermaRicezione.
     * 
     */
    public void setConfermaRicezione(boolean value) {
        this.confermaRicezione = value;
    }

    /**
     * Recupera il valore della proprietˆ consegnaInOrdine.
     * 
     */
    public boolean isConsegnaInOrdine() {
        return consegnaInOrdine;
    }

    /**
     * Imposta il valore della proprietˆ consegnaInOrdine.
     * 
     */
    public void setConsegnaInOrdine(boolean value) {
        this.consegnaInOrdine = value;
    }

    /**
     * Recupera il valore della proprietˆ gestioneDuplicati.
     * 
     */
    public boolean isGestioneDuplicati() {
        return gestioneDuplicati;
    }

    /**
     * Imposta il valore della proprietˆ gestioneDuplicati.
     * 
     */
    public void setGestioneDuplicati(boolean value) {
        this.gestioneDuplicati = value;
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
     * Recupera il valore della proprietˆ idCollaborazione.
     * 
     */
    public boolean isIdCollaborazione() {
        return idCollaborazione;
    }

    /**
     * Imposta il valore della proprietˆ idCollaborazione.
     * 
     */
    public void setIdCollaborazione(boolean value) {
        this.idCollaborazione = value;
    }

    /**
     * Recupera il valore della proprietˆ scadenza.
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
     * Imposta il valore della proprietˆ scadenza.
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
