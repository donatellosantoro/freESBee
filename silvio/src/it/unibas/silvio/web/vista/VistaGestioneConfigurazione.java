package it.unibas.silvio.web.vista;

import java.io.Serializable;

public class VistaGestioneConfigurazione implements Serializable{

    private String dirConfig;
    private String porta;

    public String getDirConfig() {
        return dirConfig;
    }

    public void setDirConfig(String dirConfig) {
        this.dirConfig = dirConfig;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }
}
