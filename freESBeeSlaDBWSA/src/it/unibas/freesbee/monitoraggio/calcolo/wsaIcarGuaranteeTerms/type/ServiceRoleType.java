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
 * <p>Java class for ServiceRoleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ServiceRoleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ServiceConsumer"/>
 *     &lt;enumeration value="ServiceProvider"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ServiceRoleType {

    @XmlEnumValue("ServiceConsumer")
    SERVICE_CONSUMER("ServiceConsumer"),
    @XmlEnumValue("ServiceProvider")
    SERVICE_PROVIDER("ServiceProvider");
    private final String value;

    ServiceRoleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceRoleType fromValue(String v) {
        for (ServiceRoleType c: ServiceRoleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}