
package it.unibas.freesbee.websla.ws.web.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for statisticheSLAErogatore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="statisticheSLAErogatore">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nomeErogatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeFruitore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomiParSla" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="slaNome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="step" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statisticheSLAErogatore", propOrder = {
    "dataFine",
    "dataInizio",
    "nomeErogatore",
    "nomeFruitore",
    "nomeServizio",
    "nomiParSla",
    "slaNome",
    "step"
})
public class StatisticheSLAErogatore {

    protected XMLGregorianCalendar dataFine;
    protected XMLGregorianCalendar dataInizio;
    protected String nomeErogatore;
    protected String nomeFruitore;
    protected String nomeServizio;
    @XmlElement(required = true, nillable = true)
    protected List<Object> nomiParSla;
    protected String slaNome;
    protected String step;

    /**
     * Gets the value of the dataFine property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Sets the value of the dataFine property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
    }

    /**
     * Gets the value of the dataInizio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Sets the value of the dataInizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(XMLGregorianCalendar value) {
        this.dataInizio = value;
    }

    /**
     * Gets the value of the nomeErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeErogatore() {
        return nomeErogatore;
    }

    /**
     * Sets the value of the nomeErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeErogatore(String value) {
        this.nomeErogatore = value;
    }

    /**
     * Gets the value of the nomeFruitore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeFruitore() {
        return nomeFruitore;
    }

    /**
     * Sets the value of the nomeFruitore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeFruitore(String value) {
        this.nomeFruitore = value;
    }

    /**
     * Gets the value of the nomeServizio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeServizio() {
        return nomeServizio;
    }

    /**
     * Sets the value of the nomeServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeServizio(String value) {
        this.nomeServizio = value;
    }

    /**
     * Gets the value of the nomiParSla property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nomiParSla property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNomiParSla().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getNomiParSla() {
        if (nomiParSla == null) {
            nomiParSla = new ArrayList<Object>();
        }
        return this.nomiParSla;
    }

    /**
     * Gets the value of the slaNome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaNome() {
        return slaNome;
    }

    /**
     * Sets the value of the slaNome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaNome(String value) {
        this.slaNome = value;
    }

    /**
     * Gets the value of the step property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStep() {
        return step;
    }

    /**
     * Sets the value of the step property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStep(String value) {
        this.step = value;
    }

}
