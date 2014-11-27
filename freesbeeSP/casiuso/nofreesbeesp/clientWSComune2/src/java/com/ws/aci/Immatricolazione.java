
package com.ws.aci;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per immatricolazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="immatricolazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alimentazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cavalli" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="classe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cognome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comuneNascita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comuneResidenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataImmatricolazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataNascita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="kw" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="modello" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="posti" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="targa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telaio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="via" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "immatricolazione", propOrder = {
    "alimentazione",
    "cavalli",
    "classe",
    "codiceFiscale",
    "cognome",
    "comuneNascita",
    "comuneResidenza",
    "dataImmatricolazione",
    "dataNascita",
    "id",
    "kw",
    "modello",
    "nome",
    "posti",
    "targa",
    "telaio",
    "uso",
    "via"
})
public class Immatricolazione {

    protected String alimentazione;
    protected int cavalli;
    protected String classe;
    protected String codiceFiscale;
    protected String cognome;
    protected String comuneNascita;
    protected String comuneResidenza;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataImmatricolazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataNascita;
    protected Long id;
    protected double kw;
    protected String modello;
    protected String nome;
    protected int posti;
    protected String targa;
    protected String telaio;
    protected String uso;
    protected String via;

    /**
     * Recupera il valore della proprietà alimentazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlimentazione() {
        return alimentazione;
    }

    /**
     * Imposta il valore della proprietà alimentazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlimentazione(String value) {
        this.alimentazione = value;
    }

    /**
     * Recupera il valore della proprietà cavalli.
     * 
     */
    public int getCavalli() {
        return cavalli;
    }

    /**
     * Imposta il valore della proprietà cavalli.
     * 
     */
    public void setCavalli(int value) {
        this.cavalli = value;
    }

    /**
     * Recupera il valore della proprietà classe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClasse() {
        return classe;
    }

    /**
     * Imposta il valore della proprietà classe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClasse(String value) {
        this.classe = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprietà codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprietà cognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il valore della proprietà cognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Recupera il valore della proprietà comuneNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComuneNascita() {
        return comuneNascita;
    }

    /**
     * Imposta il valore della proprietà comuneNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComuneNascita(String value) {
        this.comuneNascita = value;
    }

    /**
     * Recupera il valore della proprietà comuneResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComuneResidenza() {
        return comuneResidenza;
    }

    /**
     * Imposta il valore della proprietà comuneResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComuneResidenza(String value) {
        this.comuneResidenza = value;
    }

    /**
     * Recupera il valore della proprietà dataImmatricolazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataImmatricolazione() {
        return dataImmatricolazione;
    }

    /**
     * Imposta il valore della proprietà dataImmatricolazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataImmatricolazione(XMLGregorianCalendar value) {
        this.dataImmatricolazione = value;
    }

    /**
     * Recupera il valore della proprietà dataNascita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta il valore della proprietà dataNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascita(XMLGregorianCalendar value) {
        this.dataNascita = value;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà kw.
     * 
     */
    public double getKw() {
        return kw;
    }

    /**
     * Imposta il valore della proprietà kw.
     * 
     */
    public void setKw(double value) {
        this.kw = value;
    }

    /**
     * Recupera il valore della proprietà modello.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModello() {
        return modello;
    }

    /**
     * Imposta il valore della proprietà modello.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModello(String value) {
        this.modello = value;
    }

    /**
     * Recupera il valore della proprietà nome.
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
     * Imposta il valore della proprietà nome.
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
     * Recupera il valore della proprietà posti.
     * 
     */
    public int getPosti() {
        return posti;
    }

    /**
     * Imposta il valore della proprietà posti.
     * 
     */
    public void setPosti(int value) {
        this.posti = value;
    }

    /**
     * Recupera il valore della proprietà targa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarga() {
        return targa;
    }

    /**
     * Imposta il valore della proprietà targa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarga(String value) {
        this.targa = value;
    }

    /**
     * Recupera il valore della proprietà telaio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelaio() {
        return telaio;
    }

    /**
     * Imposta il valore della proprietà telaio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelaio(String value) {
        this.telaio = value;
    }

    /**
     * Recupera il valore della proprietà uso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUso() {
        return uso;
    }

    /**
     * Imposta il valore della proprietà uso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUso(String value) {
        this.uso = value;
    }

    /**
     * Recupera il valore della proprietà via.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVia() {
        return via;
    }

    /**
     * Imposta il valore della proprietà via.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVia(String value) {
        this.via = value;
    }

}
