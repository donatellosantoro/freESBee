package it.unibas.icar.updatefreesbee.modello;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.persistenza.LoadConfig;
import it.unibas.icar.updatefreesbee.persistenza.PersistenceException;
import it.unibas.icar.updatefreesbee.persistenza.UpdateWar;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Updater extends Observable {

    private String percorsoWebApps;
    private String percorsoFileTemporanei;
    private String percorsoFileConfig;
    private String percorsoVecchiWars;
    private boolean salvaConfig;
    private boolean salvaWar;
    private List<File> listaWarDaAggiornare = new ArrayList<File>();
    private int warAggiornati = 0;
    private static Log logger = LogFactory.getLog(Updater.class);
    private String errori = "";
    private String stato = "";
    
    public Updater(String percorsoWebApps, String percorsoFileTemporanei, String percorsoFileConfig, String percorsoVecchiWars, boolean salvaConfig, boolean salvaWar) {
        this.percorsoWebApps = percorsoWebApps;
        this.percorsoFileTemporanei = percorsoFileTemporanei;
        this.percorsoFileConfig = percorsoFileConfig;
        this.percorsoVecchiWars = percorsoVecchiWars;
        this.salvaConfig = salvaConfig;
        this.salvaWar = salvaWar;
    }

    public void update() {
        try {
            setStato(Costanti.STATO_INIZIALIZZAZIONE);
            LoadConfig loadConfig = LoadConfig.getInstance();
            loadConfig.loadConfig(percorsoWebApps, percorsoFileTemporanei, percorsoFileConfig, salvaConfig);
            listaWarDaAggiornare = loadConfig.getListaFreesbeeWar();
            setStato(Costanti.STATO_INIZIALIZZAZIONE_FINE);
            if (salvaWar) {
                salvaFileWars();
            }
            UpdateWar updateWar = new UpdateWar();
            setStato(Costanti.STATO_WAR_INIZIO);
            updateWar.update(percorsoWebApps, percorsoFileTemporanei, this);
            setStato(Costanti.STATO_WAR_AGGIORNATI);
            eseguiOperazioniConfig();
        } catch (PersistenceException ex) {
            logger.error("Errore: " + ex);
            errori += "Errore nell'esecuzione dell'aggiornamento. Controllare che i war siano ancora tutti nella cartella webapps e riprovare. Altrimenti controllare che esista qualche copia nelle cartelle di configurazione\n";
        }
    }

    public void setStato(String stato) {
        this.stato = stato;
        setChanged();
        notifyObservers();
    }

    private void salvaFileWars() {
        setStato(Costanti.STATO_COPIA_INIZIO);
        try {
            // salvo config
            for (File war : listaWarDaAggiornare) {
                FileUtils.copyFileToDirectory(war, new File(percorsoVecchiWars));
            }
        } catch (IOException ex) {
            logger.error("Errore nella copia dei file war: " + ex);
            errori += "Errore nella copia dei file war dalla cartella webapps alla cartella: " + percorsoVecchiWars + "\n";

        }
        incrementaPercentuale();
        setStato(Costanti.STATO_COPIA_FINE);
    }

    public void incrementaPercentuale() {
        warAggiornati++;
        setChanged();
        notifyObservers();
    }

    public String getPercentuale() {
        double percentuale = (warAggiornati / listaWarDaAggiornare.size()) * 100;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(percentuale);
    }

    private void eseguiOperazioniConfig() {
        // cancellare le cartelle che non servono
        try {
            if (!salvaConfig && !salvaWar) {
                if (percorsoFileConfig.equals(percorsoVecchiWars)) {
                    FileUtils.deleteDirectory(new File(percorsoFileConfig));
                } else {
                    FileUtils.deleteDirectory(new File(percorsoFileConfig));
                    FileUtils.deleteDirectory(new File(percorsoVecchiWars));
                }
            } else {
                if (!salvaConfig) {
                    if (!percorsoFileConfig.equals(percorsoVecchiWars)) {
                        FileUtils.deleteDirectory(new File(percorsoFileConfig));
                    }
                }
                if (!salvaWar) {
                    if (!percorsoVecchiWars.equals(percorsoFileConfig)) {
                        FileUtils.deleteDirectory(new File(percorsoVecchiWars));
                    }
                }
            }
            File tempDir = new File(percorsoFileTemporanei);
            if (tempDir.exists()) {
                FileUtils.deleteDirectory(tempDir);
            }
        } catch (IOException iOException) {
            logger.error("Errore nel cancellamento di qualche file: " + iOException);
            errori += "Errore nel cancellare i file\n";
        }
        incrementaPercentuale();
        setStato(Costanti.STATO_TERMINATO);
    }

    public String getErrori() {
        return errori;
    }

    public String getStato() {
        return stato;
    }
}
