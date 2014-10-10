package it.unibas.silvio.web.vista;

import it.unibas.silvio.modello.MessaggioSbloccoManuale;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIData;

public class VistaSbloccoAsincrono implements Serializable{
    
    private UIData tabellaMessaggi;
    private List<MessaggioSbloccoManuale> listaMessaggi;
    private boolean visualizzaMessaggio;
    private MessaggioSbloccoManuale messaggio;

    public MessaggioSbloccoManuale getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(MessaggioSbloccoManuale messaggio) {
        this.messaggio = messaggio;
    }        

    public boolean isVisualizzaMessaggio() {
        return visualizzaMessaggio;
    }

    public void setVisualizzaMessaggio(boolean visualizzaMessaggio) {
        this.visualizzaMessaggio = visualizzaMessaggio;
    }        
    
    public List getListaMessaggi() {
        return listaMessaggi;
    }

    public void setListaMessaggi(List listaMessaggi) {
        this.listaMessaggi = listaMessaggi;
    }

    public UIData getTabellaMessaggi() {
        return tabellaMessaggi;
    }

    public void setTabellaMessaggi(UIData tabellaMessaggi) {
        this.tabellaMessaggi = tabellaMessaggi;
    }
    
    
}
