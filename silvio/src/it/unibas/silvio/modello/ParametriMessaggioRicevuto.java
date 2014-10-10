package it.unibas.silvio.modello;

public class ParametriMessaggioRicevuto {

    private String nomeOperazione;
    private IstanzaOperation istanzaOperation;
    private String indirizzoRisposta;
    private boolean test;
    private String tipoCorrelazione;

    public String getIndirizzoRisposta() {
        return indirizzoRisposta;
    }

    public void setIndirizzoRisposta(String indirizzoRisposta) {
        this.indirizzoRisposta = indirizzoRisposta;
    }

    public IstanzaOperation getIstanzaOperation() {
        return istanzaOperation;
    }

    public void setIstanzaOperation(IstanzaOperation istanzaOperation) {
        this.istanzaOperation = istanzaOperation;
    }

    public String getNomeOperazione() {
        return nomeOperazione;
    }

    public void setNomeOperazione(String nomeOperazione) {
        this.nomeOperazione = nomeOperazione;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getTipoCorrelazione() {
        return tipoCorrelazione;
    }

    public void setTipoCorrelazione(String tipoCorrelazione) {
        this.tipoCorrelazione = tipoCorrelazione;
    }
    
    

}
