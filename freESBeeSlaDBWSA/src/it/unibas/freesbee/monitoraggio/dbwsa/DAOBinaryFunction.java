package it.unibas.freesbee.monitoraggio.dbwsa;

import it.unibas.freesbee.monitoraggio.calcolo.functions.binary.BinaryFunction;
import it.unibas.freesbee.monitoraggio.calcolo.windowutils.GestoreDate;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DBUtilita;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2NotDeterminatedAlgorithmException;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class DAOBinaryFunction {

    private static Log logger = LogFactory.getLog(DAOBinaryFunction.class);
    private static DBUtilita db = DBUtilita.getInstance();
    private static Monitoraggio m = Monitoraggio.getInstance();
    
    private DAOBinaryFunction() {}

    //-- Eseguito in seguito ad una chiamata del metodo getResultOperation(..) della classe BinaryFunction
    public static List<Double> caricaRisultato(BinaryFunction function, Window win, String basicMetric1, String basicMetric2, DateTime dataFine)
            throws DAOException {
        String query = creaQuery(function, win, basicMetric1, basicMetric2, dataFine);
        logger.debug(query);
        List<Double> listaRisultato = null;
        try {
            db.connetti();
            listaRisultato = db.eseguiQuery(query);
            if (win != null && win.getTimes() != null) {
                if ((long) listaRisultato.size() < win.getTimes().longValue()) {
                    throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
                }
            }
            return listaRisultato;
        } catch (DAOException daoe) {
            throw daoe;
        }

    }

    private static String creaQuery(BinaryFunction function, Window window, String basicMetric1, String basicMetric2, DateTime dataFine) {
        String strDataFine = dataFine.toString("yyyy-MM-dd HH:mm:ss");
        String query = "SELECT f.inf2_sla_basic_metric_value" + function.getSqlFunction() + "i.inf2_sla_basic_metric_value as divisione ";
        query += "FROM icar_inf2_sla_object_trace i,icar_inf2_sla_object_trace f ";
        query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
        query += "AND i.inf2_id_service = f.inf2_id_service AND i.inf2_id_initiator = f.inf2_id_initiator AND i.inf2_id_responder = f.inf2_id_responder ";
        query += "AND f.inf2_sla_basic_metric='" + basicMetric1 + "' AND i.inf2_sla_basic_metric='" + basicMetric2 + "' ";
        query += "AND i.inf2_sla_basic_metric_data_insert = f.inf2_sla_basic_metric_data_insert ";
        query += "AND i.inf2_sla_basic_metric_milliseconds_insert = f.inf2_sla_basic_metric_milliseconds_insert ";
        query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
        if (window != null) {
            if (window.getInterval() != null) {
                String strDataInizio = GestoreDate.getDataInizio(dataFine, window.getInterval().value()).toString("yyyy-MM-dd HH:mm:ss");
                query += "AND i.inf2_sla_basic_metric_data_insert > '" + strDataInizio + "' ";
                query += "order by f.inf2_sla_basic_metric_data_insert DESC,f.inf2_sla_basic_metric_milliseconds_insert DESC;";
            } else {
                query += "order by f.inf2_sla_basic_metric_data_insert DESC,f.inf2_sla_basic_metric_milliseconds_insert DESC ";
                query += "LIMIT " + window.getTimes().longValue() + ";";
            }
        } else {
            query += "order by f.inf2_sla_basic_metric_data_insert DESC,f.inf2_sla_basic_metric_milliseconds_insert DESC;";
        }
        return query;
    }

    //-- Eseguito in seguito ad una chiamata del metodo getResultSingleOperation(..) della classe BinaryFunction
    public static List<Double> caricaRisultato(String basicMetric, Window window, DateTime dataFine) throws DAOException {
        String query = creaQuery(basicMetric, window, dataFine);
        logger.debug(query);
        List<Double> listaRisultato = null;
        try {
            db.connetti();
            listaRisultato = db.eseguiQuery(query);
            if (window != null && window.getTimes() != null) {
                if ((long) listaRisultato.size() < window.getTimes().longValue()) {
                    throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
                }
            }
            return listaRisultato;
        } catch (DAOException daoe) {
            throw daoe;
        } finally {
            db.disconnetti();
        }
    }

    private static String creaQuery(String basicMetric, Window window, DateTime dataFine) {
        String strDataFine = dataFine.toString("yyyy-MM-dd HH:mm:ss");
        String query = "SELECT i.inf2_sla_basic_metric_value ";
        query += "FROM icar_inf2_sla_object_trace i ";
        query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
        query += "AND i.inf2_sla_basic_metric='" + basicMetric + "' ";
        query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
        if (window != null) {
            if (window.getInterval() != null) {
                String strDataInizio = GestoreDate.getDataInizio(dataFine, window.getInterval().value()).toString("yyyy-MM-dd HH:mm:ss");
                query += "AND i.inf2_sla_basic_metric_data_insert > '" + strDataInizio + "' ";
                query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC;";
            } else {
                query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC ";
                query += "LIMIT " + window.getTimes().longValue() + ";";
            }
        } else {
            query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC;";
        }
        return query;
    }

    //-- Eseguito in seguito ad una chiamata del metodo getResultOperationLongScalar(.., Long longScalar, String bmName2, Window window)
    //-- Eseguito in seguito ad una chiamata del metodo getResultOperationLongScalar(.., String bmName1, Long longScalar, Window window)
    public static List<Double> caricaRisultato(BinaryFunction function, Long longScalar, String basicMetric, Window window, boolean longScalarAlDenominatore, DateTime dataFine)
            throws DAOException {
        if (longScalarAlDenominatore && longScalar.longValue() == 0L) {
            throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
        }
        String query = creaQuery(function, longScalar, basicMetric, window, longScalarAlDenominatore, dataFine);
        logger.debug(query);
        List<Double> listaRisultato = null;
        try {
            db.connetti();
            listaRisultato = db.eseguiQuery(query);
            if (window != null && window.getTimes() != null) {
                if ((long) listaRisultato.size() < window.getTimes().longValue()) {
                    throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
                }
            }
            return listaRisultato;
        } catch (DAOException daoe) {
            throw new DAOException(daoe);
        } finally {
            db.disconnetti();
        }
    }

    private static String creaQuery(BinaryFunction function, Long longScalar, String basicMetric, Window window, boolean longScalarAlDenominatore, DateTime dataFine) {
        String strDataFine = dataFine.toString("yyyy-MM-dd HH:mm:ss");
        String query;
        if (longScalarAlDenominatore) {
            query = "SELECT i.inf2_sla_basic_metric_value" + function.getSqlFunction() + longScalar + " ";
        } else {
            query = "SELECT " + longScalar + function.getSqlFunction() + "i.inf2_sla_basic_metric_value ";
        }
        query += "FROM icar_inf2_sla_object_trace i ";
        query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
        query += "AND i.inf2_sla_basic_metric='" + basicMetric + "' ";
        query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
        if (window != null) {
            if (window.getInterval() != null) {
                String strDataInizio = GestoreDate.getDataInizio(dataFine, window.getInterval().value()).toString("yyyy-MM-dd HH:mm:ss");
                query += "AND i.inf2_sla_basic_metric_data_insert > '" + strDataInizio + "' ";
                query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC;";
            } else {
                query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC ";
                query += "LIMIT " + window.getTimes().longValue() + ";";
            }
        } else {
            query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC;";
        }
        return query;
    }

    //-- Eseguito in seguito ad una chiamata del metodo getResultOperationFloatScalar(.., Float floatScalar, String bmName2, Window window)
    //-- Eseguito in seguito ad una chiamata del metodo getResultOperationFloatScalar(.., String bmName1, Float floatScalar, Window window)
    public static List<Double> caricaRisultato(BinaryFunction function, Float floatScalar, String basicMetric, Window window, boolean floatScalarAlDenominatore, DateTime dataFine) 
            throws DAOException {
        if (floatScalarAlDenominatore && floatScalar.floatValue() == 0F) {
            throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_002);
        }
        String query = creaQuery(function, floatScalar, basicMetric, window, floatScalarAlDenominatore, dataFine);
        logger.debug(query);
        List<Double> listaRisultato = null;
        try {
            db.connetti();
            listaRisultato = db.eseguiQuery(query);
            if (window != null && window.getTimes() != null) {
                if ((long) listaRisultato.size() < window.getTimes().longValue()) {
                    throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
                }
            }
            return listaRisultato;
        } catch (DAOException daoe) {
            throw daoe;
        } finally {
            db.disconnetti();
        }
    }

    private static String creaQuery(BinaryFunction function, Float floatScalar, String bmName, Window window, boolean floatScalarAlDenominatore, DateTime dataFine) {
        String strDataFine = dataFine.toString("yyyy-MM-dd HH:mm:ss");
        String query = "";
        if (floatScalarAlDenominatore) {
            query = "SELECT i.inf2_sla_basic_metric_value" + function.getSqlFunction() + floatScalar + " ";
        } else {
            query = "SELECT " + floatScalar + function.getSqlFunction() + "i.inf2_sla_basic_metric_value ";
        }
        query += "FROM icar_inf2_sla_object_trace i ";
        query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
        query += "AND i.inf2_sla_basic_metric='" + bmName + "' ";
        query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
        if (window != null) {
            if (window.getInterval() != null) {
                String strDataInizio = GestoreDate.getDataInizio(dataFine, window.getInterval().value()).toString("yyyy-MM-dd HH:mm:ss");
                query += "AND i.inf2_sla_basic_metric_data_insert > '" + strDataInizio + "' ";
                query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC;";
            } else {
                query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC ";
                query += "LIMIT " + window.getTimes().longValue() + ";";
            }
        } else {
            query += "order by i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC;";
        }
        return query;
    }
}
