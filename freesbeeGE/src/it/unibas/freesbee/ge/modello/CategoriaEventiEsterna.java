package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"})})
public class CategoriaEventiEsterna implements Serializable, ICategoriaEventi {

    private long id;
    private String nome;
    private boolean attiva;
    private boolean inAttesa;
    private List<SottoscrizioneEsterna> listaSottoscrizioni = new ArrayList<SottoscrizioneEsterna>();
    private List<PubblicatoreEsterno> listaPubblicatori = new ArrayList<PubblicatoreEsterno>();

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @OneToMany(mappedBy = "categoriaEventi")
    public List<SottoscrizioneEsterna> getListaSottoscrizioni() {
        return listaSottoscrizioni;
    }

    public void setListaSottoscrizioni(List<SottoscrizioneEsterna> listaSottoscrizioni) {
        this.listaSottoscrizioni = listaSottoscrizioni;
    }

    @ManyToMany(mappedBy = "listaCatgoriaEventi")
    public List<PubblicatoreEsterno> getListaPubblicatori() {
        return listaPubblicatori;
    }

    public void setListaPubblicatori(List<PubblicatoreEsterno> listaPubblicatori) {
        this.listaPubblicatori = listaPubblicatori;
    }

    @Override
    public String toString() {
        return nome + " " + "esterna";
    }

    public boolean isAttiva() {
        return attiva;
    }

    public void setAttiva(boolean attiva) {
        this.attiva = attiva;
    }

    public boolean isInAttesa() {
        return inAttesa;
    }

    public void setInAttesa(boolean inAttesa) {
        this.inAttesa = inAttesa;
    }

    public int compareTo(Object o) {
        ICategoriaEventi c = (ICategoriaEventi) o;
        return nome.compareToIgnoreCase(c.getNome());
    }
}
