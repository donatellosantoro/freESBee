package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

public class ServizioApplicativo implements Serializable{
    
    private long Id;
    private String nome;
    private String descrizione;
    private String connettore;
    private List<PortaApplicativa> listaPorteApplicative;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConnettore() {
        return connettore;
    }

    public void setConnettore(String connettore) {
        this.connettore = connettore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    @XmlTransient()
    public List<PortaApplicativa> getListaPorteApplicative() {
        return listaPorteApplicative;
    }

    public void setListaPorteApplicative(List<PortaApplicativa> listaPorteApplicative) {
        this.listaPorteApplicative = listaPorteApplicative;
    }
}
