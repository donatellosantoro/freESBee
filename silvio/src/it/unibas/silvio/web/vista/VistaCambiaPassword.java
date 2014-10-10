package it.unibas.silvio.web.vista;

import java.io.Serializable;

public class VistaCambiaPassword implements Serializable {

    private String vecchiaPassword;
    private String nuovaPassword;

    public String getNuovaPassword() {
        return nuovaPassword;
    }

    public void setNuovaPassword(String nuovaPassword) {
        this.nuovaPassword = nuovaPassword;
    }

    public String getVecchiaPassword() {
        return vecchiaPassword;
    }

    public void setVecchiaPassword(String vecchiaPassword) {
        this.vecchiaPassword = vecchiaPassword;
    }
}
