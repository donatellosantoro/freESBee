
package it.unibas.icar.freesbee.ws.client.autenticazione.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRuolo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRuolo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomeUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRuolo", propOrder = {
    "nomeUtente"
})
public class GetRuolo {

    protected String nomeUtente;

    /**
     * Gets the value of the nomeUtente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeUtente() {
        return nomeUtente;
    }

    /**
     * Sets the value of the nomeUtente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeUtente(String value) {
        this.nomeUtente = value;
    }

}
