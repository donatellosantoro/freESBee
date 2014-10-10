
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getFruitoreServizioSpcoopResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getFruitoreServizioSpcoopResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://icar.unibas.it/freesbee/}soggettoRSRisposta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFruitoreServizioSpcoopResponse", propOrder = {
    "_return"
})
public class GetFruitoreServizioSpcoopResponse {

    @XmlElement(name = "return")
    protected SoggettoRSRisposta _return;

    /**
     * Recupera il valore della proprietˆ return.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRSRisposta }
     *     
     */
    public SoggettoRSRisposta getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprietˆ return.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRSRisposta }
     *     
     */
    public void setReturn(SoggettoRSRisposta value) {
        this._return = value;
    }

}
