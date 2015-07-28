package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.vista.PannelloPrincipale;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

public class AzioneSelezionaCartellaTemporanea extends AbstractAction {

    private Controllo controllo;

    public AzioneSelezionaCartellaTemporanea(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(NAME, "Seleziona");
        this.putValue(SHORT_DESCRIPTION, "Seleziona la cartella dove salvare temporaneamente i file");
    }

    public void actionPerformed(ActionEvent e) {
        String percorsoTemporaneo = getPercorso();
        if (percorsoTemporaneo != null) {
            PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getMappaViste().get(Costanti.PANNELLO_PRINCIPALE);
            //pp.setPercorsoCartellaTemporanea(percorsoTemporaneo);
            pp.setPercorsoCartellaTemporanea(percorsoTemporaneo+File.separator+"freESBeeUpdate");
            if (!pp.getCheckBoxSalvaConfig()) {
                pp.setPercorsoFileConfig(percorsoTemporaneo);
            }
            if (!pp.getCheckBoxSalvaWar()) {
                pp.setPercorsoFileWar(percorsoTemporaneo);
            }
        }
    }

    private String getPercorso() {
        JFileChooser fileChooser = this.controllo.getVista().getFileChooser();
        int codice = fileChooser.showOpenDialog(this.controllo.getVista());
        if (codice == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            if (f.isDirectory()) {
                return f.getPath();
            }
        }
        return null;
    }
}
