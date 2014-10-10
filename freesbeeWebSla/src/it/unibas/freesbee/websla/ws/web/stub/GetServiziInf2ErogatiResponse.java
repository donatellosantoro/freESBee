
package it.unibas.freesbee.websla.ws.web.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getServiziInf2ErogatiResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getServiziInf2ErogatiResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servizio" type="{http://web.ws.freesbeesla.unibas.it/}servizio" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getServiziInf2ErogatiResponse", propOrder = {
    "servizio"
})
public class GetServiziInf2ErogatiResponse {

    protected List<Servizio> servizio;

    /**
     * Gets the value of the servizio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servizio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServizio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Servizio }
     * 
     * 
     */
    public List<Servizio> getServizio() {
        if (servizio == null) {
            servizio = new ArrayList<Servizio>();
        }
        return this.servizio;
    }

}
