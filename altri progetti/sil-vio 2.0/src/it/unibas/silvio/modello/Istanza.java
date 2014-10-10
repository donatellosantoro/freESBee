package it.unibas.silvio.modello;

import it.unibas.silvio.util.StringUtil;

public class Istanza {

    private String nome;
    private Step primoStep;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Step getPrimoStep() {
        return primoStep;
    }

    public void setPrimoStep(Step primoStep) {
        String tipo = primoStep.getTipo();
        if(StringUtil.diverso(tipo, Step.AVVIO_ISTANZA)){
            throw new IllegalArgumentException("Il primo step dev'essere del tipo AVVIO_ISTANZA");
        }
        this.primoStep = primoStep;
    }

}
