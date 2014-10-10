
package it.unibas.icar.freesbee.ws.client.servizioapplicativo.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for removeServizioApplicativo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="removeServizioApplicativo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servizioApplicativo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeServizioApplicativo", propOrder = {
    "servizioApplicativo"
})
public class RemoveServizioApplicativo {

    protected long servizioApplicativo;

    /**
     * Gets the value of the servizioApplicativo property.
     * 
     */
    public long getServizioApplicativo() {
        return servizioApplicativo;
    }

    /**
     * Sets the value of the servizioApplicativo property.
     * 
     */
    public void setServizioApplicativo(long value) {
        this.servizioApplicativo = value;
    }

}
