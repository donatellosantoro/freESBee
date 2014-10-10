package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConfigurazioneSP implements Serializable {

    private long id;
    private String nomeUtenteSP;
    private String passwordSP;
    private String urlFreesbeeSP;
    private String autenticazione;
    private String risorsa;
    private String risorsaPdConsegna;
    private String risorsaPdPubblica;
    private String risorsaPdNotifica;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUtenteSP() {
        return nomeUtenteSP;
    }

    public void setNomeUtenteSP(String nomeUtenteSP) {
        this.nomeUtenteSP = nomeUtenteSP;
    }

    public String getPasswordSP() {
        return passwordSP;
    }

    public void setPasswordSP(String passwordSP) {
        this.passwordSP = passwordSP;
    }

    public String getUrlFreesbeeSP() {
        return urlFreesbeeSP;
    }

    public void setUrlFreesbeeSP(String urlFreesbeeSP) {
        this.urlFreesbeeSP = urlFreesbeeSP;
    }

    public String getAutenticazione() {
        return autenticazione;
    }

    public void setAutenticazione(String autenticazione) {
        this.autenticazione = autenticazione;
    }

    public String getRisorsa() {
        return risorsa;
    }

    public void setRisorsa(String risorsa) {
        this.risorsa = risorsa;
    }

    public String getRisorsaPdConsegna() {
        return risorsaPdConsegna;
    }

    public void setRisorsaPdConsegna(String risorsaPdConsegna) {
        this.risorsaPdConsegna = risorsaPdConsegna;
    }

    public String getRisorsaPdPubblica() {
        return risorsaPdPubblica;
    }

    public void setRisorsaPdPubblica(String risorsaPdPubblica) {
        this.risorsaPdPubblica = risorsaPdPubblica;
    }

    public String getRisorsaPdNotifica() {
        return risorsaPdNotifica;
    }

    public void setRisorsaPdNotifica(String risorsaPdNotifica) {
        this.risorsaPdNotifica = risorsaPdNotifica;
    }
}
