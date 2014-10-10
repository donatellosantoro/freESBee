package it.unibas.freesbee.websla.modello;

public class ConfigurazioneStatico {

    private static ConfigurazioneStatico singleton = new ConfigurazioneStatico();
    //Dati da prelevare dal modulo freesbeesla
    private String indirizzoModuloSLA = "http://localhost:8080/";
    private String tipoNica = NON_VERIFICATO;
    //
    
    public static final String FREESBEE = "FREESBEE";
    public static final String NICA = "NICA";
    public static final String NON_VERIFICATO = "NON_VERIFICATO";

    public static ConfigurazioneStatico getInstance() {
        return singleton;
    }

    public String getTipoNica() {
        return tipoNica;
    }

    public void setTipoNica(String tipoNica) {
        this.tipoNica = tipoNica;
    }

    public String getIndirizzoModuloSLA() {
        return indirizzoModuloSLA;
    }

    public void setIndirizzoModuloSLA(String indirizzoModuloSLA) {
        this.indirizzoModuloSLA = indirizzoModuloSLA;
    }

    public boolean isTipoNica(){
    return tipoNica.equals(FREESBEE);
    }
}
