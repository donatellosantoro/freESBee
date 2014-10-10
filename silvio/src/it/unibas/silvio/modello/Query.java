package it.unibas.silvio.modello;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class Query implements Serializable{
    
    private long id;    
    private String query;
    private String nomeUtente;
    private String password;
    private String tipoDB;
    private String nomeDB;
    private String indirizzoDB;
    
    
    @Id 
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Column(nullable=true)
    @Type(type="text")
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }        
    
    @Column(nullable=false)
    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
    
    @Column(nullable=false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(nullable=false)
    public String getIndirizzoDB() {
        return indirizzoDB;
    }

    public void setIndirizzoDB(String indirizzoDB) {
        this.indirizzoDB = indirizzoDB;
    }
    
    @Column(nullable=false)
    public String getNomeDB() {
        return nomeDB;
    }

    public void setNomeDB(String nomeDB) {
        this.nomeDB = nomeDB;
    }
    
    @Column(nullable=false)
    public String getTipoDB() {
        return tipoDB;
    }

    public void setTipoDB(String tipoDB) {
        this.tipoDB = tipoDB;
    }
    
    
    
    

}
