package it.unibas.silvio.sql;

import it.unibas.silvio.modello.DatiDB;
import it.unibas.silvio.modello.DatoPrimitivo;
import it.unibas.silvio.persistenza.DAOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Namespace;
import org.postgresql.PGResultSetMetaData;

public class ResultSetParser {

    private static Log logger = LogFactory.getLog(ResultSetParser.class);
    public static final Namespace XSD_NAMESPACE = Namespace.getNamespace("xs", "http://www.w3.org/2001/XMLSchema");

    public static List<DatiDB> estraiDatiDB(ResultSet resulSet) throws DAOException {
        if (resulSet == null) {
            throw new IllegalArgumentException("ResultSet nullo");
        }
        try {
            List<DatiDB> listaDati = new ArrayList<DatiDB>();
            ResultSetMetaData rsmd = resulSet.getMetaData();
            int colCount = rsmd.getColumnCount();
            while (resulSet.next()) {
                DatiDB datiDB = new DatiDB();
                for (int columnid = 1; columnid <= colCount; columnid++) {
                    String columnName = rsmd.getColumnName(columnid);
                    String tableName = "";
                    if (rsmd instanceof PGResultSetMetaData) {
                        PGResultSetMetaData pgrsmd = (PGResultSetMetaData) rsmd;
                        tableName = pgrsmd.getBaseTableName(columnid);
                    } else {
                        tableName = rsmd.getTableName(columnid);
                    }
                    String type = rsmd.getColumnTypeName(columnid);
                    DatoPrimitivo dato = new DatoPrimitivo(tableName + "." + columnName);
                    dato.setTipo(convertTypeDBtoXSD(type));
                    if (resulSet.getObject(columnid) != null) {
                        dato.setValore(resulSet.getObject(columnid).toString());
                    }
                    datiDB.getDatiDB().add(dato);
                }
                listaDati.add(datiDB);
            }
            return listaDati;
        } catch (SQLException ex) {
            logger.error("Impossibile generare l'xsd dal resulset. " + ex);
            throw new DAOException(ex);
        }
    }

    public static List<DatoPrimitivo> parse(java.sql.ResultSet resulSet) throws DAOException {
        return parse(resulSet, false);
    }

    public static List<DatoPrimitivo> parseFirst(java.sql.ResultSet resulSet) throws DAOException {
        return parse(resulSet, true);
    }

    public static List<DatoPrimitivo> parse(java.sql.ResultSet resulSet, boolean onlyFirst) throws DAOException {
        if (resulSet == null) {
            throw new IllegalArgumentException("ResultSet nullo");
        }
        try {
            List<DatoPrimitivo> listaDati = new ArrayList<DatoPrimitivo>();
            ResultSetMetaData rsmd = resulSet.getMetaData();
            boolean stop = false;
            int colCount = rsmd.getColumnCount();
            while (resulSet.next() && !stop) {
                for (int columnid = 1; columnid <= colCount; columnid++) {
                    String columnName = rsmd.getColumnName(columnid);
                    String tableName = "";
                    if (rsmd instanceof PGResultSetMetaData) {
                        PGResultSetMetaData pgrsmd = (PGResultSetMetaData) rsmd;
                        tableName = pgrsmd.getBaseTableName(columnid);
                    } else {
                        tableName = rsmd.getTableName(columnid);
                    }
                    String type = rsmd.getColumnTypeName(columnid);
                    DatoPrimitivo dato = new DatoPrimitivo(tableName + "." + columnName);
                    dato.setTipo(convertTypeDBtoXSD(type));
//                    dato.setValore();
                    listaDati.add(dato);
                }
                stop = onlyFirst;
            }
            return listaDati;
        } catch (SQLException ex) {
            logger.error("Impossibile generare l'xsd dal resulset. " + ex);
            throw new DAOException(ex);
        }
    }

    private static String convertTypeDBtoXSD(String columnType) {
        String type = columnType.toLowerCase();
        if (type.equalsIgnoreCase("varchar") || type.equalsIgnoreCase("char") ||
                type.equalsIgnoreCase("text") || type.equalsIgnoreCase("bpchar") ||
                type.equalsIgnoreCase("bit") || type.equalsIgnoreCase("mediumtext") ||
                type.equalsIgnoreCase("longtext")) {
            return "xs:string";
        }
        if (type.equalsIgnoreCase("serial") || type.equalsIgnoreCase("enum")) {
            return "xs:string";
        }
        if (type.equalsIgnoreCase("date") || type.equalsIgnoreCase("datetime") || type.equalsIgnoreCase("timestamp")) {
            return "xs:date";
        }
        if (type.startsWith("int") || type.startsWith("tinyint") || type.startsWith("bigint") || type.startsWith("smallint")) {
            return "xs:integer";
        }
        if (type.startsWith("float")) {
            return "xs:decimal";
        }
        if (type.equalsIgnoreCase("bool")) {
            return "xs:boolean";
        }
        logger.info("Non sono riuscito a convertire il tipo " + type);
        return "xs:string";
    }
}
