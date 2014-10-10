package it.unibas.silvio.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class DatiSQL implements Serializable{
    
    private long id;
    private Query select = new Query();
    private List<DatiDB> listaDatiDB = new ArrayList<DatiDB>();
    
    @Id 
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @OneToOne(optional=true,cascade = CascadeType.ALL)
    public Query getSelect() {
        return select;
    }

    public void setSelect(Query select) {
        this.select = select;
    }    
    
    @Override
    public String toString(){
        String s = "Dati SQL: [" + this.getSelect().getQuery() +"]\n";
        for (DatiDB datoDB : listaDatiDB) {
            s += "\t " + datoDB +"\n";
        }
        return s;
    }


    @Transient
    public List<DatiDB> getListaDatiDB() {
        return listaDatiDB;
    }

    public void setListaDatiDB(List<DatiDB> listaDatiDB) {
        this.listaDatiDB = listaDatiDB;
    }
}
