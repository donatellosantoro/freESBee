package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"tipo", "nome"})})
public class GestoreEventi implements Serializable, Comparable {

    private long id;
    private String tipo;
    private String nome;
    private String descrizione;

    public GestoreEventi() {
    }

    public GestoreEventi(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return tipo + " " + nome;
    }

    @Override
    public int compareTo(Object o) {
        GestoreEventi soggetto = (GestoreEventi) o;
        if (soggetto.getNome().equals(nome) && soggetto.getTipo().equals(tipo)) {
            return 0;
        }
        return -1;
    }
}
