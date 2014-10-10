package it.unibas.freesbee.ge.web.vista;

import it.unibas.freesbee.ge.web.utilita.Utilita;
import it.unibas.freesbee.ge.web.ws.stub.CategoriaEventiInterna;
import it.unibas.freesbee.ge.web.ws.stub.PubblicatoreInterno;
import it.unibas.freesbee.ge.web.ws.stub.SottoscrizioneInterna;
import java.util.List;
import javax.faces.component.UIData;
import org.richfaces.component.UITabPanel;

public class VistaCategoriaEventiInterna {

    private transient UIData tabellaCategoriaEventi;
    private transient UIData tabellaPubblicatore;
    private transient UIData tabellaPubblicatoreFiltro;
    private transient UIData tabellaSottoscrizione;
    private List<CategoriaEventiInterna> listaCategoriaEventi;
    private List<PubblicatoreInterno> listaPubblicatore;
    private List<PubblicatoreInterno> listaPubblicatoreFiltro;
    private List<SottoscrizioneInterna> listaSottoscrizione;
    private UITabPanel tabPanel;
    private UITabPanel tabPanelPubblicatore;
    private UITabPanel tabPanelPubblicatoreFiltro;
    private UITabPanel tabPanelSottoscrizione;
    private CategoriaEventiInterna categoriaEventiSelezionata = new CategoriaEventiInterna();
    private SottoscrizioneInterna sottoscrizioneSelezionata = new SottoscrizioneInterna();
    private boolean elimina = false;
    private boolean pubblicatori = false;
    private boolean sottoscrizioni = false;
    private boolean dettagli = false;
    private String dataI;
    private String dataF;
    private String nomeNuovaCategoria;

    public VistaCategoriaEventiInterna() {
    }

    public void ripulisci() {
        setCategoriaEventiSelezionata(new CategoriaEventiInterna());
        sottoscrizioneSelezionata = new SottoscrizioneInterna();
        elimina = false;
        pubblicatori = false;
        sottoscrizioni = false;
        dettagli = false;
        nomeNuovaCategoria = "";
    }

    public List<CategoriaEventiInterna> getListaCategoriaEventi() {
        return listaCategoriaEventi;
    }

    public void setListaCategoriaEventi(List<CategoriaEventiInterna> lista) {
        this.listaCategoriaEventi = lista;
    }

    public UIData getTabellaCategoriaEventi() {
        return tabellaCategoriaEventi;
    }

    public void setTabellaCategoriaEventi(UIData tabellaCategoriaEventi) {
        this.tabellaCategoriaEventi = tabellaCategoriaEventi;
    }

    public UITabPanel getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(UITabPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        this.elimina = elimina;
    }

    public CategoriaEventiInterna getCategoriaEventiSelezionata() {
        return categoriaEventiSelezionata;
    }

    public void setCategoriaEventiSelezionata(CategoriaEventiInterna categoriaEventiSelezionata) {
        this.categoriaEventiSelezionata = categoriaEventiSelezionata;
    }

    public boolean isPubblicatori() {
        return pubblicatori;
    }

    public void setPubblicatori(boolean pubblicatori) {
        this.pubblicatori = pubblicatori;
    }

    public UIData getTabellaPubblicatore() {
        return tabellaPubblicatore;
    }

    public void setTabellaPubblicatore(UIData tabellaPubblicatore) {
        this.tabellaPubblicatore = tabellaPubblicatore;
    }

    public List<PubblicatoreInterno> getListaPubblicatore() {
        return listaPubblicatore;
    }

    public void setListaPubblicatore(List<PubblicatoreInterno> listaPubblicatore) {
        this.listaPubblicatore = listaPubblicatore;
    }

    public UITabPanel getTabPanelPubblicatore() {
        return tabPanelPubblicatore;
    }

    public void setTabPanelPubblicatore(UITabPanel tabPanelPubblicatore) {
        this.tabPanelPubblicatore = tabPanelPubblicatore;
    }

    public UIData getTabellaSottoscrizione() {
        return tabellaSottoscrizione;
    }

    public void setTabellaSottoscrizione(UIData tabellaSottoscrizione) {
        this.tabellaSottoscrizione = tabellaSottoscrizione;
    }

    public UITabPanel getTabPanelSottoscrizione() {
        return tabPanelSottoscrizione;
    }

    public void setTabPanelSottoscrizione(UITabPanel tabPanelSottoscrizione) {
        this.tabPanelSottoscrizione = tabPanelSottoscrizione;
    }

    public boolean isSottoscrizioni() {
        return sottoscrizioni;
    }

    public void setSottoscrizioni(boolean sottoscrizioni) {
        this.sottoscrizioni = sottoscrizioni;
    }

    public List<SottoscrizioneInterna> getListaSottoscrizione() {
        return listaSottoscrizione;
    }

    public void setListaSottoscrizione(List<SottoscrizioneInterna> listaSottoscrizione) {
        this.listaSottoscrizione = listaSottoscrizione;
    }

    public UIData getTabellaPubblicatoreFiltro() {
        return tabellaPubblicatoreFiltro;
    }

    public void setTabellaPubblicatoreFiltro(UIData tabellaPubblicatoreFiltro) {
        this.tabellaPubblicatoreFiltro = tabellaPubblicatoreFiltro;
    }

    public List<PubblicatoreInterno> getListaPubblicatoreFiltro() {
        return listaPubblicatoreFiltro;
    }

    public void setListaPubblicatoreFiltro(List<PubblicatoreInterno> listaPubblicatoreFiltro) {
        this.listaPubblicatoreFiltro = listaPubblicatoreFiltro;
    }

    public UITabPanel getTabPanelPubblicatoreFiltro() {
        return tabPanelPubblicatoreFiltro;
    }

    public void setTabPanelPubblicatoreFiltro(UITabPanel tabPanelPubblicatoreFiltro) {
        this.tabPanelPubblicatoreFiltro = tabPanelPubblicatoreFiltro;
    }

    public boolean isDettagli() {
        return dettagli;
    }

    public void setDettagli(boolean dettagli) {
        this.dettagli = dettagli;
    }

    public String getDataI() {
        return Utilita.formattaDataEOraMinutiSecondiMillisecondi(Utilita.convertiXMLGregorianCalendarToDate(sottoscrizioneSelezionata.getFiltroData().getDataInizio()));
    }

    public void setDataI(String dataI) {
        this.dataI = dataI;
    }

    public String getDataF() {
        if (getSottoscrizioneSelezionata().getFiltroData().getDataFine() != null) {
            return Utilita.formattaDataEOraMinutiSecondiMillisecondi(Utilita.convertiXMLGregorianCalendarToDate(sottoscrizioneSelezionata.getFiltroData().getDataFine()));
        }
        return "";

    }

    public void setDataF(String dataF) {
        this.dataF = dataF;
    }

    public SottoscrizioneInterna getSottoscrizioneSelezionata() {
        return sottoscrizioneSelezionata;
    }

    public void setSottoscrizioneSelezionata(SottoscrizioneInterna sottoscrizioneSelezionata) {
        this.sottoscrizioneSelezionata = sottoscrizioneSelezionata;
    }

    public String getNomeNuovaCategoria() {
        return nomeNuovaCategoria;
    }

    public void setNomeNuovaCategoria(String nomeNuovaCategoria) {
        this.nomeNuovaCategoria = nomeNuovaCategoria;
    }
}
