package it.unibas.silvio.transql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SQLParser {

    private static final Log logger = LogFactory.getLog(SQLParser.class);
    public static final String END_STATEMENT = "\\$\\{endStatement\\}";

    public static List<SQLInsert> parse(String listaSQL) throws SQLParserException {
        List<SQLInsert> lista = new ArrayList<SQLInsert>();
        String[] insert = listaSQL.split(END_STATEMENT);
        for (int i = 0; i < insert.length; i++) {
            SQLInsert operation = new SQLInsert(insert[i].trim());
            parse(operation);
            lista.add(operation);
        }
        collegaSQLOperation(lista);
        return lista;
    }

    private static void parse(SQLInsert operation) throws SQLParserException {
        String query = operation.getQuery();

        if(query.toUpperCase().startsWith("UPDATE ") && query.toUpperCase().contains(" SET ")){
            logger.debug("La query è un'UPDATE, quindi non la elaboro, ma la eseguo direttamente");
            return;
        }

        if (!query.toUpperCase().startsWith("INSERT INTO ") || !query.toUpperCase().contains("VALUES") || !query.toUpperCase().contains("(")) {
            throw new SQLParserException("La query '" + query + "' non è una insert valida.");
        }
        logger.debug("Faccio il parsing della query '" + query + "'");
        operation.setNomeTabella(estraiNomeTabella(query));
        operation.setMappaParametri(estraiParametri(query));
        elaboraPlaceHolder(operation);
    }

    private static String estraiNomeTabella(String query) throws SQLParserException {
        int posInizialeValues = query.toUpperCase().indexOf("VALUES");
        int posPrimaParentesi = query.indexOf("(");
        return query.substring("INSERT INTO ".length(), Math.min(posInizialeValues, posPrimaParentesi)).trim();
    }

    private static Map<String, String> estraiParametri(String query) throws SQLParserException {
        Map<String, String> mappaParametri = new HashMap<String, String>();
        int posInizialeValues = query.toUpperCase().indexOf("VALUES");
        int posPrimaParentesi = query.indexOf("(");
        if (posInizialeValues < posPrimaParentesi) {//E' UN INSERT SENZA PARAMETRI            
            return mappaParametri;
        }
        String[] parametri = estraiNomeParametri(query);
        String[] valori = estraiValoriParametri(query);
        if (valori.length != parametri.length) {
            throw new SQLParserException("La query " + query + " non è una insert valida");
        }
        for (int i = 0; i < parametri.length; i++) {
            mappaParametri.put(parametri[i].trim(), valori[i].trim());
        }
        return mappaParametri;
    }

    private static String[] estraiNomeParametri(String query) throws SQLParserException {
        int posInizialeValues = query.toUpperCase().indexOf("VALUES");
        int posPrimaParentesi = query.indexOf("(");
        int posChiusuraParentesi = query.indexOf(")");
        if (posPrimaParentesi > posChiusuraParentesi || posChiusuraParentesi > posInizialeValues) {
            throw new SQLParserException("La query " + query + " non è una insert valida");
        }
        String bloccoParametri = query.substring(posPrimaParentesi + 1, posChiusuraParentesi).trim();
        String[] parametri = bloccoParametri.split(",");
        for (int i = 0; i < parametri.length; i++) {
            parametri[i].trim();
        }
        return parametri;
    }

    private static String[] estraiValoriParametri(String query) throws SQLParserException {
        int posInizialeValues = query.toUpperCase().indexOf("VALUES");
        String stringaValori = query.substring(posInizialeValues);
        int posPrimaParentesi = stringaValori.indexOf("(");
        int posChiusuraParentesi = stringaValori.lastIndexOf(")");
        if (posPrimaParentesi > posChiusuraParentesi) {
            throw new SQLParserException("La query " + query + " non è una insert valida");
        }
        String bloccoValori = stringaValori.substring(posPrimaParentesi + 1, posChiusuraParentesi).trim();
        String[] valori = separaValori(bloccoValori);
        return valori;
    }

    private static String[] separaValori(String bloccoValori) {
        List<String> listaValori = new ArrayList<String>();
        int posCorrente = 0;
        int posInizioStringa = 0;
        int posizioneVirgola;
        do {
            posizioneVirgola = bloccoValori.indexOf(",", posCorrente);
            if (posizioneVirgola == -1) {
                listaValori.add(bloccoValori.substring(posInizioStringa));
            } else if (fuoriStringa(bloccoValori.substring(0, posizioneVirgola))) {
                listaValori.add(bloccoValori.substring(posInizioStringa, posizioneVirgola));
                posInizioStringa = posizioneVirgola + 1;
            }
            posCorrente = posizioneVirgola + 1;
        } while (posizioneVirgola != -1);
        return listaValori.toArray(new String[0]);
    }

    public static boolean fuoriStringa(String s) {
        int occorrenze = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\'') {
                occorrenze++;
            }
        }
        return (occorrenze % 2) == 0;
    }

    private static void elaboraPlaceHolder(SQLInsert operation) throws SQLParserException {
        Map<String, String> mappaParametri = operation.getMappaParametri();
        Set<String> nomeParametri = mappaParametri.keySet();
        for (Iterator<String> it = nomeParametri.iterator(); it.hasNext();) {
            String chiave = it.next();
            String valore = mappaParametri.get(chiave);
            if (cercaPlaceHolderId(chiave, valore, operation)) {
                it.remove();
            } else {
                cercaPlaceHolderRefId(valore, operation);
            }
        }
    }

    private static boolean cercaPlaceHolderId(String chiave, String valore, SQLInsert operation) throws SQLParserException {
        if (valore.startsWith("${id.") && valore.endsWith("}")) {
            String idPlaceHolder = valore.substring("${id.".length(), valore.length() - 1);
            if (operation.getIdPlaceHolder() != null) {
                throw new SQLParserException("Impossibile gestire una insert con due id");
            }
            operation.setIdPlaceHolder(idPlaceHolder.trim());
            operation.setNomeColonnaID(chiave.trim());
            return true;
        }
        return false;
    }

    private static void cercaPlaceHolderRefId(String valore, SQLInsert operation) {
        if (valore.startsWith("${refId.") && valore.endsWith("}")) {
            String refIdPlaceHolder = valore.substring("${refId.".length(), valore.length() - 1);
            operation.getMappaRiferimenti().put(refIdPlaceHolder.trim(), null);
        }
    }

    private static void collegaSQLOperation(List<SQLInsert> lista) throws SQLParserException {
        for (SQLInsert op : lista) {
            Set<String> riferimenti = op.getMappaRiferimenti().keySet();
            for (String nomeRiferimento : riferimenti) {
                SQLInsert opRef = cercaOperationByIdPH(lista, nomeRiferimento);
                if (opRef == null) {
                    throw new SQLParserException("Impossibile risolvere il riferimento " + nomeRiferimento);
                }
                if (opRef.getMappaRiferimenti().values().contains(op)) {
                    throw new SQLParserException("Riferimenti ciclici tra le tabelle " + op.getNomeTabella() + " e " + opRef.getNomeTabella());
                }
                op.getMappaRiferimenti().put(nomeRiferimento, opRef);
            }
        }
    }

    private static SQLInsert cercaOperationByIdPH(List<SQLInsert> lista, String nomeRiferimento) {
        for (SQLInsert op : lista) {
            if (op.getIdPlaceHolder()!=null && op.getIdPlaceHolder().equals(nomeRiferimento)) {
                return op;
            }
        }
        return null;
    }
}
