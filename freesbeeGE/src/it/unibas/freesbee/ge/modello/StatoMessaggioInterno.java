package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class StatoMessaggioInterno implements Serializable, IStatoMessaggio {


    private long id;
    private String stato;
    private Date dataAggiornamento;
    private String erroreProcessamento;
    private EventoPubblicatoInterno eventoPubblicato;
    private Sottoscrittore sottoscrittore;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Date getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(Date dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public String getErroreProcessamento() {
        return erroreProcessamento;
    }

    public void setErroreProcessamento(String erroreProcessamento) {
        this.erroreProcessamento = erroreProcessamento;
    }

    @ManyToOne(optional = false)
    public EventoPubblicatoInterno getEventoPubblicato() {
        return eventoPubblicato;
    }

    public void setEventoPubblicato(EventoPubblicatoInterno eventoPubblicato) {
        this.eventoPubblicato = eventoPubblicato;
    }

    @ManyToOne(optional = false)
    public Sottoscrittore getSottoscrittore() {
        return sottoscrittore;
    }

    public void setSottoscrittore(Sottoscrittore sottoscrittore) {
        this.sottoscrittore = sottoscrittore;
    }

    @Transient
    public IEventoPubblicato getIEventoPubblicato() {
       return eventoPubblicato;
    }
}
