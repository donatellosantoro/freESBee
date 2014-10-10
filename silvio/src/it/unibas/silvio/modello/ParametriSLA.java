package it.unibas.silvio.modello;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class ParametriSLA implements Serializable{

    private long id;
    private String indirizzoWS;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Type(type="text")
    public String getIndirizzoWS() {
        return indirizzoWS;
    }

    public void setIndirizzoWS(String indirizzoWS) {
        this.indirizzoWS = indirizzoWS;
    }
}
