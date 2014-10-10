
package it.unibas.freesbee.websla.ws.web.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getSoggettiSLA complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getSoggettiSLA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomeAccordoServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSoggettiSLA", propOrder = {
    "nomeAccordoServizio",
    "nomeServizio",
    "tipoServizio"
})
public class GetSoggettiSLA {

    protected String nomeAccordoServizio;
    protected String nomeServizio;
    protected String tipoServizio;

    /**
     * Recupera il valore della proprietˆ nomeAccordoServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeAccordoServizio() {
        return nomeAccordoServizio;
    }

    /**
     * Imposta il valore della proprietˆ nomeAccordoServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeAccordoServizio(String value) {
        this.nomeAccordoServizio = value;
    }

    /**
     * Recupera il valore della proprietˆ nomeServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeServizio() {
        return nomeServizio;
    }

    /**
     * Imposta il valore della proprietˆ nomeServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeServizio(String value) {
        this.nomeServizio = value;
    }

    /**
     * Recupera il valore della proprietˆ tipoServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServizio() {
        return tipoServizio;
    }

    /**
     * Imposta il valore della proprietˆ tipoServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServizio(String value) {
        this.tipoServizio = value;
    }

}
