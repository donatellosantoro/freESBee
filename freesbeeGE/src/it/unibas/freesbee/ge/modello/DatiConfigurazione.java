package it.unibas.freesbee.ge.modello;

public class DatiConfigurazione {

    protected Configurazione configurazione;
    protected ConfigurazioneSP configurazioneSP;
    protected boolean protezioneSP;

    public Configurazione getConfigurazione() {
        return configurazione;
    }

    public void setConfigurazione(Configurazione value) {
        this.configurazione = value;
    }

    public ConfigurazioneSP getConfigurazioneSP() {
        return configurazioneSP;
    }

    public void setConfigurazioneSP(ConfigurazioneSP value) {
        this.configurazioneSP = value;
    }

    public boolean isProtezioneSP() {
        return protezioneSP;
    }

    public void setProtezioneSP(boolean value) {
        this.protezioneSP = value;
    }
}
