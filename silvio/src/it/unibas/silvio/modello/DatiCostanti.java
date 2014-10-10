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

@Entity
public class DatiCostanti implements Serializable {

    private long id;
    private List<DatoPrimitivo> datiCostanti = new ArrayList<DatoPrimitivo>();

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "datiCostanti", cascade = CascadeType.ALL/*,fetch=FetchType.EAGER*/)
    public List<DatoPrimitivo> getDatiCostanti() {
        return datiCostanti;
    }

    public void setDatiCostanti(List<DatoPrimitivo> datiCostanti) {
        this.datiCostanti = datiCostanti;
    }

    @Override
    public String toString() {
        String s = "Dati Costanti:\n";
        for (DatoPrimitivo datoPrimitivo : datiCostanti) {
            s += "\t " + datoPrimitivo + "\n";
        }
        return s;
    }
}
