
package it.unibas.freesbee.ge.web.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateCategoriaEventiInterna complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateCategoriaEventiInterna">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoriaEventi" type="{http://ge.freesbee.unibas.it/}categoriaEventiInterna" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateCategoriaEventiInterna", propOrder = {
    "categoriaEventi"
})
public class UpdateCategoriaEventiInterna {

    protected CategoriaEventiInterna categoriaEventi;

    /**
     * Gets the value of the categoriaEventi property.
     * 
     * @return
     *     possible object is
     *     {@link CategoriaEventiInterna }
     *     
     */
    public CategoriaEventiInterna getCategoriaEventi() {
        return categoriaEventi;
    }

    /**
     * Sets the value of the categoriaEventi property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriaEventiInterna }
     *     
     */
    public void setCategoriaEventi(CategoriaEventiInterna value) {
        this.categoriaEventi = value;
    }

}
