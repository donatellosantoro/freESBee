package it.unibas.silvio.modello;

public class StepTransazioneBD extends Step{

    public StepTransazioneBD(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return TRANSAZIONE_BD;
    }
}
