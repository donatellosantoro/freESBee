package it.unibas.icar.freesbee.ws.registroservizi;

import it.unibas.icar.freesbee.modello.AccordoServizio;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name="AccordoServizio")
public class AccordoServizioRS {
    
    private String nome;

    public AccordoServizioRS() {
    }

    public AccordoServizioRS(AccordoServizio accordo) {
        this.nome = accordo.getNome();
    }
    public AccordoServizio trasformaAccordoServizio(){
        AccordoServizio accordoServizio = new AccordoServizio();
        accordoServizio.setNome(this.nome);
        return accordoServizio;
    }

    @XmlElement(required = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
