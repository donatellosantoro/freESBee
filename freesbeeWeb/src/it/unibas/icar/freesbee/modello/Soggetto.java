package it.unibas.icar.freesbee.modello;

import it.unibas.icar.freesbee.Costanti;
import java.io.Serializable;
import java.util.List;

public class Soggetto implements Serializable, Comparable{
    
    private long id;
    private String tipo = Costanti.SPC;
    private String nome;
    private String descrizione;
    private String portaDominio;
    
    private List<PortaDelegata> listaPorteDelegateFruitore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPortaDominio() {
        return portaDominio;
    }

    public void setPortaDominio(String portaDominio) {
        this.portaDominio = portaDominio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<PortaDelegata> getListaPorteDelegateFruitore() {
        return listaPorteDelegateFruitore;
    }

    public void setListaPorteDelegateFruitore(List<PortaDelegata> listaPorteDelegateFruitore) {
        this.listaPorteDelegateFruitore = listaPorteDelegateFruitore;
    }

    public int compareTo(Object o) {
        Soggetto other = (Soggetto)o;
        if(this.getTipo().equalsIgnoreCase(other.getTipo())){
            return this.getNome().compareToIgnoreCase(other.getNome());
        }else{
            return this.getTipo().compareToIgnoreCase(other.getTipo());
        }        
    }
    
    public String getNomeBreve(){
        return this.tipo + "\\" + this.nome;
    }
    
    public String getNomeLista(){
        return this.tipo + "\\" + this.nome + " [" + this.id + "]";
    }
}
