package it.unibas.silvio.modello;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;

@Entity
public class Dati implements Serializable{
    
    private long id;
    private DatiSQL datiSQL;
    private DatiCostanti datiCostanti;
    private DatiInterattivi datiInterattivi;
    private String payloadCostante;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
                   
    @OneToOne(cascade=CascadeType.ALL, optional = true)
    public DatiCostanti getDatiCostanti() {
        return datiCostanti;
    }

    public void setDatiCostanti(DatiCostanti datiCostanti) {
        this.datiCostanti = datiCostanti;
    }
        
    @OneToOne(cascade=CascadeType.ALL, optional = true)
    public DatiInterattivi getDatiInterattivi() {
        return datiInterattivi;
    }

    public void setDatiInterattivi(DatiInterattivi datiInterattivi) {
        this.datiInterattivi = datiInterattivi;
    }
        
    @OneToOne(cascade=CascadeType.ALL, optional = true)
    public DatiSQL getDatiSQL() {
        return datiSQL;
    }

    public void setDatiSQL(DatiSQL datiSQL) {
        this.datiSQL = datiSQL;
    }
    
    @Type(type="text")
    public String getPayloadCostante() {
        return payloadCostante;
    }

    public void setPayloadCostante(String payloadCostante) {
        this.payloadCostante = payloadCostante;
    }
    
    @Transient
    public boolean isPayloadCostante(){
        return this.getPayloadCostante()!=null;
    }
    
    public String toString(){
        String s = "Dati:\n";
        s += "\t" + this.datiSQL;        
        s += "\t" + this.datiCostanti;        
        s += "\t" + this.datiInterattivi;      
        s += "\t" + this.payloadCostante;      
        return s;
    }
}
