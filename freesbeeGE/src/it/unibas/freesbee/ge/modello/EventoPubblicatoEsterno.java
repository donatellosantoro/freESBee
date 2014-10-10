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
public class EventoPubblicatoEsterno implements Serializable, IEventoPubblicato {

    private long id;
    private String messaggio;
    private Object messaggioSoap;
    private Date dataPubblicazione;
    private CategoriaEventiEsterna categoriaEventi;
    private PubblicatoreEsterno pubblicatore;
    private String tipoPubblicatoreEsterno;
    private String nomePubblicatoreEsterno;
    private Log logger = LogFactory.getLog(this.getClass());
    private List<StatoMessaggioEsterno> listaStatoMessaggio = new ArrayList<StatoMessaggioEsterno>();

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
    public PubblicatoreEsterno getPubblicatore() {
        return pubblicatore;
    }

    public void setPubblicatore(PubblicatoreEsterno pubblicatore) {
        this.pubblicatore = pubblicatore;
    }

    @ManyToOne(optional = false)
    public CategoriaEventiEsterna getCategoriaEventi() {
        return categoriaEventi;
    }

    public void setCategoriaEventi(CategoriaEventiEsterna categoriaEventi) {
        this.categoriaEventi = categoriaEventi;
    }

    @OneToMany(mappedBy = "eventoPubblicato")
    public List<StatoMessaggioEsterno> getListaStatoMessaggio() {
        return listaStatoMessaggio;
    }

    public void setListaStatoMessaggio(List<StatoMessaggioEsterno> listaStatoMessaggio) {
        this.listaStatoMessaggio = listaStatoMessaggio;
    }

    public void setPubblicatore(IPubblicatore pubblicatore) {
        this.pubblicatore = (PubblicatoreEsterno) pubblicatore;
    }

    public void setCategoriaEventi(ICategoriaEventi categoriaEventi) {
        this.categoriaEventi = (CategoriaEventiEsterna) categoriaEventi;
    }

    @Transient
    public StatoMessaggioEsterno getStatoMessaggio(Sottoscrittore sottoscrittore) {
        for (StatoMessaggioEsterno stato : listaStatoMessaggio) {
            if (sottoscrittore.compareTo(stato.getSottoscrittore()) == 0) {
                return stato;
            }
        }
        return null;
    }

    public String getTipoPubblicatoreEsterno() {
        return tipoPubblicatoreEsterno;
    }

    public void setTipoPubblicatoreEsterno(String tipoPubblicatoreEsterno) {
        this.tipoPubblicatoreEsterno = tipoPubblicatoreEsterno;
    }

    public String getNomePubblicatoreEsterno() {
        return nomePubblicatoreEsterno;
    }

    public void setNomePubblicatoreEsterno(String nomePubblicatoreEsterno) {
        this.nomePubblicatoreEsterno = nomePubblicatoreEsterno;
    }
}
