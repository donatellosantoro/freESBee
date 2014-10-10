package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

@Entity
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class AccordoServizio implements Serializable, Comparable {

    public static String PROFILO_ONEWAY = "EGOV_IT_MessaggioSingoloOneWay";
    public static String PROFILO_SINCRONO = "EGOV_IT_ServizioSincrono";
    public static String PROFILO_ASINCRONO_SIMMETRICO = "EGOV_IT_ServizioAsincronoSimmetrico";
    public static String PROFILO_ASINCRONO_ASIMMETRICO = "EGOV_IT_ServizioAsincronoAsimmetrico";
    private long id;
    private String nome;
    private String descrizione;
    private String profiloCollaborazione;
    private List<Servizio> servizi = new ArrayList<Servizio>();
    private long idProfiloEGov;
    private ProfiloEGov profiloEGov;
    private List<Azione> azioni = new ArrayList<Azione>();
    private boolean privato;
    private String oldNomeForUpdate;
    private Date oraRegistrazione;
    private String policyXACML;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    public void setProfiloCollaborazione(String profiloCollaborazione) {
        this.profiloCollaborazione = profiloCollaborazione;
    }

    @OneToMany(mappedBy = "accordoServizio", cascade = CascadeType.ALL)
    @XmlTransient
    public List<Servizio> getServizi() {
        return servizi;
    }

    public void setServizi(List<Servizio> servizi) {
        this.servizi = servizi;
    }

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    @XmlTransient
    public ProfiloEGov getProfiloEGov() {
        return profiloEGov;
    }

    public void setProfiloEGov(ProfiloEGov profiloEGov) {
        this.profiloEGov = profiloEGov;
    }

    @OneToMany(mappedBy = "accordoServizio", cascade = CascadeType.ALL)
    @XmlTransient
    public List<Azione> getAzioni() {
        return azioni;
    }

    public void setAzioni(List<Azione> azioni) {
        this.azioni = azioni;
    }

    @Transient
    public String getProfiloCollaborazioneBreve() {
        return AccordoServizio.convertiNomeProfilo(this.profiloCollaborazione);
    }

    public static String convertiNomeProfilo(String profilo) {
        if (profilo == null) {
            return "";
        }
        if (profilo.equals(AccordoServizio.PROFILO_ONEWAY)) {
            return "OneWay";
        } else if (profilo.equals(AccordoServizio.PROFILO_SINCRONO)) {
            return "Sincrono";
        } else if (profilo.equals(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO)) {
            return "Asincrono simmetrico";
        } else if (profilo.equals(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO)) {
            return "Asincrono asimmetrico";
        } else {
            return "";
        }
    }

    public boolean isPrivato() {
        return privato;
    }

    public void setPrivato(boolean privato) {
        this.privato = privato;
    }

    public int compareTo(Object o) {
        return this.getNome().compareToIgnoreCase(((AccordoServizio) o).getNome());
    }

    @Transient
    public long getIdProfiloEGov() {
        return idProfiloEGov;
    }

    public void setIdProfiloEGov(long idProfiloEGov) {
        this.idProfiloEGov = idProfiloEGov;
    }

    @Transient
    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    public void setOldNomeForUpdate(String oldNomeForUpdate) {
        this.oldNomeForUpdate = oldNomeForUpdate;
    }

    public Date getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(Date oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }

    @Type(type="text")
    public String getPolicyXACML() {
        return policyXACML;
    }

    public void setPolicyXACML(String policyXACML) {
        this.policyXACML = policyXACML;
    }



    @Transient
    public Azione cercaAzione(String nomeAzione) {
        if(nomeAzione==null){
            return null;
        }
        for (Azione azione : azioni) {
            if(azione.getNome().equalsIgnoreCase(nomeAzione)){
                return azione;
            }
        }
        return null;
    }
}
