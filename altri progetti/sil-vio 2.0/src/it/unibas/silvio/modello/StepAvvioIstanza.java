package it.unibas.silvio.modello;

public class StepAvvioIstanza extends Step{

    private String indirizzo;

    public StepAvvioIstanza(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return AVVIO_ISTANZA;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    
}
