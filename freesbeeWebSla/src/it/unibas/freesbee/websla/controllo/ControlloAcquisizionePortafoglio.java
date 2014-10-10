package it.unibas.freesbee.websla.controllo;


import it.unibas.freesbee.websla.vista.VistaAcquisizionePortafoglio;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ControlloAcquisizionePortafoglio {

    private Log logger = LogFactory.getLog(this.getClass());
    private String messaggio;
    private String errore;
    private VistaAcquisizionePortafoglio vista;

    public String acquisisciPortafoglio() {
        return null;

    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String errore) {
        this.errore = errore;
    }

    public VistaAcquisizionePortafoglio getVista() {
        return vista;
    }

    public void setVista(VistaAcquisizionePortafoglio vista) {
        this.vista = vista;
    }

    public boolean isVisualizzaErrore() {
        return (this.errore != null && !this.errore.equalsIgnoreCase(""));
    }
}
