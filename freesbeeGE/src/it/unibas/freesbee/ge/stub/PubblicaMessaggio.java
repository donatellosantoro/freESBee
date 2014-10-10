
package it.unibas.freesbee.ge.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pubblicaMessaggio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pubblicaMessaggio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messaggioSoap" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="nomePubblicatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPubblicatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoriaEventi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomePubblicatoreEsterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPubblicatoreEsterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pubblicaMessaggio", propOrder = {
    "messaggioSoap",
    "nomePubblicatore",
    "tipoPubblicatore",
    "categoriaEventi",
    "nomePubblicatoreEsterno",
    "tipoPubblicatoreEsterno"
})
public class PubblicaMessaggio {

    protected Object messaggioSoap;
    protected String nomePubblicatore;
    protected String tipoPubblicatore;
    protected String categoriaEventi;
    protected String nomePubblicatoreEsterno;
    protected String tipoPubblicatoreEsterno;

    /**
     * Gets the value of the messaggioSoap property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMessaggioSoap() {
        return messaggioSoap;
    }

    /**
     * Sets the value of the messaggioSoap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMessaggioSoap(Object value) {
        this.messaggioSoap = value;
    }

    /**
     * Gets the value of the nomePubblicatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomePubblicatore() {
        return nomePubblicatore;
    }

    /**
     * Sets the value of the nomePubblicatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomePubblicatore(String value) {
        this.nomePubblicatore = value;
    }

    /**
     * Gets the value of the tipoPubblicatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPubblicatore() {
        return tipoPubblicatore;
    }

    /**
     * Sets the value of the tipoPubblicatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPubblicatore(String value) {
        this.tipoPubblicatore = value;
    }

    /**
     * Gets the value of the categoriaEventi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaEventi() {
        return categoriaEventi;
    }

    /**
     * Sets the value of the categoriaEventi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaEventi(String value) {
        this.categoriaEventi = value;
    }

    /**
     * Gets the value of the nomePubblicatoreEsterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomePubblicatoreEsterno() {
        return nomePubblicatoreEsterno;
    }

    /**
     * Sets the value of the nomePubblicatoreEsterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomePubblicatoreEsterno(String value) {
        this.nomePubblicatoreEsterno = value;
    }

    /**
     * Gets the value of the tipoPubblicatoreEsterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPubblicatoreEsterno() {
        return tipoPubblicatoreEsterno;
    }

    /**
     * Sets the value of the tipoPubblicatoreEsterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPubblicatoreEsterno(String value) {
        this.tipoPubblicatoreEsterno = value;
    }

}
