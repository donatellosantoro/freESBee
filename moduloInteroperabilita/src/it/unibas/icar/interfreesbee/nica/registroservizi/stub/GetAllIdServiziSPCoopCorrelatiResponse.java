
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllIdServiziSPCoopCorrelatiResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getAllIdServiziSPCoopCorrelatiResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="getAllIdServiziSPCoopCorrelatiReturn" type="{http://driver.registry.dao.openspcoop.org}IDServizio" maxOccurs="unbounded"/>
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
    "getAllIdServiziSPCoopCorrelatiReturn"
})
@XmlRootElement(name = "getAllIdServiziSPCoopCorrelatiResponse")
public class GetAllIdServiziSPCoopCorrelatiResponse {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected List<IDServizio> getAllIdServiziSPCoopCorrelatiReturn;

    /**
     * Gets the value of the getAllIdServiziSPCoopCorrelatiReturn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getAllIdServiziSPCoopCorrelatiReturn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGetAllIdServiziSPCoopCorrelatiReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDServizio }
     * 
     * 
     */
    public List<IDServizio> getGetAllIdServiziSPCoopCorrelatiReturn() {
        if (getAllIdServiziSPCoopCorrelatiReturn == null) {
            getAllIdServiziSPCoopCorrelatiReturn = new ArrayList<IDServizio>();
        }
        return this.getAllIdServiziSPCoopCorrelatiReturn;
    }

}
