package it.unibas.icar.updatefreesbee.vista;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class OsservatoreCheckBoxTextFieldBottone implements ItemListener {

    // abilita textField e bottone
    private JButton button;
    private JTextField textField;

    public OsservatoreCheckBoxTextFieldBottone(JButton button, JTextField textField) {
        this.button = button;
        this.textField = textField;
    }

    public void itemStateChanged(ItemEvent e) {
        textField.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
        button.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
    }
}
