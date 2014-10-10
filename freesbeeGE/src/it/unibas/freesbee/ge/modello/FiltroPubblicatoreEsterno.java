package it.unibas.freesbee.ge.modello;

import it.unibas.freesbee.ge.persistenza.IDAOCategoriaEventiEsterna;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreEsterno;
import it.unibas.freesbee.ge.persistenza.IDAOPubblicatoreInterno;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOCategoriaEventiEsternaHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreEsternoHibernate;
import it.unibas.freesbee.ge.persistenza.hibernate.DAOPubblicatoreInternoHibernate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class FiltroPubblicatoreEsterno implements Serializable, IFiltroPubblicatore {

    private long id;
    private PubblicatoreEsterno pubblicatore;
    private SottoscrizioneEsterna sottoscrizione;

    public FiltroPubblicatoreEsterno() {
    }

    public FiltroPubblicatoreEsterno(PubblicatoreEsterno pubblicatore, SottoscrizioneEsterna sottoscrizione) {
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
    public PubblicatoreEsterno getPubblicatore() {
        return pubblicatore;
    }

    public void setPubblicatore(PubblicatoreEsterno publbicatore) {
        this.pubblicatore = publbicatore;
    }

    @XmlTransient
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    public SottoscrizioneEsterna getSottoscrizione() {
        return sottoscrizione;
    }

    public void setSottoscrizione(SottoscrizioneEsterna sottoscrizione) {
        this.sottoscrizione = sottoscrizione;
    }

    public static List<FiltroPubblicatoreEsterno> binding(String bodyMessaggio, CategoriaEventiEsterna categoriaEventiEsterna, SottoscrizioneEsterna sottoscrizioneEsterna) throws Exception {
        IDAOPubblicatoreEsterno daoPubblicatoreEsterno = new DAOPubblicatoreEsternoHibernate();
        IDAOPubblicatoreInterno daoPubblicatoreInterno = new DAOPubblicatoreInternoHibernate();
        IDAOCategoriaEventiEsterna daoCategoriaEventiEsterna = new DAOCategoriaEventiEsternaHibernate();
        List<FiltroPubblicatoreEsterno> listaFiltroPubblicatore = new ArrayList<FiltroPubblicatoreEsterno>();
        FiltroPubblicatoreEsterno filtroPubblicatoreEsterno = null;

        Document doc = XMLUtils.parse(bodyMessaggio);
        NodeList nodi = doc.getDocumentElement().getElementsByTagName("pubblicatore");
        Node nodo = null;

        for (int i = 0; i < nodi.getLength(); i++) {
            nodo = nodi.item(i);

            filtroPubblicatoreEsterno = new FiltroPubblicatoreEsterno();
            PubblicatoreEsterno soggetto = PubblicatoreEsterno.binding(XMLUtils.toString(nodo));

            if (daoPubblicatoreInterno.findByTipoNome(soggetto.getTipo(), soggetto.getNome()) != null) {
                throw new Exception("Esiste già un pubblicatore " + soggetto + " ed e' di tipo interno. Non e' possibile registrare un pubblicatore esterno con lo stesso nome");
            }

            //Cerco fra i pubblicatori esterni
            PubblicatoreEsterno pubblicatore = daoPubblicatoreEsterno.findByTipoNome(soggetto.getTipo(), soggetto.getNome());

            if (pubblicatore == null) {
                //Il pubblicatore esterno non esiste lo aggiungo
                //Non specifico se è esterno
                pubblicatore = new PubblicatoreEsterno();
                pubblicatore.setTipo(soggetto.getTipo());
                pubblicatore.setNome(soggetto.getNome());
                daoPubblicatoreEsterno.makePersistent(pubblicatore);
            }

            //Verifico se è pubblicatore per la categoria
            if (!daoCategoriaEventiEsterna.existsPubblicatoreEsterno(categoriaEventiEsterna, pubblicatore)) {
                //la sottoscrizione è in attesa solo se il soggetto non è pubblicatore
                long scadenzaAttesa = new Date().getTime();
                scadenzaAttesa += 1000 * 3600 * 24; //SETTO COME SCADENZA DOMANI
                sottoscrizioneEsterna.setScadenzaAttesa(new Date(scadenzaAttesa));
            }

            filtroPubblicatoreEsterno.setPubblicatore(pubblicatore);
            filtroPubblicatoreEsterno.setSottoscrizione(sottoscrizioneEsterna);
            listaFiltroPubblicatore.add(filtroPubblicatoreEsterno);
        }

        return listaFiltroPubblicatore;
    }
}
