package it.unibas.silvio.modello;

public class StepInterrogazioneBD extends Step{

    private Query query;

    public StepInterrogazioneBD(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return INTERROGAZIONE_BD;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    
}
