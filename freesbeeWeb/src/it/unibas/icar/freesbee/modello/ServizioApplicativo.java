package it.unibas.icar.freesbee.modello;

import java.io.Serializable;

public class ServizioApplicativo implements Serializable, Comparable {

    private long Id;
    private String nome;
    private String descrizione;
    private String connettore;
    private boolean mutuaAutenticazione;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConnettore() {
        return connettore;
    }

    public void setConnettore(String connettore) {
        this.connettore = connettore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isMutuaAutenticazione() {
        return mutuaAutenticazione;
    }

    public void setMutuaAutenticazione(boolean mutuaAutenticazione) {
        this.mutuaAutenticazione = mutuaAutenticazione;
    }

    public int compareTo(Object o) {
        return this.getNome().compareToIgnoreCase(((ServizioApplicativo) o).getNome());
    }

}
