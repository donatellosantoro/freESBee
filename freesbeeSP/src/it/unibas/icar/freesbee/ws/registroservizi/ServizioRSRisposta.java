package it.unibas.icar.freesbee.ws.registroservizi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ServizioRSRisposta {

    private long id;
    private String tipo;
    private String nome;
    private SoggettoRS erogatore;
    private List<SoggettoRSRisposta> fruitori = new ArrayList<SoggettoRSRisposta>();
    private AccordoServizioRSRisposta accordoServizio;
    private boolean correlato;
    private boolean privato;
    private Date oraRegistrazione;
    private String oldNomeForUpdate;
    private String oldTipoForUpdate;
    private String oldNomeSoggettoErogatoreForUpdate;
    private String oldTipoSoggettoErogatoreForUpdate;

    public ServizioRSRisposta() {

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

    public SoggettoRS getErogatore() {
        return erogatore;
    }

    public void setErogatore(SoggettoRS erogatore) {
        this.erogatore = erogatore;
    }

    @XmlElement(name = "fruitori")
    public List<SoggettoRSRisposta> getFruitori() {
        return fruitori;
    }

    public void setFruitori(List<SoggettoRSRisposta> fruitori) {
        this.fruitori = fruitori;
    }

    public AccordoServizioRSRisposta getAccordoServizio() {
        return accordoServizio;
    }

    public void setAccordoServizio(AccordoServizioRSRisposta accordoServizio) {
        this.accordoServizio = accordoServizio;
    }

    public boolean isCorrelato() {
        return correlato;
    }

    public void setCorrelato(boolean correlato) {
        this.correlato = correlato;
    }

    public boolean isPrivato() {
        return privato;
    }

    public void setPrivato(boolean privato) {
        this.privato = privato;
    }

    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    public void setOldNomeForUpdate(String oldNomeForUpdate) {
        this.oldNomeForUpdate = oldNomeForUpdate;
    }

    public String getOldNomeSoggettoErogatoreForUpdate() {
        return oldNomeSoggettoErogatoreForUpdate;
    }

    public void setOldNomeSoggettoErogatoreForUpdate(String oldNomeSoggettoErogatoreForUpdate) {
        this.oldNomeSoggettoErogatoreForUpdate = oldNomeSoggettoErogatoreForUpdate;
    }

    public String getOldTipoForUpdate() {
        return oldTipoForUpdate;
    }

    public void setOldTipoForUpdate(String oldTipoForUpdate) {
        this.oldTipoForUpdate = oldTipoForUpdate;
    }

    public String getOldTipoSoggettoErogatoreForUpdate() {
        return oldTipoSoggettoErogatoreForUpdate;
    }

    public void setOldTipoSoggettoErogatoreForUpdate(String oldTipoSoggettoErogatoreForUpdate) {
        this.oldTipoSoggettoErogatoreForUpdate = oldTipoSoggettoErogatoreForUpdate;
    }

    public Date getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(Date oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }
}
