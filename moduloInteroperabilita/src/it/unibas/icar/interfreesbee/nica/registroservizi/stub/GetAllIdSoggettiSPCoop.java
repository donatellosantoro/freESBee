
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllIdSoggettiSPCoop element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="getAllIdSoggettiSPCoop">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="filtroRicerca" type="{http://driver.registry.dao.openspcoop.org}FiltroSPCoop"/>
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
@XmlRootElement(name = "getAllIdSoggettiSPCoop")
public class GetAllIdSoggettiSPCoop {

    @XmlElement(namespace = "http://ws.registry.openspcoop.org", required = true)
    protected FiltroSPCoop filtroRicerca;

    /**
     * Gets the value of the filtroRicerca property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroSPCoop }
     *     
     */
    public FiltroSPCoop getFiltroRicerca() {
        return filtroRicerca;
    }

    /**
     * Sets the value of the filtroRicerca property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroSPCoop }
     *     
     */
    public void setFiltroRicerca(FiltroSPCoop value) {
        this.filtroRicerca = value;
    }

}
