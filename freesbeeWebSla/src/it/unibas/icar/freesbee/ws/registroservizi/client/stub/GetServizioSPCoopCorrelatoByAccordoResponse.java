
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getServizioSPCoopCorrelatoByAccordoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getServizioSPCoopCorrelatoByAccordoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://icar.unibas.it/freesbee/}servizioRSRisposta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getServizioSPCoopCorrelatoByAccordoResponse", propOrder = {
    "_return"
})
public class GetServizioSPCoopCorrelatoByAccordoResponse {

    @XmlElement(name = "return")
    protected ServizioRSRisposta _return;

    /**
     * Recupera il valore della proprietˆ return.
     * 
     * @return
     *     possible object is
     *     {@link ServizioRSRisposta }
     *     
     */
    public ServizioRSRisposta getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprietˆ return.
     * 
     * @param value
     *     allowed object is
     *     {@link ServizioRSRisposta }
     *     
     */
    public void setReturn(ServizioRSRisposta value) {
        this._return = value;
    }

}
