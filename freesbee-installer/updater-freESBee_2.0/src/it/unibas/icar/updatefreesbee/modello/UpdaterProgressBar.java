package it.unibas.icar.updatefreesbee.modello;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.persistenza.LoadConfig;
import it.unibas.icar.updatefreesbee.persistenza.PersistenceException;
import it.unibas.icar.updatefreesbee.persistenza.UpdateWar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UpdaterProgressBar extends Thread implements IUpdater {

    private JProgressBar jProgressBar;
    private JLabel labelStato;
    private JButton button;
    private String stato;
    private String percorsoWebApps;
    private String percorsoFileTemporanei;
    private String percorsoFileConfig;
    private String percorsoVecchiWars;
    private boolean salvaConfig;
    private boolean salvaWar;
    private List<File> listaWarDaAggiornare = new ArrayList<File>();
    private int warAggiornati = 0;
    private static Log logger = LogFactory.getLog(UpdaterProgressBar.class);
    private String errori = "";

    public UpdaterProgressBar(JProgressBar jProgressBar, JLabel labelStato, String percorsoWebApps, String persorsoFileTemporanei, String percorsoFileConfig, String percorsoVecchiWars, boolean salvaConfig, boolean salvaWar) {
        this.jProgressBar = jProgressBar;
        this.labelStato = labelStato;
        this.percorsoWebApps = percorsoWebApps;
        this.percorsoFileTemporanei = persorsoFileTemporanei;
        this.percorsoFileConfig = percorsoFileConfig;
        this.percorsoVecchiWars = percorsoVecchiWars;
        this.salvaConfig = salvaConfig;
        this.salvaWar = salvaWar;
    }

    @Override
    public void run() {
        try {
            setStato(Costanti.STATO_INIZIALIZZAZIONE);
            this.button.setEnabled(false);
            this.jProgressBar.setMinimum(0);
            this.jProgressBar.setMaximum(1);
            this.jProgressBar.setIndeterminate(true);
            LoadConfig loadConfig = LoadConfig.getInstance();
            loadConfig.loadConfig(percorsoWebApps, percorsoFileTemporanei,percorsoFileConfig,salvaConfig);
            listaWarDaAggiornare = loadConfig.getListaFreesbeeWar();
            setStato(Costanti.STATO_INIZIALIZZAZIONE_FINE);
            this.jProgressBar.setMaximum(listaWarDaAggiornare.size() + 1);
            this.jProgressBar.setIndeterminate(false);
//            if (salvaConfig) {
//                salvaFileConfigurazione();
//            }
            if (salvaWar) {
                salvaFileWars();
            }
            //setStato(Costanti.STATO_FILE_CONFIG_CARICATI);
            UpdateWar updateWar = new UpdateWar();
            updateWar.setUpdater(this);
            setStato(Costanti.STATO_WAR_INIZIO);
            updateWar.update(percorsoWebApps, percorsoFileTemporanei);
            setStato(Costanti.STATO_WAR_AGGIORNATI);
            eseguiOperazioniConfig();
            this.button.setEnabled(true);
        } catch (PersistenceException ex) {
            logger.error("Errore: " + ex);
            errori += "Errore nell'esecuzione dell'aggiornamento. Controllare che i war siano ancora tutti nella cartella webapps e riprovare. Altrimenti controllare che esista qualche copia nelle cartelle di configurazione\n";
        }
    }

    public void incrementaPercentuale() {
        this.warAggiornati++;
        this.jProgressBar.setValue(warAggiornati);
    }

    public void setStato(String stato) {
        this.stato = stato;
        this.labelStato.setText(stato);
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

    private void salvaFileConfigurazione() {
        setStato(Costanti.STATO_COPIA_INIZIO);
        try {
            // salvo config
            FileUtils.copyDirectory(new File(percorsoFileTemporanei), new File(percorsoFileConfig));
        } catch (IOException ex) {
            logger.error("Errore nella copia dei file di configurazione: " + ex);
            errori += "Errore nella copia dei file di configurazione dalla cartella: " + percorsoFileTemporanei + " alla cartella: " + percorsoFileConfig + "\n";
        }
        incrementaPercentuale();
        setStato(Costanti.STATO_COPIA_FINE);
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

    public void setButton(JButton bottoneAggiorna) {
        this.button = bottoneAggiorna;
    }

    public String getErrori() {
        return errori;
    }
}
