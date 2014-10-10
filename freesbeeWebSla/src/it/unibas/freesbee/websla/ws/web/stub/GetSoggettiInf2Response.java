
package it.unibas.freesbee.websla.ws.web.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getSoggettiInf2Response complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getSoggettiInf2Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="soggetto" type="{http://web.ws.freesbeesla.unibas.it/}soggetto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSoggettiInf2Response", propOrder = {
    "soggetto"
})
public class GetSoggettiInf2Response {

    protected List<Soggetto> soggetto;

    /**
     * Gets the value of the soggetto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the soggetto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoggetto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Soggetto }
     * 
     * 
     */
    public List<Soggetto> getSoggetto() {
        if (soggetto == null) {
            soggetto = new ArrayList<Soggetto>();
        }
        return this.soggetto;
    }

}
