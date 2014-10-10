package it.unibas.freesbee.ge.web.vista;

import it.unibas.freesbee.ge.web.modello.ConfigurazioneStatico;
import it.unibas.freesbee.ge.web.modello.Utente;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VistaLogin {

    private Log logger = LogFactory.getLog(this.getClass());
    private Utente utente = new Utente();
    
    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getIndirizzoGE() {
        return ConfigurazioneStatico.getInstance().getIndirizzoGE();
    }

    public void setIndirizzoGE(String server) {
        ConfigurazioneStatico.getInstance().setIndirizzoGE(server);
    }
}
