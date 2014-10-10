package it.unibas.silvio.modello;

import it.unibas.silvio.util.CostantiSilvio;
import java.util.ArrayList;
import java.util.List;

public class ParametriEseguiIstanza {

    private String nomeOperazione;
    private String indirizzo;
    private String risorsa;
    private List<DatoPrimitivo> datiDB = new ArrayList<DatoPrimitivo>();
    private List<DatoPrimitivo> datiInterattivi = new ArrayList<DatoPrimitivo>();
    private boolean test;
    private String correlazione = CostantiSilvio.CORRELAZIONE_WSA;

    public List<DatoPrimitivo> getDatiDB() {
        return datiDB;
    }

    public void setDatiDB(List<DatoPrimitivo> datiDB) {
        this.datiDB = datiDB;
    }

    public List<DatoPrimitivo> getDatiInterattivi() {
        return datiInterattivi;
    }

    public void setDatiInterattivi(List<DatoPrimitivo> datiInterattivi) {
        this.datiInterattivi = datiInterattivi;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNomeOperazione() {
        return nomeOperazione;
    }

    public void setNomeOperazione(String nomeOperazione) {
        this.nomeOperazione = nomeOperazione;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getCorrelazione() {
        return correlazione;
    }

    public void setCorrelazione(String correlazione) {
        this.correlazione = correlazione;
    }

    public String getRisorsa() {
        return risorsa;
    }

    public void setRisorsa(String risorsa) {
        this.risorsa = risorsa;
    }

    
    
    public String toString(){
        String s = "Parametri di configurazione per eseguire l'operation " + nomeOperazione + "\n";
        s += "\tIstanza di test: " + this.test + "\n";
        s += "\tIndirizzo: " + this.indirizzo + "\n";
        for (DatoPrimitivo datoPrimitivo : datiDB) {
            s += "\tDatoDB: " + datoPrimitivo + "\n";
        }
        for (DatoPrimitivo datoPrimitivo : datiInterattivi) {
            s += "\tDatiInterattivi: " + datoPrimitivo + "\n";
        }
        return s;
    }
}
