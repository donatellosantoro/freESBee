
package it.unibas.icar.freesbee.ws.client.azione.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for azione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="azione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idAccordoServizio" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idProfiloEGov" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "azione", propOrder = {
    "id",
    "idAccordoServizio",
    "idProfiloEGov",
    "nome",
    "descrizione",
    "profiloCollaborazione"
})
public class Azione {

    protected long id;
    protected long idAccordoServizio;
    protected long idProfiloEGov;
    protected String nome;
    protected String descrizione;
    protected String profiloCollaborazione;

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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
