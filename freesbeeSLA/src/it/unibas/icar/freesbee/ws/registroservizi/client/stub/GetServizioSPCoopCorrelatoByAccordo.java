
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getServizioSPCoopCorrelatoByAccordo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getServizioSPCoopCorrelatoByAccordo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="soggetto" type="{http://icar.unibas.it/freesbee/}soggettoRS" minOccurs="0"/>
 *         &lt;element name="accordoServizio" type="{http://icar.unibas.it/freesbee/}AccordoServizio" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getServizioSPCoopCorrelatoByAccordo", propOrder = {
    "soggetto",
    "accordoServizio"
})
public class GetServizioSPCoopCorrelatoByAccordo {

    protected SoggettoRS soggetto;
    protected AccordoServizio accordoServizio;

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

    /**
     * Recupera il valore della proprietˆ accordoServizio.
     * 
     * @return
     *     possible object is
     *     {@link AccordoServizio }
     *     
     */
    public AccordoServizio getAccordoServizio() {
        return accordoServizio;
    }

    /**
     * Imposta il valore della proprietˆ accordoServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link AccordoServizio }
     *     
     */
    public void setAccordoServizio(AccordoServizio value) {
        this.accordoServizio = value;
    }

}
