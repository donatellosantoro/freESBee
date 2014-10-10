package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"tipo", "nome"})})
public class Sottoscrittore implements Serializable, Comparable {

    private long id;
    private String tipo;
    private String nome;
    private String descrizione;

    public Sottoscrittore() {
    }

    public Sottoscrittore(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(nullable = false)
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

    @Override
    public String toString() {
        return tipo + " " + nome;
    }

    @Override
    public int compareTo(Object o) {
        Sottoscrittore soggetto = (Sottoscrittore) o;
        if (soggetto.getNome().equals(nome) && soggetto.getTipo().equals(tipo)) {
            return 0;
        }
        return -1;
    }

    public static Sottoscrittore binding(String bodyMessaggio) throws Exception {
        Sottoscrittore soggetto = new Sottoscrittore();
        Document doc = XMLUtils.parse(bodyMessaggio);
        Node nodo = doc.getDocumentElement().getElementsByTagName("nome").item(0);
        soggetto.setNome(nodo.getTextContent());
        nodo = doc.getDocumentElement().getElementsByTagName("tipo").item(0);
        soggetto.setTipo(nodo.getTextContent());
        nodo = doc.getDocumentElement().getElementsByTagName("descrizione").item(0);
        soggetto.setDescrizione(nodo.getTextContent());
        return soggetto;
    }
}
