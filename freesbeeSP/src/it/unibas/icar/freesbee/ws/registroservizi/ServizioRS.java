package it.unibas.icar.freesbee.ws.registroservizi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ServizioRS {

    private String tipo;
    private String nome;
    private SoggettoRS soggettoErogatore;

    public ServizioRS() {
    }

    public ServizioRS(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    @XmlElement(required = true)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @XmlElement(required = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlElement(required = true)
    public SoggettoRS getSoggettoErogatore() {
        return soggettoErogatore;
    }

    public void setSoggettoErogatore(SoggettoRS soggettoErogatore) {
        this.soggettoErogatore = soggettoErogatore;
    }
}
