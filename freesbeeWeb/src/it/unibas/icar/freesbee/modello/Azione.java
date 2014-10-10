package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlTransient;

public class Azione implements Serializable,Comparable{
    
    private long id;
    private String nome;
    private String descrizione;
    private String profiloCollaborazione;
    private long idProfiloEGov;    
    private ProfiloEGov profiloEGov;
    private long idAccordoServizio;
    private AccordoServizio accordoServizio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @XmlTransient
    public ProfiloEGov getProfiloEGov() {
        return profiloEGov;
    }

    public void setProfiloEGov(ProfiloEGov profiloEGov) {
        this.profiloEGov = profiloEGov;
    }
    
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
    
    public long getIdAccordoServizio() {
        return idAccordoServizio;
    }

    public void setIdAccordoServizio(long idAccordoServizio) {
        this.idAccordoServizio = idAccordoServizio;
    }
    
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

    @XmlTransient
    public String getProfiloCollaborazioneBreve() {
        return AccordoServizio.convertiNomeProfilo(this.profiloCollaborazione);
    }
    
}
