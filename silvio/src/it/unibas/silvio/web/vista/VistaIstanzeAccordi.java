package it.unibas.silvio.web.vista;

import it.unibas.silvio.modello.AccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaAccordoDiCollaborazione;
import it.unibas.silvio.modello.IstanzaOperation;
import it.unibas.silvio.modello.IstanzaPortType;
import it.unibas.silvio.modello.Operation;
import it.unibas.silvio.web.vista.pm.DatiPM;
import it.unibas.silvio.web.vista.pm.IstanzaAccordoDiCollaborazionePM;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIInput;
import javax.faces.model.SelectItem;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.richfaces.component.UIDataGrid;
import org.richfaces.component.html.HtmlTree;

public class VistaIstanzeAccordi implements Serializable {

    private int step = 1;
    private int stepErogatore = 1;
    private boolean secondoPasso;
    private boolean terzoPasso;
    private boolean quartoPasso;
    private boolean quintoPasso;
    private AccordoDiCollaborazione accordoDiCollaborazione = new AccordoDiCollaborazione();
    private IstanzaAccordoDiCollaborazione istanzaAccordo = new IstanzaAccordoDiCollaborazione();
    private IstanzaAccordoDiCollaborazione istanzaAccordoEliminare;
    private IstanzaPortType istanzaPortTypeModificare;
    private UIInput istanzaPortTypeSelezionato;
    private IstanzaOperation istanzaOperation = new IstanzaOperation();
    private List<SelectItem> listaItem = new ArrayList<SelectItem>();
    private List<SelectItem> listaItemOperation = new ArrayList<SelectItem>();
    private String accordoSelezionato;
    private String sceltaRuolo;
    private Map<String, Operation> mappaIdentificatore = new HashMap<String, Operation>();
    private List<String> listaOperationSelezionate = new ArrayList<String>();
    private List<Operation> operationSelezionate = new ArrayList<Operation>();
    private List<IstanzaAccordoDiCollaborazione> listaIstanze;
    private List<IstanzaAccordoDiCollaborazionePM> listaIstanzePM;
    private UIDataGrid listaIstanzeAccordiCollaborazioneGrid;
    private Operation operationConfigurare;
    private int operationConfigurareInt;
    private boolean elimina;
    private boolean elaboraRichiesta;
    private boolean eliminaDatoCostante;
    private boolean eliminaDatoInterattivo;
    private boolean visualizzaXSD;
    private UploadedFile fileXSLTFruitoreRichiesta;
    private UploadedFile fileXSLTFruitoreRisposta;
    private UploadedFile fileXSLTErogatoreRichiesta;
    private UploadedFile fileXSLTErogatoreRisposta;
    private UploadedFile fileXSLTErogatoreRichiestaInsert;
    private String indirizzoAscoltoErogatoreAsincrono;
    private String tipoRisposta = "AUTOMATICA";
    private DatiPM datiPM;
    private String nomeRoot;
    private boolean visualizzaSorgenteRichiesta;
    private boolean visualizzaModalPanelURLInvio;
    private String indirizzoSLA;
    private boolean aggiornaSLA;
    private boolean elaboraRichiestaMail;
    private UploadedFile fileXSLTErogatoreMailRichiesta;
    private String indirizzoMail;
    private String oggettoMail;

    public String getOggettoMail() {
        return oggettoMail;
    }

    public void setOggettoMail(String oggettoMail) {
        this.oggettoMail = oggettoMail;
    }

    public String getIndirizzoMail() {
        return indirizzoMail;
    }

    public void setIndirizzoMail(String indirizzoMail) {
        this.indirizzoMail = indirizzoMail;
    }

    public UploadedFile getFileXSLTErogatoreMailRichiesta() {
        return fileXSLTErogatoreMailRichiesta;
    }

    public void setFileXSLTErogatoreMailRichiesta(UploadedFile fileXSLTErogatoreMailRichiesta) {
        this.fileXSLTErogatoreMailRichiesta = fileXSLTErogatoreMailRichiesta;
    }

    public boolean isElaboraRichiestaMail() {
        return elaboraRichiestaMail;
    }

