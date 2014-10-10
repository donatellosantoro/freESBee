package it.unibas.icar.freesbeesp.vista;

import it.unibas.icar.freesbeesp.modello.Istanza;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.richfaces.component.UIDataGrid;

public class VistaSchermoConfigurazione implements Serializable{
    
   private String uriAscolto;
   private String accordoServizio;
   private String risorsa;
   private List<Istanza> listaIstanza = new ArrayList<Istanza>();
   private UIDataGrid listaIstanzaGrid;
   private boolean visualizzaIstanze = false;
   private boolean visualizzaPannelloAggiungi = false;
   private boolean visualizzaRegistroServizi = false;
   private boolean elimina = false;
   private Istanza istanzaEliminare;
   private String indirizzoRegistroServizi;

    public String getAccordoServizio() {
        return accordoServizio;
    }

    public void setAccordoServizio(String accordoServizio) {
        this.accordoServizio = accordoServizio;
    }

    public String getRisorsa() {
        return risorsa;
    }

    public void setRisorsa(String risorsa) {
        this.risorsa = risorsa;
    }

    public String getUriAscolto() {
        return uriAscolto;
    }

    public void setUriAscolto(String uriAscolto) {
        this.uriAscolto = uriAscolto;
    }

    public List<Istanza> getListaIstanza() {
        return listaIstanza;
    }

    public void setListaIstanza(List<Istanza> listaIstanza) {
        this.listaIstanza = listaIstanza;
    }

    public UIDataGrid getListaIstanzaGrid() {
        return listaIstanzaGrid;
    }

    public void setListaIstanzaGrid(UIDataGrid listaIstanzaGrid) {
        this.listaIstanzaGrid = listaIstanzaGrid;
    }

    public boolean isVisualizzaIstanze() {
        return visualizzaIstanze;
    }

    public void setVisualizzaIstanze(boolean visualizzaIstanze) {
        this.visualizzaIstanze = visualizzaIstanze;
    }

    public boolean isVisualizzaPannelloAggiungi() {
        return visualizzaPannelloAggiungi;
    }

    public void setVisualizzaPannelloAggiungi(boolean visualizzaPannelloAggiungi) {
        this.visualizzaPannelloAggiungi = visualizzaPannelloAggiungi;
    }

    public boolean isElimina() {
        return elimina;
    }

    public void setElimina(boolean elimina) {
        this.elimina = elimina;
    }

    public Istanza getIstanzaEliminare() {
        return istanzaEliminare;
    }

    public void setIstanzaEliminare(Istanza istanzaEliminare) {
        this.istanzaEliminare = istanzaEliminare;
    }

    public String getIndirizzoRegistroServizi() {
        return indirizzoRegistroServizi;
    }

    public void setIndirizzoRegistroServizi(String indirizzoRegistroServizi) {
        this.indirizzoRegistroServizi = indirizzoRegistroServizi;
    }

    public boolean isVisualizzaRegistroServizi() {
        return visualizzaRegistroServizi;
    }

    public void setVisualizzaRegistroServizi(boolean visualizzaRegistroServizi) {
        this.visualizzaRegistroServizi = visualizzaRegistroServizi;
    }

    
        
}
