package it.unibas.icar.freesbee.modello;

import it.unibas.icar.freesbee.utilita.CostantiSOAP;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Eccezione implements Cloneable {

    public static final String CONTESTO_CODIFICA = "ErroreIntestazioneMessaggioSPCoop";

    private long id;
    private String contestoCodifica;
    private String codiceEccezione;
    private String rilevanza;
    private String posizione;
    private Messaggio messaggio;

    public Eccezione() {
    }
    
    public Eccezione(String contestoCodifica, String codiceEccezione, String rilevanza, String posizione) {
        this.contestoCodifica = contestoCodifica;
        this.codiceEccezione = codiceEccezione;
        this.rilevanza = rilevanza;
        this.posizione = posizione;
    }

    public Eccezione(String[] descrizione) {
        if (descrizione.length != 3) {
            throw new IllegalArgumentException("La descrizione dell'eccezione deve avere tre campi: Codice, Rilevanza, Posizione");
        }
        this.contestoCodifica = CONTESTO_CODIFICA;
        this.codiceEccezione = descrizione[0];
        this.rilevanza = descrizione[1];
        this.posizione = descrizione[2];
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String convertiCodiceEccezione() {
        try {
            int cod = Integer.parseInt(codiceEccezione);
            DecimalFormat df = new DecimalFormat("000");
            String stringaEccezione = CostantiSOAP.PREFIX_EGOV_ERRORE + df.format(cod);
            return stringaEccezione;
        } catch (Exception e) {
        }
        return codiceEccezione;
    }

    @ManyToOne()
    public Messaggio getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(Messaggio messaggio) {
        this.messaggio = messaggio;
    }

    @Override
    public String toString() {
        return "Errore " + this.codiceEccezione + " [" + this.contestoCodifica + "] ";
    }

    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {}
        return null;
    }
    
}
