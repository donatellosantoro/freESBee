
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per existsSoggettoSPCoop complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="existsSoggettoSPCoop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="soggetto" type="{http://icar.unibas.it/freesbee/}soggettoRS" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "existsSoggettoSPCoop", propOrder = {
    "soggetto"
})
public class ExistsSoggettoSPCoop {

    @XmlElement(namespace = "http://icar.unibas.it/freesbee/")
    protected SoggettoRS soggetto;

    /**
     * Recupera il valore della proprietˆ soggetto.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRS }
     *     
     */
    public SoggettoRS getSoggetto() {
        return soggetto;
    }

    /**
     * Imposta il valore della proprietˆ soggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRS }
     *     
     */
    public void setSoggetto(SoggettoRS value) {
        this.soggetto = value;
    }

}
