package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Azione implements Serializable,Comparable{
    
    private long id;
    private String nome;
    private String profiloCollaborazione;
    private long idProfiloEGov;    
    private ProfiloEGov profiloEGov;
    private long idAccordoServizio;
    private AccordoServizio accordoServizio;

    @Id 
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable=false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @XmlTransient
    public ProfiloEGov getProfiloEGov() {
        return profiloEGov;
    }

    public void setProfiloEGov(ProfiloEGov profiloEGov) {
        this.profiloEGov = profiloEGov;
    }
    @ManyToOne(optional=false)
    @XmlTransient
    public AccordoServizio getAccordoServizio() {
        return accordoServizio;
    }

    public void setAccordoServizio(AccordoServizio accordoServizio) {
        this.accordoServizio = accordoServizio;
    }

    public int compareTo(Object o) {
        return this.getNome().compareToIgnoreCase(((Azione)o).getNome());
    }
    
    @Transient
    public long getIdAccordoServizio() {
        return idAccordoServizio;
    }

    public void setIdAccordoServizio(long idAccordoServizio) {
        this.idAccordoServizio = idAccordoServizio;
    }
    
    @Transient
    public long getIdProfiloEGov() {
        return idProfiloEGov;
    }

    public void setIdProfiloEGov(long idProfiloEGov) {
        this.idProfiloEGov = idProfiloEGov;
    }

    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    public void setProfiloCollaborazione(String profiloCollaborazione) {
        this.profiloCollaborazione = profiloCollaborazione;
    }
}
