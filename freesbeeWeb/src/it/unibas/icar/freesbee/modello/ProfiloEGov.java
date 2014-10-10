package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.Date;

public class ProfiloEGov implements Serializable{
    
    private long id;
    private boolean gestioneDuplicati = true;
    private boolean confermaRicezione;
    private boolean idCollaborazione;
    private boolean consegnaInOrdine;
    private Date scadenza;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isGestioneDuplicati() {
        return gestioneDuplicati;
    }

    public void setGestioneDuplicati(boolean gestioneDuplicati) {
        this.gestioneDuplicati = gestioneDuplicati;
    }

    public boolean isConfermaRicezione() {
        return confermaRicezione;
    }

    public void setConfermaRicezione(boolean confermaRicezione) {
        this.confermaRicezione = confermaRicezione;
    }

    public boolean isIdCollaborazione() {
        return idCollaborazione;
    }

    public void setIdCollaborazione(boolean idCollaborazione) {
        this.idCollaborazione = idCollaborazione;
    }

    public boolean isConsegnaInOrdine() {
        return consegnaInOrdine;
    }

    public void setConsegnaInOrdine(boolean consegnaInOrdine) {
        this.consegnaInOrdine = consegnaInOrdine;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }
}
