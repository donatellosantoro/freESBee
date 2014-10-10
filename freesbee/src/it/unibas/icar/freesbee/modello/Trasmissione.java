package it.unibas.icar.freesbee.modello;

public class Trasmissione {

    private String origineNome;
    private String origineTipo;
    private String origineIndirizzoTelematico;
    private String destinazioneNome;
    private String destinazioneTipo;
    private String destinazioneIndirizzoTelematico;
    private String tempo;
    private String oraRegistrazione;

    public String getDestinazioneNome() {
        return destinazioneNome;
    }

    public void setDestinazioneNome(String destinazioneNome) {
        this.destinazioneNome = destinazioneNome;
    }

    public String getDestinazioneTipo() {
        return destinazioneTipo;
    }

    public void setDestinazioneTipo(String destinazioneTipo) {
        this.destinazioneTipo = destinazioneTipo;
    }

    public String getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(String oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }

    public String getOrigineNome() {
        return origineNome;
    }

    public void setOrigineNome(String origineNome) {
        this.origineNome = origineNome;
    }

    public String getOrigineTipo() {
        return origineTipo;
    }

    public void setOrigineTipo(String origineTipo) {
        this.origineTipo = origineTipo;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getDestinazioneIndirizzoTelematico() {
        return destinazioneIndirizzoTelematico;
    }

    public void setDestinazioneIndirizzoTelematico(String destinazioneIndirizzoTelematico) {
        this.destinazioneIndirizzoTelematico = destinazioneIndirizzoTelematico;
    }

    public String getOrigineIndirizzoTelematico() {
        return origineIndirizzoTelematico;
    }

    public void setOrigineIndirizzoTelematico(String origineIndirizzoTelematico) {
        this.origineIndirizzoTelematico = origineIndirizzoTelematico;
    }

    @Override
    public String toString() {
        String s = "Trasmissione\n";
        s += "\tOrigine Nome: " + origineNome + "\n";
        s += "\tOrigine Tipo: " + origineTipo + "\n";
        s += "\tOrigine IndTel: " + origineIndirizzoTelematico + "\n";
        s += "\tDestinazione Nome: " + destinazioneNome + "\n";
        s += "\tDestinazione Tipo: " + destinazioneTipo + "\n";
        s += "\tDestinazione IndTel: " + destinazioneIndirizzoTelematico + "\n";
        s += "\tTempo: " + tempo + "\n";
        s += "\tOra: " + oraRegistrazione + "\n";
        return s;
    }

    
    
}
