package it.unibas.freesbeesla.tracciatura.ws.server.modello;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * <p>Java class for ServiceTermStateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ServiceTermStateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Not_Ready"/>
 *     &lt;enumeration value="Ready_Idle"/>
 *     &lt;enumeration value="Ready_Processing"/>
 *     &lt;enumeration value="Completed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ServiceTermStateType {

    @XmlEnumValue("Completed")
    COMPLETED("Completed"),
    
    @XmlEnumValue("Not_Ready")
    NOT_READY("Not_Ready"),
    
    @XmlEnumValue("Ready_Idle")
    READY_IDLE("Ready_Idle"),
    
    @XmlEnumValue("Ready_Processing")
    READY_PROCESSING("Ready_Processing");
    private final String value;

    ServiceTermStateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceTermStateType fromValue(String v) {
        for (ServiceTermStateType c : ServiceTermStateType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
}
