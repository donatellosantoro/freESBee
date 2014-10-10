
package it.unibas.freesbee.websla.ws.web.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statisticheErogatore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="statisticheErogatore">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://web.ws.freesbeesla.unibas.it/}StatisticheSLAErogatore" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statisticheErogatore", propOrder = {
    "statisticheSLAErogatore"
})
public class StatisticheErogatore {

    @XmlElement(name = "StatisticheSLAErogatore")
    protected StatisticheSLAErogatore statisticheSLAErogatore;

    /**
     * Gets the value of the statisticheSLAErogatore property.
     * 
     * @return
     *     possible object is
     *     {@link StatisticheSLAErogatore }
     *     
     */
    public StatisticheSLAErogatore getStatisticheSLAErogatore() {
        return statisticheSLAErogatore;
    }

    /**
     * Sets the value of the statisticheSLAErogatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatisticheSLAErogatore }
     *     
     */
    public void setStatisticheSLAErogatore(StatisticheSLAErogatore value) {
        this.statisticheSLAErogatore = value;
    }

}
