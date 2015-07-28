package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.modello.Modello;
import it.unibas.icar.updatefreesbee.vista.Vista;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;

public class Controllo {

    private Modello modello;
    private Vista vista;
    private Map<String, Action> mappaAzioni = new HashMap<String, Action>();

    public Controllo() {
        this.modello = new Modello();
        initAzioni();
        this.vista = new Vista(modello, this);
        
    }

    public Modello getModello() {
        return modello;
    }

    public void setModello(Modello modello) {
        this.modello = modello;
    }

    public Vista getVista() {
        return vista;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }
    
    public void putAzione(String key, Action azione) {
        this.mappaAzioni.put(key, azione);
    }
    
    public Action getAzione(String key) {
        return this.mappaAzioni.get(key);
    }

    private void initAzioni() {
        AzioneSelezionaWebApp selezionaWebApp = new AzioneSelezionaWebApp(this);
        this.mappaAzioni.put(Costanti.AZIONE_SELEZIONA_WEBAPP, selezionaWebApp);
        AzioneSelezionaCartellaTemporanea selezionaCartellaTemporanea = new AzioneSelezionaCartellaTemporanea(this);
        this.mappaAzioni.put(Costanti.AZIONE_SELEZIONA_CARTELLA_TEMP, selezionaCartellaTemporanea);
        AzioneSelezionaCartellaFileConfig selezionaCartellaFileConfig = new AzioneSelezionaCartellaFileConfig(this);
        this.mappaAzioni.put(Costanti.AZIONE_SELEZIONA_CARTELLA_FILE_CONFIG, selezionaCartellaFileConfig);
        AzioneSelezionaCartellaWar selezionaCartellaWar = new AzioneSelezionaCartellaWar(this);
        this.mappaAzioni.put(Costanti.AZIONE_SELEZIONA_CARTELLA_FILE_WAR, selezionaCartellaWar);
        AzioneAggiorna azioneAggiorna = new AzioneAggiorna(this);
        this.mappaAzioni.put(Costanti.AZIONE_AGGIORNA, azioneAggiorna);
        AzioneEsci azioneEsci = new AzioneEsci();
        this.mappaAzioni.put(Costanti.AZIONE_ESCI, azioneEsci);
        AzioneInfo azioneInfo = new AzioneInfo(this);
        this.mappaAzioni.put(Costanti.AZIONE_INFO, azioneInfo);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Controllo();
            }
        });
    }
}