    public void setElaboraRichiestaMail(boolean elaboraRichiestaMail) {
        this.elaboraRichiestaMail = elaboraRichiestaMail;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public List<SelectItem> getListaItem() {
        return listaItem;
    }

    public void setListaItem(List<SelectItem> listaItem) {
        this.listaItem = listaItem;
    }

    public String getAccordoSelezionato() {
        return accordoSelezionato;
    }

    public void setAccordoSelezionato(String accordoSelezionato) {
        this.accordoSelezionato = accordoSelezionato;
    }

    public List<SelectItem> getListaItemOperation() {
        return listaItemOperation;
    }

    public void setListaItemOperation(List<SelectItem> listaItemOperation) {
        this.listaItemOperation = listaItemOperation;
    }

    public Map<String, Operation> getMappaIdentificatore() {
        return mappaIdentificatore;
    }

    public void setMappaIdentificatore(Map<String, Operation> mappaIdentificatore) {
        this.mappaIdentificatore = mappaIdentificatore;
    }

    public List<String> getListaOperationSelezionate() {
        return listaOperationSelezionate;
    }

    public void setListaOperationSelezionate(List<String> listaOperationSelezionate) {
        this.listaOperationSelezionate = listaOperationSelezionate;
    }

    public String getSceltaRuolo() {
        return sceltaRuolo;
    }

    public void setSceltaRuolo(String sceltaRuolo) {
        this.sceltaRuolo = sceltaRuolo;
    }

    public List<Operation> getOperationSelezionate() {
        return operationSelezionate;
    }

    public void setOperationSelezionate(List<Operation> operationSelezionate) {
        this.operationSelezionate = operationSelezionate;
    }

    public Operation getOperationConfigurare() {
        return operationConfigurare;
    }

    public void setOperationConfigurare(Operation operationConfigurare) {
        this.operationConfigurare = operationConfigurare;
    }

    public int getOperationConfigurareInt() {
        return operationConfigurareInt;
    }

    public void setOperationConfigurareInt(int operationConfigurareInt) {
        this.operationConfigurareInt = operationConfigurareInt;
    }

    public DatiPM getDatiPM() {
        return datiPM;
    }

    public void setDatiPM(DatiPM datiPM) {
        this.datiPM = datiPM;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        this.elimina = elimina;
    }

    public UploadedFile getFileXSLTFruitoreRichiesta() {
        return fileXSLTFruitoreRichiesta;
    }

    public void setFileXSLTFruitoreRichiesta(UploadedFile fileXSLTFruitoreRichiesta) {
        this.fileXSLTFruitoreRichiesta = fileXSLTFruitoreRichiesta;
    }

    public UploadedFile getFileXSLTErogatoreRichiestaInsert() {
        return fileXSLTErogatoreRichiestaInsert;
    }

    public void setFileXSLTErogatoreRichiestaInsert(UploadedFile fileXSLTErogatoreRichiestaInsert) {
        this.fileXSLTErogatoreRichiestaInsert = fileXSLTErogatoreRichiestaInsert;
    }

    public IstanzaAccordoDiCollaborazione getIstanzaAccordo() {
        return istanzaAccordo;
    }

    public void setIstanzaAccordo(IstanzaAccordoDiCollaborazione istanzaAccordo) {
        this.istanzaAccordo = istanzaAccordo;
    }

    public boolean isVisualizzaXSD() {
        return visualizzaXSD;
    }

    public void setVisualizzaXSD(boolean visualizzaXSD) {
        this.visualizzaXSD = visualizzaXSD;
    }

    public AccordoDiCollaborazione getAccordoDiCollaborazione() {
        return accordoDiCollaborazione;
    }

    public void setAccordoDiCollaborazione(AccordoDiCollaborazione accordoDiCollaborazione) {
        this.accordoDiCollaborazione = accordoDiCollaborazione;
    }

    public IstanzaOperation getIstanzaOperation() {
        return istanzaOperation;
    }

    public void setIstanzaOperation(IstanzaOperation istanzaOperation) {
        this.istanzaOperation = istanzaOperation;
    }

    public UploadedFile getFileXSLTFruitoreRisposta() {
        return fileXSLTFruitoreRisposta;
    }

    public void setFileXSLTFruitoreRisposta(UploadedFile fileXSLTFruitoreRisposta) {
        this.fileXSLTFruitoreRisposta = fileXSLTFruitoreRisposta;
    }

    public String getRiepilogoIstanza() {
        return this.getIstanzaAccordo().toString();
    }

    public boolean isEliminaDatoCostante() {
        return eliminaDatoCostante;
    }

    public void setEliminaDatoCostante(boolean eliminaDatoCostante) {
        this.eliminaDatoCostante = eliminaDatoCostante;
    }

    public boolean isEliminaDatoInterattivo() {
        return eliminaDatoInterattivo;
    }

    public void setEliminaDatoInterattivo(boolean eliminaDatoInterattivo) {
        this.eliminaDatoInterattivo = eliminaDatoInterattivo;
    }

    public IstanzaAccordoDiCollaborazionePM getIstanzaAccordoPM() {
        return new IstanzaAccordoDiCollaborazionePM(istanzaAccordo, "");
    }

    public void setIstanzaAccordoPM(IstanzaAccordoDiCollaborazionePM istanzaAccordoPM) {
    }

    public IstanzaAccordoDiCollaborazione getIstanzaAccordoEliminare() {
        return istanzaAccordoEliminare;
    }

    public void setIstanzaAccordoEliminare(IstanzaAccordoDiCollaborazione istanzaAccordoEliminare) {
        this.istanzaAccordoEliminare = istanzaAccordoEliminare;
    }

    public List<IstanzaAccordoDiCollaborazione> getListaIstanze() {
        return listaIstanze;
    }

    public void setListaIstanze(List<IstanzaAccordoDiCollaborazione> listaIstanze) {
        this.listaIstanze = listaIstanze;
    }

    public List<IstanzaAccordoDiCollaborazionePM> getListaIstanzePM() {
        return listaIstanzePM;
    }

    public void setListaIstanzePM(List<IstanzaAccordoDiCollaborazionePM> listaIstanzePM) {
        this.listaIstanzePM = listaIstanzePM;
    }

    public UIDataGrid getListaIstanzeAccordiCollaborazioneGrid() {
        return listaIstanzeAccordiCollaborazioneGrid;
    }

    public void setListaIstanzeAccordiCollaborazioneGrid(UIDataGrid listaIstanzeAccordiCollaborazioneGrid) {
        this.listaIstanzeAccordiCollaborazioneGrid = listaIstanzeAccordiCollaborazioneGrid;
    }

    /* EROGATORE */
    public int getStepErogatore() {
        return stepErogatore;
    }

    public void setStepErogatore(int stepErogatore) {
        this.stepErogatore = stepErogatore;
    }

    public boolean isElaboraRichiesta() {
        return elaboraRichiesta;
    }

    public void setElaboraRichiesta(boolean elaboraRichiesta) {
        this.elaboraRichiesta = elaboraRichiesta;
    }

    public UploadedFile getFileXSLTErogatoreRichiesta() {
        return fileXSLTErogatoreRichiesta;
    }

    public void setFileXSLTErogatoreRichiesta(UploadedFile fileXSLTErogatoreRichiesta) {
        this.fileXSLTErogatoreRichiesta = fileXSLTErogatoreRichiesta;
    }

    public UploadedFile getFileXSLTErogatoreRisposta() {
        return fileXSLTErogatoreRisposta;
    }

    public void setFileXSLTErogatoreRisposta(UploadedFile fileXSLTErogatoreRisposta) {
        this.fileXSLTErogatoreRisposta = fileXSLTErogatoreRisposta;
    }

    public String getIndirizzoAscoltoErogatoreAsincrono() {
        return indirizzoAscoltoErogatoreAsincrono;
    }

    public void setIndirizzoAscoltoErogatoreAsincrono(String indirizzoAscoltoErogatoreAsincrono) {
        this.indirizzoAscoltoErogatoreAsincrono = indirizzoAscoltoErogatoreAsincrono;
    }

    public String getTipoRisposta() {
        return tipoRisposta;
    }

    public void setTipoRisposta(String tipoRisposta) {
        this.tipoRisposta = tipoRisposta;
    }

    public boolean isQuartoPasso() {
        return quartoPasso;
    }

    public void setQuartoPasso(boolean quartoPasso) {
        this.quartoPasso = quartoPasso;
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

    public boolean isQuintoPasso() {
        return quintoPasso;
    }

    public void setQuintoPasso(boolean quintoPasso) {
        this.quintoPasso = quintoPasso;
    }

    public String getNomeRoot() {
        return nomeRoot;
    }

    public void setNomeRoot(String nomeRoot) {
        this.nomeRoot = nomeRoot;
    }

    public boolean isVisualizzaSorgenteRichiesta() {
        return visualizzaSorgenteRichiesta;
    }

    public void setVisualizzaSorgenteRichiesta(boolean visualizzaSorgenteRichiesta) {
        this.visualizzaSorgenteRichiesta = visualizzaSorgenteRichiesta;
    }

    public boolean isVisualizzaModalPanelURLInvio() {
        return visualizzaModalPanelURLInvio;
    }

    public void setVisualizzaModalPanelURLInvio(boolean visualizzaModalPanelURLInvio) {
        this.visualizzaModalPanelURLInvio = visualizzaModalPanelURLInvio;
    }

    public IstanzaPortType getIstanzaPortTypeModificare() {
        return istanzaPortTypeModificare;
    }

    public void setIstanzaPortTypeModificare(IstanzaPortType istanzaPortTypeModificare) {
        this.istanzaPortTypeModificare = istanzaPortTypeModificare;
    }

    public UIInput getIstanzaPortTypeSelezionato() {
        return istanzaPortTypeSelezionato;
    }

    public void setIstanzaPortTypeSelezionato(UIInput istanzaPortTypeSelezionato) {
        this.istanzaPortTypeSelezionato = istanzaPortTypeSelezionato;
    }

    public String getIndirizzoSLA() {
        return indirizzoSLA;
    }

    public void setIndirizzoSLA(String indirizzoSLA) {
        this.indirizzoSLA = indirizzoSLA;
    }

    public boolean isAggiornaSLA() {
        return aggiornaSLA;
    }

    public void setAggiornaSLA(boolean aggiornaSLA) {
        this.aggiornaSLA = aggiornaSLA;
    }
}