package it.unibas.silvio.modello;

public class Query {

    private String sqlQuery;
    private String nomeUtente;
    private String password;
    private String tipoDB;
    private String nomeDB;
    private String indirizzoDB;

    public String getIndirizzoDB() {
        return indirizzoDB;
    }

    public void setIndirizzoDB(String indirizzoDB) {
        this.indirizzoDB = indirizzoDB;
    }

    public String getNomeDB() {
        return nomeDB;
    }

    public void setNomeDB(String nomeDB) {
        this.nomeDB = nomeDB;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getTipoDB() {
        return tipoDB;
    }

    public void setTipoDB(String tipoDB) {
        this.tipoDB = tipoDB;
    }

    

}
