package it.unibas.freesbee.ge.modello;

public class ConsegnaEventoPubblicato {

    private String idEventoPubblicato;
    private Object messaggioSoap;
    private String nomePubblicatore;
    private String tipoPubblicatore;
    private String categoriaEventi;

    public String getIdEventoPubblicato() {
        return idEventoPubblicato;
    }

    public void setIdEventoPubblicato(String idEventoPubblicato) {
        this.idEventoPubblicato = idEventoPubblicato;
    }

    public String getNomePubblicatore() {
        return nomePubblicatore;
    }

    public void setNomePubblicatore(String nomePubblicatore) {
        this.nomePubblicatore = nomePubblicatore;
    }

    public String getTipoPubblicatore() {
        return tipoPubblicatore;
    }

    public void setTipoPubblicatore(String tipoPubblicatore) {
        this.tipoPubblicatore = tipoPubblicatore;
    }

    public String getCategoriaEventi() {
        return categoriaEventi;
    }

    public void setCategoriaEventi(String categoriaEventi) {
        this.categoriaEventi = categoriaEventi;
    }

    public Object getMessaggioSoap() {
        return messaggioSoap;
    }

    public void setMessaggioSoap(Object messaggioSoap) {
        this.messaggioSoap = messaggioSoap;
    }

}
