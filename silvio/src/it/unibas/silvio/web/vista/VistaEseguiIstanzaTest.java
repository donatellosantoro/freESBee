package it.unibas.silvio.web.vista;

import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaOperation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public class VistaEseguiIstanzaTest implements Serializable {

    private IstanzaAccordoDiCollaborazione istanzaAccordoSelezionata = new IstanzaAccordoDiCollaborazione();
    private IstanzaOperation istanzaOperationSelezionata = new IstanzaOperation();
    private Map<String, IstanzaOperation> mappaIdentificatore = new HashMap<String, IstanzaOperation>();
    private List<SelectItem> listaItem = new ArrayList<SelectItem>(); //Lista istanze
    private List<SelectItem> listaItemOperation = new ArrayList<SelectItem>(); //Lista IstanzeOperation
    private String istanzaSelezionata;
    private String operationSelezionata;
    private String urlInvio;
    private String urlAscolto;
    private String numeroUtenti;
    private String numeroRichieste;
    private String secondiAttesa = "3";
    private String correlazione;
    private boolean invia = false;
    private boolean conferma = true;
    private boolean secondoPasso;
    private boolean terzoPasso;
    private boolean visualizzaModalPanel;
    private String testResult;

    public Map<String, IstanzaOperation> getMappaIdentificatore() {
        return mappaIdentificatore;
    }

    public void setMappaIdentificatore(Map<String, IstanzaOperation> mappaIdentificatore) {
        this.mappaIdentificatore = mappaIdentificatore;
    }

    public List<SelectItem> getListaItem() {
        return listaItem;
    }

    public void setListaItem(List<SelectItem> listaItem) {
        this.listaItem = listaItem;
    }

    public List<SelectItem> getListaItemOperation() {
        return listaItemOperation;
    }

    public void setListaItemOperation(List<SelectItem> listaItemOperation) {
        this.listaItemOperation = listaItemOperation;
    }

    public String getIstanzaSelezionata() {
        return istanzaSelezionata;
    }

    public void setIstanzaSelezionata(String istanzaSelezionata) {
        this.istanzaSelezionata = istanzaSelezionata;
    }

    public String getOperationSelezionata() {
        return operationSelezionata;
    }

    public void setOperationSelezionata(String operationSelezionata) {
        this.operationSelezionata = operationSelezionata;
    }

    public IstanzaAccordoDiCollaborazione getIstanzaAccordoSelezionata() {
        return istanzaAccordoSelezionata;
    }

    public void setIstanzaAccordoSelezionata(IstanzaAccordoDiCollaborazione istanzaAccordoSelezionata) {
        this.istanzaAccordoSelezionata = istanzaAccordoSelezionata;
    }

    public IstanzaOperation getIstanzaOperationSelezionata() {
        return istanzaOperationSelezionata;
    }

    public void setIstanzaOperationSelezionata(IstanzaOperation istanzaOperationSelezionata) {
        this.istanzaOperationSelezionata = istanzaOperationSelezionata;
    }

    public String getNumeroUtenti() {
        return numeroUtenti;
    }

    public void setNumeroUtenti(String numeroUtenti) {
        this.numeroUtenti = numeroUtenti;
    }

    public String getUrlInvio() {
        return urlInvio;
    }

    public void setUrlInvio(String urlInvio) {
        this.urlInvio = urlInvio;
    }

    public String getUrlAscolto() {
        return urlAscolto;
    }

    public void setUrlAscolto(String urlAscolto) {
        this.urlAscolto = urlAscolto;
    }

    public String getNumeroRichieste() {
        return numeroRichieste;
    }

    public void setNumeroRichieste(String numeroRichieste) {
        this.numeroRichieste = numeroRichieste;
    }

    public String getCorrelazione() {
        return correlazione;
    }

    public void setCorrelazione(String correlazione) {
        this.correlazione = correlazione;
    }

    public boolean isSecondoPasso() {
        return secondoPasso;
    }

    public void setSecondoPasso(boolean secondoPasso) {
        this.secondoPasso = secondoPasso;
    }

    public boolean isTerzoPasso() {
        return terzoPasso;
    }

    public void setTerzoPasso(boolean terzoPasso) {
        this.terzoPasso = terzoPasso;
    }

    public boolean isInvia() {
        return invia;
    }

    public void setInvia(boolean invia) {
        this.invia = invia;
    }

    public boolean isConferma() {
        return conferma;
    }

    public void setConferma(boolean conferma) {
        this.conferma = conferma;
    }

    public String getSecondiAttesa() {
        return secondiAttesa;
    }

    public void setSecondiAttesa(String secondiAttesa) {
        this.secondiAttesa = secondiAttesa;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public boolean isVisualizzaModalPanel() {
        return visualizzaModalPanel;
    }

    public void setVisualizzaModalPanel(boolean visualizzaModalPanel) {
        this.visualizzaModalPanel = visualizzaModalPanel;
    }
    
}
