package it.unibas.freesbee.monitoraggio.dbwsa;

import it.unibas.freesbee.monitoraggio.calcolo.windowutils.GestoreDate;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.IntervalTimeType;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DBUtilita;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.exception.inf2exception.INF2NotDeterminatedAlgorithmException;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class DAOWindowUtils {

    private static Log logger = LogFactory.getLog(DAOWindowUtils.class);
    private static Monitoraggio m = Monitoraggio.getInstance();
    private static DBUtilita db = DBUtilita.getInstance();
    
    private DAOWindowUtils() {}

    
    public static List<DateTime> getWindowTimesDAO(long winRoot, long win, DateTime dataFinale, String basicMetricName)
            throws DAOException {
        List<DateTime> arrDate = new ArrayList<DateTime>();
        String strDtFinale = dataFinale.toString("yyyy-MM-dd HH:mm:ss");
        try {
            db.connetti();
            String query = "SELECT i.inf2_sla_basic_metric_data_insert ";
            query += "FROM icar_inf2_sla_object_trace i ";
            query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' ";
            query += "AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
            query += "AND i.inf2_sla_basic_metric='" + basicMetricName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert < '" + strDtFinale + "' ";
            query += "ORDER BY i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC ";
            query += "LIMIT " + winRoot + ";";
            logger.debug(query.toString());
            List<DateTime> listaRisultato = db.eseguiQueryDate(query.toString());
            int size = listaRisultato.size();
            if (size != winRoot) {
                throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
            }
            int numeroiterazioni = 0;
            for (int i = 0; i < size; i++) {
                numeroiterazioni++;
                if (i == 0) {
                    arrDate.add(listaRisultato.get(i));
                    numeroiterazioni = 0;
                } else if (numeroiterazioni == win) {
                    int rimn = size - i;
                    if (rimn >= win) {
                        numeroiterazioni = 0;
                        arrDate.add(listaRisultato.get(i));
                    }
                }
            }
            return arrDate;
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            db.disconnetti();
        }

    }
    
    
    public static List<DateTime> getWindowInterTimesDAO(IntervalTimeType winRootInter, long winTimes, DateTime dataFinale, String basicMetricName)
            throws DAOException {
        List<DateTime> arrDate = new ArrayList<DateTime>();
        String strDtFinale = dataFinale.toString("yyyy-MM-dd HH:mm:ss");
        try {
            db.connetti();
            String strDataInizio = GestoreDate.getDataInizio(dataFinale, winRootInter.value()).toString("yyyy-MM-dd HH:mm:ss");
            String query = "SELECT i.inf2_sla_basic_metric_data_insert ";
            query += "FROM icar_inf2_sla_object_trace i ";
            query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND i.inf2_id_initiator='" + m.getIdInitiator() + "' ";
            query += "AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
            query += "AND i.inf2_sla_basic_metric='" + basicMetricName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDtFinale + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert > '" + strDataInizio + "' ";
            query += "ORDER BY i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC;";
            logger.debug(query.toString());
            List<DateTime> listaRisultato = db.eseguiQueryDate(query.toString());
            int size = listaRisultato.size();
            if (size < winTimes) {
                throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
            }
            int numeroiterazioni = 0;
            for (int i = 0; i < size; i++) {
                numeroiterazioni++;
                if (i == 0) {
                    arrDate.add(listaRisultato.get(i));
                    numeroiterazioni = 0;
                } else if (numeroiterazioni == winTimes) {
                    int rimn = size - i;
                    if (rimn >= winTimes) {
                        numeroiterazioni = 0;
                        arrDate.add(listaRisultato.get(i));
                    }
                }
            }
            return arrDate;
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            db.disconnetti();
        }

    }

    public static List<DateTime> getWindowTimesInterDAO(long winRootTimes, IntervalTimeType winInter, DateTime dataFinale, String basicMetricName)
            throws DAOException {
        List<DateTime> arrDate = new ArrayList<DateTime>();
        String strDtFinale = dataFinale.toString("yyyy-MM-dd HH:mm:ss");
        try {
            db.connetti();
            String query = "SELECT i.inf2_sla_basic_metric_data_insert ";
            query += "FROM icar_inf2_sla_object_trace i ";
            query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' ";
            query += "AND i.inf2_id_responder='" + m.getIdResponder() + "' ";
            query += "AND i.inf2_sla_basic_metric='" + basicMetricName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDtFinale + "' ";
            query += "ORDER BY i.inf2_sla_basic_metric_data_insert DESC,i.inf2_sla_basic_metric_milliseconds_insert DESC ";
            query += "LIMIT " + winRootTimes + ";";
            logger.debug(query.toString());
            List<DateTime> listaRisultato = db.eseguiQueryDate(query.toString());
            if (listaRisultato.size() < winRootTimes) { //se il numero delle le rilevazioni trovate è inferiore a quello richiesto 
                throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
            }
            if (listaRisultato.size() != 0) {
                int size = listaRisultato.size();
                if (listaRisultato.get(size - 1).isAfter(GestoreDate.getDataInizio(listaRisultato.get(0), winInter.value()))) {
                    //se tutte le winRootTimes rilevazioni stanno in un intervallo inferiore a winInter
                    throw new DAOException(INF2NotDeterminatedAlgorithmException.MSG_ERROR_001);
                }
            }
            DateTime dataTemp = listaRisultato.get(0);
            arrDate.add(dataTemp);
            for (DateTime dataInLista : listaRisultato) {
                if (dataInLista.isAfter(dataTemp)) {
                    arrDate.add(dataInLista);
                    dataTemp = GestoreDate.getDataInizio(dataTemp, winInter.value());
                }
            }
            return arrDate;
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            db.disconnetti();
        }
    }
}
