package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
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

    @Id 
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Column(nullable=false)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(nullable=false)
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

    @ManyToMany(mappedBy="soggettoFruitore", cascade=CascadeType.ALL)
    @XmlTransient
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
    
    @Transient
    public String getNomeBreve(){
        return this.tipo + "\\" + this.nome;
    }
    
    @Transient
    public String getNomeLista(){
        return this.tipo + "\\" + this.nome + " [" + this.id + "]";
    }
    
    @XmlTransient()
    @OneToMany(mappedBy="erogatore", cascade=CascadeType.ALL)
    public List<Servizio> getListaServiziErogatore() {
        return listaServiziErogatore;
    }
    
    public void setListaServiziErogatore(List<Servizio> listaServiziErogatore) {
        this.listaServiziErogatore = listaServiziErogatore;
    }

    @XmlTransient()
    @ManyToMany(mappedBy="fruitori")
    public List<Servizio> getListaServiziFruitore() {
        return listaServiziFruitore;
    }

    public void setListaServiziFruitore(List<Servizio> listaServiziFruitore) {
        this.listaServiziFruitore = listaServiziFruitore;
    }

    @Transient
    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    public void setOldNomeForUpdate(String oldNomeForUpdate) {
        this.oldNomeForUpdate = oldNomeForUpdate;
    }

    @Transient
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

    @Override
    public String toString() {
        return "Servizio " + tipo + " @ " + nome;
    }
    
    
    
    
}
