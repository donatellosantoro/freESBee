package it.unibas.icar.updatefreesbee.vista;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.controllo.AzioneEsci;
import it.unibas.icar.updatefreesbee.controllo.AzioneInfo;
import it.unibas.icar.updatefreesbee.controllo.Controllo;
import it.unibas.icar.updatefreesbee.modello.Modello;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Vista extends javax.swing.JFrame {

    private Modello modello;
    private Controllo controllo;
    private Map<String, Object> mappaViste = new HashMap<String, Object>();
    private JFileChooser fileChooser = new JFileChooser();

    public Vista(Modello modello, Controllo controllo) {
        this.modello = modello;
        this.controllo = controllo;
        initComponents();
        initAzioni();
        postInit();
    }

    public Controllo getControllo() {
        return controllo;
    }

    public void setControllo(Controllo controllo) {
        this.controllo = controllo;
    }

    public Map<String, Object> getMappaViste() {
        return mappaViste;
    }

    public void setMappaViste(Map<String, Object> mappaViste) {
        this.mappaViste = mappaViste;
    }

    public Modello getModello() {
        return modello;
    }

    public void setModello(Modello modello) {
        this.modello = modello;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemEsci = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jMenu1.setText("File");

        itemEsci.setText("Esci");
        jMenu1.add(itemEsci);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("?");

        itemInfo.setText("Info");
        jMenu2.add(itemInfo);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemEsci;
    private javax.swing.JMenuItem itemInfo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    private void postInit() {
        this.setTitle("Update freESBee 1.0 to 2.0");
        PannelloPrincipale pp = new PannelloPrincipale(this);
        this.mappaViste.put(Costanti.PANNELLO_PRINCIPALE, pp);
        this.getContentPane().add((Component) pp);
        this.pack();
        this.setVisible(true);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    private void initAzioni() {
       AzioneEsci azioneEsci = (AzioneEsci)this.controllo.getAzione(Costanti.AZIONE_ESCI);
       this.itemEsci.setAction(azioneEsci);
       AzioneInfo azioneInfo = (AzioneInfo) this.controllo.getAzione(Costanti.AZIONE_INFO);
       this.itemInfo.setAction(azioneInfo);
    }
    
    public void finestraInfo(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "Informazioni", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void finestraErrore(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "ERRORE", JOptionPane.ERROR_MESSAGE);
    }
}
