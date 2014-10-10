package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"})})
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class PortaApplicativa implements Serializable {

    private Long id;
    private String nome;
    private String descrizione;
    private Long idServizio;
    private Servizio servizio;
    private Long idAzione;
    private Azione azione;
    private Long idServizioApplicativo;
    private ServizioApplicativo servizioApplicativo;
    private String stringaTipoServizio;
    private String stringaServizio;
    private String stringaTipoErogatore;
    private String stringaErogatore;
    private String stringaAzione;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ManyToOne
    @XmlTransient
    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }

    @OneToOne
    @XmlTransient
    public Azione getAzione() {
        return azione;
    }

    public void setAzione(Azione azione) {
        this.azione = azione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @XmlTransient
    @ManyToOne
    public ServizioApplicativo getServizioApplicativo() {
        return servizioApplicativo;
    }

    public void setServizioApplicativo(ServizioApplicativo servizioApplicativo) {
        this.servizioApplicativo = servizioApplicativo;
    }
    
    @Transient
    public Long getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(Long idServizio) {
        this.idServizio = idServizio;
    }    
    
    @Transient
    public Long getIdAzione() {
        return idAzione;
    }

    public void setIdAzione(Long idAzione) {
        this.idAzione = idAzione;
    }
    
    @Transient
    public Long getIdServizioApplicativo() {
        return idServizioApplicativo;
    }

    public void setIdServizioApplicativo(Long idServizioApplicativo) {
        this.idServizioApplicativo = idServizioApplicativo;
    }

    public String getStringaAzione() {
        return stringaAzione;
    }

    public void setStringaAzione(String stringaAzione) {
        this.stringaAzione = stringaAzione;
    }

    public String getStringaServizio() {
        return stringaServizio;
    }

    public void setStringaServizio(String stringaServizio) {
        this.stringaServizio = stringaServizio;
    }

    public String getStringaTipoServizio() {
        return stringaTipoServizio;
    }

    public void setStringaTipoServizio(String stringaTipoServizio) {
        this.stringaTipoServizio = stringaTipoServizio;
    }

    public String getStringaErogatore() {
        return stringaErogatore;
    }

    public void setStringaErogatore(String stringaErogatore) {
        this.stringaErogatore = stringaErogatore;
    }

    public String getStringaTipoErogatore() {
        return stringaTipoErogatore;
    }

    public void setStringaTipoErogatore(String stringaTipoErogatore) {
        this.stringaTipoErogatore = stringaTipoErogatore;
    }
    
    
}
