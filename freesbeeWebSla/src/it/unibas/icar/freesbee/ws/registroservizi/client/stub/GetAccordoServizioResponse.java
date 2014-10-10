
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getAccordoServizioResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getAccordoServizioResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://icar.unibas.it/freesbee/}accordoServizioRSRisposta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccordoServizioResponse", propOrder = {
    "_return"
})
public class GetAccordoServizioResponse {

    @XmlElement(name = "return")
    protected AccordoServizioRSRisposta _return;

    /**
     * Recupera il valore della proprietˆ return.
     * 
     * @return
     *     possible object is
     *     {@link AccordoServizioRSRisposta }
     *     
     */
    public AccordoServizioRSRisposta getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprietˆ return.
     * 
     * @param value
     *     allowed object is
     *     {@link AccordoServizioRSRisposta }
     *     
     */
    public void setReturn(AccordoServizioRSRisposta value) {
        this._return = value;
    }

}
