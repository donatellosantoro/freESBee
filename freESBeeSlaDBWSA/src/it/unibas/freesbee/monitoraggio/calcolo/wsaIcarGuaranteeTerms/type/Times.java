//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.09.20 at 02:59:44 PM CEST 
//


package it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Times complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Times">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.ggf.org/graap/2007/03/ws-agreement}FunctionType">
 *       &lt;sequence>
 *         &lt;element name="Operand" type="{http://schemas.ggf.org/graap/2007/03/ws-agreement}OperandType" maxOccurs="2" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Times", namespace = "http://schemas.ggf.org/graap/2007/03/ws-agreement", propOrder = {
    "operand"
})
public class Times
    extends FunctionType
{

    @XmlElement(name = "Operand", namespace = "http://schemas.ggf.org/graap/2007/03/ws-agreement", required = true)
    protected List<OperandType> operand;

    /**
     * Gets the value of the operand property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operand property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperand().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperandType }
     * 
     * 
     */
    public List<OperandType> getOperand() {
        if (operand == null) {
            operand = new ArrayList<OperandType>();
        }
        return this.operand;
    }

}
