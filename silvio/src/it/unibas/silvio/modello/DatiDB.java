package it.unibas.silvio.modello;

import java.util.ArrayList;
import java.util.List;

public class DatiDB {
    
    private List<DatoPrimitivo> datiDB = new ArrayList<DatoPrimitivo>();

    public List<DatoPrimitivo> getDatiDB() {
        return datiDB;
    }

    public void setDatiDB(List<DatoPrimitivo> datiDB) {
        this.datiDB = datiDB;
    }
    
    @Override
    public String toString(){
        String s = "Dati SQL:\n";
        for (DatoPrimitivo datoPrimitivo : datiDB) {
            s += "\t " + datoPrimitivo +"\n";
        }
        return s;
    }
}
