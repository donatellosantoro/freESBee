
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per existsAccordoServizioResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="existsAccordoServizioResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "existsAccordoServizioResponse", propOrder = {
    "_return"
})
public class ExistsAccordoServizioResponse {

    @XmlElement(name = "return")
    protected boolean _return;

    /**
     * Recupera il valore della proprietˆ return.
     * 
     */
    public boolean isReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprietˆ return.
     * 
     */
    public void setReturn(boolean value) {
        this._return = value;
    }

}
