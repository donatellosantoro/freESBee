package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.modello.Updater;
import it.unibas.icar.updatefreesbee.modello.UpdaterProgressBar;
import it.unibas.icar.updatefreesbee.persistenza.PersistenceException;
import it.unibas.icar.updatefreesbee.vista.PannelloPrincipale;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AzioneAggiorna extends AbstractAction implements Observer {

    private Controllo controllo;
    private static Log logger = LogFactory.getLog(AzioneAggiorna.class);

    public AzioneAggiorna(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(NAME, "Aggiorna");
        this.putValue(SHORT_DESCRIPTION, "Aggiorna freESBee");
    }

//    public void actionPerformed(ActionEvent e) {
//        PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getMappaViste().get(Costanti.PANNELLO_PRINCIPALE);
//        if (pp.getPercorsoWebApps() != null && pp.getPercorsoWebApps().endsWith("webapps")) {
//            try {
//                String percorsoWebApps = pp.getPercorsoWebApps();
//                String percorsoFileTemp = pp.getPercorsoCartellaTemporanea();
//                String percorsoFileConfig = pp.getPercorsoFileConfig();
//                String percorsoFileWar = pp.getPercorsoFileWar();
//                boolean salvaConfig = pp.getCheckBoxSalvaConfig();
//                boolean salvaWar = pp.getCheckBoxSalvaWar();
//                pp.initIndeterminateBar();
//                Updater updater = new Updater(percorsoWebApps, percorsoFileTemp, percorsoFileConfig, percorsoFileWar, salvaConfig, salvaWar);
//                updater.addObserver(this);
//                updater.init();
//                pp.initBarra(0, updater.getNumWar());
//                updater.updateWar();
//            } catch (PersistenceException ex) {
//                logger.error("Eccezione: "+ex);
//                // pannello errore
//            }
//        }
//    }
    public void actionPerformed(ActionEvent e) {
        PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getMappaViste().get(Costanti.PANNELLO_PRINCIPALE);
        if (pp.getPercorsoWebApps() != null && pp.getPercorsoWebApps().endsWith("webapps")) {
            String percorsoWebApps = pp.getPercorsoWebApps();
            String percorsoFileTemp = pp.getPercorsoCartellaTemporanea();
            String percorsoFileConfig = pp.getPercorsoFileConfig();
            String percorsoFileWar = pp.getPercorsoFileWar();
            boolean salvaConfig = pp.getCheckBoxSalvaConfig();
            boolean salvaWar = pp.getCheckBoxSalvaWar();
            String convalidaErrori = convalida(percorsoFileTemp, percorsoFileConfig, percorsoFileWar,salvaConfig,salvaWar);
            if (convalidaErrori.equals("")) {
                UpdaterProgressBar updaterProgressBar = new UpdaterProgressBar(pp.getProgressBar(), pp.getLabelStatus(), percorsoWebApps, percorsoFileTemp, percorsoFileConfig, percorsoFileWar, salvaConfig, salvaWar);
                updaterProgressBar.setButton(pp.getBottoneAggiorna());
                updaterProgressBar.start();
                String erroriAggiornamento = updaterProgressBar.getErrori();
                if (!erroriAggiornamento.equals("")) {
                    this.controllo.getVista().finestraErrore(erroriAggiornamento);
                }
            } else {
                this.controllo.getVista().finestraErrore(convalidaErrori);
            }

        }
    }

    public void update(Observable o, Object arg) {
        logger.info("Devo aggiornare la barra");
        Updater u = (Updater) o;
        PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getMappaViste().get(Costanti.PANNELLO_PRINCIPALE);
        pp.aggiornaBarra(u.getWarAggiornati());
    }

    private String convalida(String percorsoFileTemp, String percorsoFileConfig, String percorsoFileWar,boolean salvaConfig,boolean salvaWar) {
        String errori = "";
        if (salvaConfig) {
            if (percorsoFileConfig.equals(percorsoFileTemp)) {
                errori+="Attenzione, selezionare un percorso per il salvataggio dei file di configurazione diverso dal percorso della cartella temporanea\n";
            }
        }
        if (salvaWar) {
            if (percorsoFileWar.equals(percorsoFileTemp)) {
                errori +="Attenzione, selezionare un percorso per il salvataggio dei war diverso dal percorso della cartella temporanea\n";
            }
        }
        return errori;
    }
}
