package it.unibas.freesbee.websla.modello;

import java.io.Serializable;

public class Modello implements Serializable {

    private Utente utente;

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    public void removeUtente(){
        this.utente = null;
    }

}


