package it.unibas.icar.updatefreesbee;

import it.unibas.icar.updatefreesbee.controllo.AzioneAggiorna;
import it.unibas.icar.updatefreesbee.controllo.AzioneEsci;
import it.unibas.icar.updatefreesbee.controllo.AzioneInfo;
import it.unibas.icar.updatefreesbee.controllo.AzioneSelezionaCartellaFileConfig;
import it.unibas.icar.updatefreesbee.controllo.AzioneSelezionaCartellaTemporanea;
import it.unibas.icar.updatefreesbee.controllo.AzioneSelezionaCartellaWar;
import it.unibas.icar.updatefreesbee.controllo.AzioneSelezionaWebApp;
import it.unibas.icar.updatefreesbee.vista.PannelloPrincipale;

public class Costanti {
    
//    public static final String TEMPLATE_FOLDER = "./varie/risorse/templateFolder/";

    public static final String PANNELLO_PRINCIPALE = PannelloPrincipale.class.getName();
    
    // STATI
    public static final String STATO_INIZIALIZZAZIONE = "Inizializzazione";
    public static final String STATO_INIZIALIZZAZIONE_FINE = "Fine inizializzazione";
    public static final String STATO_COPIA_INIZIO = "Copia file iniziata";
    public static final String STATO_COPIA_FINE = "Copia file finita";
    public static final String STATO_FILE_CONFIG_CARICATI = "File di configurazione caricati";
    public static final String STATO_WAR_AGGIORNATI = "War di freESBee aggiornati";
    public static final String STATO_WAR_INIZIO = "Inizio aggiornamento freESBee";
    public static final String STATO_TERMINATO = "Aggiornamento completato";
    
    // AZIONI
    public static final String AZIONE_SELEZIONA_WEBAPP = AzioneSelezionaWebApp.class.getName();
    public static final String AZIONE_SELEZIONA_CARTELLA_TEMP = AzioneSelezionaCartellaTemporanea.class.getName();
    public static final String AZIONE_SELEZIONA_CARTELLA_FILE_CONFIG = AzioneSelezionaCartellaFileConfig.class.getName();
    public static final String AZIONE_SELEZIONA_CARTELLA_FILE_WAR = AzioneSelezionaCartellaWar.class.getName();
    public static final String AZIONE_AGGIORNA = AzioneAggiorna.class.getName();
    public static final String AZIONE_ESCI = AzioneEsci.class.getName();
    public static final String AZIONE_INFO = AzioneInfo.class.getName();
    
}
