
package it.unibas.icar.freesbee.ws.registroservizi.client.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per azioneRSRisposta complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="azioneRSRisposta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profiloCollaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profiloEGov" type="{http://icar.unibas.it/freesbee/}profiloEGov" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "azioneRSRisposta", propOrder = {
    "nome",
    "profiloCollaborazione",
    "profiloEGov"
})
public class AzioneRSRisposta {

    protected String nome;
    protected String profiloCollaborazione;
    protected ProfiloEGov profiloEGov;

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
     * Recupera il valore della proprietˆ profiloCollaborazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    /**
     * Imposta il valore della proprietˆ profiloCollaborazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfiloCollaborazione(String value) {
        this.profiloCollaborazione = value;
    }

    /**
     * Recupera il valore della proprietˆ profiloEGov.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloEGov }
     *     
     */
    public ProfiloEGov getProfiloEGov() {
        return profiloEGov;
    }

    /**
     * Imposta il valore della proprietˆ profiloEGov.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloEGov }
     *     
     */
    public void setProfiloEGov(ProfiloEGov value) {
        this.profiloEGov = value;
    }

}
