
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllIdServiziSPCoopCorrelati element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getAllIdServiziSPCoopCorrelati">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="filtroRicerca" type="{http://driver.registry.dao.openspcoop.org}FiltroServiziSPCoop"/>
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
    "filtroRicerca"
})
@XmlRootElement(name = "getAllIdServiziSPCoopCorrelati")
public class GetAllIdServiziSPCoopCorrelati {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected FiltroServiziSPCoop filtroRicerca;

    /**
     * Gets the value of the filtroRicerca property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroServiziSPCoop }
     *     
     */
    public FiltroServiziSPCoop getFiltroRicerca() {
        return filtroRicerca;
    }

    /**
     * Sets the value of the filtroRicerca property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroServiziSPCoop }
     *     
     */
    public void setFiltroRicerca(FiltroServiziSPCoop value) {
        this.filtroRicerca = value;
    }

}
