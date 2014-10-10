
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per existsFruitoreServizioSpcoop complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="existsFruitoreServizioSpcoop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servizio" type="{http://icar.unibas.it/freesbee/}servizioRS" minOccurs="0"/>
 *         &lt;element name="soggettoFruitore" type="{http://icar.unibas.it/freesbee/}soggettoRS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "existsFruitoreServizioSpcoop", propOrder = {
    "servizio",
    "soggettoFruitore"
})
public class ExistsFruitoreServizioSpcoop {

    protected ServizioRS servizio;
    protected SoggettoRS soggettoFruitore;

    /**
     * Recupera il valore della propriet� servizio.
     * 
     * @return
     *     possible object is
     *     {@link ServizioRS }
     *     
     */
    public ServizioRS getServizio() {
        return servizio;
    }

    /**
     * Imposta il valore della propriet� servizio.
     * 
     * @param value
     *     allowed object is
     *     {@link ServizioRS }
     *     
     */
    public void setServizio(ServizioRS value) {
        this.servizio = value;
    }

    /**
     * Recupera il valore della propriet� soggettoFruitore.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRS }
     *     
     */
    public SoggettoRS getSoggettoFruitore() {
        return soggettoFruitore;
    }

    /**
     * Imposta il valore della propriet� soggettoFruitore.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRS }
     *     
     */
    public void setSoggettoFruitore(SoggettoRS value) {
        this.soggettoFruitore = value;
    }

}
