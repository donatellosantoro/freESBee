package it.unibas.silvio.modello;

public abstract class Step {

    public static final String AVVIO_ISTANZA = "AVVIO_ISTANZA";
    public static final String INVIO_MESSAGGIO = "INVIO_MESSAGGIO";
    public static final String INTERROGAZIONE_BD = "INTERROGAZIONE_BD";
    public static final String TRANSAZIONE_BD = "TRANSAZIONE_BD";
    //-------------------------------------------------------------
    public static final String SBLOCCA_POLLING = "SBLOCCA_POLLING";

    private Step prossimoStep;
    private String nome;

    public Step(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Step getProssimoStep() {
        return prossimoStep;
    }

    public void setProssimoStep(Step prossimoStep) {
        this.prossimoStep = prossimoStep;
    }

    public abstract String getTipo();
}
