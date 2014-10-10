
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Connettore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Connettore">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="properties" type="{http://xml.apache.org/xml-soap}Map"/>
 *         &lt;element name="property" type="{http://registry.dao.openspcoop.org}ConnettoreProperty" maxOccurs="unbounded"/>
 *         &lt;element name="propertyList" type="{http://ws.registry.openspcoop.org}ArrayOf_tns2_ConnettoreProperty"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Connettore", /*namespace = "http://registry.dao.openspcoop.org",*/ propOrder = {
    "id",
    "nome",
    "properties",
    "property",
    "propertyList",
    "tipo"
})
public class Connettore {

    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nome;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected Map properties;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected List<ConnettoreProperty> property;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected ArrayOfTns2ConnettoreProperty propertyList;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String tipo;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link Map }
     *     
     */
    public Map getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Map }
     *     
     */
    public void setProperties(Map value) {
        this.properties = value;
    }

    /**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConnettoreProperty }
     * 
     * 
     */
    public List<ConnettoreProperty> getProperty() {
        if (property == null) {
            property = new ArrayList<ConnettoreProperty>();
        }
        return this.property;
    }

    /**
     * Gets the value of the propertyList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns2ConnettoreProperty }
     *     
     */
    public ArrayOfTns2ConnettoreProperty getPropertyList() {
        return propertyList;
    }

    /**
     * Sets the value of the propertyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns2ConnettoreProperty }
     *     
     */
    public void setPropertyList(ArrayOfTns2ConnettoreProperty value) {
        this.propertyList = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}
