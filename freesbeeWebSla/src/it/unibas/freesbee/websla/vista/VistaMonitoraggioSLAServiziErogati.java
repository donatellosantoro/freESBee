package it.unibas.freesbee.websla.vista;

import it.unibas.freesbee.websla.modello.WebSla;
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

public class VistaMonitoraggioSLAServiziErogati {

    private Log logger = LogFactory.getLog(this.getClass());
    private WebServizio webServizio = new WebServizio();
    private transient UIData tabellaServizi;
    private transient UIData tabellaSLA;
    private boolean aggiungi;
    private boolean pannelloVisibile;
    private boolean verificato = false;
    private boolean tabellaSLAAggiuntiVisibile;
    private UITabPanel tabPanelServizi;
    private UITabPanel tabPanelSLA;
    private List<WebServizio> listaServizi;
    private List<WebSla> listaSlaAggiunti = new ArrayList<WebSla>();
    private List<SelectItem> listaParametriSLA = new ArrayList<SelectItem>();
    private String nomeParametroSLA;
    private Date dataFineOsservazione;
        private String urlInvio;

    public VistaMonitoraggioSLAServiziErogati() {
        this.ripulisci();
    }

    public void ripulisci() {
        pannelloVisibile = false;
        verificato = false;
        tabellaSLAAggiuntiVisibile = false;
    }

    public boolean isAggiungi() {
        if (listaParametriSLA.size() < 2) {
            return true;
        }
        return aggiungi;
    }

    public void setAggiungi(boolean aggiungi) {
        if (aggiungi) {
            setPannelloVisibile(true);
        }
        this.aggiungi = aggiungi;
    }

    public boolean isPannelloVisibile() {
        return pannelloVisibile;
    }

    public void setPannelloVisibile(boolean pannelloVisibile) {
        this.pannelloVisibile = pannelloVisibile;
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


    public String getUrlInvio() {
        return urlInvio;
    }

  
    public void setUrlInvio(String urlInvio) {
        this.urlInvio = urlInvio;
    }

}
