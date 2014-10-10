package it.unibas.freesbee.ge.web.vista;

import it.unibas.freesbee.ge.web.ws.stub.GestoreEventi;
import java.util.List;
import javax.faces.component.UIData;
import org.richfaces.component.UITabPanel;

public class VistaGestoreEventi {

    private transient UIData tabellaGestoreEventi;
    private List<GestoreEventi> listaGestoreEventi;
    private UITabPanel tabPanel;
    private GestoreEventi gestoreEventiSelezionato = new GestoreEventi();
    private boolean elimina = false;

    public VistaGestoreEventi() {
    }

    public void ripulisci() {
        gestoreEventiSelezionato = new GestoreEventi();
        elimina = false;
    }

    public UITabPanel getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(UITabPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        this.elimina = elimina;
    }

    public UIData getTabellaGestoreEventi() {
        return tabellaGestoreEventi;
    }

    public void setTabellaGestoreEventi(UIData tabellaGestoreEventi) {
        this.tabellaGestoreEventi = tabellaGestoreEventi;
    }

    public List<GestoreEventi> getListaGestoreEventi() {
        return listaGestoreEventi;
    }

    public void setListaGestoreEventi(List<GestoreEventi> listaGestoreEventi) {
        this.listaGestoreEventi = listaGestoreEventi;
    }

    public GestoreEventi getGestoreEventiSelezionato() {
        return gestoreEventiSelezionato;
    }

    public void setGestoreEventiSelezionato(GestoreEventi gestoreEventiSelezionato) {
        this.gestoreEventiSelezionato = gestoreEventiSelezionato;
    }
}
