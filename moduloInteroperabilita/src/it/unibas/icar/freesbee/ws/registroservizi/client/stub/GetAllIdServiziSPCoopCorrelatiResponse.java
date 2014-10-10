
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.unibas.icar.freesbee.ws.registroservizi.ServizioRS;

/**
 * <p>Java class for getAllIdServiziSPCoopCorrelatiResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllIdServiziSPCoopCorrelatiResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://icar.unibas.it/freesbee/}servizioRS" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllIdServiziSPCoopCorrelatiResponse", propOrder = {
    "_return"
})
public class GetAllIdServiziSPCoopCorrelatiResponse {

    @XmlElement(name = "return")
    protected List<ServizioRS> _return;

    /**
     * Gets the value of the return property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the return property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServizioRS }
     * 
     * 
     */
    public List<ServizioRS> getReturn() {
        if (_return == null) {
            _return = new ArrayList<ServizioRS>();
        }
        return this._return;
    }

}
