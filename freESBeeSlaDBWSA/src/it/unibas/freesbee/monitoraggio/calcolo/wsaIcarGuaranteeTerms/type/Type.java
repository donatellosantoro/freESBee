//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.09.20 at 02:59:44 PM CEST 
//


package it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * <p>Java class for Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="integer"/>
 *     &lt;enumeration value="float"/>
 *     &lt;enumeration value="double"/>
 *     &lt;enumeration value="long"/>
 *     &lt;enumeration value="byte"/>
 *     &lt;enumeration value="boolean"/>
 *     &lt;enumeration value="string"/>
 *     &lt;enumeration value="time"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Type {

    @XmlEnumValue("integer")
    INTEGER("integer"),
    @XmlEnumValue("float")
    FLOAT("float"),
    @XmlEnumValue("double")
    DOUBLE("double"),
    @XmlEnumValue("long")
    LONG("long"),
    @XmlEnumValue("byte")
    BYTE("byte"),
    @XmlEnumValue("boolean")
    BOOLEAN("boolean"),
    @XmlEnumValue("string")
    STRING("string"),
    @XmlEnumValue("time")
    TIME("time");
    private final String value;

    Type(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Type fromValue(String v) {
        for (Type c: Type.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}