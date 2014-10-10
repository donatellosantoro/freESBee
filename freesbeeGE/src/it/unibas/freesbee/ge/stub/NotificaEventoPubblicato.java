package it.unibas.freesbee.ge.stub;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificaEventoPubblicato", propOrder = {
    "idEventoPubblicato", "categoriaEventi"})
public class NotificaEventoPubblicato implements Serializable {

    @XmlElement(name = "idEventoPubblicato")
    private String idEventoPubblicato;
    @XmlElement(name = "categoriaEventi")
    private String categoriaEventi;


    public String getIdEventoPubblicato() {
        return idEventoPubblicato;
    }

    public void setIdEventoPubblicato(String idEventoPubblicato) {
        this.idEventoPubblicato = idEventoPubblicato;
    }

    public String getCategoriaEventi() {
        return categoriaEventi;
    }

    public void setCategoriaEventi(String categoriaEventi) {
        this.categoriaEventi = categoriaEventi;
    }
}
