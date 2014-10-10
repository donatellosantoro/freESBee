
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FiltroServiziSPCoop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FiltroServiziSPCoop">
 *   &lt;complexContent>
 *     &lt;extension base="{http://driver.registry.dao.openspcoop.org}FiltroSPCoop">
 *       &lt;sequence>
 *         &lt;element name="nomeAccordo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nomeSoggettoErogatore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoSoggettoErogatore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FiltroServiziSPCoop", /*namespace = "http://driver.registry.dao.openspcoop.org",*/ propOrder = {
    "nomeAccordo",
    "nomeSoggettoErogatore",
    "tipoSoggettoErogatore"
})
public class FiltroServiziSPCoop
    extends FiltroSPCoop
{

    @XmlElement(/*namespace = "http://driver.registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nomeAccordo;
    @XmlElement(/*namespace = "http://driver.registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nomeSoggettoErogatore;
    @XmlElement(/*namespace = "http://driver.registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String tipoSoggettoErogatore;

    /**
     * Gets the value of the nomeAccordo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeAccordo() {
        return nomeAccordo;
    }

    /**
     * Sets the value of the nomeAccordo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeAccordo(String value) {
        this.nomeAccordo = value;
    }

    /**
     * Gets the value of the nomeSoggettoErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeSoggettoErogatore() {
        return nomeSoggettoErogatore;
    }

    /**
     * Sets the value of the nomeSoggettoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeSoggettoErogatore(String value) {
        this.nomeSoggettoErogatore = value;
    }

    /**
     * Gets the value of the tipoSoggettoErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSoggettoErogatore() {
        return tipoSoggettoErogatore;
    }

    /**
     * Sets the value of the tipoSoggettoErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSoggettoErogatore(String value) {
        this.tipoSoggettoErogatore = value;
    }

}
