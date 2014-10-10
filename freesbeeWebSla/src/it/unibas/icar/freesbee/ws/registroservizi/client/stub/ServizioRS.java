
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per servizioRS complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="servizioRS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="soggettiFruitori" type="{http://icar.unibas.it/freesbee/}soggettoRS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="soggettoErogatore" type="{http://icar.unibas.it/freesbee/}soggettoRS"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servizioRS", propOrder = {
    "nome",
    "soggettiFruitori",
    "soggettoErogatore",
    "tipo"
})
public class ServizioRS {

    @XmlElement(required = true)
    protected String nome;
    @XmlElement(nillable = true)
    protected List<SoggettoRS> soggettiFruitori;
    @XmlElement(required = true)
    protected SoggettoRS soggettoErogatore;
    @XmlElement(required = true)
    protected String tipo;

    /**
     * Recupera il valore della proprietˆ nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietˆ nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the soggettiFruitori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the soggettiFruitori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoggettiFruitori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoggettoRS }
     * 
     * 
     */
    public List<SoggettoRS> getSoggettiFruitori() {
        if (soggettiFruitori == null) {
            soggettiFruitori = new ArrayList<SoggettoRS>();
        }
        return this.soggettiFruitori;
    }

    /**
     * Recupera il valore della proprietˆ soggettoErogatore.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRS }
     *     
     */
    public SoggettoRS getSoggettoErogatore() {
        return soggettoErogatore;
    }

    /**
     * Imposta il valore della proprietˆ soggettoErogatore.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRS }
     *     
     */
    public void setSoggettoErogatore(SoggettoRS value) {
        this.soggettoErogatore = value;
    }

    /**
     * Recupera il valore della proprietˆ tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietˆ tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}
