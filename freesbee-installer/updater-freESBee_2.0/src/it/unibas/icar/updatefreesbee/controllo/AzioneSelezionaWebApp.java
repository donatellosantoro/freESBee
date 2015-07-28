package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.vista.PannelloPrincipale;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AzioneSelezionaWebApp extends AbstractAction {

    private Controllo controllo;
    private static Log logger = LogFactory.getLog(AzioneSelezionaWebApp.class);
    private File padre;

    public AzioneSelezionaWebApp(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(NAME, "Seleziona");
        this.putValue(SHORT_DESCRIPTION, "Seleziona la cartella webapps di tomcat");

    }

    public void actionPerformed(ActionEvent e) {
        String percorsoWebApp = getPercorso();
        logger.info("Percorso selezionato: " + percorsoWebApp);
        if (percorsoWebApp != null) {
            PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getMappaViste().get(Costanti.PANNELLO_PRINCIPALE);
            pp.setPercorsoWebApps(percorsoWebApp);
            // settiamo i parametri di default
            if (percorsoWebApp.endsWith("webapps")) {
                //String percorsoTemporaneo = padre.getPath() + "\\temp\\freESBeeUpdate";
                String percorsoTemporaneo = padre.getPath() + File.separator+"temp"+File.separator+"freESBeeUpdate";
                logger.info("Percorso temporaneo: " + percorsoTemporaneo);
                if (pp.getPercorsoCartellaTemporanea().equals("")) {
                    pp.setPercorsoCartellaTemporanea(percorsoTemporaneo);
                }
                if (pp.getPercorsoFileConfig().equals("")) {
                    pp.setPercorsoFileConfig(percorsoTemporaneo);
                }
                if (pp.getPercorsoFileWar().equals("")) {
                    pp.setPercorsoFileWar(percorsoTemporaneo);
                }
                pp.abilitaUpdate();
            }
        }

    }

    private String getPercorso() {
        JFileChooser fileChooser = this.controllo.getVista().getFileChooser();
        int codice = fileChooser.showOpenDialog(this.controllo.getVista());
        if (codice == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            if (f.isDirectory()) {
                padre = f.getParentFile();
                return f.getPath();
            }
        }
        return null;
    }
}
