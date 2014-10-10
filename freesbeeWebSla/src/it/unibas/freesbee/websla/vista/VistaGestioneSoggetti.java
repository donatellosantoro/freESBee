package it.unibas.freesbee.websla.vista;

import it.unibas.icar.freesbee.modello.Soggetto;
import java.util.List;
import javax.faces.component.UIData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UITabPanel;

public class VistaGestioneSoggetti {

    private Log logger = LogFactory.getLog(this.getClass());
    private transient UIData tabellaSoggettiNICA;
    private List<Soggetto> listaSoggettiNICA;
    private transient UIData tabellaSoggettiINF2;
    private List<Soggetto> listaSoggettiINF2;
    private UITabPanel tabPanelNICA;
    private UITabPanel tabPanelINF2;
    
    public VistaGestioneSoggetti() {
    }
    
    public UIData getTabellaSoggettiNICA() {
        return tabellaSoggettiNICA;
    }

    public void setTabellaSoggettiNICA(UIData tabellaSoggettiNICA) {
        this.tabellaSoggettiNICA = tabellaSoggettiNICA;
    }

    public List<Soggetto> getListaSoggettiNICA() {
        return listaSoggettiNICA;
    }

    public void setListaSoggettiNICA(List<Soggetto> listaSoggettiNICA) {
        this.listaSoggettiNICA = listaSoggettiNICA;
    }

    public List<Soggetto> getListaSoggettiINF2() {
        return listaSoggettiINF2;
    }

    public void setListaSoggettiINF2(List<Soggetto> listaSoggettiINF2) {
        this.listaSoggettiINF2 = listaSoggettiINF2;
    }

    public UIData getTabellaSoggettiINF2() {
        return tabellaSoggettiINF2;
    }

    public void setTabellaSoggettiINF2(UIData tabellaSoggettiINF2) {
        this.tabellaSoggettiINF2 = tabellaSoggettiINF2;
    }

    public UITabPanel getTabPanelINF2() {
        return tabPanelINF2;
    }

    public void setTabPanelINF2(UITabPanel tabPanelINF2) {
        this.tabPanelINF2 = tabPanelINF2;
    }

    public UITabPanel getTabPanelNICA() {
        return tabPanelNICA;
    }

    public void setTabPanelNICA(UITabPanel tabPanelNICA) {
        this.tabPanelNICA = tabPanelNICA;
    }
    
}
