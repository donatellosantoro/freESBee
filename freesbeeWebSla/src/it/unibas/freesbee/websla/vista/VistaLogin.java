package it.unibas.freesbee.websla.vista;

import it.unibas.freesbee.websla.modello.ConfigurazioneStatico;
import it.unibas.freesbee.websla.modello.Utente;
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

    public String getIndirizzoModuloSLA() {
        return ConfigurazioneStatico.getInstance().getIndirizzoModuloSLA();
    }

    public void setIndirizzoModuloSLA(String server) {
        ConfigurazioneStatico.getInstance().setIndirizzoModuloSLA(server);
    }
}
