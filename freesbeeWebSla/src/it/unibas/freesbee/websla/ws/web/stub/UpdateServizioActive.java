
package it.unibas.freesbee.websla.ws.web.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per updateServizioActive complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="updateServizioActive">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servizio" type="{http://web.ws.freesbeesla.unibas.it/}servizio" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateServizioActive", propOrder = {
    "servizio"
})
public class UpdateServizioActive {

    protected Servizio servizio;

    /**
     * Recupera il valore della proprietˆ servizio.
     * 
     * @return
     *     possible object is
     *     {@link Servizio }
     *     
     */
    public Servizio getServizio() {
        return servizio;
    }

    /**
     * Imposta il valore della proprietˆ servizio.
     * 
     * @param value
     *     allowed object is
     *     {@link Servizio }
     *     
     */
    public void setServizio(Servizio value) {
        this.servizio = value;
    }

}
