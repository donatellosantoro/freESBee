
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllIdServiziSPCoopResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getAllIdServiziSPCoopResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="getAllIdServiziSPCoopReturn" type="{http://driver.registry.dao.openspcoop.org}IDServizio" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getAllIdServiziSPCoopReturn"
})
@XmlRootElement(name = "getAllIdServiziSPCoopResponse")
public class GetAllIdServiziSPCoopResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected List<IDServizio> getAllIdServiziSPCoopReturn;

    /**
     * Gets the value of the getAllIdServiziSPCoopReturn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getAllIdServiziSPCoopReturn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGetAllIdServiziSPCoopReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDServizio }
     * 
     * 
     */
    public List<IDServizio> getGetAllIdServiziSPCoopReturn() {
        if (getAllIdServiziSPCoopReturn == null) {
            getAllIdServiziSPCoopReturn = new ArrayList<IDServizio>();
        }
        return this.getAllIdServiziSPCoopReturn;
    }

}
