package it.unibas.silvio.modello;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DatoPrimitivo implements Serializable{

    private long id;
    private String nome;
    private String tipo;
    private String valore;
    private DatiCostanti datiCostanti;
    private DatiInterattivi datiInterattivi;

    public DatoPrimitivo() {
    }

    public DatoPrimitivo(String nome) {
        this.nome = nome;
    }

    public DatoPrimitivo(String nome, String valore) {
        this.nome = nome;
        this.valore = valore;
    }
    
    @Id 
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }
    
    @ManyToOne(optional=true)
    public DatiCostanti getDatiCostanti() {
        return datiCostanti;
    }

    public void setDatiCostanti(DatiCostanti datiCostanti) {
        this.datiCostanti = datiCostanti;
    }
    
    @ManyToOne(optional=true)
    public DatiInterattivi getDatiInterattivi() {
        return datiInterattivi;
    }

    public void setDatiInterattivi(DatiInterattivi datiInterattivi) {
        this.datiInterattivi = datiInterattivi;
    }
    
    @Override
    public String toString(){
        return this.nome + ": " + this.valore;
    }
            
}
