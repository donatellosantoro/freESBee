
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per accordoServizioRSRisposta complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="accordoServizioRSRisposta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="azioni" type="{http://icar.unibas.it/freesbee/}azioneRSRisposta" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldNomeForUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oraRegistrazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="policyXACML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="profiloCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profiloEGov" type="{http://icar.unibas.it/freesbee/}profiloEGov" minOccurs="0"/>
 *         &lt;element name="servizi" type="{http://icar.unibas.it/freesbee/}servizioRSRisposta" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accordoServizioRSRisposta", propOrder = {
    "azioni",
    "descrizione",
    "nome",
    "oldNomeForUpdate",
    "oraRegistrazione",
    "policyXACML",
    "privato",
    "profiloCollaborazione",
    "profiloEGov",
    "servizi"
})
public class AccordoServizioRSRisposta {

    protected List<AzioneRSRisposta> azioni;
    protected String descrizione;
    protected String nome;
    protected String oldNomeForUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar oraRegistrazione;
    protected String policyXACML;
    protected boolean privato;
    protected String profiloCollaborazione;
    protected ProfiloEGov profiloEGov;
    protected List<ServizioRSRisposta> servizi;

    /**
     * Gets the value of the azioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the azioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAzioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AzioneRSRisposta }
     * 
     * 
     */
    public List<AzioneRSRisposta> getAzioni() {
        if (azioni == null) {
            azioni = new ArrayList<AzioneRSRisposta>();
        }
        return this.azioni;
    }

    /**
     * Recupera il valore della proprietˆ descrizione.
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
     * Imposta il valore della proprietˆ descrizione.
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
     * Recupera il valore della proprietˆ policyXACML.
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
     * Imposta il valore della proprietˆ policyXACML.
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
     * Recupera il valore della proprietˆ profiloCollaborazione.
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
     * Imposta il valore della proprietˆ profiloCollaborazione.
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
     * Recupera il valore della proprietˆ profiloEGov.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloEGov }
     *     
     */
    public ProfiloEGov getProfiloEGov() {
        return profiloEGov;
    }

    /**
     * Imposta il valore della proprietˆ profiloEGov.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloEGov }
     *     
     */
    public void setProfiloEGov(ProfiloEGov value) {
        this.profiloEGov = value;
    }

    /**
     * Gets the value of the servizi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servizi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServizi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServizioRSRisposta }
     * 
     * 
     */
    public List<ServizioRSRisposta> getServizi() {
        if (servizi == null) {
            servizi = new ArrayList<ServizioRSRisposta>();
        }
        return this.servizi;
    }

}
