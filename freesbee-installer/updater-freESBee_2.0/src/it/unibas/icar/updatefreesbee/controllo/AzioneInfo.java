package it.unibas.icar.updatefreesbee.controllo;

import it.unibas.icar.updatefreesbee.vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class AzioneInfo extends AbstractAction{
    
    private Controllo controllo;

    public AzioneInfo(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(NAME, "Info");
        this.putValue(SHORT_DESCRIPTION, "Info sull'applicazione");
        this.putValue(MNEMONIC_KEY, KeyEvent.VK_I);
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl I"));
    }

    public void actionPerformed(ActionEvent e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Applicazione per l'aggiornamento da freESBee 1.0 a freESBee 2.0\n");
        stringBuilder.append("\n");
        stringBuilder.append("Informazioni sull'utilizzo:\n");
        stringBuilder.append("Selezionare la cartella webapps di tomcat\n");
        stringBuilder.append("Il programma indichera' una cartella temporanea dove lavorare\n");
        stringBuilder.append("La cartella di default temporanea e' la cartella temp di tomcat\n");
        stringBuilder.append("E' possibile specificare un'altra cartella temporanea\n");
        stringBuilder.append("E' possibile salvare solo i file di configurazione di freESBee\n");
        stringBuilder.append("E' possibile salvare solo i war di freESBee 1.0\n");
        stringBuilder.append("E' possibile selezionare dove salvare i file di configurazione e i war\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Autori\n");
        stringBuilder.append("2012 Gruppo di lavoro freESBee\n");
        Vista vista = this.controllo.getVista();
        vista.finestraInfo(stringBuilder.toString());
    }
    

}
