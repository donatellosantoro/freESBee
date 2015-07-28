package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.vista.PannelloPrincipale;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

public class AzioneSelezionaCartellaWar extends AbstractAction {

    private Controllo controllo;

    public AzioneSelezionaCartellaWar(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(NAME, "Seleziona");
        this.putValue(SHORT_DESCRIPTION, "Seleziona la cartella dove salvare temporaneamente i file");
    }

    public void actionPerformed(ActionEvent e) {
        String percorso = getPercorso();
        if (percorso != null) {
            PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getMappaViste().get(Costanti.PANNELLO_PRINCIPALE);
            pp.setPercorsoFileWar(percorso);
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
