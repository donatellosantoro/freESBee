package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
public class CategoriaEventiInterna implements Serializable, ICategoriaEventi {

    private long id;
    private String nome;
    private boolean attiva;
    private List<SottoscrizioneInterna> listaSottoscrizioni = new ArrayList<SottoscrizioneInterna>();
    private List<PubblicatoreInterno> listaPubblicatori = new ArrayList<PubblicatoreInterno>();

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
    public List<SottoscrizioneInterna> getListaSottoscrizioni() {
        return listaSottoscrizioni;
    }

    public void setListaSottoscrizioni(List<SottoscrizioneInterna> listaSottoscrizioni) {
        this.listaSottoscrizioni = listaSottoscrizioni;
    }

    @ManyToMany(mappedBy = "listaCatgoriaEventi", cascade = CascadeType.PERSIST)
    public List<PubblicatoreInterno> getListaPubblicatori() {
        return listaPubblicatori;
    }

    public void setListaPubblicatori(List<PubblicatoreInterno> listaPubblicatori) {
        this.listaPubblicatori = listaPubblicatori;
    }

    @Override
    public String toString() {
        return nome + " " + "interna";
    }

    public boolean isAttiva() {
        return attiva;
    }

    public void setAttiva(boolean attiva) {
        this.attiva = attiva;
    }

    public int compareTo(Object o) {
        ICategoriaEventi c = (ICategoriaEventi) o;
        return nome.compareToIgnoreCase(c.getNome());
    }
}
