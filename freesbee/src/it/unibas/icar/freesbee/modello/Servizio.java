package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tipo", "nome", "erogatore_id"})})
/*@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"tipo", "nome", "erogatore_id"}),
 @UniqueConstraint(columnNames = {"erogatore_id", "accordoServizio_id"})})*/
public class Servizio implements Serializable, Comparable {

    private static final String SEPARATORE_AZIONI = ",";
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
    private String urlServizio;
    private Date oraRegistrazione;
    private String oldNomeForUpdate;
    private String oldTipoForUpdate;
    private String oldNomeSoggettoErogatoreForUpdate;
    private String oldTipoSoggettoErogatoreForUpdate;
    private String azioni;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, columnDefinition = "varchar(20) default 'SPC'")
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

    @ManyToOne(optional = false)
    @XmlTransient
    public Soggetto getErogatore() {
        return erogatore;
    }

    public void setErogatore(Soggetto erogatore) {
        this.erogatore = erogatore;
    }

//    @ManyToMany(cascade=CascadeType.ALL)
    @ManyToMany()
    @XmlTransient
    public List<Soggetto> getFruitori() {
        return fruitori;
    }

    public void setFruitori(List<Soggetto> fruitori) {
        this.fruitori = fruitori;
    }

    @ManyToOne(optional = false)
    @XmlTransient
    public AccordoServizio getAccordoServizio() {
        return accordoServizio;
    }

    public void setAccordoServizio(AccordoServizio accordoServizio) {
        this.accordoServizio = accordoServizio;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "servizio")
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

    public String getUrlServizio() {
        return urlServizio;
    }

    public void setUrlServizio(String urlServizio) {
        this.urlServizio = urlServizio;
    }

    public int compareTo(Object o) {
        Servizio other = (Servizio) o;
        if (this.getTipo().equalsIgnoreCase(other.getTipo())) {
            return this.getNome().compareToIgnoreCase(other.getNome());
        } else {
            return this.getTipo().compareToIgnoreCase(other.getTipo());
        }
    }

    @Transient
    public String getNomeBreve() {
        return this.tipo + "\\" + this.nome;
    }

    @Override
    public String toString() {
        return this.tipo + "\\" + this.nome;
    }

    @Transient
    public int getNumeroFruitori() {
        if (this.getFruitori() == null) {
            return 0;
        }
        return this.getFruitori().size();
    }

    @Transient
    public long getIdErogatore() {
        return idErogatore;
    }

    public void setIdErogatore(long idErogatore) {
        this.idErogatore = idErogatore;
    }

    @Transient
    public long getIdAccordoServizio() {
        return idAccordoServizio;
    }

    public void setIdAccordoServizio(long idAccordoServizio) {
        this.idAccordoServizio = idAccordoServizio;
    }

    @Transient
    public long getIdPortaApplicativa() {
        return idPortaApplicativa;
    }

    public void setIdPortaApplicativa(long idPortaApplicativa) {
        this.idPortaApplicativa = idPortaApplicativa;
    }

    @Transient
    public List<Long> getIdFruitori() {
        return idFruitori;
    }

    public void setIdFruitori(List<Long> idFruitori) {
        this.idFruitori = idFruitori;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servizio")
    public List<PortaDelegata> getListaPorteDelegate() {
        return listaPorteDelegate;
    }

    public void setListaPorteDelegate(List<PortaDelegata> listaPorteDelegate) {
        this.listaPorteDelegate = listaPorteDelegate;
    }

    @Transient
    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    public void setOldNomeForUpdate(String oldNomeForUpdate) {
        this.oldNomeForUpdate = oldNomeForUpdate;
    }

    @Transient
    public String getOldNomeSoggettoErogatoreForUpdate() {
        return oldNomeSoggettoErogatoreForUpdate;
    }

    public void setOldNomeSoggettoErogatoreForUpdate(String oldNomeSoggettoErogatoreForUpdate) {
        this.oldNomeSoggettoErogatoreForUpdate = oldNomeSoggettoErogatoreForUpdate;
    }

    @Transient
    public String getOldTipoForUpdate() {
        return oldTipoForUpdate;
    }

    public void setOldTipoForUpdate(String oldTipoForUpdate) {
        this.oldTipoForUpdate = oldTipoForUpdate;
    }

    @Transient
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

    public String getAzioni() {
        return azioni;
    }

    public void setAzioni(String azioni) {
        this.azioni = azioni;
    }

    @Transient
    public List<String> getListaAzioni() {
        List<String> result = new ArrayList<String>();
        if (azioni != null && !azioni.trim().isEmpty()) {
            for (String azione : azioni.trim().split(SEPARATORE_AZIONI)) {
                if (azione.trim().isEmpty()) continue;
                result.add(azione.trim());
            }
        }
        return result;
    }
}
