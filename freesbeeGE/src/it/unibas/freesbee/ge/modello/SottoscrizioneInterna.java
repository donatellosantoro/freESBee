package it.unibas.freesbee.ge.modello;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.w3c.dom.Node;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;

@Entity
public class SottoscrizioneInterna implements Serializable, ISottoscrizione {

    private long id;
    private String tipoSottoscrizione;
    private Sottoscrittore sottoscrittore;
    private CategoriaEventiInterna categoriaEventi;
    private FiltroContenuto filtroContenuto;
    private FiltroData filtroData;
    private List<FiltroPubblicatoreInterno> listaFiltroPublicatore = new ArrayList<FiltroPubblicatoreInterno>();

    public SottoscrizioneInterna() {
    }

    public SottoscrizioneInterna(Sottoscrittore sottoscrittore, CategoriaEventiInterna categoriaEventi, String tipoSottoscrizione, FiltroData filtroData) {
        this.sottoscrittore = sottoscrittore;
        this.categoriaEventi = categoriaEventi;
        this.tipoSottoscrizione = tipoSottoscrizione;
        this.filtroData = filtroData;
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
    public CategoriaEventiInterna getCategoriaEventi() {
        return categoriaEventi;
    }

    public void setCategoriaEventi(CategoriaEventiInterna categoriaEventi) {
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
    public List<FiltroPubblicatoreInterno> getListaFiltroPublicatore() {
        return listaFiltroPublicatore;
    }

    public void setListaFiltroPublicatore(List<FiltroPubblicatoreInterno> listaFiltroPubliccatore) {
        this.listaFiltroPublicatore = listaFiltroPubliccatore;
    }

    public static SottoscrizioneInterna binding(String bodyMessaggio, CategoriaEventiInterna categoriaEventiInterna) throws Exception {
        Document doc = XMLUtils.parse(bodyMessaggio);
        Node nodo = doc.getDocumentElement().getElementsByTagName("sottoscrittore").item(0);
        Sottoscrittore sottoscrittore = Sottoscrittore.binding(XMLUtils.toString(nodo));
        nodo = doc.getDocumentElement().getElementsByTagName("tipoSottoscrizione").item(0);
        SottoscrizioneInterna sottoscrizioneInterna = new SottoscrizioneInterna();
        sottoscrizioneInterna.setSottoscrittore(sottoscrittore);
        sottoscrizioneInterna.setTipoSottoscrizione(nodo.getTextContent().toUpperCase());
        nodo = doc.getDocumentElement().getElementsByTagName("filtroData").item(0);
        if (nodo == null) {
            throw new Exception("Bisogna speciifcare il filtro data");
        }
        FiltroData filtroData = FiltroData.binding(XMLUtils.toString(nodo));
        sottoscrizioneInterna.setFiltroData(filtroData);
        nodo = doc.getDocumentElement().getElementsByTagName("filtroContenuto").item(0);
        if (nodo != null) {
            FiltroContenuto filtroContenuto = FiltroContenuto.binding(XMLUtils.toString(nodo));
            sottoscrizioneInterna.setFiltroContenuto(filtroContenuto);
        }

        nodo = doc.getDocumentElement().getElementsByTagName("listaFiltroPublicatore").item(0);
        if (nodo != null) {
            List<FiltroPubblicatoreInterno> listaFiltroPubblicatore = FiltroPubblicatoreInterno.binding(bodyMessaggio, categoriaEventiInterna, sottoscrizioneInterna);
            sottoscrizioneInterna.setListaFiltroPublicatore(listaFiltroPubblicatore);
        }

        return sottoscrizioneInterna;

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
