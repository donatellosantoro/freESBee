package it.unibas.freesbee.ge.web.modello;

public class WebServizio {

    private String nome;
    private String erogatore;
    private String fruitore;
    private String stato;
    private Long contatoreRischieste;
    private boolean attiva;

    public String getErogatore() {
        return erogatore;
    }

    public void setErogatore(String erogatore) {
        this.erogatore = erogatore;
    }

    public String getFruitore() {
        return fruitore;
    }

    public void setFruitore(String fruitore) {
        this.fruitore = fruitore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getContatoreRichieste() {
        return contatoreRischieste;
    }

    public void setContatoreRichieste(Long contatoreRichieste) {
        this.contatoreRischieste = contatoreRichieste;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    

    public boolean isAttiva() {
        return attiva;
    }

    public void setAttiva(boolean attiva) {
        this.attiva = attiva;
    }
    
}
