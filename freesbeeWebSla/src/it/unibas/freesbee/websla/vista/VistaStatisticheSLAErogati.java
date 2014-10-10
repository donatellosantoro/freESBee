package it.unibas.freesbee.websla.vista;

import it.unibas.freesbee.client.cxf.sistemamonitoraggio.GuaranteeTermObj;
import it.unibas.freesbee.websla.modello.WebServizio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaStatisticheSLAErogati {

    private Log logger = LogFactory.getLog(this.getClass());
    private WebServizio webServizio = new WebServizio();
    private transient UIData tabellaServizi;
    private transient UIData tabellaSLA;
    private boolean pannelloVisibile = false;
    private boolean visualizzaGrafico = false;
    private boolean tabellaAggiungiSLAVisibile = false;
    private UITabPanel tabPanelServizi;
    private UITabPanel tabPanelSLA;
    private List<WebServizio> listaServizi;
    private List<GuaranteeTermObj> listaSlaAggiunti = new ArrayList<GuaranteeTermObj>();
    private List<SelectItem> listaParametriSLA = new ArrayList<SelectItem>();
    private List<SelectItem> listaStep = new ArrayList<SelectItem>();
    private String nomeParametroSLA;
    private String nomeStep;
    private Date dataInizioOsservazione;
    private Date dataFineOsservazione;
    private long tempoInMillisecondi;

    public VistaStatisticheSLAErogati() {
        this.ripulisci();
    }

    public void ripulisci() {
        this.pannelloVisibile = false;
        this.visualizzaGrafico = false;
        this.tabellaAggiungiSLAVisibile = false;
        this.listaStep = new ArrayList<SelectItem>();
        this.listaStep.add(new SelectItem(""));
        this.listaStep.add(new SelectItem("YEAR", "YEAR"));
        this.listaStep.add(new SelectItem("SIXMONTHS", "SIX MONTHS"));
        this.listaStep.add(new SelectItem("THREEMONTHS", "THREE MONTHS"));
        this.listaStep.add(new SelectItem("MONTH", "MONTH"));
        this.listaStep.add(new SelectItem("TWOWEEKS", "TWO WEEKS"));
        this.listaStep.add(new SelectItem("WEEK", "WEEK"));
        this.listaStep.add(new SelectItem("DAY", "DAY"));
        this.listaStep.add(new SelectItem("SIXHOURS", "SIX HOURS"));
        this.listaStep.add(new SelectItem("HOUR", "HOUR"));
    }

    public boolean isPannelloVisibile() {
        return pannelloVisibile;
    }

    public void setPannelloVisibile(boolean pannelloVisibile) {
        this.pannelloVisibile = pannelloVisibile;
    }

    public boolean isVisualizzaGrafico() {
        return visualizzaGrafico;
    }

    public void setVisualizzaGrafico(boolean visualizzaGrafico) {
        this.visualizzaGrafico = visualizzaGrafico;
    }

    public boolean isTabellaAggiungiSLAVisibile() {
        return tabellaAggiungiSLAVisibile;
    }

    public void setTabellaAggiungiSLAVisibile(boolean tabellaAggiungiSLAVisibile) {
        this.tabellaAggiungiSLAVisibile = tabellaAggiungiSLAVisibile;
    }

    public List<WebServizio> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<WebServizio> listaServizi) {
        this.listaServizi = listaServizi;
    }

    public List<GuaranteeTermObj> getListaSlaAggiunti() {
        return listaSlaAggiunti;
    }

    public void setListaSlaAggiunti(List<GuaranteeTermObj> listaSlaAggiunti) {
        this.listaSlaAggiunti = listaSlaAggiunti;
    }

    public List<SelectItem> getListaParametriSLA() {
        return listaParametriSLA;
    }

    public void setListaParametriSLA(List<String> lista) {
        this.listaParametriSLA = new ArrayList<SelectItem>();
        this.listaParametriSLA.add(new SelectItem(""));
        if (lista == null) {
            return;
        }
        Collections.sort(lista);
        for (String nomeParametro : lista) {
            this.listaParametriSLA.add(new SelectItem(nomeParametro, nomeParametro));
        }
    }

    public List<SelectItem> getListaStep() {
        return listaStep;
    }

    public WebServizio getWebServizio() {
        return webServizio;
    }

    public void setWebServizio(WebServizio webServizio) {
        this.webServizio = webServizio;
    }

    public UITabPanel getTabPanelServizi() {
        return tabPanelServizi;
    }

    public void setTabPanelServizi(UITabPanel tabPanelServizi) {
        this.tabPanelServizi = tabPanelServizi;
    }

    public UITabPanel getTabPanelSLA() {
        return tabPanelSLA;
    }

    public void setTabPanelSLA(UITabPanel tabPanelSLA) {
        this.tabPanelSLA = tabPanelSLA;
    }

    public UIData getTabellaServizi() {
        return tabellaServizi;
    }

    public UIData getTabellaSLA() {
        return tabellaSLA;
    }

    public void setTabellaSLA(UIData tabellaSLA) {
        this.tabellaSLA = tabellaSLA;
    }

    public void setTabellaServizi(UIData tabellaServizi) {
        this.tabellaServizi = tabellaServizi;
    }

    public String getNomeParametroSLA() {
        return nomeParametroSLA;
    }

    public void setNomeParametroSLA(String nomeParametroSLA) {
        this.nomeParametroSLA = nomeParametroSLA;
    }

    public String getNomeStep() {
        return nomeStep;
    }

    public void setNomeStep(String nomeStep) {
        this.nomeStep = nomeStep;
    }

    public Date getDataFineOsservazione() {
        return dataFineOsservazione;
    }

    public void setDataFineOsservazione(Date dataFineOsservazione) {
        this.dataFineOsservazione = dataFineOsservazione;
    }

    public Date getDataInizioOsservazione() {
        return dataInizioOsservazione;
    }

    public void setDataInizioOsservazione(Date dataInizioOsservazione) {
        this.dataInizioOsservazione = dataInizioOsservazione;
    }

    public long getTempoInMillisecondi() {
        return tempoInMillisecondi;
    }

    public void setTempoInMillisecondi(long tempoInMillisecondi) {
        this.tempoInMillisecondi = tempoInMillisecondi;
    }
}
