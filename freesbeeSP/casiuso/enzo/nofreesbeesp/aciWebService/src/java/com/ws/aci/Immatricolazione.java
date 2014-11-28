package com.ws.aci;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.*;

@Entity
public class Immatricolazione {

    private Long id;
    private String targa;
    private String telaio;
    private Calendar dataImmatricolazione;
    private String modello;
    private int cavalli;
    private double kw;
    private String classe;
    private String uso;
    private String alimentazione;
    private int posti;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Calendar dataNascita;
    private String comuneNascita;
    private String comuneResidenza;
    private String via;

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlimentazione() {
        return alimentazione;
    }

    public void setAlimentazione(String alimentazione) {
        this.alimentazione = alimentazione;
    }

    public int getCavalli() {
        return cavalli;
    }

    public void setCavalli(int cavalli) {
        this.cavalli = cavalli;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getComuneNascita() {
        return comuneNascita;
    }

    public void setComuneNascita(String comuneNascita) {
        this.comuneNascita = comuneNascita;
    }

     @Temporal(value= TemporalType.DATE)
    public Calendar getDataImmatricolazione() {
        return dataImmatricolazione;
    }

    public void setDataImmatricolazione(Calendar dataImmatricolazione) {
        this.dataImmatricolazione = dataImmatricolazione;
    }
    
    @Temporal(value= TemporalType.DATE)
    public Calendar getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Calendar dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }

  

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getTelaio() {
        return telaio;
    }

    public void setTelaio(String telaio) {
        this.telaio = telaio;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getComuneResidenza() {
        return comuneResidenza;
    }

    public void setComuneResidenza(String comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public double getKw() {
        return kw;
    }

    public void setKw(double kw) {
        this.kw = kw;
    }
    
    
}
