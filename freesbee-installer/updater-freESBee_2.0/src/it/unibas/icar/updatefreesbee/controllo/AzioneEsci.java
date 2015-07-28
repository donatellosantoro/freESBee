package it.unibas.icar.updatefreesbee.controllo;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class AzioneEsci extends AbstractAction {
    
    public AzioneEsci() {
        this.putValue(NAME, "Esci");
        this.putValue(SHORT_DESCRIPTION, "Esci dall'applicazione");
        this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Q"));
        
    }
    
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
