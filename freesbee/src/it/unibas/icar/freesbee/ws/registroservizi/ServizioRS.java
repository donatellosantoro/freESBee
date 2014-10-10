package it.unibas.icar.freesbee.ws.registroservizi;

import it.unibas.icar.freesbee.modello.Servizio;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ServizioRS {

    private String tipo;
    private String nome;
    private SoggettoRS soggettoErogatore;
    private List<SoggettoRS> soggettiFruitori;

    public ServizioRS() {
    }

    public ServizioRS(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    public ServizioRS(Servizio servizio) {
        this.nome = servizio.getNome();
        this.tipo = servizio.getTipo();
        this.soggettoErogatore = new SoggettoRS(servizio.getErogatore());
    }
    public Servizio trasformaServizio(){
        Servizio servizio = new Servizio();
        servizio.setNome(this.nome);
        servizio.setTipo(this.tipo);
        servizio.setErogatore(this.soggettoErogatore.trasformaSoggetto());
        return servizio;
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

    public List<SoggettoRS> getSoggettiFruitori() {
        return soggettiFruitori;
    }

    public void setSoggettiFruitori(List<SoggettoRS> soggettiFruitori) {
        this.soggettiFruitori = soggettiFruitori;
    }
}
