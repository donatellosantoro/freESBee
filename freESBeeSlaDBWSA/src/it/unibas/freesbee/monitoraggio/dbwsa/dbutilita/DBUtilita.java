package it.unibas.freesbee.monitoraggio.dbwsa.dbutilita;

import it.unibas.freesbee.monitoraggio.calcolo.core.StatoServizio;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2NotDeterminatedAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class DBUtilita {

    private static Log logger = LogFactory.getLog(DBUtilita.class);
    private static DBUtilita singleton = new DBUtilita();
    private static Connection dbConnection = null;
    private boolean connesso = false;

    private DBUtilita() {
    }

    public static DBUtilita getInstance() {
        return singleton;
    }

    public boolean connetti() {
        connesso = false;
        try {
            dbConnection = DataSource.getConnection();
            connesso = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return connesso;
    }

    public void disconnetti() {
        try {
            DataSource.close(dbConnection);
            connesso = false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String eseguiQueryTesto(String query) throws DAOException {
        String risultato = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = dbConnection.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                risultato = rs.getString(1);
            }
        } catch (SQLException sqle) {
            throw new DAOException(sqle);
        } finally {
            if (rs != null) {
                DataSource.close(rs);
            }
            if (stm != null) {
                DataSource.close(stm);
            }
        }
        return risultato;
    }

    public List<Double> eseguiQuery(String query) throws DAOException {
        Statement stm = null;
        ResultSet rs = null;
        List listaValori;
        double valore;
        try {
            stm = dbConnection.createStatement();
            rs = stm.executeQuery(query);
            listaValori = new ArrayList<Double>();
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
                } else {
                    valore = rs.getDouble(1);
                    listaValori.add(valore);
                }
            }
        } catch (SQLException sqle) {
            throw new DAOException(sqle);
        } finally {
            if (rs != null) {
                DataSource.close(rs);
            }
            if (stm != null) {
                DataSource.close(stm);
            }
        }
        return listaValori;
    }

    public List<Double> eseguiQuery(String query, long times) throws DAOException {
        List<Double> listaValori;
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = dbConnection.createStatement();
            rs = stm.executeQuery(query);
            listaValori = new ArrayList<Double>();
            while (rs.next()) {
                if (rs.getInt(2) < times) {
                    throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
                }
                if (rs.getString(1) == null) {
                    throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
                } else {
                    double valore = rs.getDouble(1);
                    listaValori.add(valore);
                }
            }
        } catch (SQLException sqle) {
            throw new DAOException(sqle);
        } finally {
            if (rs != null) {
                DataSource.close(rs);
            }
            if (stm != null) {
                DataSource.close(stm);

            }
        }

        return listaValori;
    }

    public List<DateTime> eseguiQueryDate(String query) throws DAOException {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DateTime> listaRisultato = new ArrayList<DateTime>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = dbConnection.createStatement();
            rs = stmt.executeQuery(query);
            java.util.Date dataJava;
            while (rs.next()) {
                dataJava = formatter.parse(rs.getString(1));
                listaRisultato.add(new DateTime(dataJava));
            }
        } catch (Exception daoe) {
            throw new DAOException(daoe);
        } finally {
            if (rs != null) {
                DataSource.close(rs);
            }
            if (stmt != null) {
                DataSource.close(stmt);
            }
        }
        return listaRisultato;
    }

    public StatoServizio verificaStato(String query) throws DAOException {
        StatoServizio res = new StatoServizio();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = dbConnection.createStatement();
            for (rs = stmt.executeQuery(query); rs.next();) {
                if (rs.getString(1) != null) {
                    res.setStato(rs.getString(1));
                }

                res.setCount(rs.getInt(2));

            }
        } catch (SQLException daoe) {
            daoe.printStackTrace();
            throw new DAOException(daoe);
        } finally {
            if (rs != null) {
                DataSource.close(rs);
            }
            if (stmt != null) {
                DataSource.close(stmt);
            }
        }
        return res;
    }
}

