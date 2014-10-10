package it.unibas.freesbee.websla.vista;

import it.unibas.freesbee.websla.modello.WebServizio;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaMonitoraggioStatoServiziErogati {

    private Log logger = LogFactory.getLog(this.getClass());
    private transient UIData tabellaServizi;
    private UITabPanel tabPanelServizi;
    private List<WebServizio> listaServizi;
    private String nomeParametroSLA;
    private Date dataFineOsservazione;
    private String urlInvio;
      private boolean verificato = false;

    public VistaMonitoraggioStatoServiziErogati() {
        this.ripulisci();
    }

    public void ripulisci() {

        this.verificato = false;

    }
    public Date getDataFineOsservazione() {
        return dataFineOsservazione;
    }

    public void setDataFineOsservazione(Date dataFineOsservazione) {
        this.dataFineOsservazione = dataFineOsservazione;
    }

  
    public List<WebServizio> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<WebServizio> listaServizi) {
        this.listaServizi = listaServizi;
    }

    public String getNomeParametroSLA() {
        return nomeParametroSLA;
    }

    public void setNomeParametroSLA(String nomeParametroSLA) {
        this.nomeParametroSLA = nomeParametroSLA;
    }


    public UITabPanel getTabPanelServizi() {
        return tabPanelServizi;
    }

    public void setTabPanelServizi(UITabPanel tabPanelServizi) {
        this.tabPanelServizi = tabPanelServizi;
    }

    public UIData getTabellaServizi() {
        return tabellaServizi;
    }

    public void setTabellaServizi(UIData tabellaServizi) {
        this.tabellaServizi = tabellaServizi;
    }


    public String getUrlInvio() {
        return urlInvio;
    }


    public void setUrlInvio(String urlInvio) {
        this.urlInvio = urlInvio;
    }


    public boolean isVerificato() {
        return verificato;
    }


    public void setVerificato(boolean verificato) {
        this.verificato = verificato;
    }


}
