package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configurazione implements Serializable {

    private long id;
    private String tipoGestoreEventi;
    private String nomeGestoreEventi;
    // numero secondi
    private int scadenzaMessaggi;
    private String nomeServizioNotifica;
    private String tipoServizioNotifica;
    private String azioneServizioNotifica;
    private String nomeServizioConsegna;
    private String tipoServizioConsegna;
    private String azioneServizioConsegna;
    private String nomeServizioPubblica;
    private String tipoServizioPubblica;
    private String pdNotifica;
    private String pdPubblica;
    private String pdConsegna;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoGestoreEventi() {
        return tipoGestoreEventi;
    }

    public void setTipoGestoreEventi(String tipoGestoreEventi) {
        this.tipoGestoreEventi = tipoGestoreEventi;
    }

    public String getNomeGestoreEventi() {
        return nomeGestoreEventi;
    }

    public void setNomeGestoreEventi(String nomeGestoreEventi) {
        this.nomeGestoreEventi = nomeGestoreEventi;
    }

    public String getNomeServizioNotifica() {
        return nomeServizioNotifica;
    }

    public void setNomeServizioNotifica(String nomeServizioNotifica) {
        this.nomeServizioNotifica = nomeServizioNotifica;
    }

    public String getTipoServizioNotifica() {
        return tipoServizioNotifica;
    }

    public void setTipoServizioNotifica(String tipoServizioNotifica) {
        this.tipoServizioNotifica = tipoServizioNotifica;
    }

    public String getNomeServizioConsegna() {
        return nomeServizioConsegna;
    }

    public void setNomeServizioConsegna(String nomeServizioConsegna) {
        this.nomeServizioConsegna = nomeServizioConsegna;
    }

    public String getTipoServizioConsegna() {
        return tipoServizioConsegna;
    }

    public void setTipoServizioConsegna(String tipoServizioConsegna) {
        this.tipoServizioConsegna = tipoServizioConsegna;
    }

    public String getNomeServizioPubblica() {
        return nomeServizioPubblica;
    }

    public void setNomeServizioPubblica(String nomeServizioPubblica) {
        this.nomeServizioPubblica = nomeServizioPubblica;
    }

    public String getTipoServizioPubblica() {
        return tipoServizioPubblica;
    }

    public void setTipoServizioPubblica(String tipoServizioPubblica) {
        this.tipoServizioPubblica = tipoServizioPubblica;
    }

    public String getPdNotifica() {
        return pdNotifica;
    }

    public void setPdNotifica(String pdNotifica) {
        this.pdNotifica = pdNotifica;
    }

    public String getPdPubblica() {
        return pdPubblica;
    }

    public void setPdPubblica(String pdPubblica) {
        this.pdPubblica = pdPubblica;
    }

    public String getPdConsegna() {
        return pdConsegna;
    }

    public void setPdConsegna(String pdConsegna) {
        this.pdConsegna = pdConsegna;
    }

    public int getScadenzaMessaggi() {
        return scadenzaMessaggi;
    }

    public String getAzioneServizioNotifica() {
        return azioneServizioNotifica;
    }

    public void setAzioneServizioNotifica(String azioneServizioNotifica) {
        this.azioneServizioNotifica = azioneServizioNotifica;
    }

    public String getAzioneServizioConsegna() {
        return azioneServizioConsegna;
    }

    public void setAzioneServizioConsegna(String azioneServizioConsegna) {
        this.azioneServizioConsegna = azioneServizioConsegna;
    }
    
    public void setScadenzaMessaggi(int scadenzaMessaggi) {
        // scadenzaMessaggi rappresenta la scadenza in secondi.
        if (scadenzaMessaggi <= 0) {
            throw new IllegalArgumentException("La scadenza dei meggaggi non puo' essere un numero negativo,errore nella configurazione.");
        }
        this.scadenzaMessaggi = scadenzaMessaggi;
    }
}
