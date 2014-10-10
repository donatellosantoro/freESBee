package it.unibas.silvio.transql;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SQLInsert implements Comparable {

    private String query;
    private String nomeTabella;
    private String nomeColonnaID;
    private String idPlaceHolder;
    private long valoreId;
    private boolean eseguito;
    private Map<String, String> mappaParametri = new HashMap<String, String>();
    private Map<String, SQLInsert> mappaRiferimenti = new HashMap<String, SQLInsert>();

    public SQLInsert(String query) {
        this.query = query;
    }

    public boolean isEseguito() {
        return eseguito;
    }

    public void setEseguito(boolean eseguito) {
        this.eseguito = eseguito;
    }

    public Map<String, String> getMappaParametri() {
        return mappaParametri;
    }

    public void setMappaParametri(Map<String, String> mappaParametri) {
        this.mappaParametri = mappaParametri;
    }

    public String getNomeColonnaID() {
        return nomeColonnaID;
    }

    public void setNomeColonnaID(String nomeColonnaID) {
        this.nomeColonnaID = nomeColonnaID;
    }

    public String getNomeTabella() {
        return nomeTabella;
    }

    public void setNomeTabella(String nomeTabella) {
        this.nomeTabella = nomeTabella;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getIdPlaceHolder() {
        return idPlaceHolder;
    }

    public void setIdPlaceHolder(String idPlaceHolder) {
        this.idPlaceHolder = idPlaceHolder;
    }

    public long getValoreId() {
        return valoreId;
    }

    public void setValoreId(long valoreId) {
        this.valoreId = valoreId;
    }

    public int getSQLOperationDaReferenziare() {
        int contatore = 0;
        Collection<SQLInsert> sqlOperationsReferenziate = mappaRiferimenti.values();
        for (SQLInsert op : sqlOperationsReferenziate) {
            if (!op.isEseguito()) {
                contatore++;
            }
        }
        return contatore;
    }

    public boolean hasRiferimentiCompleti() {
        return getSQLOperationDaReferenziare() == 0;
    }

    public Map<String, SQLInsert> getMappaRiferimenti() {
        return mappaRiferimenti;
    }

    public void setMappaRiferimenti(Map<String, SQLInsert> mappaRiferimenti) {
        this.mappaRiferimenti = mappaRiferimenti;
    }

    public String generaSelectId() {
        if (this.getNomeColonnaID() == null || this.getMappaParametri().size() == 0) {
            return "";
        }
        Map<String, String> mappaParametriPieni = new HashMap<String, String>();
        for (Iterator<String> it = this.getMappaParametri().keySet().iterator(); it.hasNext();) {
            String key = it.next();
            String valore = getValore(this.getMappaParametri(),key);
            if (!valore.isEmpty() && !valore.equals("''")) {
                mappaParametriPieni.put(key, valore);
            }
        }

        String select = "SELECT " + this.getNomeColonnaID() + " FROM " + this.getNomeTabella() + " WHERE ";

        for (Iterator<String> it = mappaParametriPieni.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            String valore = getValore(mappaParametriPieni,key);
            select += key + " = " + valore;
            if (it.hasNext()) {
                select += " AND ";
            }
        }
        return select;
    }

    public String getValore(Map<String, String> mappaParametriPieni, String key) {
        String val = mappaParametriPieni.get(key);
        if (val.startsWith("${refId.") && val.endsWith("}")) {
            String refIdPlaceHolder = val.substring(8, val.length() - 1);
            String refId = refIdPlaceHolder.trim();
            SQLInsert insert = this.mappaRiferimenti.get(refId);
            if (insert == null) {
                return val;
            }
            return insert.getValoreId() + "";
        } else {
            return val;
        }
    }

    @Override
    public String toString() {
        String s = "Query: " + this.query + "\n";
        s += "NomeTabella: " + this.getNomeTabella() + "\n";
        s += "NomeColonnaID: " + this.getNomeColonnaID() + "\n";
        s += "IdPlaceHolder: " + this.getIdPlaceHolder() + "\n";
        s += "ID: " + this.getValoreId() + "\n";
        s += "MappaParametri: " + this.mappaParametri + "\n";
        s += "MappaRiferimenti: " + this.mappaRiferimenti + "\n";
        return s;
    }

    public int compareTo(Object o) {
        SQLInsert op = (SQLInsert) o;
        if (this.getSQLOperationDaReferenziare() == op.getSQLOperationDaReferenziare()) {
            return 0;
        }
        if (this.getSQLOperationDaReferenziare() < op.getSQLOperationDaReferenziare()) {
            return -1;
        } else {
            return 1;
        }
    }
}
