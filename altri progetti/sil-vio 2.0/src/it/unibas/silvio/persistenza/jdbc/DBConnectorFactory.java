package it.unibas.silvio.persistenza.jdbc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DBConnectorFactory {

    private static Map<String, IDBConnector> mappa = new HashMap<String, IDBConnector>();

    static{
        mappa.put("PostgreSQL", new PSQLConnector());
        mappa.put("MySQL", new MySQLConnector());
    }

    public static IDBConnector getDBConnector(String tipoDB) {
        IDBConnector connector = mappa.get(tipoDB);
        if(connector==null){
            throw new IllegalArgumentException("Il tipo " + tipoDB + " non e' supportato");
        }
        return connector;
    }
    
    public static Set<String> getDBSupportati(){
        return mappa.keySet();
    }
}
