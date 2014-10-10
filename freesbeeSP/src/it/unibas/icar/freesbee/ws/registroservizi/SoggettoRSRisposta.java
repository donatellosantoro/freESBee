package it.unibas.icar.freesbee.ws.registroservizi;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class SoggettoRSRisposta implements Serializable, Comparable {

    private long id;
    private String tipo;
    private String nome;
    private String descrizione;
    private String portaDominio;
    private Date oraRegistrazione;
    private String oldNomeForUpdate;
    private String oldTipoForUpdate;
    
    public SoggettoRSRisposta(){
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPortaDominio() {
        return portaDominio;
    }

    public void setPortaDominio(String portaDominio) {
        this.portaDominio = portaDominio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int compareTo(Object o) {
        SoggettoRSRisposta other = (SoggettoRSRisposta) o;
        if (this.getTipo().equalsIgnoreCase(other.getTipo())) {
            return this.getNome().compareToIgnoreCase(other.getNome());
        } else {
            return this.getTipo().compareToIgnoreCase(other.getTipo());
        }
    }

    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    public void setOldNomeForUpdate(String oldNomeForUpdate) {
        this.oldNomeForUpdate = oldNomeForUpdate;
    }

    public String getOldTipoForUpdate() {
        return oldTipoForUpdate;
    }

    public void setOldTipoForUpdate(String oldTipoForUpdate) {
        this.oldTipoForUpdate = oldTipoForUpdate;
    }

    public Date getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(Date oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }
}
