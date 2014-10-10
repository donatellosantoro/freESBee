package it.unibas.freesbee.websla.vista;

import it.unibas.freesbee.websla.modello.WebSla;
import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.modello.WebServizioFruito;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaMonitoraggioSLAServiziFruiti {

    private Log logger = LogFactory.getLog(this.getClass());
    //Servizio selezionato
    private WebServizioFruito webServizioSelezionato = new WebServizioFruito();
    //Sla selezionato
    private WebSla webSLASelezionato = new WebSla();
    //ModuloSla selezionato
    private String soggettoSlaSelezionato = "";
    private List<SelectItem> listaItem = new ArrayList<SelectItem>(); //Lista moduliSLA
    //Tabelle
    private transient UIData tabellaServizi;
    private transient UIData tabellaSLA;
    private UITabPanel tabPanelServizi;
    private UITabPanel tabPanelSLA;
    private List<WebServizio> listaServizi;
    private List<WebSla> listaSlaAggiunti = new ArrayList<WebSla>();
    //Visibilità pannelli
    private boolean tabellaSLAAggiuntiVisibile;
    private boolean tabellaServizioVisibile;
    //Attivazione tasti
    private boolean aggiungi;
    private boolean aggiungiServizio;
    private boolean verificato = false;
    //Dati da acquisire
    private List<SelectItem> listaParametriSLA = new ArrayList<SelectItem>();
    private String nomeParametroSLA;
    private Date dataFineOsservazione;
    private String portaDelegataMonitoraggioSLA;
    private boolean tipo = true;
    private String urlInvio;

    public VistaMonitoraggioSLAServiziFruiti() {
        this.ripulisci();
    }

    public void ripulisci() {
        
        verificato = false;
        aggiungi = true;
        aggiungiServizio = false;
        tabellaSLAAggiuntiVisibile = false;
        tabellaServizioVisibile = false;
        listaSlaAggiunti = new ArrayList<WebSla>();
        portaDelegataMonitoraggioSLA = "";
        tipo = true;
        nomeParametroSLA = "";
        dataFineOsservazione = null;
    }

    public boolean getTabellaSLAAggiuntiVisibile() {
        return tabellaSLAAggiuntiVisibile;
    }

    public void setTabellaSLAAggiuntiVisibile(boolean tabellaSLAAggiuntiVisibile) {
        this.tabellaSLAAggiuntiVisibile = tabellaSLAAggiuntiVisibile;
    }

    public List<WebServizio> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<WebServizio> listaServizi) {
        this.listaServizi = listaServizi;
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

    public Date getDataFineOsservazione() {
        return dataFineOsservazione;
    }

    public void setDataFineOsservazione(Date dataFineOsservazione) {
        this.dataFineOsservazione = dataFineOsservazione;
    }

    public List<WebSla> getListaSlaAggiunti() {
        return listaSlaAggiunti;
    }

    public void setListaSlaAggiunti(List<WebSla> listaSlaAggiunti) {
        this.listaSlaAggiunti = listaSlaAggiunti;
    }

    public boolean getVerificato() {
        return verificato;
    }

    public void setVerificato(boolean verificato) {
        this.verificato = verificato;
    }

    public String getPortaDelegataMonitoraggioSLA() {
        return portaDelegataMonitoraggioSLA;
    }

    public void setPortaDelegataMonitoraggioSLA(String portaDelegataMonitoraggioSLA) {
        this.portaDelegataMonitoraggioSLA = portaDelegataMonitoraggioSLA;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public WebServizioFruito getWebServizioSelezionato() {
        return webServizioSelezionato;
    }

    public void setWebServizioSelezionato(WebServizioFruito webServizioSelezionato) {
        this.webServizioSelezionato = webServizioSelezionato;
    }

    public boolean isAggiungiServizio() {
        return aggiungiServizio;
    }

    public void setAggiungiServizio(boolean aggiungiServizio) {
        this.aggiungiServizio = aggiungiServizio;
    }

    public boolean isTabellaServizioVisibile() {
        return tabellaServizioVisibile;
    }

    public void setTabellaServizioVisibile(boolean tabellaServizioVisibile) {
        this.tabellaServizioVisibile = tabellaServizioVisibile;
    }

    public boolean isAggiungi() {
        return aggiungi;
    }

    public void setAggiungi(boolean aggiungi) {
        this.aggiungi = aggiungi;
    }

    public //Sla selezionato
            WebSla getWebSLASelezionato() {
        return webSLASelezionato;
    }

    public void setWebSLASelezionato(WebSla webSLASelezionato) {
        this.webSLASelezionato = webSLASelezionato;
    }


    public String getUrlInvio() {
        return urlInvio;
    }


    public void setUrlInvio(String urlInvio) {
        this.urlInvio = urlInvio;
    }


    public String getSoggettoSlaSelezionato() {
        return soggettoSlaSelezionato;
    }


    public void setSoggettoSlaSelezionato(String soggettoSlaSelezionato) {
        this.soggettoSlaSelezionato = soggettoSlaSelezionato;
    }


    public List<SelectItem> getListaItem() {
        return listaItem;
    }

    public void setListaItem(List<SelectItem> listaItem) {
        this.listaItem = listaItem;
    }
}
