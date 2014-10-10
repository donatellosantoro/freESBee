
package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addCategoriaEventiEsterna complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addCategoriaEventiEsterna">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoriaEventi" type="{http://ge.freesbee.unibas.it/}categoriaEventiEsterna" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addCategoriaEventiEsterna", propOrder = {
    "categoriaEventi"
})
public class AddCategoriaEventiEsterna {

    protected CategoriaEventiEsterna categoriaEventi;

    /**
     * Gets the value of the categoriaEventi property.
     * 
     * @return
     *     possible object is
     *     {@link CategoriaEventiEsterna }
     *     
     */
    public CategoriaEventiEsterna getCategoriaEventi() {
        return categoriaEventi;
    }

    /**
     * Sets the value of the categoriaEventi property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriaEventiEsterna }
     *     
     */
    public void setCategoriaEventi(CategoriaEventiEsterna value) {
        this.categoriaEventi = value;
    }

}
