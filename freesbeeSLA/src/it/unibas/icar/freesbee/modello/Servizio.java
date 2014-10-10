package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

public class Servizio implements Serializable, Comparable {

    private long id;
    private String tipo;
    private String nome;
    private long idErogatore;
    private Soggetto erogatore;
    private List<Long> idFruitori = new ArrayList<Long>();
    private List<Soggetto> fruitori = new ArrayList<Soggetto>();
    private long idAccordoServizio;
    private AccordoServizio accordoServizio;
    private long idPortaApplicativa;
    private List<PortaApplicativa> portaApplicativa = new ArrayList<PortaApplicativa>();
    private List<PortaDelegata> listaPorteDelegate = new ArrayList<PortaDelegata>();
    private boolean correlato;
    private boolean privato;
    private Date oraRegistrazione;
    private String oldNomeForUpdate;
    private String oldTipoForUpdate;
    private String oldNomeSoggettoErogatoreForUpdate;
    private String oldTipoSoggettoErogatoreForUpdate;

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

    public Soggetto getErogatore() {
        return erogatore;
    }

    public void setErogatore(Soggetto erogatore) {
        this.erogatore = erogatore;
    }

    public List<Soggetto> getFruitori() {
        return fruitori;
    }

    public void setFruitori(List<Soggetto> fruitori) {
        this.fruitori = fruitori;
    }

    @XmlTransient
    public AccordoServizio getAccordoServizio() {
        return accordoServizio;
    }

    public void setAccordoServizio(AccordoServizio accordoServizio) {
        this.accordoServizio = accordoServizio;
    }

    @XmlTransient
    public List<PortaApplicativa> getPortaApplicativa() {
        return portaApplicativa;
    }

    public void setPortaApplicativa(List<PortaApplicativa> portaApplicativa) {
        this.portaApplicativa = portaApplicativa;
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

    public int compareTo(Object o) {
        Servizio other = (Servizio) o;
        if (this.getTipo().equalsIgnoreCase(other.getTipo())) {
            return this.getNome().compareToIgnoreCase(other.getNome());
        } else {
            return this.getTipo().compareToIgnoreCase(other.getTipo());
        }
    }

    public String getNomeBreve() {
        return this.tipo + "\\" + this.nome;
    }

    @Override
    public String toString() {
        return this.tipo + "\\" + this.nome;
    }

    public int getNumeroFruitori() {
        if (this.getFruitori() == null) {
            return 0;
        }
        return this.getFruitori().size();
    }

    public long getIdErogatore() {
        return idErogatore;
    }

    public void setIdErogatore(long idErogatore) {
        this.idErogatore = idErogatore;
    }

    public long getIdAccordoServizio() {
        return idAccordoServizio;
    }

    public void setIdAccordoServizio(long idAccordoServizio) {
        this.idAccordoServizio = idAccordoServizio;
    }

    public long getIdPortaApplicativa() {
        return idPortaApplicativa;
    }

    public void setIdPortaApplicativa(long idPortaApplicativa) {
        this.idPortaApplicativa = idPortaApplicativa;
    }

    public List<Long> getIdFruitori() {
        return idFruitori;
    }

    public void setIdFruitori(List<Long> idFruitori) {
        this.idFruitori = idFruitori;
    }

    @XmlTransient
    public List<PortaDelegata> getListaPorteDelegate() {
        return listaPorteDelegate;
    }

    public void setListaPorteDelegate(List<PortaDelegata> listaPorteDelegate) {
        this.listaPorteDelegate = listaPorteDelegate;
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
