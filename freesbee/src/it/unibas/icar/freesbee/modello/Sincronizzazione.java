package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sincronizzazione implements Serializable {

    private long id;
    private String nome;
    private String indirizzo;
    private boolean freesbee;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isFreesbee() {
        return freesbee;
    }

    public void setFreesbee(boolean freesbee) {
        this.freesbee = freesbee;
    }
    
    
}
