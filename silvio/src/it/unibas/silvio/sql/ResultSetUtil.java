package it.unibas.silvio.sql;

import it.unibas.silvio.modello.Query;
import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDataSource;
import it.unibas.silvio.persistenza.jdbc.DataSourceJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResultSetUtil {

    private Log logger = LogFactory.getLog(this.getClass());
    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement stmt = null;
    private IDataSource dataSource = null;

    public ResultSet queryToRs(Query query, String sql) throws DAOException {
        String indirizzoDB = query.getIndirizzoDB();
        String nomeDB = query.getNomeDB();
        String tipoDB = query.getTipoDB();
        String userName = query.getNomeUtente();
        String password = query.getPassword();
        dataSource = new DataSourceJDBC(nomeDB, indirizzoDB, tipoDB, userName, password);
        try {
            connection = dataSource.getConnection();
            stmt = connection.createStatement();
            logger.info("Eseguo la query " + sql);
            resultSet = stmt.executeQuery(sql);
        } catch (Exception e) {
            chiudiConnessioni();
            logger.error("Impossibile eseguire la query " + sql + ". " + e);
            throw new DAOException(e);
        }
        return resultSet;
    }

    public void excuteInsert(Query query, String sql) throws DAOException {
        String indirizzoDB = query.getIndirizzoDB();
        String nomeDB = query.getNomeDB();
        String tipoDB = query.getTipoDB();
        String userName = query.getNomeUtente();
        String password = query.getPassword();
        dataSource = new DataSourceJDBC(nomeDB, indirizzoDB, tipoDB, userName, password);
        try {
            connection = dataSource.getConnection();
            stmt = connection.createStatement();
            logger.info("Eseguo la query " + sql);
            stmt.execute(sql);
        } catch (Exception e) {
            chiudiConnessioni();
            logger.error("Impossibile eseguire la query " + sql + ". " + e);
            throw new DAOException(e);
        }
    }

    public void chiudiConnessioni() {
        if (connection != null) {
            dataSource.close(connection);
        }
        if (stmt != null) {
            dataSource.close(stmt);
        }
        if (resultSet != null) {
            dataSource.close(resultSet);
        }
    }
}
