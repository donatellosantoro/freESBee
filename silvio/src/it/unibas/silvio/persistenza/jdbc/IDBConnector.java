package it.unibas.silvio.persistenza.jdbc;

public interface IDBConnector {
    
    public String getNome();
    
    public String getDriverClass();
    
    public String getURLDatabase(String indirizzoDB, String nomeDB);

}
