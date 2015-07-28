package it.unibas.icar.updatefreesbee.modello;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.persistenza.LoadConfig;
import it.unibas.icar.updatefreesbee.persistenza.PersistenceException;
import it.unibas.icar.updatefreesbee.persistenza.UpdateWar;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Updater extends  Observable implements IUpdater{

    private String stato;
    private String percorsoWebApps;
    private String persorsoFileTemporanei;
    private String percorsoFileConfig;
    private String percorsoVecchiWars;
    private boolean salvaConfig;
    private boolean salvaWar;
    private List<File> listaWarDaAggiornare = new ArrayList<File>();
    private int warAggiornati = 0;
    private static Log logger = LogFactory.getLog(Updater.class);

    public Updater(String percorsoWebApps, String persorsoFileTemporanei, String percorsoFileConfig, String percorsoVecchiWars, boolean salvaConfig, boolean salvaWar) {
        this.percorsoWebApps = percorsoWebApps;
        this.persorsoFileTemporanei = persorsoFileTemporanei;
        this.percorsoFileConfig = percorsoFileConfig;
        this.percorsoVecchiWars = percorsoVecchiWars;
        this.salvaConfig = salvaConfig;
        this.salvaWar = salvaWar;
        this.stato = Costanti.STATO_INIZIALIZZAZIONE;
    }

//    public double getPercentuale() {
//        if (listaWarDaAggiornare.isEmpty()) {
//            return 0.0;
//        } else {
//            return warAggiornati / listaWarDaAggiornare.size() + 2;
//        }
//    }
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }
    
    public void incrementaPercentuale() {
        this.warAggiornati++;
        setChanged();
        notifyObservers();
        // notifica per progress bar
    }

    public int getNumWar() {
        return this.listaWarDaAggiornare.size()+2;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
        setChanged();
        notifyObservers();
        // notifica per barra di stato
    }

    public int getWarAggiornati() {
        return warAggiornati;
    }

    public void init() throws PersistenceException {
        incrementaPercentuale();
        LoadConfig loadConfig = LoadConfig.getInstance();
        loadConfig.loadConfig(percorsoWebApps, persorsoFileTemporanei,percorsoFileConfig,salvaConfig);
        listaWarDaAggiornare = loadConfig.getListaFreesbeeWar();
//        if (salvaConfig) {
//            salvaFileConfigurazione();
//        }
        if (salvaWar) {
            salvaFileWars();
        }
        //incrementaPercentuale();
        setStato(Costanti.STATO_FILE_CONFIG_CARICATI);
        // updateWar();
    }

    public void updateWar() throws PersistenceException {
        UpdateWar updateWar = new UpdateWar();
        updateWar.setUpdater(this);
        updateWar.update(percorsoWebApps, persorsoFileTemporanei);
        eseguiOperazioniConfig();
    }

    private void eseguiOperazioniConfig() {
        // cancellare le cartelle che non servono
        incrementaPercentuale();
        setStato(Costanti.STATO_TERMINATO);
    }

    private void salvaFileConfigurazione() {
    }

    private void salvaFileWars() {
    }

}
