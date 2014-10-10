package it.unibas.silvio.persistenza.jdbc;

public class PSQLConnector implements IDBConnector {

    protected PSQLConnector() {
    }

    public String getNome() {
        return "PostgreSQL";
    }

    public String getDriverClass() {
        return "org.postgresql.Driver";
    }

    public String getURLDatabase(String indirizzoDB, String nomeDB) {
        if (nomeDB == null) {
            throw new IllegalArgumentException("Impossibile creare il connettore senza il nome del DB");
        }
        if (indirizzoDB == null) {
            return "jdbc:postgresql:" + nomeDB;
        }
        return "jdbc:postgresql://" + indirizzoDB + "/" + nomeDB;
    }
}
