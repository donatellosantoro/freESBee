//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.09.20 at 02:59:44 PM CEST 
//


package it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Max complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Max">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.ggf.org/graap/2007/03/ws-agreement}FunctionType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://schemas.ggf.org/graap/2007/03/ws-agreement}BasicMetric"/>
 *           &lt;element name="Function" type="{http://schemas.ggf.org/graap/2007/03/ws-agreement}FunctionType"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://schemas.ggf.org/graap/2007/03/ws-agreement}Window"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Max", namespace = "http://schemas.ggf.org/graap/2007/03/ws-agreement", propOrder = {
    "basicMetric",
    "function",
    "window"
})
public class Max
    extends FunctionType
{

    @XmlElement(name = "BasicMetric", namespace = "http://schemas.ggf.org/graap/2007/03/ws-agreement")
    protected BasicMetric basicMetric;
    @XmlElement(name = "Function", namespace = "http://schemas.ggf.org/graap/2007/03/ws-agreement")
    protected FunctionType function;
    @XmlElement(name = "Window", namespace = "http://schemas.ggf.org/graap/2007/03/ws-agreement", required = true)
    protected Window window;

    /**
     * Gets the value of the basicMetric property.
     * 
     * @return
     *     possible object is
     *     {@link BasicMetric }
     *     
     */
    public BasicMetric getBasicMetric() {
        return basicMetric;
    }

    /**
     * Sets the value of the basicMetric property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicMetric }
     *     
     */
    public void setBasicMetric(BasicMetric value) {
        this.basicMetric = value;
    }

    /**
     * Gets the value of the function property.
     * 
     * @return
     *     possible object is
     *     {@link FunctionType }
     *     
     */
    public FunctionType getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     * 
     * @param value
     *     allowed object is
     *     {@link FunctionType }
     *     
     */
    public void setFunction(FunctionType value) {
        this.function = value;
    }

    /**
     * Gets the value of the window property.
     * 
     * @return
     *     possible object is
     *     {@link Window }
     *     
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Sets the value of the window property.
     * 
     * @param value
     *     allowed object is
     *     {@link Window }
     *     
     */
    public void setWindow(Window value) {
        this.window = value;
    }

}
