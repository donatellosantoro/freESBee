
package it.unibas.freesbee.ge.web.ws.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sottoscrizioneInterna complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sottoscrizioneInterna">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filtroContenuto" type="{http://ge.freesbee.unibas.it/}filtroContenuto" minOccurs="0"/>
 *         &lt;element name="filtroData" type="{http://ge.freesbee.unibas.it/}filtroData" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="listaFiltroPublicatore" type="{http://ge.freesbee.unibas.it/}filtroPubblicatoreInterno" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sottoscrittore" type="{http://ge.freesbee.unibas.it/}sottoscrittore" minOccurs="0"/>
 *         &lt;element name="tipoSottoscrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sottoscrizioneInterna", propOrder = {
    "filtroContenuto",
    "filtroData",
    "id",
    "listaFiltroPublicatore",
    "sottoscrittore",
    "tipoSottoscrizione"
})
public class SottoscrizioneInterna {

    protected FiltroContenuto filtroContenuto;
    protected FiltroData filtroData;
    protected long id;
    @XmlElement(required = true, nillable = true)
    protected List<FiltroPubblicatoreInterno> listaFiltroPublicatore;
    protected Sottoscrittore sottoscrittore;
    protected String tipoSottoscrizione;

    /**
     * Gets the value of the filtroContenuto property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroContenuto }
     *     
     */
    public FiltroContenuto getFiltroContenuto() {
        return filtroContenuto;
    }

    /**
     * Sets the value of the filtroContenuto property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroContenuto }
     *     
     */
    public void setFiltroContenuto(FiltroContenuto value) {
        this.filtroContenuto = value;
    }

    /**
     * Gets the value of the filtroData property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroData }
     *     
     */
    public FiltroData getFiltroData() {
        return filtroData;
    }

    /**
     * Sets the value of the filtroData property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroData }
     *     
     */
    public void setFiltroData(FiltroData value) {
        this.filtroData = value;
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
     * Gets the value of the listaFiltroPublicatore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaFiltroPublicatore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaFiltroPublicatore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FiltroPubblicatoreInterno }
     * 
     * 
     */
    public List<FiltroPubblicatoreInterno> getListaFiltroPublicatore() {
        if (listaFiltroPublicatore == null) {
            listaFiltroPublicatore = new ArrayList<FiltroPubblicatoreInterno>();
        }
        return this.listaFiltroPublicatore;
    }

    /**
     * Gets the value of the sottoscrittore property.
     * 
     * @return
     *     possible object is
     *     {@link Sottoscrittore }
     *     
     */
    public Sottoscrittore getSottoscrittore() {
        return sottoscrittore;
    }

    /**
     * Sets the value of the sottoscrittore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sottoscrittore }
     *     
     */
    public void setSottoscrittore(Sottoscrittore value) {
        this.sottoscrittore = value;
    }

    /**
     * Gets the value of the tipoSottoscrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSottoscrizione() {
        return tipoSottoscrizione;
    }

    /**
     * Sets the value of the tipoSottoscrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSottoscrizione(String value) {
        this.tipoSottoscrizione = value;
    }

}
