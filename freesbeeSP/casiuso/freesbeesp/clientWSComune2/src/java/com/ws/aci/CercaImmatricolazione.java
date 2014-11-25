
package com.ws.aci;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per cercaImmatricolazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="cercaImmatricolazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="targa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cercaImmatricolazione", propOrder = {
    "targa"
})
public class CercaImmatricolazione {

    protected String targa;

    /**
     * Recupera il valore della proprietà targa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarga() {
        return targa;
    }

    /**
     * Imposta il valore della proprietà targa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarga(String value) {
        this.targa = value;
    }

}
