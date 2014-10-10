package it.unibas.freesbee.ge.modello;

import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.facade.DAOPubblicatoreInternoFacade;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.cxf.helpers.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"pubblicatore_id", "sottoscrizione_id"})})
public class FiltroPubblicatoreInterno implements Serializable , IFiltroPubblicatore{

    private long id;
    private PubblicatoreInterno pubblicatore;
    private SottoscrizioneInterna sottoscrizione;

    public FiltroPubblicatoreInterno() {
    }

    public FiltroPubblicatoreInterno(PubblicatoreInterno pubblicatore, SottoscrizioneInterna sottoscrizione) {
        this.pubblicatore = pubblicatore;
        this.sottoscrizione = sottoscrizione;
    }

    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    public PubblicatoreInterno getPubblicatore() {
        return pubblicatore;
    }

    public void setPubblicatore(PubblicatoreInterno pubblicatore) {
        this.pubblicatore = pubblicatore;
    }

    @XmlTransient
    @ManyToOne(optional = false)
    public SottoscrizioneInterna getSottoscrizione() {
        return sottoscrizione;
    }

    public void setSottoscrizione(SottoscrizioneInterna sottoscrizione) {
        this.sottoscrizione = sottoscrizione;
    }

    public static List<FiltroPubblicatoreInterno> binding(String bodyMessaggio, CategoriaEventiInterna categoriaEventiInterna, SottoscrizioneInterna sottoscrizioneInterna) throws Exception {
        IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
        List<FiltroPubblicatoreInterno> listaFiltroPubblicatore = new ArrayList<FiltroPubblicatoreInterno>();
        FiltroPubblicatoreInterno filtroPubblicatoreInterno = null;

        Document doc = XMLUtils.parse(bodyMessaggio);
        NodeList nodi = doc.getDocumentElement().getElementsByTagName("pubblicatore");
        Node nodo = null;

        if (categoriaEventiInterna.getNome().equals(CategoriaEventiInterna.GE_CONTROL_PROTOCOL)) {
            throw new Exception("La sottoscrizione per gli eventi della categoria " + CategoriaEventiInterna.GE_CONTROL_PROTOCOL + " non deve avere filtri pubblicatori");
        }

        for (int i = 0; i < nodi.getLength(); i++) {
            nodo = nodi.item(i);

            filtroPubblicatoreInterno = new FiltroPubblicatoreInterno();
            PubblicatoreInterno soggetto = PubblicatoreInterno.binding(XMLUtils.toString(nodo));

            //Cerco fra i pubblicatori interni
            PubblicatoreInterno pubblicatoreInterno = daoPubblicatoreInterno.findByTipoNome(soggetto.getTipo(), soggetto.getNome());

            if (pubblicatoreInterno != null) {
                DAOPubblicatoreInternoFacade.verificaEsistenzaPubblicatoreInterno(categoriaEventiInterna, pubblicatoreInterno);
                filtroPubblicatoreInterno.setPubblicatore(pubblicatoreInterno);
                filtroPubblicatoreInterno.setSottoscrizione(sottoscrizioneInterna);
                listaFiltroPubblicatore.add(filtroPubblicatoreInterno);
            } else {
                throw new Exception("Il soggetto " + soggetto + " non e' un pubblicatore interno");
            }
        }

        return listaFiltroPubblicatore;
    }
}
