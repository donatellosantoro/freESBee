package it.unibas.freesbee.websla.vista;

import it.unibas.freesbee.websla.modello.WebServizio;
import it.unibas.freesbee.websla.modello.WebServizioFruito;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaMonitoraggioStatoServiziFruiti {

    private Log logger = LogFactory.getLog(this.getClass());

    //Servizio selezionato
    private WebServizioFruito webServizioSelezionato = new WebServizioFruito();
    //ModuloSla selezionato
    private String soggettoSlaSelezionato = "";
    private List<SelectItem> listaItem = new ArrayList<SelectItem>(); //Lista moduliSLA

    //Tabelle
    private transient UIData tabellaServizi;
    private transient UIData tabellaServiziAggiunti;
    private UITabPanel tabPanelServizi;
    private UITabPanel tabPanelServiziAggiunti;
    private List<WebServizio> listaServizi;
    private List<WebServizioFruito> listaServiziAggiunti;
    //Visibilità pannelli
    private boolean tabellaServizioVisibile;
    private boolean tabellaServiziAggiuntiVisibile;
    //Attivazione tasti
    private boolean verificato = false;
    private boolean aggiungi = false;
    //Dati da acquisire
    private String portaDelegataMonitoraggioStato;
    private boolean tipo = true;
    private String urlInvio;

    public VistaMonitoraggioStatoServiziFruiti() {
        ripulisci();
    }

    public void ripulisci() {
        this.tabellaServizioVisibile = false;
        this.tabellaServiziAggiuntiVisibile = false;
        this.listaServiziAggiunti = new ArrayList<WebServizioFruito>();
        this.aggiungi = false;
        this.verificato = false;
        this.portaDelegataMonitoraggioStato = "";
        this.tipo = true;
    }

    public UIData getTabellaServizi() {
        return tabellaServizi;
    }

    public void setTabellaServizi(UIData tabellaServizi) {
        this.tabellaServizi = tabellaServizi;
    }

    public UIData getTabellaServiziAggiunti() {
        return tabellaServiziAggiunti;
    }

    public void setTabellaServiziAggiunti(UIData tabellaServiziAggiunti) {
        this.tabellaServiziAggiunti = tabellaServiziAggiunti;
    }

    public boolean isAggiungi() {
        return aggiungi;
    }

    public void setAggiungi(boolean aggiungi) {
        this.aggiungi = aggiungi;
    }

    public boolean isTabellaServiziAggiuntiVisibile() {
        return tabellaServiziAggiuntiVisibile;
    }

    public void setTabellaServiziAggiuntiVisibile(boolean tabellaServiziAggiuntiVisibile) {
        this.tabellaServiziAggiuntiVisibile = tabellaServiziAggiuntiVisibile;
    }

    public boolean isVerificato() {
        return verificato;
    }

    public void setVerificato(boolean verificato) {
        this.verificato = verificato;
    }

    public UITabPanel getTabPanelServizi() {
        return tabPanelServizi;
    }

    public void setTabPanelServizi(UITabPanel tabPanelServizi) {
        this.tabPanelServizi = tabPanelServizi;
    }

    public UITabPanel getTabPanelServiziAggiunti() {
        return tabPanelServiziAggiunti;
    }

    public void setTabPanelServiziAggiunti(UITabPanel tabPanelServiziAggiunti) {
        this.tabPanelServiziAggiunti = tabPanelServiziAggiunti;
    }

    public List<WebServizio> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<WebServizio> listaServizi) {
        this.listaServizi = listaServizi;
    }

    public List<WebServizioFruito> getListaServiziAggiunti() {
        return listaServiziAggiunti;
    }

    public void setListaServiziAggiunti(List<WebServizioFruito> listaServiziAggiunti) {
        this.listaServiziAggiunti = listaServiziAggiunti;
    }

    public String getPortaDelegataMonitoraggioStato() {
        return portaDelegataMonitoraggioStato;
    }

    public void setPortaDelegataMonitoraggioStato(String portaDelegataMonitoraggioStato) {
        this.portaDelegataMonitoraggioStato = portaDelegataMonitoraggioStato;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public boolean isTabellaServizioVisibile() {
        return tabellaServizioVisibile;
    }

    public void setTabellaServizioVisibile(boolean tabellaServizioVisibile) {
        this.tabellaServizioVisibile = tabellaServizioVisibile;
    }

    public WebServizioFruito getWebServizioSelezionato() {
        return webServizioSelezionato;
    }

    public void setWebServizioSelezionato(WebServizioFruito webServizioSelezionato) {
        this.webServizioSelezionato = webServizioSelezionato;
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
