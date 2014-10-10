package it.unibas.freesbee.ge.web.vista;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VistaCambiaPassword {

    private Log logger = LogFactory.getLog(this.getClass());
    private String nuovaPassword;
    private String vecchiaPassword;

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
