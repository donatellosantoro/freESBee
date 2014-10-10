package it.unibas.silvio.persistenza;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IDataSource {
    
    public Connection getConnection() throws DAOException;
    
    public void close(Connection connection);
    
    public void close(Statement statement);

    public void close(ResultSet resultSet);
    
}