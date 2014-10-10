package it.unibas.freesbee.ge.web.vista;

import it.unibas.freesbee.ge.web.utilita.Utilita;
import it.unibas.freesbee.ge.web.ws.stub.CategoriaEventiEsterna;
import it.unibas.freesbee.ge.web.ws.stub.PubblicatoreEsterno;
import it.unibas.freesbee.ge.web.ws.stub.SottoscrizioneEsterna;
import java.util.List;
import javax.faces.component.UIData;
import org.richfaces.component.UITabPanel;

public class VistaCategoriaEventiEsterna {

    private transient UIData tabellaCategoriaEventi;
    private transient UIData tabellaPubblicatore;
    private transient UIData tabellaPubblicatoreFiltro;
    private transient UIData tabellaSottoscrizione;
    private List<CategoriaEventiEsterna> listaCategoriaEventi;
    private List<PubblicatoreEsterno> listaPubblicatore;
    private List<PubblicatoreEsterno> listaPubblicatoreFiltro;
    private List<SottoscrizioneEsterna> listaSottoscrizione;
    private UITabPanel tabPanel;
    private UITabPanel tabPanelPubblicatore;
    private UITabPanel tabPanelPubblicatoreFiltro;
    private UITabPanel tabPanelSottoscrizione;
    private CategoriaEventiEsterna categoriaEventiSelezionata = new CategoriaEventiEsterna();
    private SottoscrizioneEsterna sottoscrizioneSelezionata = new SottoscrizioneEsterna();
    private boolean elimina = false;
    private boolean pubblicatori = false;
    private boolean sottoscrizioni = false;
    private boolean dettagli = false;
    private String dataI;
    private String dataF;
    private String dataScadenza;
       private String nomeNuovaCategoria;


    public VistaCategoriaEventiEsterna() {
    }

    public void ripulisci() {
        setCategoriaEventiSelezionata(new CategoriaEventiEsterna());
        sottoscrizioneSelezionata = new SottoscrizioneEsterna();
        elimina = false;
        pubblicatori = false;
        sottoscrizioni = false;
        dettagli = false;
        nomeNuovaCategoria = "";
    }

    public List<CategoriaEventiEsterna> getListaCategoriaEventi() {
        return listaCategoriaEventi;
    }

    public void setListaCategoriaEventi(List<CategoriaEventiEsterna> lista) {
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

    public CategoriaEventiEsterna getCategoriaEventiSelezionata() {
        return categoriaEventiSelezionata;
    }

    public void setCategoriaEventiSelezionata(CategoriaEventiEsterna categoriaEventiSelezionata) {
        this.categoriaEventiSelezionata = categoriaEventiSelezionata;
    }

    public UIData getTabellaPubblicatore() {
        return tabellaPubblicatore;
    }

    public void setTabellaPubblicatore(UIData tabellaPubblicatore) {
        this.tabellaPubblicatore = tabellaPubblicatore;
    }

    public List<PubblicatoreEsterno> getListaPubblicatore() {
        return listaPubblicatore;
    }

    public void setListaPubblicatore(List<PubblicatoreEsterno> listaPubblicatore) {
        this.listaPubblicatore = listaPubblicatore;
    }

    public UITabPanel getTabPanelPubblicatore() {
        return tabPanelPubblicatore;
    }

    public void setTabPanelPubblicatore(UITabPanel tabPanelPubblicatore) {
        this.tabPanelPubblicatore = tabPanelPubblicatore;
    }

    public boolean isPubblicatori() {
        return pubblicatori;
    }

    public void setPubblicatori(boolean pubblicatori) {
        this.pubblicatori = pubblicatori;
    }

    public UIData getTabellaSottoscrizione() {
        return tabellaSottoscrizione;
    }

    public void setTabellaSottoscrizione(UIData tabellaSottoscrizione) {
        this.tabellaSottoscrizione = tabellaSottoscrizione;
    }

    public List<SottoscrizioneEsterna> getListaSottoscrizione() {
        return listaSottoscrizione;
    }

    public void setListaSottoscrizione(List<SottoscrizioneEsterna> listaSottoscrizione) {
        this.listaSottoscrizione = listaSottoscrizione;
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

    public SottoscrizioneEsterna getSottoscrizioneSelezionata() {
        return sottoscrizioneSelezionata;
    }

    public void setSottoscrizioneSelezionata(SottoscrizioneEsterna sottoscrizioneSelezionata) {
        this.sottoscrizioneSelezionata = sottoscrizioneSelezionata;
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
        if (sottoscrizioneSelezionata.getFiltroData().getDataFine() != null) {
            return Utilita.formattaDataEOraMinutiSecondiMillisecondi(Utilita.convertiXMLGregorianCalendarToDate(sottoscrizioneSelezionata.getFiltroData().getDataFine()));
        }
        return "";
    }

    public void setDataF(String dataF) {
        this.dataF = dataF;
    }

    public UIData getTabellaPubblicatoreFiltro() {
        return tabellaPubblicatoreFiltro;
    }

    public void setTabellaPubblicatoreFiltro(UIData tabellaPubblicatoreFiltro) {
        this.tabellaPubblicatoreFiltro = tabellaPubblicatoreFiltro;
    }

    public List<PubblicatoreEsterno> getListaPubblicatoreFiltro() {
        return listaPubblicatoreFiltro;
    }

    public void setListaPubblicatoreFiltro(List<PubblicatoreEsterno> listaPubblicatoreFiltro) {
        this.listaPubblicatoreFiltro = listaPubblicatoreFiltro;
    }

    public UITabPanel getTabPanelPubblicatoreFiltro() {
        return tabPanelPubblicatoreFiltro;
    }

    public void setTabPanelPubblicatoreFiltro(UITabPanel tabPanelPubblicatoreFiltro) {
        this.tabPanelPubblicatoreFiltro = tabPanelPubblicatoreFiltro;
    }

    public String getDataScadenza() {
        if (sottoscrizioneSelezionata.getScadenzaAttesa() != null) {
            return Utilita.formattaDataEOraMinutiSecondiMillisecondi(Utilita.convertiXMLGregorianCalendarToDate(sottoscrizioneSelezionata.getScadenzaAttesa()));
        }
        return "";

    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getNomeNuovaCategoria() {
        return nomeNuovaCategoria;
    }

    public void setNomeNuovaCategoria(String nomeNuovaCategoria) {
        this.nomeNuovaCategoria = nomeNuovaCategoria;
    }
}
