package it.unibas.freesbee.monitoraggio.dbwsa.dbutilita;

import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataSource {
    
    private static Log logger = LogFactory.getLog(DataSource.class);
    private static String databaseURI = "";
    private static String dbUserName = "";
    private static String dbPassword = "";
    
    private DataSource() {}
    
    public static boolean configure(String aDriver, String adatabaseURI, String adbUserName, String adbPassword) {
        try {
            Class.forName(aDriver);
            databaseURI = adatabaseURI;
            dbUserName = adbUserName;
            dbPassword = adbPassword;
            return true;
        }catch(ClassNotFoundException e) {
            logger.error(e);
            return false;
        }
    }
    
    public static Connection getConnection() throws DAOException  {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(databaseURI, dbUserName, dbPassword);
        }catch(SQLException sqle) {
            logger.error(sqle);
            throw new DAOException("getConnection(): " + sqle);
        }
        return connection;
    }
    
    public static void close(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        }catch(SQLException sqle) {
            logger.error(sqle);
        }
    }
    
    public static void close(Statement statement) {
        try {
            if(statement != null) {
                statement.close();
            }
        }catch(SQLException sqle) {
            logger.error(sqle);
        }
    }
    
    public static void close(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        }catch(SQLException sqle) {
            logger.error(sqle);
        }
    }    

}
