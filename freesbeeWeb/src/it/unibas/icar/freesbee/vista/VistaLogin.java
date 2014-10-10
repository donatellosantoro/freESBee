package it.unibas.icar.freesbee.vista;

import it.unibas.icar.freesbee.modello.Utente;
import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VistaLogin implements Serializable{

    private Log logger = LogFactory.getLog(this.getClass());
    private Utente utente = new Utente();

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

}
