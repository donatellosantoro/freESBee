package it.unibas.silvio.web.vista;

import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.RispostaEseguiIstanza;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.richfaces.component.UITabPanel;

public class VistaEffettuaLogin implements Serializable {

//    private IstanzaAccordoDiCollaborazione istanzaAccordoSelezionata = new IstanzaAccordoDiCollaborazione();
//    private IstanzaOperation istanzaOperationSelezionata = new IstanzaOperation();
//    private Map<String, IstanzaOperation> mappaIdentificatore = new HashMap<String, IstanzaOperation>();
//    private List<SelectItem> listaItem = new ArrayList<SelectItem>(); //Lista istanze
//    private List<SelectItem> listaItemOperation = new ArrayList<SelectItem>(); //Lista IstanzeOperation
//    private String istanzaSelezionata;
//    private String operationSelezionata;
//    private UITabPanel tabPanelParametriDB;
//    private UIData tabellaParametriDB;
//    private List<DatoPrimitivo> listaParametriDB = new ArrayList<DatoPrimitivo>();
//    private UITabPanel tabPanelParametriInterattivi;
//    private UIData tabellaParametriInterattivi;
//    private List<DatoPrimitivo> listaParametriInterattivi = new ArrayList<DatoPrimitivo>();
//    private boolean visualizzaDatiDB = false;
//    private boolean visualizzaDatiInterattivi = false;
//    private String urlInvio;
//    private String urlAscolto;
//    private String correlazione;
//    private boolean visualizzaRispostaSincrona = false;
//    private RispostaEseguiIstanza rispostaEseguiIstanza;
//    private boolean visualizzaPannelloRispostaSincrona = false;
//    private boolean visualizzaVerificaRispostaAincrona = false;
//    private boolean terzoPasso;
//    private boolean secondoPasso;
//    private boolean conferma = true;
//    private boolean invia = false;
//
//    public Map<String, IstanzaOperation> getMappaIdentificatore() {
//        return mappaIdentificatore;
//    }
//
//    public void setMappaIdentificatore(Map<String, IstanzaOperation> mappaIdentificatore) {
//        this.mappaIdentificatore = mappaIdentificatore;
//    }
//
//    public List<SelectItem> getListaItem() {
//        return listaItem;
//    }
//
//    public void setListaItem(List<SelectItem> listaItem) {
//        this.listaItem = listaItem;
//    }
//
//    public List<SelectItem> getListaItemOperation() {
//        return listaItemOperation;
//    }
//
//    public void setListaItemOperation(List<SelectItem> listaItemOperation) {
//        this.listaItemOperation = listaItemOperation;
//    }
//
//    public String getIstanzaSelezionata() {
//        return istanzaSelezionata;
//    }
//
//    public void setIstanzaSelezionata(String istanzaSelezionata) {
//        this.istanzaSelezionata = istanzaSelezionata;
//    }
//
//    public String getOperationSelezionata() {
//        return operationSelezionata;
//    }
//
//    public void setOperationSelezionata(String operationSelezionata) {
//        this.operationSelezionata = operationSelezionata;
//    }
//
//    public UITabPanel getTabPanelParametriDB() {
//        return tabPanelParametriDB;
//    }
//
//    public void setTabPanelParametriDB(UITabPanel tabPanelParametriDB) {
//        this.tabPanelParametriDB = tabPanelParametriDB;
//    }
//
//    public UIData getTabellaParametriDB() {
//        return tabellaParametriDB;
//    }
//
//    public void setTabellaParametriDB(UIData tabellaParametriDB) {
//        this.tabellaParametriDB = tabellaParametriDB;
//    }
//
//    public List<DatoPrimitivo> getListaParametriDB() {
//        return listaParametriDB;
//    }
//
//    public void setListaParametriDB(List<DatoPrimitivo> listaParametriDB) {
//        this.listaParametriDB = listaParametriDB;
//    }
//
//    public UITabPanel getTabPanelParametriInterattivi() {
//        return tabPanelParametriInterattivi;
//    }
//
//    public void setTabPanelParametriInterattivi(UITabPanel tabPanelParametriInterattivi) {
//        this.tabPanelParametriInterattivi = tabPanelParametriInterattivi;
//    }
//
//    public UIData getTabellaParametriInterattivi() {
//        return tabellaParametriInterattivi;
//    }
//
//    public void setTabellaParametriInterattivi(UIData tabellaParametriInterattivi) {
//        this.tabellaParametriInterattivi = tabellaParametriInterattivi;
//    }
//
//    public List<DatoPrimitivo> getListaParametriInterattivi() {
//        return listaParametriInterattivi;
//    }
//
//    public void setListaParametriInterattivi(List<DatoPrimitivo> listaParametriInterattivi) {
//        this.listaParametriInterattivi = listaParametriInterattivi;
//    }
//
//    public boolean getVisualizzaDatiDB() {
//        return visualizzaDatiDB;
//    }
//
//    public void setVisualizzaDatiDB(boolean visualizzaDatiDB) {
//        this.visualizzaDatiDB = visualizzaDatiDB;
//    }
//
//    public boolean getVisualizzaDatiInterattivi() {
//        return visualizzaDatiInterattivi;
//    }
//
//    public void setVisualizzaDatiInterattivi(boolean visualizzaDatiInterattivi) {
//        this.visualizzaDatiInterattivi = visualizzaDatiInterattivi;
//    }
//
//    public IstanzaAccordoDiCollaborazione getIstanzaAccordoSelezionata() {
//        return istanzaAccordoSelezionata;
//    }
//
//    public void setIstanzaAccordoSelezionata(IstanzaAccordoDiCollaborazione istanzaAccordoSelezionata) {
//        this.istanzaAccordoSelezionata = istanzaAccordoSelezionata;
//    }
//
//    public IstanzaOperation getIstanzaOperationSelezionata() {
//        return istanzaOperationSelezionata;
//    }
//
//    public void setIstanzaOperationSelezionata(IstanzaOperation istanzaOperationSelezionata) {
//        this.istanzaOperationSelezionata = istanzaOperationSelezionata;
//    }
//
//    public String getUrlInvio() {
//        return urlInvio;
//    }
//
//    public void setUrlInvio(String urlInvio) {
//        this.urlInvio = urlInvio;
//    }
//
//    public String getUrlAscolto() {
//        return urlAscolto;
//    }
//
//    public void setUrlAscolto(String urlAscolto) {
//        this.urlAscolto = urlAscolto;
//    }
//
//    public boolean getVisualizzaRispostaSincrona() {
//        return visualizzaRispostaSincrona;
//    }
//
//    public void setVisualizzaRispostaSincrona(boolean visualizzaRispostaSincrona) {
//        this.visualizzaRispostaSincrona = visualizzaRispostaSincrona;
//    }
//
//    public RispostaEseguiIstanza getRispostaEseguiIstanza() {
//        return rispostaEseguiIstanza;
//    }
//
//    public void setRispostaEseguiIstanza(RispostaEseguiIstanza rispostaEseguiIstanza) {
//        this.rispostaEseguiIstanza = rispostaEseguiIstanza;
//    }
//
//    public boolean getVisualizzaPannelloRispostaSincrona() {
//        return visualizzaPannelloRispostaSincrona;
//    }
//
//    public void setVisualizzaPannelloRispostaSincrona(boolean visualizzaPannelloRispostaSincrona) {
//        this.visualizzaPannelloRispostaSincrona = visualizzaPannelloRispostaSincrona;
//    }
//
//    public boolean isVisualizzaVerificaRispostaAincrona() {
//        return visualizzaVerificaRispostaAincrona;
//    }
//
//    public void setVisualizzaVerificaRispostaAincrona(boolean visualizzaVerificaRispostaAincrona) {
//        this.visualizzaVerificaRispostaAincrona = visualizzaVerificaRispostaAincrona;
//    }
//
//    public String getCorrelazione() {
//        return correlazione;
//    }
//
//    public void setCorrelazione(String correlazione) {
//        this.correlazione = correlazione;
//    }
//
//    public boolean isTerzoPasso() {
//        return terzoPasso;
//    }
//
//    public void setTerzoPasso(boolean terzoPasso) {
//        this.terzoPasso = terzoPasso;
//    }
//
//    public boolean isSecondoPasso() {
//        return secondoPasso;
//    }
//
//    public void setSecondoPasso(boolean secondoPasso) {
//        this.secondoPasso = secondoPasso;
//    }
//
//    public boolean isConferma() {
//        return conferma;
//    }
//
//    public void setConferma(boolean conferma) {
//        this.conferma = conferma;
//    }
//
//    public boolean isInvia() {
//        return invia;
//    }
//
//    public void setInvia(boolean invia) {
//        this.invia = invia;
//    }
    
}
