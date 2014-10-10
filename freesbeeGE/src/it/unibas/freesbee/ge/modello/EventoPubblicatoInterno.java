package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.hibernate.annotations.Type;

@Entity
public class EventoPubblicatoInterno implements Serializable, IEventoPubblicato {

    private long id;
    private String messaggio;
    private Object messaggioSoap;
    private Date dataPubblicazione;
    private CategoriaEventiInterna categoriaEventi;
    private PubblicatoreInterno pubblicatore;
    private Log logger = LogFactory.getLog(this.getClass());
    private List<StatoMessaggioInterno> listaStatoMessaggio = new ArrayList<StatoMessaggioInterno>();

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Type(type = "text")
    @XmlTransient
    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    @Transient
    public Object getMessaggioSoap() {
        return messaggioSoap;
    }

    public void setMessaggioSoap(Object messaggioSoap) {
        this.messaggioSoap = messaggioSoap;
    }

    @Column(nullable = false)
    @XmlTransient
    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Date dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    @ManyToOne(optional = false)
    public PubblicatoreInterno getPubblicatore() {
        return pubblicatore;
    }

    public void setPubblicatore(PubblicatoreInterno pubblicatore) {
        this.pubblicatore = pubblicatore;
    }

    @ManyToOne(optional = false)
    public CategoriaEventiInterna getCategoriaEventi() {
        return categoriaEventi;
    }

    public void setCategoriaEventi(CategoriaEventiInterna categoriaEventi) {
        this.categoriaEventi = categoriaEventi;
    }

    @OneToMany(mappedBy = "eventoPubblicato")
    public List<StatoMessaggioInterno> getListaStatoMessaggio() {
        return listaStatoMessaggio;
    }

    public void setListaStatoMessaggio(List<StatoMessaggioInterno> listaStatoMessaggio) {
        this.listaStatoMessaggio = listaStatoMessaggio;
    }

    public void setPubblicatore(IPubblicatore pubblicatore) {
        this.pubblicatore = (PubblicatoreInterno) pubblicatore;
    }

    public void setCategoriaEventi(ICategoriaEventi categoriaEventi) {
        this.categoriaEventi = (CategoriaEventiInterna) categoriaEventi;
    }

    @Transient
    public StatoMessaggioInterno getStatoMessaggio(Sottoscrittore sottoscrittore) {
        for (StatoMessaggioInterno stato : listaStatoMessaggio) {
            if (sottoscrittore.compareTo(stato.getSottoscrittore()) == 0) {
                return stato;
            }
        }
        return null;
    }
}
