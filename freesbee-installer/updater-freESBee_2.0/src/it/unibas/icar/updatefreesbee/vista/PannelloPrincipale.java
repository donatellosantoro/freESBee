package it.unibas.icar.updatefreesbee.vista;

import it.unibas.icar.updatefreesbee.Costanti;
import it.unibas.icar.updatefreesbee.controllo.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeListener;

public class PannelloPrincipale extends javax.swing.JPanel {

    private Vista vista;

    public PannelloPrincipale(Vista vista) {
        this.vista = vista;
        initComponents();
        initAzioni();
        inizializza();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        textFieldPercorso = new javax.swing.JTextField();
        bottoneSelezionaPercorso = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        checkBoxCartellaTemporanea = new javax.swing.JCheckBox();
        checkBoxFileConfig = new javax.swing.JCheckBox();
        checkBoxWar = new javax.swing.JCheckBox();
        textiFieldPercorsoWar = new javax.swing.JTextField();
        textFieldCartellaFileConfig = new javax.swing.JTextField();
        textFieldCartellaTemporanea = new javax.swing.JTextField();
        bottoneSelezionaPercorsoCartellaTemporanea = new javax.swing.JButton();
        bottoneSelezionaPercorsoCartellaFileConfig = new javax.swing.JButton();
        bottoneSelezionaPercorsoCartellaWar = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        bottoneAggiorna = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        bottoneSelezionaPercorso.setText("Seleziona");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selezionare la cartella webapps di tomcat o inserire il percorso");

        checkBoxCartellaTemporanea.setText("Seleziona cartella temporanea");

        checkBoxFileConfig.setText("Salva file cofigurazione");

        checkBoxWar.setText("Salva war precedenti");

        bottoneSelezionaPercorsoCartellaTemporanea.setText("Seleziona");

        bottoneSelezionaPercorsoCartellaFileConfig.setText("Seleziona");

        bottoneSelezionaPercorsoCartellaWar.setText("Seleziona");

        bottoneAggiorna.setText("Aggiorna");

        labelStatus.setText("Selezionare prima la cartella webApps");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(textFieldPercorso)
                                .addGap(18, 18, 18)
                                .addComponent(bottoneSelezionaPercorso))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(checkBoxCartellaTemporanea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(checkBoxFileConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(checkBoxWar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldCartellaFileConfig)
                                    .addComponent(textiFieldPercorsoWar)
                                    .addComponent(textFieldCartellaTemporanea))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bottoneSelezionaPercorsoCartellaTemporanea, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bottoneSelezionaPercorsoCartellaFileConfig, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bottoneSelezionaPercorsoCartellaWar, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(bottoneAggiorna)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldPercorso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneSelezionaPercorso))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxCartellaTemporanea)
                    .addComponent(textFieldCartellaTemporanea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneSelezionaPercorsoCartellaTemporanea))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxFileConfig)
                    .addComponent(textFieldCartellaFileConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneSelezionaPercorsoCartellaFileConfig))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxWar)
                    .addComponent(textiFieldPercorsoWar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneSelezionaPercorsoCartellaWar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneAggiorna, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneAggiorna;
    private javax.swing.JButton bottoneSelezionaPercorso;
    private javax.swing.JButton bottoneSelezionaPercorsoCartellaFileConfig;
    private javax.swing.JButton bottoneSelezionaPercorsoCartellaTemporanea;
    private javax.swing.JButton bottoneSelezionaPercorsoCartellaWar;
    private javax.swing.JCheckBox checkBoxCartellaTemporanea;
    private javax.swing.JCheckBox checkBoxFileConfig;
    private javax.swing.JCheckBox checkBoxWar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JTextField textFieldCartellaFileConfig;
    private javax.swing.JTextField textFieldCartellaTemporanea;
    private javax.swing.JTextField textFieldPercorso;
    private javax.swing.JTextField textiFieldPercorsoWar;
    // End of variables declaration//GEN-END:variables

    private void inizializza() {
        disabilitaCartellaTemporanea();
        disabilitaFileConfig();
        disabilitaWar();
        this.bottoneAggiorna.setEnabled(false);
    }

    public void disabilitaCartellaTemporanea() {
        this.checkBoxCartellaTemporanea.setSelected(false);
        this.checkBoxCartellaTemporanea.addItemListener(new OsservatoreCheckBoxTextFieldBottone(bottoneSelezionaPercorsoCartellaTemporanea, textFieldCartellaTemporanea));
        this.textFieldCartellaTemporanea.setEnabled(false);
        this.bottoneSelezionaPercorsoCartellaTemporanea.setEnabled(false);
    }

    public void disabilitaFileConfig() {
        this.checkBoxFileConfig.setSelected(false);
        this.checkBoxFileConfig.addItemListener(new OsservatoreCheckBoxTextFieldBottone(bottoneSelezionaPercorsoCartellaFileConfig, textFieldCartellaFileConfig));
        this.textFieldCartellaFileConfig.setEnabled(false);
        this.bottoneSelezionaPercorsoCartellaFileConfig.setEnabled(false);
    }

    public void disabilitaWar() {
        this.checkBoxWar.setSelected(false);
        this.checkBoxWar.addItemListener(new OsservatoreCheckBoxTextFieldBottone(bottoneSelezionaPercorsoCartellaWar, textiFieldPercorsoWar));
        this.textiFieldPercorsoWar.setEnabled(false);
        this.bottoneSelezionaPercorsoCartellaWar.setEnabled(false);
    }

    private void initAzioni() {
        AzioneSelezionaWebApp selezionaWebApp = (AzioneSelezionaWebApp) this.vista.getControllo().getAzione(Costanti.AZIONE_SELEZIONA_WEBAPP);
        this.bottoneSelezionaPercorso.setAction(selezionaWebApp);
        AzioneSelezionaCartellaTemporanea selezionaCartellaTemporanea = (AzioneSelezionaCartellaTemporanea) this.vista.getControllo().getAzione(Costanti.AZIONE_SELEZIONA_CARTELLA_TEMP);
        this.bottoneSelezionaPercorsoCartellaTemporanea.setAction(selezionaCartellaTemporanea);
        AzioneSelezionaCartellaFileConfig selezionaCartellaFileConfig = (AzioneSelezionaCartellaFileConfig) this.vista.getControllo().getAzione(Costanti.AZIONE_SELEZIONA_CARTELLA_FILE_CONFIG);
        this.bottoneSelezionaPercorsoCartellaFileConfig.setAction(selezionaCartellaFileConfig);
        AzioneSelezionaCartellaWar selezionaCartellaWar = (AzioneSelezionaCartellaWar) this.vista.getControllo().getAzione(Costanti.AZIONE_SELEZIONA_CARTELLA_FILE_WAR);
        this.bottoneSelezionaPercorsoCartellaWar.setAction(selezionaCartellaWar);
        AzioneAggiorna azioneAggiorna = (AzioneAggiorna) this.vista.getControllo().getAzione(Costanti.AZIONE_AGGIORNA);
        this.bottoneAggiorna.setAction(azioneAggiorna);
    }

    public void setPercorsoWebApps(String percorso) {
        this.textFieldPercorso.setText(percorso);
    }

    public String getPercorsoWebApps() {
        return this.textFieldPercorso.getText().trim();
    }

    public void setPercorsoCartellaTemporanea(String percorso) {
        this.textFieldCartellaTemporanea.setText(percorso);
    }

    public String getPercorsoCartellaTemporanea() {
        return this.textFieldCartellaTemporanea.getText();
    }

    public void setPercorsoFileConfig(String percorso) {
        this.textFieldCartellaFileConfig.setText(percorso);
    }

    public String getPercorsoFileConfig() {
        return this.textFieldCartellaFileConfig.getText();
    }

    public void setPercorsoFileWar(String percorso) {
        this.textiFieldPercorsoWar.setText(percorso);
    }

    public String getPercorsoFileWar() {
        return this.textiFieldPercorsoWar.getText();
    }

    public void abilitaUpdate() {
        this.bottoneAggiorna.setEnabled(true);
    }

    public boolean getCheckBoxSalvaConfig() {
        return this.checkBoxFileConfig.isSelected();
    }

    public boolean getCheckBoxSalvaWar() {
        return this.checkBoxWar.isSelected();
    }

    public void initBarra(int min, int max) {
        this.jProgressBar1.setMaximum(min);
        this.jProgressBar1.setMaximum(max);
    }

    public void aggiornaBarra(int value) {
        this.jProgressBar1.setValue(value);
    }

    public void initIndeterminateBar() {
        this.jProgressBar1.setMinimum(0);
        this.jProgressBar1.setMaximum(1);
        this.jProgressBar1.setIndeterminate(true);
    }

    public JProgressBar getProgressBar() {
        return this.jProgressBar1;
    }

    public JLabel getLabelStatus() {
        return this.labelStatus;
    }
    
    public JButton getBottoneAggiorna() {
        return this.bottoneAggiorna;
    }
}
