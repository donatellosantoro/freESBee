package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

@Entity
public class SottoscrizioneEsterna implements Serializable, ISottoscrizione {

    private long id;
    private String tipoSottoscrizione;
    private Sottoscrittore sottoscrittore;
    private CategoriaEventiEsterna categoriaEventi;
    private FiltroContenuto filtroContenuto;
    private FiltroData filtroData;
    private Date scadenzaAttesa;
    private List<FiltroPubblicatoreEsterno> listaFiltroPublicatore = new ArrayList<FiltroPubblicatoreEsterno>();

    public SottoscrizioneEsterna(Sottoscrittore sottoscrittore, CategoriaEventiEsterna categoriaEventi, String tipoSottoscrizione, FiltroData filtroData) {
        this.sottoscrittore = sottoscrittore;
        this.categoriaEventi = categoriaEventi;
        this.tipoSottoscrizione = tipoSottoscrizione;
        this.filtroData = filtroData;
    }

    public SottoscrizioneEsterna() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, columnDefinition = "varchar(20) CHECK (tipoSottoscrizione LIKE '" + TIPO_SOTTOSCRIZIONE_CONSEGNA + "' OR tipoSottoscrizione LIKE '" + TIPO_SOTTOSCRIZIONE_NOTIFICA + "')")
    public String getTipoSottoscrizione() {
        return tipoSottoscrizione;
    }

    public void setTipoSottoscrizione(String tipo) {
        this.tipoSottoscrizione = tipo;
    }

    @ManyToOne(optional = false)
    public Sottoscrittore getSottoscrittore() {
        return sottoscrittore;
    }

    public void setSottoscrittore(Sottoscrittore sottoscrittore) {
        this.sottoscrittore = sottoscrittore;
    }

    @XmlTransient
    @ManyToOne(optional = false)
    public CategoriaEventiEsterna getCategoriaEventi() {
        return categoriaEventi;
    }

    public void setCategoriaEventi(CategoriaEventiEsterna categoriaEventi) {
        this.categoriaEventi = categoriaEventi;
    }

    @Override
    public String toString() {
        return "" + tipoSottoscrizione;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public FiltroContenuto getFiltroContenuto() {
        return filtroContenuto;
    }

    public void setFiltroContenuto(FiltroContenuto filtroContenuto) {
        this.filtroContenuto = filtroContenuto;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    public FiltroData getFiltroData() {
        return filtroData;
    }

    public void setFiltroData(FiltroData filtroData) {
        this.filtroData = filtroData;
    }

    @OneToMany(mappedBy = "sottoscrizione", cascade = CascadeType.PERSIST)
    public List<FiltroPubblicatoreEsterno> getListaFiltroPublicatore() {
        return listaFiltroPublicatore;
    }

    public void setListaFiltroPublicatore(List<FiltroPubblicatoreEsterno> listaFiltroPubliccatore) {
        this.listaFiltroPublicatore = listaFiltroPubliccatore;
    }

    public Date getScadenzaAttesa() {
        return scadenzaAttesa;
    }

    public void setScadenzaAttesa(Date scadenzaAttesa) {
        this.scadenzaAttesa = scadenzaAttesa;
    }

    public static SottoscrizioneEsterna binding(String bodyMessaggio, CategoriaEventiEsterna categoriaEventiEsterna) throws Exception {
        Document doc = XMLUtils.parse(bodyMessaggio);
        Node nodo = doc.getDocumentElement().getElementsByTagName("sottoscrittore").item(0);
        Sottoscrittore sottoscrittore = Sottoscrittore.binding(XMLUtils.toString(nodo));
        nodo = doc.getDocumentElement().getElementsByTagName("tipoSottoscrizione").item(0);
        SottoscrizioneEsterna sottoscrizioneEsterna = new SottoscrizioneEsterna();
        sottoscrizioneEsterna.setSottoscrittore(sottoscrittore);
        sottoscrizioneEsterna.setTipoSottoscrizione(nodo.getTextContent().toUpperCase());
        nodo = doc.getDocumentElement().getElementsByTagName("filtroData").item(0);
        if (nodo == null) {
            throw new Exception("Bisogna speciifcare il filtro data");
        }
        FiltroData filtroData = FiltroData.binding(XMLUtils.toString(nodo));
        sottoscrizioneEsterna.setFiltroData(filtroData);
        nodo = doc.getDocumentElement().getElementsByTagName("filtroContenuto").item(0);
        if (nodo != null) {
            FiltroContenuto filtroContenuto = FiltroContenuto.binding(XMLUtils.toString(nodo));
            sottoscrizioneEsterna.setFiltroContenuto(filtroContenuto);
        }


        nodo = doc.getDocumentElement().getElementsByTagName("listaFiltroPublicatore").item(0);
        if (nodo != null) {
            List<FiltroPubblicatoreEsterno> listaFiltroPubblicatore = FiltroPubblicatoreEsterno.binding(bodyMessaggio, categoriaEventiEsterna, sottoscrizioneEsterna);
            sottoscrizioneEsterna.setListaFiltroPublicatore(listaFiltroPubblicatore);

        }

        if (sottoscrizioneEsterna.getScadenzaAttesa() == null && categoriaEventiEsterna.isInAttesa()) {
            //la sottoscrizione è in attesa solo se il soggetto non è pubblicatore
            long scadenzaAttesa = new Date().getTime();
            scadenzaAttesa += 1000 * 3600 * 24; //SETTO COME SCADENZA DOMANI
            sottoscrizioneEsterna.setScadenzaAttesa(new Date(scadenzaAttesa));
        }

        return sottoscrizioneEsterna;

    }

    @Transient
    public List getListaIFiltroPublicatore() {
        return listaFiltroPublicatore;
    }

    @Transient
    public String getNomeCategoriaEventi() {
        return categoriaEventi.getNome();
    }
}
