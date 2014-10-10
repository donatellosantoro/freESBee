package it.unibas.freesbee.monitoraggio.dbwsa;

import it.unibas.freesbee.monitoraggio.calcolo.windowutils.GestoreDate;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Threshold;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.utils.ThresholdUtils;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DBUtilita;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class DAOHitsFunction {

    private static DBUtilita db = DBUtilita.getInstance();
    private static Log logger = LogFactory.getLog(DAOHitsFunction.class);
    private static Monitoraggio m = Monitoraggio.getInstance();
    
    private DAOHitsFunction() {}

    public static Double caricaRisultato(String bmName, Threshold threshold, Window window, DateTime dataFinale) 
            throws DAOException {
        String query = creaQuery(bmName, threshold, window, dataFinale);
        logger.debug(query);
        List<Double> listaRisultato = null;
        try {
            db.connetti();
            listaRisultato = db.eseguiQuery(query);// che ritorna una lista    
            if (listaRisultato.size() == 0) {
                return Double.valueOf(0.0D);
            }
            return (Double) listaRisultato.get(0);
        } catch (DAOException daoe) {
            throw daoe;
        } finally {
            db.disconnetti();
        }
    }

    private static String creaQuery(String bmName, Threshold threshold, Window window, DateTime dataFinale) {
        String strDataFine = dataFinale.toString("yyyy-MM-dd HH:mm:ss");
        String query = "";
        if (window.getInterval() != null) {
            String strDataInizio = GestoreDate.getDataInizio(dataFinale, window.getInterval().value()).toString("yyyy-MM-dd HH:mm:ss");
            query += "SELECT COUNT(*) ";
            query += "FROM (";
            query += "SELECT * ";
            query += "FROM icar_inf2_sla_object_trace i ";
            query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
            query += "AND i.inf2_sla_basic_metric='" + bmName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert > '" + strDataInizio + "' ";
            if (threshold != null) {
                query += "AND i.inf2_sla_basic_metric_value " + ThresholdUtils.getSQLOperator(threshold) + threshold.getValue() + " ";
            }
            query += "order by inf2_sla_basic_metric_data_insert DESC,inf2_sla_basic_metric_milliseconds_insert DESC ";
            query += ") as view";
        } else {
            query += "SELECT COUNT(*) ";
            query += "FROM ( SELECT * FROM icar_inf2_sla_object_trace i ";
            query += "WHERE i.inf2_id_service='" + m.getIdService() + "' AND  i.inf2_id_initiator='" + m.getIdInitiator() + "' AND  i.inf2_id_responder='" + m.getIdResponder() + "' ";
            query += "AND i.inf2_sla_basic_metric='" + bmName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
            if (threshold != null) {
                query += "AND i.inf2_sla_basic_metric_value " + ThresholdUtils.getSQLOperator(threshold) + threshold.getValue() + " ";
            }
            query += "order by inf2_sla_basic_metric_data_insert DESC,inf2_sla_basic_metric_milliseconds_insert DESC ";
            query += "LIMIT " + window.getTimes() + ") subQuery;";
        }
        return query;
    }
}
