
package it.unibas.icar.interfreesbee.nica.registroservizi.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServizioSpcoopAzione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServizioSpcoopAzione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connettore" type="{http://registry.dao.openspcoop.org}Connettore"/>
 *         &lt;element name="fruitore" type="{http://registry.dao.openspcoop.org}ServizioSpcoopAzioneFruitore" maxOccurs="unbounded"/>
 *         &lt;element name="fruitoreList" type="{http://ws.registry.openspcoop.org}ArrayOf_tns2_ServizioSpcoopAzioneFruitore"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServizioSpcoopAzione", /*namespace = "http://registry.dao.openspcoop.org",*/ propOrder = {
    "connettore",
    "fruitore",
    "fruitoreList",
    "id",
    "nome"
})
public class ServizioSpcoopAzione {

    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected Connettore connettore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected List<ServizioSpcoopAzioneFruitore> fruitore;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected ArrayOfTns2ServizioSpcoopAzioneFruitore fruitoreList;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(/*namespace = "http://registry.dao.openspcoop.org",*/ required = true, nillable = true)
    protected String nome;

    /**
     * Gets the value of the connettore property.
     * 
     * @return
     *     possible object is
     *     {@link Connettore }
     *     
     */
    public Connettore getConnettore() {
        return connettore;
    }

    /**
     * Sets the value of the connettore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Connettore }
     *     
     */
    public void setConnettore(Connettore value) {
        this.connettore = value;
    }

    /**
     * Gets the value of the fruitore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fruitore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFruitore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServizioSpcoopAzioneFruitore }
     * 
     * 
     */
    public List<ServizioSpcoopAzioneFruitore> getFruitore() {
        if (fruitore == null) {
            fruitore = new ArrayList<ServizioSpcoopAzioneFruitore>();
        }
        return this.fruitore;
    }

    /**
     * Gets the value of the fruitoreList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns2ServizioSpcoopAzioneFruitore }
     *     
     */
    public ArrayOfTns2ServizioSpcoopAzioneFruitore getFruitoreList() {
        return fruitoreList;
    }

    /**
     * Sets the value of the fruitoreList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns2ServizioSpcoopAzioneFruitore }
     *     
     */
    public void setFruitoreList(ArrayOfTns2ServizioSpcoopAzioneFruitore value) {
        this.fruitoreList = value;
    }

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

}
