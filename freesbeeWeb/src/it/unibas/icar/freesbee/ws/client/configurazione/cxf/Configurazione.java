package it.unibas.icar.freesbee.ws.client.configurazione.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for configurazione complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="configurazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connettoreRegistroServizi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idSoggettoErogatoreNICA" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idSoggettoErogatoreRegistroServizi" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="indirizzoPortaApplicativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indirizzoPortaDelegata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inviaANICA" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mutuaAutenticazionePortaApplicativa" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mutuaAutenticazionePortaDelegata" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NICA" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="registroFreesbee" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tempo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configurazione", propOrder = {
    "connettoreRegistroServizi",
    "id",
    "idSoggettoErogatoreNICA",
    "idSoggettoErogatoreRegistroServizi",
    "indirizzoPortaApplicativa",
    "indirizzoPortaDelegata",
    "inviaANICA",
    "mutuaAutenticazionePortaApplicativa",
    "mutuaAutenticazionePortaDelegata",
    "nica",
    "registroFreesbee",
    "tempo"
})
public class Configurazione {

    protected String connettoreRegistroServizi;
    protected long id;
    protected long idSoggettoErogatoreNICA;
    protected long idSoggettoErogatoreRegistroServizi;
    protected String indirizzoPortaApplicativa;
    protected String indirizzoPortaDelegata;
    protected boolean inviaANICA;
    protected boolean mutuaAutenticazionePortaApplicativa;
    protected boolean mutuaAutenticazionePortaDelegata;
    @XmlElement(name = "NICA")
    protected boolean nica;
    protected boolean registroFreesbee;
    protected String tempo;

    /**
     * Gets the value of the connettoreRegistroServizi property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getConnettoreRegistroServizi() {
        return connettoreRegistroServizi;
    }

    /**
     * Sets the value of the connettoreRegistroServizi property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setConnettoreRegistroServizi(String value) {
        this.connettoreRegistroServizi = value;
    }

    /**
     * Gets the value of the id property.
     *
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the idSoggettoErogatoreNICA property.
     *
     */
    public long getIdSoggettoErogatoreNICA() {
        return idSoggettoErogatoreNICA;
    }

    /**
     * Sets the value of the idSoggettoErogatoreNICA property.
     *
     */
    public void setIdSoggettoErogatoreNICA(long value) {
        this.idSoggettoErogatoreNICA = value;
    }

    /**
     * Gets the value of the idSoggettoErogatoreRegistroServizi property.
     *
     */
    public long getIdSoggettoErogatoreRegistroServizi() {
        return idSoggettoErogatoreRegistroServizi;
    }

    /**
     * Sets the value of the idSoggettoErogatoreRegistroServizi property.
     *
     */
    public void setIdSoggettoErogatoreRegistroServizi(long value) {
        this.idSoggettoErogatoreRegistroServizi = value;
    }

    /**
     * Gets the value of the indirizzoPortaApplicativa property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIndirizzoPortaApplicativa() {
        return indirizzoPortaApplicativa;
    }

    /**
     * Sets the value of the indirizzoPortaApplicativa property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIndirizzoPortaApplicativa(String value) {
        this.indirizzoPortaApplicativa = value;
    }

    /**
     * Gets the value of the indirizzoPortaDelegata property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIndirizzoPortaDelegata() {
        return indirizzoPortaDelegata;
    }

    /**
     * Sets the value of the indirizzoPortaDelegata property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIndirizzoPortaDelegata(String value) {
        this.indirizzoPortaDelegata = value;
    }

    /**
     * Gets the value of the inviaANICA property.
     *
     */
    public boolean isInviaANICA() {
        return inviaANICA;
    }

    /**
     * Sets the value of the inviaANICA property.
     *
     */
    public void setInviaANICA(boolean value) {
        this.inviaANICA = value;
    }

    /**
     * Gets the value of the nica property.
     *
     */
    public boolean isNICA() {
        return nica;
    }

    /**
     * Sets the value of the nica property.
     *
     */
    public void setNICA(boolean value) {
        this.nica = value;
    }

    /**
     * Gets the value of the registroFreesbee property.
     *
     */
    public boolean isRegistroFreesbee() {
        return registroFreesbee;
    }

    /**
     * Sets the value of the registroFreesbee property.
     *
     */
    public void setRegistroFreesbee(boolean value) {
        this.registroFreesbee = value;
    }

    /**
     * Gets the value of the tempo property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTempo() {
        return tempo;
    }

    /**
     * Sets the value of the tempo property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTempo(String value) {
        this.tempo = value;
    }

    public boolean isMutuaAutenticazionePortaApplicativa() {
        return mutuaAutenticazionePortaApplicativa;
    }

    public void setMutuaAutenticazionePortaApplicativa(boolean mutuaAutenticazionePortaApplicativa) {
        this.mutuaAutenticazionePortaApplicativa = mutuaAutenticazionePortaApplicativa;
    }

    public boolean isMutuaAutenticazionePortaDelegata() {
        return mutuaAutenticazionePortaDelegata;
    }

    public void setMutuaAutenticazionePortaDelegata(boolean mutuaAutenticazionePortaDelegata) {
        this.mutuaAutenticazionePortaDelegata = mutuaAutenticazionePortaDelegata;
    }

}
