package it.unibas.silvio.transql;

import it.unibas.silvio.modello.DatiDB;
import it.unibas.silvio.modello.Query;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.SilvioException;
import it.unibas.silvio.sql.ResultSetUtil;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SQLExecutor {

    private Log logger = LogFactory.getLog(this.getClass());
    private Query datiQuery;
    private ResultSetUtil rsUtil = new ResultSetUtil();
    private String messaggio = "";

    public SQLExecutor(Query datiQuery) {
        this.datiQuery = datiQuery;
    }

    public String esegui(List<SQLInsert> listaInsert) throws SilvioException {
        Collections.sort(listaInsert);
        while (!listaInsert.isEmpty() && listaInsert.get(0).hasRiferimentiCompleti()) {
            SQLInsert operationDaEseguire = listaInsert.get(0);
            eseguiInsert(operationDaEseguire);
            listaInsert.remove(operationDaEseguire);
            Collections.sort(listaInsert);
        }
        if (!listaInsert.isEmpty()) {
            throw new SilvioException("Riferimenti ciclici tra le tabelle " + stampaListaTabelle(listaInsert));
        }
        return messaggio;
    }

    public void eseguiInsert(SQLInsert operationDaEseguire) throws SilvioException {
        logger.debug("Devo completare l'insert: " + operationDaEseguire);
        generaId(operationDaEseguire);
        logger.debug("...riferimenti riempiti: " + completaInsert(operationDaEseguire));

        String insert = operationDaEseguire.getQuery();
        try {
            eseguiQueryInsert(insert);
            operationDaEseguire.setEseguito(true);
            messaggio += "Ho eseguito con successo l'insert " + insert + "\n";
        } catch (SilvioException ex) {
            logger.info("Impossibile eseguire l'insert " + insert + ". " + ex);
            messaggio += "Impossibile eseguire l'insert " + insert + ". " + ex + "\n";
        }
        if (!operationDaEseguire.isEseguito()) {
            logger.debug("Non è stato possibile effettuare l'insert. Forse i dati già esistono. Proviamo ad estrarli");
            cercaDatiEsistenti(operationDaEseguire);
        }
        if (!operationDaEseguire.isEseguito()) {
            throw new SilvioException("Impossibile eseguire l'insert " + operationDaEseguire.getQuery());
        }
    }

    private void generaId(SQLInsert operationDaEseguire) throws SilvioException {
        String nomeTabella = operationDaEseguire.getNomeTabella();
        String colonnaId = operationDaEseguire.getNomeColonnaID();
        if (colonnaId == null) {
            logger.info("Non c'è nessun id da generare");
            return;
        }
        String select = "SELECT MAX(" + colonnaId + ") FROM " + nomeTabella;
        logger.info("Eseguo la select per generare l'id: " + select);
        try {
            ResultSet rs = rsUtil.queryToRs(datiQuery, select);
            if (rs.next()) {
                long id = rs.getLong("max");
                id++;
                logger.info("Generato l'id per la tabella " + nomeTabella + ": " + id);
                operationDaEseguire.setValoreId(id);
            } else {
                throw new SQLParserException("Impossibile generare l'id. La query " + select + " non ha prodotto alcun risultato");
            }
        } catch (Exception ex) {
            logger.error("Impossibile eseguire la query per generare l'id " + ex);
            throw new SilvioException("Impossibile eseguire la query per generare l'id " + ex);
        } finally {
            rsUtil.chiudiConnessioni();
        }
    }

    public String completaInsert(SQLInsert operationDaEseguire) {
        String query = operationDaEseguire.getQuery();
        if (operationDaEseguire.getIdPlaceHolder() != null) {
            String idPlaceHolder = "\\$\\{id." + operationDaEseguire.getIdPlaceHolder() + "\\}";
            query = query.replaceAll(idPlaceHolder, operationDaEseguire.getValoreId() + "");
        }
        Map<String, SQLInsert> mappaRiferimenti = operationDaEseguire.getMappaRiferimenti();
        Set<String> listaRefId = mappaRiferimenti.keySet();
        for (String key : listaRefId) {
            long idRiferimento = mappaRiferimenti.get(key).getValoreId();
            String refIdPlaceHolder = "\\$\\{refId." + key + "\\}";
            query = query.replaceAll(refIdPlaceHolder, idRiferimento + "");
        }
        operationDaEseguire.setQuery(query);
        return query;
    }

    private List<DatiDB> eseguiQueryInsert(String select) throws SilvioException {
        List<DatiDB> listaDati = null;
        try {
            rsUtil.excuteInsert(datiQuery, select);
        } catch (DAOException ex) {
            throw new SilvioException(ex);
        } finally {
            rsUtil.chiudiConnessioni();
        }
        return listaDati;
    }

    private void cercaDatiEsistenti(SQLInsert operationDaEseguire) {
        String select = operationDaEseguire.generaSelectId();
        if (!select.isEmpty()) {
            try {
                ResultSet rs = rsUtil.queryToRs(datiQuery, select);
                if (rs.next()) {
                    long id = rs.getLong(operationDaEseguire.getNomeColonnaID());
                    operationDaEseguire.setValoreId(id);
                    operationDaEseguire.setEseguito(true);
                    messaggio += "Abbiamo trovato dei dati già esistenti con id " + id + "\n";
                } else {
                    messaggio += "Impossibile leggere l'id. La select " + select + " non ha prodotto alcun risultato\n";
                }
            } catch (Exception ex) {
                logger.info("Impossibile eseguire la select " + select + ". " + ex);
                messaggio += "Impossibile eseguire la select " + select + ". " + ex;
            }
        }
    }

    private String stampaListaTabelle(List<SQLInsert> listaOperation) {
        String s = "";
        for (SQLInsert op : listaOperation) {
            s += op.getNomeTabella() + ", ";
        }
        return s;
    }
}
