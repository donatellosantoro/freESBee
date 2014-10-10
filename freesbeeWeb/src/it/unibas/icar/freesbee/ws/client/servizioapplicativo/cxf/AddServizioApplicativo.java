
package it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addServizioApplicativo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addServizioApplicativo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servizioApplicativo" type="{http://icar.unibas.it/freesbee/}servizioApplicativo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addServizioApplicativo", propOrder = {
    "servizioApplicativo"
})
public class AddServizioApplicativo {

    protected ServizioApplicativo servizioApplicativo;

    /**
     * Gets the value of the servizioApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link ServizioApplicativo }
     *     
     */
    public ServizioApplicativo getServizioApplicativo() {
        return servizioApplicativo;
    }

    /**
     * Sets the value of the servizioApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServizioApplicativo }
     *     
     */
    public void setServizioApplicativo(ServizioApplicativo value) {
        this.servizioApplicativo = value;
    }

}
