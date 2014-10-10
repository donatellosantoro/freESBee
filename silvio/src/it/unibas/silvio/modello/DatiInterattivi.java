package it.unibas.silvio.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
public class DatiInterattivi implements Serializable{

    private Log logger = LogFactory.getLog(this.getClass());
    
    private long id;
    private List<DatoPrimitivo> datiInterattivi = new ArrayList<DatoPrimitivo>();
    
    @Id 
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @OneToMany(mappedBy = "datiInterattivi", cascade = CascadeType.ALL/*,fetch=FetchType.EAGER*/)
    public List<DatoPrimitivo> getDatiInterattivi() {
        return datiInterattivi;
    }

    public void setDatiInterattivi(List<DatoPrimitivo> datiInterattivi) {
        this.datiInterattivi = datiInterattivi;
    }
    
    @Transient
    public boolean esisteDati(String nome){
        for (DatoPrimitivo datoPrimitivo : datiInterattivi) {
            if(datoPrimitivo.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        String s = "Dati Interattivi:\n";
        for (DatoPrimitivo datoPrimitivo : datiInterattivi) {
            s += "\t " + datoPrimitivo + "\n";
        }
        return s;
    }
}
