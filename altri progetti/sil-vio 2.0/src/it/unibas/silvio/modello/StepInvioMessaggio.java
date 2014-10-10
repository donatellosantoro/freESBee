package it.unibas.silvio.modello;

public class StepInvioMessaggio extends Step{

    private String indirizzo;

    public StepInvioMessaggio(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return INVIO_MESSAGGIO;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    
}
