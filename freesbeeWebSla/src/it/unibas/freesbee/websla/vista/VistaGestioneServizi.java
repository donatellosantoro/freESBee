package it.unibas.freesbee.websla.vista;

import it.unibas.icar.freesbee.modello.Servizio;
import it.unibas.freesbee.websla.modello.WebServizio;
import java.util.List;
import javax.faces.component.UIData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.richfaces.component.UITabPanel;

public class VistaGestioneServizi {

    private Log logger = LogFactory.getLog(this.getClass());
    private Servizio nuovoServizio = new Servizio();
    private transient UIData tabellaServiziNICA;
    private transient UIData tabellaServiziINF2;
    private UITabPanel tabPanelServiziNICA;
    private UITabPanel tabPanelServiziMonitorati;
    private List<WebServizio> listaServiziNICA;
    private List<WebServizio> listaServiziINF2;
    private UploadedFile fileWSAG;

    public VistaGestioneServizi() {
    }

    public UITabPanel getTabPanelServiziNICA() {
        return tabPanelServiziNICA;
    }

    public void setTabPanelServiziNICA(UITabPanel tabPanelServiziNICA) {
        this.tabPanelServiziNICA = tabPanelServiziNICA;
    }

    public List<WebServizio> getListaServiziNICA() {
        return listaServiziNICA;
    }

    public void setListaServiziNICA(List<WebServizio> listaServiziNICA) {
        this.listaServiziNICA = listaServiziNICA;
    }

    public Servizio getNuovoServizio() {
        return nuovoServizio;
    }

    public void setNuovoServizio(Servizio nuovoServizio) {
        this.nuovoServizio = nuovoServizio;
    }

    public UIData getTabellaServiziNICA() {
        return tabellaServiziNICA;
    }

    public void setTabellaServiziNICA(UIData tabellaServiziNICA) {
        this.tabellaServiziNICA = tabellaServiziNICA;
    }

    public UploadedFile getFileWSAG() {
        return fileWSAG;
    }

    public void setFileWSAG(UploadedFile fileWSAG) {
        this.fileWSAG = fileWSAG;
    }

    public UITabPanel getTabPanelServiziMonitorati() {
        return tabPanelServiziMonitorati;
    }

    public void setTabPanelServiziMonitorati(UITabPanel tabPanelServiziMonitorati) {
        this.tabPanelServiziMonitorati = tabPanelServiziMonitorati;
    }

    public UIData getTabellaServiziINF2() {
        return tabellaServiziINF2;
    }

    public void setTabellaServiziINF2(UIData tabellaServiziINF2) {
        this.tabellaServiziINF2 = tabellaServiziINF2;
    }

    public List<WebServizio> getListaServiziINF2() {
        return listaServiziINF2;
    }

    public void setListaServiziINF2(List<WebServizio> listaServiziINF2) {
        this.listaServiziINF2 = listaServiziINF2;
    }


}
