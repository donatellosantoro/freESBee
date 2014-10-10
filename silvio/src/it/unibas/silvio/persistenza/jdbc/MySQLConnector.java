package it.unibas.silvio.persistenza.jdbc;

public class MySQLConnector implements IDBConnector {
    
    protected MySQLConnector(){
        
    }

    public String getNome() {
        return "MySQL";
    }

    public String getDriverClass() {
        return "com.mysql.jdbc.Driver";
    }

    public String getURLDatabase(String indirizzoDB, String nomeDB) {
        if (nomeDB == null) {
            throw new IllegalArgumentException("Impossibile creare il connettore senza il nome del DB");
        }
        if (indirizzoDB == null) {
            return "jdbc:mysql:" + nomeDB;
        }
        return "jdbc:mysql://" + indirizzoDB + "/" + nomeDB;
    }
}
