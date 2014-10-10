
package it.unibas.freesbeesla.monitoraggio.stub;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * <p>Java class for GuaranteeStateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GuaranteeStateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Not_Determined"/>
 *     &lt;enumeration value="FulFilled"/>
 *     &lt;enumeration value="Violated"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum GuaranteeStateType {

    @XmlEnumValue("FulFilled")
    FUL_FILLED("FulFilled"),
    @XmlEnumValue("Not_Determined")
    NOT_DETERMINED("Not_Determined"),
    @XmlEnumValue("Violated")
    VIOLATED("Violated");
    private final String value;

    GuaranteeStateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GuaranteeStateType fromValue(String v) {
        for (GuaranteeStateType c: GuaranteeStateType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
