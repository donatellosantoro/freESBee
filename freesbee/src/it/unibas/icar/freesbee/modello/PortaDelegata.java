package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.LoggerFactory;

@Entity
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class PortaDelegata implements Serializable,Comparable,Cloneable{
    
//    private static Log logger = LogFactory.getLog(PortaDelegata.class.getName());
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PortaDelegata.class.getName());
    
    private long id;
    private String nome;
    private String descrizione;
    private long idServizio;
    private Servizio servizio;
    private long idAzione;
    private Azione azione;
    private long idSoggettoFruitore;
    private Soggetto soggettoFruitore;
    private boolean fruitoreQueryString;
    
    private String stringaTipoServizio;
    private String stringaServizio;
    private String stringaTipoFruitore;
    private String stringaFruitore;
    private String stringaTipoErogatore;
    private String stringaErogatore;
    private String stringaAzione;

    @Id 
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable=false)
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
    
    @ManyToOne
    @XmlTransient
    public Soggetto getSoggettoFruitore() {
        return soggettoFruitore;
    }

    public void setSoggettoFruitore(Soggetto soggettoFruitore) {
        this.soggettoFruitore = soggettoFruitore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public int compareTo(Object o) {
        return this.getNome().compareToIgnoreCase(((PortaDelegata)o).getNome());
    }
    
    public String toString(){
        return "Porta delegata: [" + id + "] " + nome;
    }
    
    @Transient
    public long getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(long idServizio) {
        this.idServizio = idServizio;
    }
    
    @Transient
    public long getIdAzione() {
        return idAzione;
    }

    public void setIdAzione(long idAzione) {
        this.idAzione = idAzione;
    }
    
    @Transient
    public long getIdSoggettoFruitore() {
        return idSoggettoFruitore;
    }

    public void setIdSoggettoFruitore(long idSoggettoFruitore) {
        this.idSoggettoFruitore = idSoggettoFruitore;
    }

    public String getStringaErogatore() {
        return stringaErogatore;
    }

    public void setStringaErogatore(String stringaErogatore) {
        this.stringaErogatore = stringaErogatore;
    }

    public String getStringaFruitore() {
        return stringaFruitore;
    }

    public void setStringaFruitore(String stringaFruitore) {
        this.stringaFruitore = stringaFruitore;
    }

    public String getStringaServizio() {
        return stringaServizio;
    }

    public void setStringaServizio(String stringaServizio) {
        this.stringaServizio = stringaServizio;
    }

    public String getStringaTipoErogatore() {
        return stringaTipoErogatore;
    }

    public void setStringaTipoErogatore(String stringaTipoErogatore) {
        this.stringaTipoErogatore = stringaTipoErogatore;
    }

    public String getStringaTipoFruitore() {
        return stringaTipoFruitore;
    }

    public void setStringaTipoFruitore(String stringaTipoFruitore) {
        this.stringaTipoFruitore = stringaTipoFruitore;
    }

    public String getStringaAzione() {
        return stringaAzione;
    }

    public void setStringaAzione(String stringaAzione) {
        this.stringaAzione = stringaAzione;
    }

    public String getStringaTipoServizio() {
        return stringaTipoServizio;
    }

    public void setStringaTipoServizio(String stringaTipoServizio) {
        this.stringaTipoServizio = stringaTipoServizio;
    }

    public boolean isFruitoreQueryString() {
        return fruitoreQueryString;
    }

    public void setFruitoreQueryString(boolean fruitoreQueryString) {
        this.fruitoreQueryString = fruitoreQueryString;
    }
    
    public PortaDelegata clone(){
        try {
            return (PortaDelegata) super.clone();
        } catch (CloneNotSupportedException ex) {
            logger.error("Impossibile clonare la porta delegata.");
            if (logger.isDebugEnabled()) logger.error(ex.getLocalizedMessage());
            return null;
        }
    }
}
