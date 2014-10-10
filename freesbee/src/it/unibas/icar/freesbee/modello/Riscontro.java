package it.unibas.icar.freesbee.modello;

public class Riscontro {

    private String identificatore;
    private String oraRegistrazione;
    private String tempo;

    public String getIdentificatore() {
        return identificatore;
    }

    public void setIdentificatore(String identificatore) {
        this.identificatore = identificatore;
    }

    public String getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(String oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return identificatore + " " + tempo + " " + oraRegistrazione;
    }

    

}
