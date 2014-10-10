package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;


public class Soggetto implements Serializable, Comparable{
    
    private long id;
    private String tipo;
    private String nome;
    private String descrizione;
    private String portaDominio;
    private List<Servizio> listaServiziErogatore;
    private List<Servizio> listaServiziFruitore;
    private List<PortaDelegata> listaPorteDelegateFruitore;
    private Date oraRegistrazione;
    private String oldNomeForUpdate;
    private String oldTipoForUpdate;


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
    
    public List<Servizio> getListaServiziErogatore() {
        return listaServiziErogatore;
    }
    
    public void setListaServiziErogatore(List<Servizio> listaServiziErogatore) {
        this.listaServiziErogatore = listaServiziErogatore;
    }

    public List<Servizio> getListaServiziFruitore() {
        return listaServiziFruitore;
    }

    public void setListaServiziFruitore(List<Servizio> listaServiziFruitore) {
        this.listaServiziFruitore = listaServiziFruitore;
    }

    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    public void setOldNomeForUpdate(String oldNomeForUpdate) {
        this.oldNomeForUpdate = oldNomeForUpdate;
    }

    public String getOldTipoForUpdate() {
        return oldTipoForUpdate;
    }

    public void setOldTipoForUpdate(String oldTipoForUpdate) {
        this.oldTipoForUpdate = oldTipoForUpdate;
    }

    public Date getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(Date oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }
    
    
    
    
}
