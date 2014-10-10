package it.unibas.silvio.web.vista;

import it.unibas.silvio.modello.Messaggio;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaVisualizzaMessaggi {

    private Log logger = LogFactory.getLog(this.getClass());
    private List<Messaggio> listaMessaggi;
    private UIData tabellaMessaggi;
    private UITabPanel tabPanelMessaggi;
    private boolean elimina;
    private boolean ricercaFiltro;
    private boolean visualizzaMessaggio;
    private Messaggio messaggio;
    private List<SelectItem> listaTipoMessaggio = new ArrayList<SelectItem>();
    private List<SelectItem> listaIstanza = new ArrayList<SelectItem>();
    private String tipoMessaggio;
    private String tipoIstanza;
    private String idMessaggioReleted;
    private String idMessaggio;

    public List<Messaggio> getListaMessaggi() {
        return listaMessaggi;
    }

    public void setListaMessaggi(List<Messaggio> listaMessaggi) {
        this.listaMessaggi = listaMessaggi;
    }

    public UIData getTabellaMessaggi() {
        return tabellaMessaggi;
    }

    public void setTabellaMessaggi(UIData tabellaMessaggi) {
        this.tabellaMessaggi = tabellaMessaggi;
    }

    public UITabPanel getTabPanelMessaggi() {
        return tabPanelMessaggi;
    }

    public void setTabPanelMessaggi(UITabPanel tabPanelMessaggi) {
        this.tabPanelMessaggi = tabPanelMessaggi;
    }

    public boolean isElimina() {
        return elimina;
    }

    public boolean isVisualizzaMessaggio() {
        return visualizzaMessaggio;
    }

    public void setVisualizzaMessaggio(boolean visualizzaMessaggio) {
        this.visualizzaMessaggio = visualizzaMessaggio;
    }
    
    public void setElimina(boolean elimina) {
        this.elimina = elimina;
    }

    public Messaggio getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(Messaggio messaggio) {
        this.messaggio = messaggio;
    }

    public List<SelectItem> getListaIstanza() {
        return listaIstanza;
    }

    public void setListaIstanza(List<SelectItem> listaIstanza) {
        this.listaIstanza = listaIstanza;
    }

    public List<SelectItem> getListaTipoMessaggio() {
        if(listaTipoMessaggio.isEmpty()){
            listaTipoMessaggio.add(new SelectItem("TUTTI"));
            listaTipoMessaggio.add(new SelectItem("INVIATO"));
            listaTipoMessaggio.add(new SelectItem("RICEVUTO"));
        }
        return listaTipoMessaggio;
    }

    public void setListaTipoMessaggio(List<SelectItem> listaTipoMessaggio) {
        this.listaTipoMessaggio = listaTipoMessaggio;
    }

    public String getTipoMessaggio() {
        return tipoMessaggio;
    }

    public void setTipoMessaggio(String tipoMessaggio) {
        this.tipoMessaggio = tipoMessaggio;
    }

    public String getTipoIstanza() {
        return tipoIstanza;
    }

    public void setTipoIstanza(String tipoIstanza) {
        this.tipoIstanza = tipoIstanza;
    }

    public String getIdMessaggio() {
        return idMessaggio;
    }

    public void setIdMessaggio(String idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public String getIdMessaggioReleted() {
        return idMessaggioReleted;
    }

    public void setIdMessaggioReleted(String idMessaggioReleted) {
        this.idMessaggioReleted = idMessaggioReleted;
    }

    public boolean isRicercaFiltro() {
        return ricercaFiltro;
    }

    public void setRicercaFiltro(boolean ricercaFiltro) {
        this.ricercaFiltro = ricercaFiltro;
    }
    
    
    
}
