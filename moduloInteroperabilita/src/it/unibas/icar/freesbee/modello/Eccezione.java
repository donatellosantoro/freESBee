package it.unibas.icar.freesbee.modello;

import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import java.text.DecimalFormat;

public class Eccezione {
    private String contestoCodifica;
    private String codiceEccezione;
    private String rilevanza;
    private String posizione;
    
    public Eccezione(){}

    public Eccezione(String contestoCodifica, String codiceEccezione, String rilevanza, String posizione) {
        this.contestoCodifica = contestoCodifica;
        this.codiceEccezione = codiceEccezione;
        this.rilevanza = rilevanza;
        this.posizione = posizione;
    }

    public Eccezione(String contestoCodifica, int codiceEccezione, String rilevanza, String posizione) {
        this.contestoCodifica = contestoCodifica;
        this.codiceEccezione = costruisciStringaCodiceEccezione(codiceEccezione);
        this.rilevanza = rilevanza;
        this.posizione = posizione;
    }

    public String getCodiceEccezione() {
        return codiceEccezione;
    }

    public void setCodiceEccezione(String codiceEccezione) {
        this.codiceEccezione = codiceEccezione;
    }

    public String getContestoCodifica() {
        return contestoCodifica;
    }

    public void setContestoCodifica(String contestoCodifica) {
        this.contestoCodifica = contestoCodifica;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public String getRilevanza() {
        return rilevanza;
    }

    public void setRilevanza(String rilevanza) {
        this.rilevanza = rilevanza;
    }

    public String costruisciStringaCodiceEccezione(int codiceEccezione) {        
        DecimalFormat df = new DecimalFormat("000");
        String stringaEccezione = CostantiSOAP.PREFIX_EGOV_ERRORE + df.format(codiceEccezione);
        return stringaEccezione;
    }
    
    

}
