package it.unibas.freesbee.ge.web.modello;

public class ConfigurazioneStatico {

    private static ConfigurazioneStatico singleton = new ConfigurazioneStatico();
    //Dati da prelevare dal Gestore Eventi
    private String inidirzzoGE = "http://localhost:8198/";

 
    public static ConfigurazioneStatico getInstance() {
        return singleton;
    }

    public String getIndirizzoGE() {
        return inidirzzoGE;
    }

    public void setIndirizzoGE(String inidirzzoGE) {
        this.inidirzzoGE = inidirzzoGE;
    }
}
