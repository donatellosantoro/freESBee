
package it.unibas.freesbee.ge.web.ws.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for categoriaEventiEsterna complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="categoriaEventiEsterna">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attiva" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="inAttesa" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="listaPubblicatori" type="{http://ge.freesbee.unibas.it/}pubblicatoreEsterno" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="listaSottoscrizioni" type="{http://ge.freesbee.unibas.it/}sottoscrizioneEsterna" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoriaEventiEsterna", propOrder = {
    "attiva",
    "id",
    "inAttesa",
    "listaPubblicatori",
    "listaSottoscrizioni",
    "nome"
})
public class CategoriaEventiEsterna {

    protected boolean attiva;
    protected long id;
    protected boolean inAttesa;
    @XmlElement(required = true, nillable = true)
    protected List<PubblicatoreEsterno> listaPubblicatori;
    @XmlElement(required = true, nillable = true)
    protected List<SottoscrizioneEsterna> listaSottoscrizioni;
    protected String nome;

    /**
     * Gets the value of the attiva property.
     * 
     */
    public boolean isAttiva() {
        return attiva;
    }

    /**
     * Sets the value of the attiva property.
     * 
     */
    public void setAttiva(boolean value) {
        this.attiva = value;
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
     * Gets the value of the inAttesa property.
     * 
     */
    public boolean isInAttesa() {
        return inAttesa;
    }

    /**
     * Sets the value of the inAttesa property.
     * 
     */
    public void setInAttesa(boolean value) {
        this.inAttesa = value;
    }

    /**
     * Gets the value of the listaPubblicatori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaPubblicatori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaPubblicatori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PubblicatoreEsterno }
     * 
     * 
     */
    public List<PubblicatoreEsterno> getListaPubblicatori() {
        if (listaPubblicatori == null) {
            listaPubblicatori = new ArrayList<PubblicatoreEsterno>();
        }
        return this.listaPubblicatori;
    }

    /**
     * Gets the value of the listaSottoscrizioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaSottoscrizioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaSottoscrizioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SottoscrizioneEsterna }
     * 
     * 
     */
    public List<SottoscrizioneEsterna> getListaSottoscrizioni() {
        if (listaSottoscrizioni == null) {
            listaSottoscrizioni = new ArrayList<SottoscrizioneEsterna>();
        }
        return this.listaSottoscrizioni;
    }

    /**
     * Gets the value of the nome property.
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
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

}
