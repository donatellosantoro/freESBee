package it.unibas.silvio.persistenza.jdbc;

import it.unibas.silvio.persistenza.DAOException;
import it.unibas.silvio.persistenza.IDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataSourceJDBC implements IDataSource {

    private static Log logger = LogFactory.getLog(DataSourceJDBC.class);
    
    private String nomeDB;
    private String indirizzoDB;
    private String tipoDB;
    private String userName;
    private String password;
    private IDBConnector connettore;

    public DataSourceJDBC(String nomeDB, String indirizzoDB, String tipoDB, String userName, String password) {
        this.nomeDB = nomeDB;
        this.indirizzoDB = indirizzoDB;
        this.tipoDB = tipoDB;
        this.userName = userName;
        this.password = password;
        this.connettore = DBConnectorFactory.getDBConnector(tipoDB);
        try {
            Class.forName(connettore.getDriverClass());
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }

    }

    public Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connettore.getURLDatabase(indirizzoDB, nomeDB), userName, password);
        } catch (SQLException sqle) {
            close(connection);
            logger.error(sqle);
            throw new DAOException("getConnection: " + sqle);
        }
        return connection;
    }

    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            logger.error(sqle);
        }
    }

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException sqle) {
            logger.error(sqle);
        }
    }

    public void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException sqle) {
            logger.error(sqle);
        }
    }
}
