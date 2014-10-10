package it.unibas.freesbee.monitoraggio.dbwsa;

import it.unibas.freesbee.monitoraggio.calcolo.functions.aggregate.AggregateFunction;
import it.unibas.freesbee.monitoraggio.calcolo.windowutils.GestoreDate;
import it.unibas.freesbee.monitoraggio.calcolo.wsaIcarGuaranteeTerms.type.Window;
import it.unibas.freesbee.monitoraggio.dbwsa.dbutilita.DBUtilita;
import it.unibas.freesbee.monitoraggio.exception.daoexception.DAOException;
import it.unibas.freesbee.monitoraggio.libreria.Monitoraggio;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

public class DAOAggregateFunction {
    
    private static Log logger = LogFactory.getLog(DAOAggregateFunction.class);
    private static Monitoraggio m = Monitoraggio.getInstance();
    private static DBUtilita db = DBUtilita.getInstance();
    
    private DAOAggregateFunction() {}
    
    
    public static List<Double> caricaRisultato(AggregateFunction function, String bmName, Window window, DateTime dataFine) throws DAOException {
        String query = DAOAggregateFunction.creaQuery(function, bmName, window, dataFine);
        logger.debug(query);
        try {
            db.connetti();
            if(window.getInterval() != null) {
                return db.eseguiQuery(query);
            }
            return db.eseguiQuery(query, window.getTimes().longValue()); 
        }catch(DAOException daoe) {
            throw daoe;
        }    
    }
    
    private static String creaQuery(AggregateFunction function, String bmName, Window window, DateTime dataFine) {
        String strDataFine = dataFine.toString("yyyy-MM-dd HH:mm:ss");
        String query;
        if(window.getInterval() != null) {
            String strDataInizio = GestoreDate.getDataInizio(dataFine, window.getInterval().value()).toString("yyyy-MM-dd HH:mm:ss");
            query  = "SELECT " + function.getSqlFunction() + "(inf2_sla_basic_metric_value) ";
            query += "FROM (";
            query += "SELECT * ";
            query += "FROM icar_inf2_sla_object_trace i ";
            query += "WHERE inf2_id_service='" + m.getIdService() + "' AND inf2_id_initiator='"+ m.getIdInitiator() +"' AND inf2_id_responder='"+ m.getIdResponder() +"' AND inf2_sla_basic_metric = '" + bmName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert > '" + strDataInizio + "' ";
            query += "order by i.inf2_sla_basic_metric_data_insert DESC, i.inf2_sla_basic_metric_milliseconds_insert DESC ";
            query += ") AS VIEW;";
            logger.debug("DAO AGGREGATE WINDOW INTERVAL");
        }else {
            query  = "SELECT " + function.getSqlFunction() + "(subQuery.val), count(*) ";
            query += "FROM (";
            query += "SELECT i.inf2_sla_basic_metric_value as val ";
            query += "FROM icar_inf2_sla_object_trace i ";
            query += "WHERE inf2_id_service='" + m.getIdService() + "' AND inf2_id_initiator='"+ m.getIdInitiator() +"' AND inf2_id_responder='"+ m.getIdResponder() +"' AND inf2_sla_basic_metric = '" + bmName + "' ";
            query += "AND i.inf2_sla_basic_metric_data_insert <= '" + strDataFine + "' ";
            query += "order by i.inf2_sla_basic_metric_data_insert DESC, i.inf2_sla_basic_metric_milliseconds_insert DESC ";
            query += "LIMIT " + window.getTimes();
            query += " ) subQuery;";
            logger.debug("DAO AGGREGATE WINDOW TIME");
        }
        return query;
    }
    

}
