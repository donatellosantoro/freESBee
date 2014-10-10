package it.unibas.icar.freesbee.ws.registroservizi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class SoggettoRS implements Comparable{

    private String tipo;
    private String nome;

    public SoggettoRS() {
    }

    public SoggettoRS(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    @XmlElement(required = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlElement(required = true)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int compareTo(Object o) {
        SoggettoRS other = (SoggettoRS) o;
        if (this.getTipo().equalsIgnoreCase(other.getTipo())) {
            return this.getNome().compareToIgnoreCase(other.getNome());
        } else {
            return this.getTipo().compareToIgnoreCase(other.getTipo());
        }
    }
}
