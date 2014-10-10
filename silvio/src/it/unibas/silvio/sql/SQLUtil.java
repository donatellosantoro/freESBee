package it.unibas.silvio.sql;

import it.unibas.silvio.modello.DatiDB;
import it.unibas.silvio.modello.DatiSQL;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.SilvioException;
import java.sql.ResultSet;
import java.util.List;

public class SQLUtil {

    private static org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(SQLUtil.class);

    public static List<DatiDB> eseguiQuery(DatiSQL datiSQL, String select) throws SilvioException {
        ResultSetUtil rsUtil = new ResultSetUtil();
        List<DatiDB> listaDati = null;
        try {
            ResultSet rs = rsUtil.queryToRs(datiSQL.getSelect(), select);
            listaDati = ResultSetParser.estraiDatiDB(rs);
        } catch (DAOException ex) {
            logger.error("Impossibile eseguire la query sul db per estrarne il resultSet. " + ex);
            throw new SilvioException("Impossibile eseguire la query sul db per estrarne il resultSet. " + ex);
        } finally {
            rsUtil.chiudiConnessioni();
        }
        return listaDati;
    }
}
