package it.unibas.icar.freesbee.modello;

import java.io.Serializable;
import java.util.Date;

public class AccordoServizio implements Serializable, Comparable {

    public static String PROFILO_ONEWAY = "EGOV_IT_MessaggioSingoloOneWay";
    public static String PROFILO_SINCRONO = "EGOV_IT_ServizioSincrono";
    public static String PROFILO_ASINCRONO_SIMMETRICO = "EGOV_IT_ServizioAsincronoSimmetrico";
    public static String PROFILO_ASINCRONO_ASIMMETRICO = "EGOV_IT_ServizioAsincronoAsimmetrico";
    private long id;
    private String nome;
    private String descrizione;
    private String profiloCollaborazione;
    private boolean privato;
    private String oldNomeForUpdate;
    private Date oraRegistrazione;
    private String policyXACML;

    public AccordoServizio() {
    }

    public AccordoServizio(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getProfiloCollaborazione() {
        return profiloCollaborazione;
    }

    public void setProfiloCollaborazione(String profiloCollaborazione) {
        this.profiloCollaborazione = profiloCollaborazione;
    }


    public String getProfiloCollaborazioneBreve() {
        return AccordoServizio.convertiNomeProfilo(this.profiloCollaborazione);
    }

    public static String convertiNomeProfilo(String profilo) {
        if (profilo == null) {
            return "";
        }
        if (profilo.equals(AccordoServizio.PROFILO_ONEWAY)) {
            return "OneWay";
        } else if (profilo.equals(AccordoServizio.PROFILO_SINCRONO)) {
            return "Sincrono";
        } else if (profilo.equals(AccordoServizio.PROFILO_ASINCRONO_SIMMETRICO)) {
            return "Asincrono simmetrico";
        } else if (profilo.equals(AccordoServizio.PROFILO_ASINCRONO_ASIMMETRICO)) {
            return "Asincrono asimmetrico";
        } else {
            return "";
        }
    }

    public boolean isPrivato() {
        return privato;
    }

    public void setPrivato(boolean privato) {
        this.privato = privato;
    }

    public int compareTo(Object o) {
        return this.getNome().compareToIgnoreCase(((AccordoServizio) o).getNome());
    }

    public String getOldNomeForUpdate() {
        return oldNomeForUpdate;
    }

    public void setOldNomeForUpdate(String oldNomeForUpdate) {
        this.oldNomeForUpdate = oldNomeForUpdate;
    }

    public Date getOraRegistrazione() {
        return oraRegistrazione;
    }

    public void setOraRegistrazione(Date oraRegistrazione) {
        this.oraRegistrazione = oraRegistrazione;
    }

    public String getPolicyXACML() {
        return policyXACML;
    }

    public void setPolicyXACML(String policyXACML) {
        this.policyXACML = policyXACML;
    }
}
